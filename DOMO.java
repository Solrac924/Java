/***
 * Developer:       Carlos A Loya
 * Create date:     2/17/2016
 * Last modified:   3/29/2016
 * Purpose:         To validate the products by Beacon Tech ordered through Dish Order Management Orchestration (DOMO)
 * AMDG
 ***/

package x64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DOMO
{
    static String acctNumber = "";
    static String dbOrdID = "";
    static String dbCustOrdID = "";
    static ArrayList<String> productList;
    static String products = "";

    public static void main(String[] args) throws SQLException,IOException
    {
        acctNumber = "8046909945627561";
        String ordNumber = acctToWO(acctNumber);
        String custOrdID = orderToCustOrdID(ordNumber);
        System.out.println("\nfrom main: " + getProducts(custOrdID) );
        System.out.println("---------------------------");
        custOrdID = acctNumToCustOrdID(acctNumber);
        System.out.println("\nacctNumToCustOrdID(acctNumber): " + acctNumToCustOrdID(acctNumber) );
        System.out.println("\nfrom main: " + getProducts(custOrdID) );

    }
    public static String acctToWO(String inAcct) throws SQLException
    {
        // purpose: from acct# get latest CSG WO#
        System.out.println("inAcct = " + inAcct);

        // setup TB-DIA connection
        String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get latest CSG WO#
        String sqlDIA = "select * from (select ACCOUNT_ID\n" +
                        "                       ,ORDER_ID\n" +
                        "                       ,ORDER_MSG_RCVD_DATE\n" +
                        "               from ENI_ORDER\n" +
                        "               where ACCOUNT_ID = '" + inAcct + "'\n" +
                        "               order by to_char(ORDER_MSG_RCVD_DATE, 'YYYY-MON-DD HH24:MI:SS') desc\n" +
                        "               )\n" +
                        "where rownum <= 1";
        ResultSet rs = stmtDIA.executeQuery(sqlDIA);

        // store database values
        if (rs.next())
        {
            dbOrdID = rs.getString("ORDER_ID");
            System.out.println("returning ordID = " + dbOrdID);
        }

        return dbOrdID;
    } // acctToWO

    public static String orderToCustOrdID(String inOrdID) throws SQLException
    {
        // purpose: from WO# get DOMO CUSTOMER_ORDER_ID (ex: OM11111111115700)
        System.out.println("inOrdID = " + inOrdID);

        // setup TOM connection
        String urlTOM = "jdbc:oracle:thin:@TOMDB:1528:TOM";
        Connection conTOM = DriverManager.getConnection(urlTOM, "TQA_USER", "tqa4d1sh");
        Statement stmtTOM = conTOM.createStatement();

        // TOM query - get latest CSG WO#
        String sqlTOM = "select BILLING_ORDER_ID\n" +
                       "       ,CUSTOMER_ORDER_ID\n" +
                       "       ,COMPLETED_AT\n" +
                       "       ,CREATED_AT\n" +
                       "from DOMO.BIL_SO\n" +
                       "where BILLING_ORDER_ID = '" + inOrdID + "'\n" +
                       "and rownum <= 1\n" +
                       "order by COMPLETED_AT desc";
        ResultSet rs = stmtTOM.executeQuery(sqlTOM);

        // store database values
        if (rs.next())
        {
            dbCustOrdID = rs.getString("CUSTOMER_ORDER_ID");
            System.out.println("returning custOrdID = " + dbCustOrdID);
        }

        return dbCustOrdID;
    }

    public static String acctNumToCustOrdID(String inAcct) throws SQLException
    {
        // purpose: from account# get DOMO CUSTOMER_ORDER_ID (ex: OM11111111115700)

        // setup TOM connection
        String urlTOM = "jdbc:oracle:thin:@TOMDB:1528:TOM";
        Connection conTOM = DriverManager.getConnection(urlTOM, "TQA_USER", "tqa4d1sh");
        Statement stmtTOM = conTOM.createStatement();

        // TOM query - get latest CSG WO#
        String sqlTOM =
                "select BILLING_ACCOUNT_NUMBER\n" +
                "      ,ID\n" +
                "      ,CREATED_AT\n" +
                "from (\n" +
                "      select * \n" +
                "      from DOMO.CUSTOMER_ORDER\n" +
                "      where BILLING_ACCOUNT_NUMBER = '" + inAcct + "'\n" +
                "      order by CREATED_AT desc\n" +
                "      )\n" +
                "where rownum = 1";
        ResultSet rs = stmtTOM.executeQuery(sqlTOM);

        // store database values
        if (rs.next())
        {
            dbCustOrdID = rs.getString("ID");
            //System.out.println("returning custOrdID = " + dbCustOrdID);
        }

        return dbCustOrdID;
    }

    public static String getProducts(String inCustOrdID) throws IOException
    {
        // from CUSTOMER_ORDER_ID perform REST GET & validate productIds from orderLines

        // send REST request
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://tvip-m1-mw-domo.dish.com:27001/orders/" + inCustOrdID);
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            fullResponse += line;
        }

        // show response values by key
        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";
        String respProductId = "";
        String respProductName = "";
        Boolean orderLines = false;
        productList = new ArrayList<String>();
        products = "";
        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("orderLines") )
            {
                orderLines = true;
            }
            if (currToken.contains("productId") && orderLines)
            {
                scrap = st.nextToken();
                respProductId = st.nextToken();
                System.out.printf(respProductId + "\t");
                productList.add(new String(respProductId));
                products += respProductId + ",";
            }
            if (currToken.contains("productName") && orderLines)
            {
                scrap = st.nextToken();
                respProductName = st.nextToken();
                System.out.println(" " + respProductName);
                productList.add(new String(respProductName));
                products += respProductName + ",";
            }

        }
        return products;
    } // showProductIds

    public static String intAcctNumToCustOrdID(String inAcct) throws SQLException
    {
        // purpose: from account# get DOMO CUSTOMER_ORDER_ID (ex: OM11111111115700)

        // setup DOM connection
        String urlDOM = "jdbc:oracle:thin:@DOMDB:1528:DOM";
        Connection conDOM = DriverManager.getConnection(urlDOM, "devrw", "devpass09");
        Statement stmtDOM = conDOM.createStatement();

        // DOM query - get latest CSG WO#
        String sqlDOM =
                "select BILLING_ACCOUNT_NUMBER\n" +
                        "      ,ID\n" +
                        "      ,CREATED_AT\n" +
                        "from (\n" +
                        "      select * \n" +
                        "      from DOMO.CUSTOMER_ORDER\n" +
                        "      where BILLING_ACCOUNT_NUMBER = '" + inAcct + "'\n" +
                        "      order by CREATED_AT desc\n" +
                        "      )\n" +
                        "where rownum = 1";
        ResultSet rs = stmtDOM.executeQuery(sqlDOM);

        // store database values
        if (rs.next())
        {
            dbCustOrdID = rs.getString("ID");
            //System.out.println("returning custOrdID = " + dbCustOrdID);
        }

        return dbCustOrdID;
    }

    public static String intGetProducts(String inCustOrdID) throws IOException
    {
        // from CUSTOMER_ORDER_ID perform REST GET & validate productIds from orderLines

        // send REST request
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://dm1omd1.echostar.com:8080/orders/" + inCustOrdID);
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            fullResponse += line;
        }

        // show response values by key
        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";
        String respProductId = "";
        String respProductName = "";
        Boolean orderLines = false;
        productList = new ArrayList<String>();
        products = "";
        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("orderLines") )
            {
                orderLines = true;
            }
            if (currToken.contains("productId") && orderLines)
            {
                scrap = st.nextToken();
                respProductId = st.nextToken();
                System.out.printf(respProductId + "\t");
                productList.add(new String(respProductId));
                products += respProductId + ",";
            }
            if (currToken.contains("productName") && orderLines)
            {
                scrap = st.nextToken();
                respProductName = st.nextToken();
                System.out.println(" " + respProductName);
                productList.add(new String(respProductName));
                products += respProductName + ",";
            }

        }
        return products;
    } // showProductIds

} // class DOMO