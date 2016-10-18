/***
 * Developer:   carlos.loya
 * Date:        3/22/2016
 * Purpose:     Tests the Beacon Tech REST service adapters from TestNG
 * ad majorem Dei gloriam
 ***/

package x64;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class testNGAgentService
{
    long startTime;
    String pad = "%-32s %s\n";

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService01() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService01 = new testAgentService(agentID);
        System.out.format(pad, "respAgentID: " + myAgentService01.respAgentID , "dbAgentID: " + testAgentService.getAgentID(agentID));
        assert myAgentService01.respAgentID.equalsIgnoreCase(testAgentService.getAgentID(agentID) );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService02() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService02 = new testAgentService(agentID);
        System.out.format(pad, "respAgreementType: " + myAgentService02.respAgreementType , "dbAgreementType: " + testAgentService.getAttribValue(agentID,"AGREEMENT_TYPE") );
        assert myAgentService02.respAgreementType.contentEquals(testAgentService.getAttribValue(agentID,"AGREEMENT_TYPE") );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService03() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService03 = new testAgentService(agentID);
        System.out.format(pad, "respOEAPI: " + myAgentService03.respOEAPI , "dbOEAPI_USER_NAME: " + testAgentService.getAttribValue(agentID,"OEAPI_USER_NAME") );
        assert myAgentService03.respOEAPI.contentEquals(testAgentService.getAttribValue(agentID,"OEAPI_USER_NAME") );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService04() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService04 = new testAgentService(agentID);
        System.out.format(pad, "respOfferCode: " + myAgentService04.respOfferCode , "dbDefaultOffercode: " + testAgentService.getAttribValue(agentID,"DEFAULT_OFFERCODE") );
        assert myAgentService04.respOfferCode.contentEquals(testAgentService.getAttribValue(agentID,"DEFAULT_OFFERCODE") );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService05() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService05 = new testAgentService(agentID);
        System.out.format(pad, "respDigLandCamp: " + myAgentService05.respDigLandCamp , "dbDigLandingCamp: " + testAgentService.getAttribValue(agentID,"DIGITAL_LANDING_CAMPAIGN") );
        assert myAgentService05.respDigLandCamp.contentEquals(testAgentService.getAttribValue(agentID,"DIGITAL_LANDING_CAMPAIGN") );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService06() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService06 = new testAgentService(agentID);
        System.out.format(pad, "respOENumber: " + myAgentService06.respOENumber , "dbOENumber: " + testAgentService.getOENumber(agentID) );
        assert myAgentService06.respOENumber.contentEquals(testAgentService.getOENumber(agentID) );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService07() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService07 = new testAgentService(agentID);
        System.out.format(pad, "respOrg: " + myAgentService07.respOrg , "dbOrg: " + testAgentService.getOrg(agentID) );
        assert myAgentService07.respOrg.contentEquals(testAgentService.getOrg(agentID) );
    }

    @Test(groups = { "AgentService" } )
    public void testAgentService08() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, SQLException
    {
        String agentID = "carlos.loya";
        testAgentService myAgentService08 = new testAgentService(agentID);
        System.out.format(pad, "respAppRoleList: " + myAgentService08.respAppRoleList , "dbRole: " + testAgentService.getRole(agentID) );
        assert myAgentService08.respAppRoleList.contentEquals(testAgentService.getRole(agentID) );
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
}
