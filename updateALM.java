/**
 * Created by carlos.loya on 2/15/2016.
 */

package x64;
import atu.alm.wrapper.exceptions.ALMServiceException;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.enums.StatusAs;
import com.jacob.com.LibraryLoader;

public class updateALM
{
    public static void main(String[] args)
    {
        // load JACOB library
        System.setProperty("jacob.dll.path", "C:\\Users\\carlos.loya\\workspace\\jacob-1.18-x64.dll");
        LibraryLoader.loadJacobLibrary();

        try
        {
            // connect to ALM
            ALMServiceWrapper wrapper = new ALMServiceWrapper("http://10.10.4.79:8080/qcbin");
            wrapper.connect("carlos.loya", "cl558", "bt", "ofm");

            // update tests
            wrapper.updateResult("ExternalTQA\\Regression - Beacon Tech", "Regression 3-1", 603, "TC09 - Service Call  Open", StatusAs.PASSED);
            wrapper.updateResult("ExternalTQA\\Regression - Beacon Tech", "Regression 3-1", 603, "TC10 - 8072 5000", StatusAs.FAILED);
            wrapper.updateResult("ExternalTQA\\Regression - Beacon Tech", "Regression 3-1", 603, "TC15 - Service Call  Closed", StatusAs.BLOCKED);
            wrapper.close();
        }
        catch (ALMServiceException e)
        {
            e.printStackTrace();
        }

    } // end Main
} // end class