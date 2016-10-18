/**
 * Created by carlos.loya on 2/18/2016.
 */

package x64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basicInput
{
    public static void main(String[] args)
    {
        int inNumber = 0;		// initializing integer
        System.out.println("Enter an integer. ");
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            inNumber = Integer.parseInt(input);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Error: Not a integer!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("inNumber = " + inNumber);
        }
    }
}
