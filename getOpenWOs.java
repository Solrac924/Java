/***
 * Developer:       Carlos A Loya
 * Date created:    2/02/2016
 * Date modified:   3/30/2016
 * Purpose:         Retrieve work order numbers that are open
 * AMDG
 *
 ***/

package x64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getOpenWOs
{
    static int inNumber;
    static String orderID = "";

    public static void main(String[] args)
    {
        System.out.println("-------- Open Work Orders ------");

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DIA connection
            String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
            Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
            Statement stmtDIA = conDIA.createStatement();

            // DIA query - get open New Connect
            String sqlNC =
                    "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                    "from (\n" +
                    "     select *\n" +
                    "     from ENI_ORDER\n" +
                    "     where ORDER_STATUS = 'O'\n" +
                    "     and ORDER_TYPE in ('NC')\n" +
                    "     and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                    "     and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                    "     and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                    "     and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate\n" +
                    "     order by ORDER_MSG_RCVD_DATE asc\n" +
                    "     )";
            ResultSet rs = stmtDIA.executeQuery(sqlNC);
            if (rs.next())
            {
                String acctNum = rs.getString("ACCOUNT_ID");
                System.out.println("open NC acct = " + acctNum);

                String orderID = rs.getString("ORDER_ID");
                System.out.println("orderID = " + orderID);

                String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
                System.out.println("orderDate = " + orderDate);

                System.out.println("--------------------------------------");

            }

            // DIA query - get open Trouble Call
            String sqlTC =
                    "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                    "from (\n" +
                    "     select *\n" +
                    "     from ENI_ORDER\n" +
                    "     where ORDER_STATUS = 'O'\n" +
                    "     and ORDER_TYPE in ('TC')\n" +
                    "     and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                    "     and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                    "     and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                    "     and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate\n" +
                    "     order by ORDER_MSG_RCVD_DATE asc\n" +
                    "     )";
            rs = stmtDIA.executeQuery(sqlTC);
            if (rs.next() )
            {
                String acctNum = rs.getString("ACCOUNT_ID");
                System.out.println("open TC acct = " + acctNum);

                String orderID = rs.getString("ORDER_ID");
                System.out.println("orderID = " + orderID);

                String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
                System.out.println("orderDate = " + orderDate);

                System.out.println("--------------------------------------");
            }

            // DIA query - get open Service Call
            String sqlSC = "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                    "from ENI_ORDER\n" +
                    "where ORDER_STATUS = 'O'\n" +
                    "and ORDER_TYPE in ('CH')\n" +
                    "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                    "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                    "and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                    "and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate\n" +
                    "and rownum <= 1";
            rs = stmtDIA.executeQuery(sqlSC);
            if (rs.next())
            {
                String acctNum = rs.getString("ACCOUNT_ID");
                System.out.println("open SC acct = " + acctNum);

                String orderID = rs.getString("ORDER_ID");
                System.out.println("orderID = " + orderID);

                String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
                System.out.println("orderDate = " + orderDate);

                System.out.println("--------------------------------------");
            }


            rs.close();
            stmtDIA.close();
            conDIA.close();
            System.out.println("\ngetOpenNC = " + getOpenNC() );
            System.out.println("getOpenTC = " + getOpenTC() );
            System.out.println("getOpenSC = " + getOpenSC() );

            System.out.println("getIntOpenNC = " + getIntOpenNC() );
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        catch (SQLException e)
        {
            System.out.println("You have a bad SQL code!");
            e.printStackTrace();
            return;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Error: Not a integer!");
        }

    }	// main

    public static String setWONumber(int option)
    {
        String WOType = "-1";

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DIA connection
            String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
            Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
            Statement stmtDIA = conDIA.createStatement();

            if (option == 1)
            {
                WOType = "NC";
            }
            else if (option == 2)
            {
                WOType = "TC";
            }
            else if (option == 3)
            {
                WOType = "CH";
            }
            else if (option == 4)
            {
                String enteredWO = "";
                System.out.println("Work Order # ?");
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String input = bufferedReader.readLine();
                    enteredWO = input;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                return enteredWO;
            } // else if
            else
            {
                System.exit(0);
            }


        // DIA query - get open New Connect
        String sqlNC = "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE from ENI_ORDER where ORDER_STATUS = 'O' and ORDER_TYPE = '" +
                WOType + "' and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS = 'C') and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate and rownum <= 5";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);

        if (rs.next())
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            System.out.println("\nopen " + WOType + " acct = " + acctNum);

            String orderID = rs.getString("ORDER_ID");
            WOType = orderID.toString();
            System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
            System.out.println("orderDate = " + orderDate);
        }

        rs.close();                              // close Result Set
        stmtDIA.close();                         // close statement
        conDIA.close();                          // close connection

    } // try

    catch (ClassNotFoundException e)
    {
        System.out.println("Where is your Oracle JDBC Driver?");
        e.printStackTrace();
    }
    catch (SQLException e)
    {
        System.out.println("You have a bad SQL code!");
        e.printStackTrace();
    }
    catch (NumberFormatException e)
    {
        System.out.println("Error: Not a integer!");
        e.printStackTrace();
    }
        return WOType;

    } // method: setWONumber

    static String getOpenNC() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get open New Connect
        String sqlNC =
                "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                "from (select *\n" +
                "      from ENI_ORDER\n" +
                "      where ORDER_STATUS = 'O'\n" +
                "      and ORDER_TYPE in ('NC')\n" +
                "      and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                "      and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                "      and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                "      and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate\n" +
                "      order by ORDER_MSG_RCVD_DATE asc)\n" +
                "where rownum <= 1";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);
        if (rs.next() )
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            //System.out.println("open NC acct = " + acctNum);

            orderID = rs.getString("ORDER_ID");
            //System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
            //System.out.println("orderDate = " + orderDate);
        }

        rs.close();
        stmtDIA.close();
        conDIA.close();

        return orderID;
    }

    static String getOpenTC() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get open Trouble Calls
        String sqlNC =
                "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                "from ENI_ORDER\n" +
                "where ORDER_STATUS = 'O'\n" +
                "and ORDER_TYPE in ('TC')\n" +
                "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                "and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                "and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);
        if (rs.next() )
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            //System.out.println("open NC acct = " + acctNum);

            orderID = rs.getString("ORDER_ID");
            //System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
            //System.out.println("orderDate = " + orderDate);
        }
        rs.close();
        stmtDIA.close();
        conDIA.close();

        return orderID;
    }

    static String getOpenSC() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get open Service Calls
        String sqlNC =
                "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                "from ENI_ORDER\n" +
                "where ORDER_STATUS = 'O'\n" +
                "and ORDER_TYPE = 'CH'\n" +
                "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                "and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                "and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                "and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);
        if (rs.next() )
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            //System.out.println("open NC acct = " + acctNum);

            orderID = rs.getString("ORDER_ID");
            //System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
            //System.out.println("orderDate = " + orderDate);
        }
        rs.close();
        stmtDIA.close();
        conDIA.close();

        return orderID;
    }

    static String getIntOpenNC() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@dbdiadb:1534:DBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "devrw", "devpass09");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get open New Connect
        String sqlNC =  "select distinct ACCOUNT_ID, ORDER_CLASS, ORDER_STATUS,ORDER_TYPE,ORDER_ID,ORDER_MSG_RCVD_DATE\n" +
                        "from (select *\n" +
                        "      from ENI_ORDER\n" +
                        "      where ORDER_STATUS = 'O'\n" +
                        "      and ORDER_TYPE in ('NC')\n" +
                        "      and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_STATUS in ('X','C') )\n" +
                        "      and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                        "      and ORDER_ID not in (select ORDER_ID from ENI_ITEM where ITEM_CD in ('T0','T:') )\n" +
                        "      and ORDER_MSG_RCVD_DATE between (sysdate-60) and sysdate\n" +
                        "      order by ORDER_MSG_RCVD_DATE asc)\n" +
                        "where rownum <= 1";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);
        if (rs.next() )
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            //System.out.println("open NC acct = " + acctNum);

            orderID = rs.getString("ORDER_ID");
            //System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("ORDER_MSG_RCVD_DATE");
            //System.out.println("orderDate = " + orderDate);
        }
        rs.close();
        stmtDIA.close();
        conDIA.close();
        return orderID;
    }

    static String getIntOpenTC() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@dbdiadb:1534:DBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "devrw", "devpass09");
        Statement stmtDIA = conDIA.createStatement();

        // DIA query - get open New Connect
        String sqlNC =
                        "select * from \n" +
                        "   (\n" +
                        "select eord.ORDER_ID as \"OrdID\"" +
                        "         ,eord.ACCOUNT_ID\n" +
                        "         ,JOB_TYPE_DESCR\n" +
                        "         ,ORDER_STATUS\n" +
                        "         ,TO_CHAR(JOB_SCHEDULE_DT, 'YYYY-MM-DD HH24:MI:SS') as \"date\"\n" +
                        "   from ENI_ORDER eord\n" +
                        "       ,ENI_JOB ejob\n" +
                        "   where eord.ORDER_ID = ejob.ORDER_ID\n" +
                        "     and ORDER_STATUS = 'O'\n" +
                        "     and JOB_TYPE_DESCR in ('Trouble Call')\n" +
                        "     and ACCOUNT_ID not in (select ACCOUNT_ID from ENI_ORDER where ORDER_TYPE = 'NP')\n" +
                        "     and rownum <= 20\n" +
                        "   )\n" +
                        "order by DBMS_RANDOM.VALUE";
        ResultSet rs = stmtDIA.executeQuery(sqlNC);
        if (rs.next() )
        {
            String acctNum = rs.getString("ACCOUNT_ID");
            //System.out.println("open NC acct = " + acctNum);

            orderID = rs.getString("OrdID");
            //System.out.println("orderID = " + orderID);

            String orderDate = rs.getString("date");
            //System.out.println("orderDate = " + orderDate);
        }
        rs.close();
        stmtDIA.close();
        conDIA.close();
        return orderID;
    }
}	// class