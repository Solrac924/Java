/***
 * developer:   Carlos Loya
 * date:        2/25/2016
 * purpose:     extracting values from the JSON response
 ***/

package x64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class JSON2String
{
    private final static String JSON_DATA =
                    "{\n" +
                    "  \"accountInfo\" : {\n" +
                    "    \"accountId\" : \"8046909808485230\",\n" +
                    "    \"customerId\" : \"1301074430241\",\n" +
                    "    \"customerName\" : {\n" +
                    "      \"givenName\" : \"AAMIR\",\n" +
                    "      \"familyName\" : \"SHUNTHOO\"\n" +
                    "    },\n" +
                    "    \"businessName\" : \"SHUNTHOO,AAMIR\",\n" +
                    "    \"serviceAddress\" : {\n" +
                    "      \"line1\" : \"170787 E CARSON DR\",\n" +
                    "      \"line2\" : \"3454345ADDD\",\n" +
                    "      \"city\" : \"PARKER\",\n" +
                    "      \"state\" : \"CO\",\n" +
                    "      \"zipCode\" : \"80134\",\n" +
                    "      \"zipCodeExtension\" : \"0000\"\n" +
                    "    },\n" +
                    "    \"phone1\" : {\n" +
                    "      \"phone\" : \"3033033030\",\n" +
                    "      \"type\" : \"HOME\"\n" +
                    "    },\n" +
                    "    \"phone2\" : {\n" +
                    "      \"phone\" : \"0000000000\",\n" +
                    "      \"type\" : \"UNKNOWN\"\n" +
                    "    },\n" +
                    "    \"emailAddress\" : \"mohd.lone@dish.com\",\n" +
                    "    \"billingAddress\" : {\n" +
                    "      \"line1\" : \"170787 G CARLSON DR\",\n" +
                    "      \"line2\" : \"7.269899871924667E7\",\n" +
                    "      \"city\" : \"PARKER\",\n" +
                    "      \"state\" : \"CO\",\n" +
                    "      \"zipCode\" : \"80134\",\n" +
                    "      \"zipCodeExtension\" : null\n" +
                    "    },\n" +
                    "    \"currentBalance\" : -1.00,\n" +
                    "    \"pendingPayment\" : null,\n" +
                    "    \"billingType\" : 1,\n" +
                    "    \"cycleCode\" : 2,\n" +
                    "    \"billingFromDate\" : \"2016-02-17\",\n" +
                    "    \"billingToDate\" : \"2016-03-16\",\n" +
                    "    \"geoCode\" : \"060351682\",\n" +
                    "    \"managementGroup\" : \"600\",\n" +
                    "    \"connectDate\" : \"2015-06-17\",\n" +
                    "    \"connectStatus\" : \" \",\n" +
                    "    \"delinquencyStatus\" : \" \",\n" +
                    "    \"primaryStatus\" : \"ACTIVE\",\n" +
                    "    \"subStatus\" : [ \"ACTIVE\" ]\n" +
                    "  },\n" +
                    "  \"offeringList\" : [ {\n" +
                    "    \"id\" : null,\n" +
                    "    \"offeringId\" : null,\n" +
                    "    \"offeringDescription\" : \"ACTIVATION\",\n" +
                    "    \"status\" : \"CURRENT\",\n" +
                    "    \"quantity\" : 1,\n" +
                    "    \"beforeQuantity\" : 1,\n" +
                    "    \"billingBeginDate\" : \"2015-06-17\",\n" +
                    "    \"autoAdd\" : false,\n" +
                    "    \"price\" : null,\n" +
                    "    \"productName\" : null,\n" +
                    "    \"maximumProductQuantity\" : 0\n" +
                    "  }, {\n" +
                    "    \"id\" : null,\n" +
                    "    \"offeringId\" : null,\n" +
                    "    \"offeringDescription\" : \"AUTO PAY\",\n" +
                    "    \"status\" : \"CURRENT\",\n" +
                    "    \"quantity\" : 1,\n" +
                    "    \"beforeQuantity\" : 1,\n" +
                    "    \"billingBeginDate\" : \"2015-06-17\",\n" +
                    "    \"autoAdd\" : false,\n" +
                    "    \"price\" : null,\n" +
                    "    \"productName\" : null,\n" +
                    "    \"maximumProductQuantity\" : 0\n" +
                    "  }, {\n" +
                    "    \"id\" : null,\n" +
                    "    \"offeringId\" : null,\n" +
                    "    \"offeringDescription\" : \"EBILL\",\n" +
                    "    \"status\" : \"CURRENT\",\n" +
                    "    \"quantity\" : 1,\n" +
                    "    \"beforeQuantity\" : 1,\n" +
                    "    \"billingBeginDate\" : \"2015-08-11\",\n" +
                    "    \"autoAdd\" : false,\n" +
                    "    \"price\" : null,\n" +
                    "    \"productName\" : null,\n" +
                    "    \"maximumProductQuantity\" : 0\n" +
                    "  } ],\n" +
                    "  \"accountAttributes\" : {\n" +
                    "    \"miscellaneous\" : null,\n" +
                    "    \"attributes\" : {\n" +
                    "      \"PARTNER\" : \"DISH\",\n" +
                    "      \"ACCOUNT_TYPE\" : \"RESIDENTIAL\",\n" +
                    "      \"BILLING_CHANNEL\" : \"DISH\",\n" +
                    "      \"ACCOUNT_SUB_TYPE\" : \"NONE\",\n" +
                    "      \"ZONE\" : null,\n" +
                    "      \"CARD_PAYMENT_ALLOWED\" : \"true\",\n" +
                    "      \"EFT_PAYMENT_ALLOWED\" : \"true\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

    public static void main(final String[] argv) throws JSONException
    {
        final JSONObject obj = new JSONObject(JSON_DATA);
        final JSONArray myData = obj.getJSONArray("offeringList");
        final int n = myData.length();
        for (int i = 0; i < n; ++i)
        {
            final JSONObject service = myData.getJSONObject(i);
            System.out.println(service.getString("offeringId")
                      + "\t" + service.getString("offeringDescription")
                      + "\t" + service.getString("status")
                      + "\t" + service.getDouble("quantity")
                      + "\t" + service.getDouble("beforeQuantity")
                      + "\t" + service.getString("billingBeginDate") );
        }
        showTokens();
    } // main

    public static void showTokens()
    {

        StringTokenizer st = new StringTokenizer(JSON_DATA, "\"");
        String currToken = "";
        String scrap = "";
        String accountIdPL = "";
        String customerIdPL = "";
        String subStatusPL = "";

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("accountId") )
            {
                scrap = st.nextToken();
                accountIdPL = st.nextToken();
            }
            if (currToken.contains("customerId") )
            {
                scrap = st.nextToken();
                customerIdPL = st.nextToken();
            }
            if (currToken.contains("subStatus") )
            {
                scrap = st.nextToken();
                subStatusPL = st.nextToken();
            }

        } // end while

        System.out.println("\naccountIdToken : " + accountIdPL);
        System.out.println("customerIdToken : " + customerIdPL);
        System.out.println("subStatusToken : " + subStatusPL);
    }
} // class JSON2String