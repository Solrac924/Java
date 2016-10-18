/**
 * Developer:   carlos.loya
 * Date:        3/03/2016
 * Purpose:     Tests the Beacon Tech REST service adapters from TestNG
 * ad majorem Dei gloriam
 **/

package x64;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class testNGAccountAttributes
{
    long startTime;

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib01Residential() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes01 = new testAccountAttributes("8046900001234567", "");

        //System.out.println("myAccountAttributes01.respPartner : " + myAccountAttributes01.respPartner);
        //System.out.println("myAccountAttributes01.respAcctType : " + myAccountAttributes01.respAcctType);
        //System.out.println("myAccountAttributes01.respBillingChan : " + myAccountAttributes01.respBillingChan);
        //System.out.println("myAccountAttributes01.respSubType : " + myAccountAttributes01.respSubType);
        //System.out.println("myAccountAttributes01.respZone : " + myAccountAttributes01.respZone);
        //System.out.println("myAccountAttributes01.dbPartner : " + myAccountAttributes01.dbPartner);
        //System.out.println("myAccountAttributes01.dbAcctType : " + myAccountAttributes01.dbAcctType);
        //System.out.println("myAccountAttributes01.dbSubType : " + myAccountAttributes01.dbSubType);
        //System.out.println("myAccountAttributes01.dbZone : " + myAccountAttributes01.dbZone);

        assert myAccountAttributes01.respPartner.equals(myAccountAttributes01.dbPartner) : "Expected";
        assert myAccountAttributes01.respAcctType.equals(myAccountAttributes01.dbAcctType) : "Expected";
        assert myAccountAttributes01.respSubType.equals(myAccountAttributes01.dbSubType) : "Expected";
        if (myAccountAttributes01.respZone != null)
        {
            assert myAccountAttributes01.respZone.equals(myAccountAttributes01.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib02SDS() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes02 = new testAccountAttributes("8046900001234567", "FC1");

        assert myAccountAttributes02.respPartner.equals(myAccountAttributes02.dbPartner) : "Expected";
        assert myAccountAttributes02.respAcctType.equals(myAccountAttributes02.dbAcctType) : "Expected";
        assert myAccountAttributes02.respSubType.equals(myAccountAttributes02.dbSubType) : "Expected";
        if (myAccountAttributes02.respZone != null)
        {
            assert myAccountAttributes02.respZone.equals(myAccountAttributes02.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib03Valor() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes03 = new testAccountAttributes("8072100001234567", "");

        assert myAccountAttributes03.respPartner.equals(myAccountAttributes03.dbPartner) : "Expected";
        assert myAccountAttributes03.respAcctType.equals(myAccountAttributes03.dbAcctType) : "Expected";
        assert myAccountAttributes03.respSubType.equals(myAccountAttributes03.dbSubType) : "Expected";
        if (myAccountAttributes03.respZone != null)
        {
            assert myAccountAttributes03.respZone.equals(myAccountAttributes03.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib04TDS() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes04 = new testAccountAttributes("8072200001234567", "");

        assert myAccountAttributes04.respPartner.equals(myAccountAttributes04.dbPartner) : "Expected";
        assert myAccountAttributes04.respAcctType.equals(myAccountAttributes04.dbAcctType) : "Expected";
        assert myAccountAttributes04.respSubType.equals(myAccountAttributes04.dbSubType) : "Expected";
        if (myAccountAttributes04.respZone != null)
        {
            assert myAccountAttributes04.respZone.equals(myAccountAttributes04.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib05BeQuick() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes05 = new testAccountAttributes("8072300001234567", "");

        assert myAccountAttributes05.respPartner.equals(myAccountAttributes05.dbPartner) : "Expected";
        assert myAccountAttributes05.respAcctType.equals(myAccountAttributes05.dbAcctType) : "Expected";
        assert myAccountAttributes05.respSubType.equals(myAccountAttributes05.dbSubType) : "Expected";
        if (myAccountAttributes05.respZone != null)
        {
            assert myAccountAttributes05.respZone.equals(myAccountAttributes05.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib06Pace() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes06 = new testAccountAttributes("8072400001234567", "");

        assert myAccountAttributes06.respPartner.equals(myAccountAttributes06.dbPartner) : "Expected";
        assert myAccountAttributes06.respAcctType.equals(myAccountAttributes06.dbAcctType) : "Expected";
        assert myAccountAttributes06.respSubType.equals(myAccountAttributes06.dbSubType) : "Expected";
        if (myAccountAttributes06.respZone != null)
        {
            assert myAccountAttributes06.respZone.equals(myAccountAttributes06.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib07Alltel() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes07 = new testAccountAttributes("8072500001234567", "");

        assert myAccountAttributes07.respPartner.equals(myAccountAttributes07.dbPartner) : "Expected";
        assert myAccountAttributes07.respAcctType.equals(myAccountAttributes07.dbAcctType) : "Expected";
        assert myAccountAttributes07.respSubType.equals(myAccountAttributes07.dbSubType) : "Expected";
        if (myAccountAttributes07.respZone != null)
        {
            assert myAccountAttributes07.respZone.equals(myAccountAttributes07.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib08Frontier() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes08 = new testAccountAttributes("8072600001234567", "");

        assert myAccountAttributes08.respPartner.equals(myAccountAttributes08.dbPartner) : "Expected";
        assert myAccountAttributes08.respAcctType.equals(myAccountAttributes08.dbAcctType) : "Expected";
        assert myAccountAttributes08.respSubType.equals(myAccountAttributes08.dbSubType) : "Expected";
        if (myAccountAttributes08.respZone != null)
        {
            assert myAccountAttributes08.respZone.equals(myAccountAttributes08.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib09IowaTelecom() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes09 = new testAccountAttributes("8072700001234567", "");

        assert myAccountAttributes09.respPartner.equals(myAccountAttributes09.dbPartner) : "Expected";
        assert myAccountAttributes09.respAcctType.equals(myAccountAttributes09.dbAcctType) : "Expected";
        assert myAccountAttributes09.respSubType.equals(myAccountAttributes09.dbSubType) : "Expected";
        if (myAccountAttributes09.respZone != null)
        {
            assert myAccountAttributes09.respZone.equals(myAccountAttributes09.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib10Centurytel() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes10 = new testAccountAttributes("8072800001234567", "");

        assert myAccountAttributes10.respPartner.equals(myAccountAttributes10.dbPartner) : "Expected";
        assert myAccountAttributes10.respAcctType.equals(myAccountAttributes10.dbAcctType) : "Expected";
        assert myAccountAttributes10.respSubType.equals(myAccountAttributes10.dbSubType) : "Expected";
        if (myAccountAttributes10.respZone != null)
        {
            assert myAccountAttributes10.respZone.equals(myAccountAttributes10.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib11MoreDishMDU() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes11 = new testAccountAttributes("8046160001234567", "");

        assert myAccountAttributes11.respPartner.equals(myAccountAttributes11.dbPartner) : "Expected";
        assert myAccountAttributes11.respAcctType.equals(myAccountAttributes11.dbAcctType) : "Expected";
        assert myAccountAttributes11.respSubType.equals(myAccountAttributes11.dbSubType) : "Expected";
        if (myAccountAttributes11.respZone != null)
        {
            assert myAccountAttributes11.respZone.equals(myAccountAttributes11.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib12MoreDishPCO() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes12 = new testAccountAttributes("8046230001234567", "");

        assert myAccountAttributes12.respPartner.equals(myAccountAttributes12.dbPartner) : "Expected";
        assert myAccountAttributes12.respAcctType.equals(myAccountAttributes12.dbAcctType) : "Expected";
        assert myAccountAttributes12.respSubType.equals(myAccountAttributes12.dbSubType) : "Expected";
        if (myAccountAttributes12.respZone != null)
        {
            assert myAccountAttributes12.respZone.equals(myAccountAttributes12.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib13CommPublic() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes13 = new testAccountAttributes("8046707051234567", "");

        assert myAccountAttributes13.respPartner.equals(myAccountAttributes13.dbPartner) : "Expected";
        assert myAccountAttributes13.respAcctType.equals(myAccountAttributes13.dbAcctType) : "Expected";
        assert myAccountAttributes13.respSubType.equals(myAccountAttributes13.dbSubType) : "Expected";
        if (myAccountAttributes13.respZone != null)
        {
            assert myAccountAttributes13.respZone.equals(myAccountAttributes13.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib14CommPrivate() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes14 = new testAccountAttributes("8046707081234567", "");

        assert myAccountAttributes14.respPartner.equals(myAccountAttributes14.dbPartner) : "Expected";
        assert myAccountAttributes14.respAcctType.equals(myAccountAttributes14.dbAcctType) : "Expected";
        assert myAccountAttributes14.respSubType.equals(myAccountAttributes14.dbSubType) : "Expected";
        if (myAccountAttributes14.respZone != null)
        {
            assert myAccountAttributes14.respZone.equals(myAccountAttributes14.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib15ResPuertoRico() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes15 = new testAccountAttributes("8046919111234567", "");

        assert myAccountAttributes15.respPartner.equals(myAccountAttributes15.dbPartner) : "Expected";
        assert myAccountAttributes15.respAcctType.equals(myAccountAttributes15.dbAcctType) : "Expected";
        assert myAccountAttributes15.respSubType.equals(myAccountAttributes15.dbSubType) : "Expected";
        if (myAccountAttributes15.respZone != null)
        {
            assert myAccountAttributes15.respZone.equals(myAccountAttributes15.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib16PublicPR() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes16 = new testAccountAttributes("8046470011234567", "");

        assert myAccountAttributes16.respPartner.equals(myAccountAttributes16.dbPartner) : "Expected";
        assert myAccountAttributes16.respAcctType.equals(myAccountAttributes16.dbAcctType) : "Expected";
        assert myAccountAttributes16.respSubType.equals(myAccountAttributes16.dbSubType) : "Expected";
        if (myAccountAttributes16.respZone != null)
        {
            assert myAccountAttributes16.respZone.equals(myAccountAttributes16.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib17PrivatePR() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes17 = new testAccountAttributes("8046490011234567", "");

        assert myAccountAttributes17.respPartner.equals(myAccountAttributes17.dbPartner) : "Expected";
        assert myAccountAttributes17.respAcctType.equals(myAccountAttributes17.dbAcctType) : "Expected";
        assert myAccountAttributes17.respSubType.equals(myAccountAttributes17.dbSubType) : "Expected";
        if (myAccountAttributes17.respZone != null)
        {
            assert myAccountAttributes17.respZone.equals(myAccountAttributes17.dbZone) : "Expected";
        }
    }

    @Test(groups = { "AccountAttributes" } )
    public void AcctAttrib18Employee() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        testAccountAttributes myAccountAttributes18 = new testAccountAttributes("8046200001234567", "IL");

        assert myAccountAttributes18.respPartner.equals(myAccountAttributes18.dbPartner) : "Expected";
        assert myAccountAttributes18.respAcctType.equals(myAccountAttributes18.dbAcctType) : "Expected";
        assert myAccountAttributes18.respSubType.equals(myAccountAttributes18.dbSubType) : "Expected";
        if (myAccountAttributes18.respZone != null)
        {
            assert myAccountAttributes18.respZone.equals(myAccountAttributes18.dbZone) : "Expected";
        }
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
}
