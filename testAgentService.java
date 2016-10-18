/**
 * Developer:       Carlos A Loya
 * Create date:     2/19/2016
 * Modified date:   3/29/2016
 * Purpose:         Tests the Beacon Tech REST service adapter
 * ad majorem Dei gloriam
 **/

package x64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.*;
import java.util.StringTokenizer;

public class testAgentService
{
    static String respAgentID = "";
    static String respOfferCode = "";
    static String respAgreementType = "";
    static String respOEAPI = "";
    static String respDigLandCamp = "";
    static String respAppRoleList = "";
    static String respOENumber = "";
    static String respOrg = "";
    static String dbAgentID = "";
    static String dbAttribValue = "";
    static String dbOENumber = "";
    static String dbOrg = "";
    static String dbRole = "";

    public testAgentService(String inAgent) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // send REST request
        HttpGet request = new HttpGet("https://agent-service.test.apps.internal-green.test.mer.cfdish.io:443/agent/" +
                inAgent + "?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");

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

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("agentId") )
            {
                scrap = st.nextToken();
                respAgentID = st.nextToken();
            }
            if (currToken.contains("DEFAULT_OFFERCODE") )
            {
                scrap = st.nextToken();
                respOfferCode = st.nextToken();
            }
            if (currToken.contains("AGREEMENT_TYPE") )
            {
                scrap = st.nextToken();
                respAgreementType = st.nextToken();
            }
            if (currToken.contains("OEAPI_USER_NAME") )
            {
                scrap = st.nextToken();
                respOEAPI = st.nextToken();
            }
            if (currToken.contains("DIGITAL_LANDING_CAMPAIGN") )
            {
                scrap = st.nextToken();
                respDigLandCamp = st.nextToken();
            }
            if (currToken.contains("SELLER_OE_NUMBER") )
            {
                scrap = st.nextToken();
                respOENumber = st.nextToken();
            }
            if (currToken.contains("PRIMARY_INSTALL_TYPE") )
            {
                scrap = st.nextToken();
                respOrg = st.nextToken();
            }
            if (currToken.contains("applicationRoleList") )
            {
                scrap = st.nextToken();
                respAppRoleList = st.nextToken();
            }
        } // while
    } // testAgentService(String)

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException,ClassNotFoundException,SQLException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // get REST response
        HttpGet request = new HttpGet("https://agent-service.test.apps.internal-green.test.mer.cfdish.io/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }

        // show the status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show the service adapter version
        System.out.println("version: " + showVersion() );

        // get database results
        System.out.println("dbAgentID: " + getAgentID("carlos.loya") );
        System.out.println("dbAgreementType: " + getAttribValue("carlos.loya", "AGREEMENT_TYPE") );
        System.out.println("dbOEAPI: " + getAttribValue("carlos.loya", "OEAPI_USER_NAME") );
        System.out.println("dbDefaultOffercode: " + getAttribValue("carlos.loya", "DEFAULT_OFFERCODE") );
        System.out.println("dbDigLandingCamp: " + getAttribValue("carlos.loya","DIGITAL_LANDING_CAMPAIGN") );
        System.out.println("dbOE_NUMBER: " + getOENumber("carlos.loya") );
        System.out.println("dbORG: " + getOrg("carlos.loya") );
        System.out.println("dbROLE_NAME: " + getRole("carlos.loya") );

    } // end main

    static void showStatusCode() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // send REST request
        HttpGet request = new HttpGet("https://agent-service.test.apps.internal-green.test.mer.cfdish.io/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");

        // get REST response
        HttpResponse response = client.execute(request);

        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );
    }

    static int getStatusCode() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // get REST response
        HttpGet request = new HttpGet("https://agent-service.test.apps.internal-green.test.mer.cfdish.io/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");
        HttpResponse response = client.execute(request);

        return response.getStatusLine().getStatusCode();
    }

    static String showVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // send REST request
        HttpGet request = new HttpGet("https://agent-service.test.apps.internal-green.test.mer.cfdish.io/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");

        HttpResponse response = client.execute(request);

        String version = "";
        org.apache.http.Header[] headers = response.getAllHeaders();
        for (org.apache.http.Header header : headers)
        {
            if (header.getName().contains("X-Application-Context") )
            {
                version = header.getValue();
            }
        }
        return version;
    } // showVersion

    static String getAgentID(String inAgent) throws ClassNotFoundException, SQLException
    {
        // get values from SQL query
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // PRM connection
        String urlPRM = "jdbc:oracle:thin:@tprmdb:1526:TPRM";
        Connection conPRM = DriverManager.getConnection(urlPRM, "TQA_TPRM", "crmdish987");
        Statement stmtPRM = conPRM.createStatement();

        // DIA query - get open New Connect
        String sqlPRM =
                    "select distinct person.USER_NAME\n" +
                    "      ,person.FIRST_NAME\n" +
                    "      ,person.LAST_NAME\n" +
                    "      ,appattr.X_ATTRIB_NAME\n" +
                    "      ,orgapp.X_ATTRIB_VALUE\n" +
                    "      ,org.OE_NUMBER\n" +
                    "      ,org.ORG_NAME\n" +
                    "      ,site.SITE_NAME\n" +
                    "      ,person.DISH_FLG\n" +
                    "      ,role.ROLE_NAME\n" +
                    "      ,appattr.TVRO_FLG\n" +
                    "from SIEBEL.CX_PT_ORG org\n" +
                    "     ,SIEBEL.CX_PT_PERSON person\n" +
                    "     ,SIEBEL.CX_PT_PERS_ROLE per_role\n" +
                    "     ,SIEBEL.CX_PT_ROLE role\n" +
                    "     ,SIEBEL.CX_PT_SITE_PERS persite\n" +
                    "     ,SIEBEL.CX_PT_SITE site\n" +
                    "     ,SIEBEL.CX_PT_ORGAPPATT orgapp\n" +
                    "     ,SIEBEL.CX_PT_APPL_ATTR appattr\n" +
                    "where person.USER_NAME = upper('" + inAgent + "')\n" +
                    "  and person.ROW_ID = per_role.PERSON_ID\n" +
                    "  and per_role.ROLE_ID = role.ROW_ID\n" +
                    "  and person.SITE_ID = site.ROW_ID\n" +
                    "  and person.ORG_ID = org.ROW_ID\n" +
                    "  and org.ROW_ID = orgapp.ORG_ID \n" +
                    "  and orgapp.APPL_ATTR_ID = appattr.ROW_ID\n" +
                    "  and appattr.TVRO_FLG = 'N'\n" +
                    "  and appattr.X_ATTRIB_NAME = 'SLING_TV'";
        ResultSet rs = stmtPRM.executeQuery(sqlPRM);
        if (rs.next())
        {
            // store database values
            dbAgentID = rs.getString("USER_NAME");
            //System.out.println("dbAgentID = " + dbAgentID);
        }
        rs.close();
        stmtPRM.close();
        conPRM.close();

        return dbAgentID;
    }

    static String getAttribValue(String inAgent, String inAttribName) throws ClassNotFoundException, SQLException
    {
        // get values from SQL query
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // PRM connection
        String urlPRM = "jdbc:oracle:thin:@tprmdb:1526:TPRM";
        Connection conPRM = DriverManager.getConnection(urlPRM, "TQA_TPRM", "crmdish987");
        Statement stmtPRM = conPRM.createStatement();

        // DIA query - get open New Connect
        String sqlPRM =
                "select distinct person.USER_NAME\n" +
                "      ,person.FIRST_NAME\n" +
                "      ,person.LAST_NAME\n" +
                "      ,appattr.X_ATTRIB_NAME\n" +
                "      ,orgapp.X_ATTRIB_VALUE\n" +
                "      ,org.OE_NUMBER\n" +
                "      ,org.ORG_NAME\n" +
                "      ,site.SITE_NAME\n" +
                "      ,person.DISH_FLG\n" +
                "      ,role.ROLE_NAME\n" +
                "      ,appattr.TVRO_FLG\n" +
                "from SIEBEL.CX_PT_ORG org\n" +
                "     ,SIEBEL.CX_PT_PERSON person\n" +
                "     ,SIEBEL.CX_PT_PERS_ROLE per_role\n" +
                "     ,SIEBEL.CX_PT_ROLE role\n" +
                "     ,SIEBEL.CX_PT_SITE_PERS persite\n" +
                "     ,SIEBEL.CX_PT_SITE site\n" +
                "     ,SIEBEL.CX_PT_ORGAPPATT orgapp\n" +
                "     ,SIEBEL.CX_PT_APPL_ATTR appattr\n" +
                "where person.USER_NAME = upper('" + inAgent + "')\n" +
                "  and person.ROW_ID = per_role.PERSON_ID\n" +
                "  and per_role.ROLE_ID = role.ROW_ID\n" +
                "  and person.SITE_ID = site.ROW_ID\n" +
                "  and person.ORG_ID = org.ROW_ID\n" +
                "  and org.ROW_ID = orgapp.ORG_ID \n" +
                "  and orgapp.APPL_ATTR_ID = appattr.ROW_ID\n" +
                "  and appattr.TVRO_FLG = 'N'\n" +
                "  and appattr.X_ATTRIB_NAME = '" + inAttribName + "'";
        ResultSet rs = stmtPRM.executeQuery(sqlPRM);
        if (rs.next())
        {
            // store database values
            dbAttribValue = rs.getString("X_ATTRIB_VALUE");
            //System.out.println("X_ATTRIB_VALUE = " + dbAgreementType);
        }
        rs.close();
        stmtPRM.close();
        conPRM.close();

        return dbAttribValue;
    }

    static String getOENumber(String inAgent) throws ClassNotFoundException, SQLException
    {
        // get values from SQL query
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // PRM connection
        String urlPRM = "jdbc:oracle:thin:@tprmdb:1526:TPRM";
        Connection conPRM = DriverManager.getConnection(urlPRM, "TQA_TPRM", "crmdish987");
        Statement stmtPRM = conPRM.createStatement();

        // DIA query - get open New Connect
        String sqlPRM =
                "select distinct person.USER_NAME\n" +
                        "      ,person.FIRST_NAME\n" +
                        "      ,person.LAST_NAME\n" +
                        "      ,appattr.X_ATTRIB_NAME\n" +
                        "      ,orgapp.X_ATTRIB_VALUE\n" +
                        "      ,org.OE_NUMBER\n" +
                        "      ,org.ORG_NAME\n" +
                        "      ,site.SITE_NAME\n" +
                        "      ,person.DISH_FLG\n" +
                        "      ,role.ROLE_NAME\n" +
                        "      ,appattr.TVRO_FLG\n" +
                        "from SIEBEL.CX_PT_ORG org\n" +
                        "     ,SIEBEL.CX_PT_PERSON person\n" +
                        "     ,SIEBEL.CX_PT_PERS_ROLE per_role\n" +
                        "     ,SIEBEL.CX_PT_ROLE role\n" +
                        "     ,SIEBEL.CX_PT_SITE_PERS persite\n" +
                        "     ,SIEBEL.CX_PT_SITE site\n" +
                        "     ,SIEBEL.CX_PT_ORGAPPATT orgapp\n" +
                        "     ,SIEBEL.CX_PT_APPL_ATTR appattr\n" +
                        "where person.USER_NAME = upper('" + inAgent + "')\n" +
                        "  and person.ROW_ID = per_role.PERSON_ID\n" +
                        "  and per_role.ROLE_ID = role.ROW_ID\n" +
                        "  and person.SITE_ID = site.ROW_ID\n" +
                        "  and person.ORG_ID = org.ROW_ID\n" +
                        "  and org.ROW_ID = orgapp.ORG_ID \n" +
                        "  and orgapp.APPL_ATTR_ID = appattr.ROW_ID\n" +
                        "  and appattr.TVRO_FLG = 'N'\n" +
                        "  and appattr.X_ATTRIB_NAME = 'AGREEMENT_TYPE'";
        ResultSet rs = stmtPRM.executeQuery(sqlPRM);
        if (rs.next())
        {
            // store database values
            dbOENumber = rs.getString("OE_NUMBER");
        }
        rs.close();
        stmtPRM.close();
        conPRM.close();

        return dbOENumber;
    }

    static String getOrg(String inAgent) throws ClassNotFoundException, SQLException
    {
        // get values from SQL query
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // PRM connection
        String urlPRM = "jdbc:oracle:thin:@tprmdb:1526:TPRM";
        Connection conPRM = DriverManager.getConnection(urlPRM, "TQA_TPRM", "crmdish987");
        Statement stmtPRM = conPRM.createStatement();

        // DIA query - get open New Connect
        String sqlPRM =
                "select distinct person.USER_NAME\n" +
                        "      ,person.FIRST_NAME\n" +
                        "      ,person.LAST_NAME\n" +
                        "      ,appattr.X_ATTRIB_NAME\n" +
                        "      ,orgapp.X_ATTRIB_VALUE\n" +
                        "      ,org.OE_NUMBER\n" +
                        "      ,org.ORG_NAME\n" +
                        "      ,site.SITE_NAME\n" +
                        "      ,person.DISH_FLG\n" +
                        "      ,role.ROLE_NAME\n" +
                        "      ,appattr.TVRO_FLG\n" +
                        "from SIEBEL.CX_PT_ORG org\n" +
                        "     ,SIEBEL.CX_PT_PERSON person\n" +
                        "     ,SIEBEL.CX_PT_PERS_ROLE per_role\n" +
                        "     ,SIEBEL.CX_PT_ROLE role\n" +
                        "     ,SIEBEL.CX_PT_SITE_PERS persite\n" +
                        "     ,SIEBEL.CX_PT_SITE site\n" +
                        "     ,SIEBEL.CX_PT_ORGAPPATT orgapp\n" +
                        "     ,SIEBEL.CX_PT_APPL_ATTR appattr\n" +
                        "where person.USER_NAME = upper('" + inAgent + "')\n" +
                        "  and person.ROW_ID = per_role.PERSON_ID\n" +
                        "  and per_role.ROLE_ID = role.ROW_ID\n" +
                        "  and person.SITE_ID = site.ROW_ID\n" +
                        "  and person.ORG_ID = org.ROW_ID\n" +
                        "  and org.ROW_ID = orgapp.ORG_ID \n" +
                        "  and orgapp.APPL_ATTR_ID = appattr.ROW_ID\n" +
                        "  and appattr.TVRO_FLG = 'N'\n" +
                        "  and appattr.X_ATTRIB_NAME = 'AGREEMENT_TYPE'";
        ResultSet rs = stmtPRM.executeQuery(sqlPRM);
        if (rs.next())
        {
            // store database values
            dbOrg = rs.getString("ORG_NAME");
        }
        rs.close();
        stmtPRM.close();
        conPRM.close();

        return dbOrg;
    }

    static String getRole(String inAgent) throws ClassNotFoundException, SQLException
    {
        // get values from SQL query
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // PRM connection
        String urlPRM = "jdbc:oracle:thin:@tprmdb:1526:TPRM";
        Connection conPRM = DriverManager.getConnection(urlPRM, "TQA_TPRM", "crmdish987");
        Statement stmtPRM = conPRM.createStatement();

        // DIA query - get open New Connect
        String sqlPRM =
                "select distinct person.USER_NAME\n" +
                        "      ,person.FIRST_NAME\n" +
                        "      ,person.LAST_NAME\n" +
                        "      ,appattr.X_ATTRIB_NAME\n" +
                        "      ,orgapp.X_ATTRIB_VALUE\n" +
                        "      ,org.OE_NUMBER\n" +
                        "      ,org.ORG_NAME\n" +
                        "      ,site.SITE_NAME\n" +
                        "      ,person.DISH_FLG\n" +
                        "      ,role.ROLE_NAME\n" +
                        "      ,appattr.TVRO_FLG\n" +
                        "from SIEBEL.CX_PT_ORG org\n" +
                        "     ,SIEBEL.CX_PT_PERSON person\n" +
                        "     ,SIEBEL.CX_PT_PERS_ROLE per_role\n" +
                        "     ,SIEBEL.CX_PT_ROLE role\n" +
                        "     ,SIEBEL.CX_PT_SITE_PERS persite\n" +
                        "     ,SIEBEL.CX_PT_SITE site\n" +
                        "     ,SIEBEL.CX_PT_ORGAPPATT orgapp\n" +
                        "     ,SIEBEL.CX_PT_APPL_ATTR appattr\n" +
                        "where person.USER_NAME = upper('" + inAgent + "')\n" +
                        "  and person.ROW_ID = per_role.PERSON_ID\n" +
                        "  and per_role.ROLE_ID = role.ROW_ID\n" +
                        "  and person.SITE_ID = site.ROW_ID\n" +
                        "  and person.ORG_ID = org.ROW_ID\n" +
                        "  and org.ROW_ID = orgapp.ORG_ID \n" +
                        "  and orgapp.APPL_ATTR_ID = appattr.ROW_ID\n" +
                        "  and appattr.TVRO_FLG = 'N'\n" +
                        "  and appattr.X_ATTRIB_NAME = 'AGREEMENT_TYPE'";
        ResultSet rs = stmtPRM.executeQuery(sqlPRM);
        if (rs.next())
        {
            // store database values
            dbRole = rs.getString("ROLE_NAME");
            //System.out.println("X_ATTRIB_VALUE = " + dbAgreementType);
        }
        rs.close();
        stmtPRM.close();
        conPRM.close();

        return dbRole;
    }
} // end class