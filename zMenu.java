/***
 * Developer:   Carlos A Loya
 * Date:        2/22/2016
 * Purpose:     To start with a menu to drive the Beacon Tech automation flow
 * AMDG
 ***/

package x64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zMenu
{
    int inNumber;	                            	                                // initializing integer

    public static void main(String[] args)
    {
        displayMenu();                                                              // show a menu in the console
        int option = getOption();                                                   // get the option #
        System.out.println("\nsetWONumber: " + getOpenWOs.setWONumber(option) );    // get WO #


    } // end main

    public static void displayMenu()
    {
        System.out.println("*** Main menu ***");
        System.out.println("1 - Create order with open New Connect");
        System.out.println("2 - ...with open Trouble Call");
        System.out.println("3 - ...with open Service Call");
        System.out.println("4 - ...with provided WO number");
        System.out.println("0 - exit program");
    }

    public static int getOption()
    {
        int inNumber = -1;
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

        return inNumber;
    }

}
