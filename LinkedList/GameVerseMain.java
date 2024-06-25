import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;


public class GameVerseMain 
{
    public static void main(String [] args)
    {
        try
        {
        Scanner scan = new Scanner(System.in);

            DecimalFormat df = new DecimalFormat("0.00");
            //DecimalFormat dt = new DecimalFormat("0.00");

            FileReader fr = new FileReader("gvData.txt");
            BufferedReader br = new BufferedReader(fr);

            LinkedList gameVerseList = new LinkedList();

            String cusID, cusName, paymentType, platformType, platformID, numPhone, dateBook, sub, pStatus;
            int duration;
            String inData = null;


            while((inData = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(inData, ";");

                cusID = st.nextToken();
                cusName = st.nextToken();
                paymentType = st.nextToken();
                platformType =  st.nextToken();               
                platformID = st.nextToken();
                numPhone = st.nextToken();
                duration = Integer.parseInt(st.nextToken());
                dateBook = st.nextToken();
                sub = st.nextToken();
                pStatus = st.nextToken();
                

                GameVerse game = new GameVerse(cusID, cusName, paymentType, platformType, platformID, numPhone, duration, dateBook, sub, pStatus);
                gameVerseList.insertAtFront(game);
            }
            br.close();

            GameVerse temp = null;

            temp = gameVerseList.getFirst();

            System.out.println("====================================================================================================================================================================");
            System.out.println("                                                                  GAMEVERSE APPLICATION SYSTEM                                                               ");
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
            System.out.println("====================================================================================================================================================================");
            while(temp != null)
            {
                System.out.println(temp.toString());
                temp = gameVerseList.getNext();
            }
            System.out.println("====================================================================================================================================================================");
    

            
            //2) SEARCH AND DISPLAY THE CUSTOMER'S DETAIL
            System.out.println(" ");
            System.out.print("Please enter customer's ID that you want to change the date >> ");
            String cID = scan.next();

            GameVerse obj = null;
            boolean found = false;

            obj = gameVerseList.getFirst();
            while(obj != null && !found)
            {
                if(obj.getCustomerID().equalsIgnoreCase(cID))
                {
                    found = true;
                    System.out.println("\n");
                    System.out.println("\n====================================================================================================================================================================");
                    System.out.println("                                                                     CUSTOMER'S DETAILS                                                                             ");
                    System.out.println("====================================================================================================================================================================");
                    System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                    "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
                    System.out.println("====================================================================================================================================================================");
                    System.out.println(obj.toString());
                    System.out.println("====================================================================================================================================================================");
                }
                else 
                    obj = gameVerseList.getNext();
            }

            //3)Update user's choice of date
            if(found)
            {
                System.out.print("\nWhen do you want to change the date? >>");
                String nDate = scan.next();
                obj.setDate(nDate);
                gameVerseList.insertAtBack(obj);
                gameVerseList.removeFromBack();
            }
            else 
                System.out.println("The id you search are not available in the database");
            
            System.out.println("\n");
            System.out.println(" ");
            System.out.println("************************************************************************UPDATED LIST********************************************************************************");
            System.out.println("====================================================================================================================================================================");
            System.out.println("                                                                  GAMEVERSE APPLICATION SYSTEM                                                               ");
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
            System.out.println("====================================================================================================================================================================");
            obj = gameVerseList.getFirst();
            while(obj != null)
            {
                System.out.println(obj.toString());
                obj = gameVerseList.getNext();
            }
            System.out.println("====================================================================================================================================================================");

            //4) SPLIT AND DISPLAY
            LinkedList gvPCGaming = new LinkedList();
            LinkedList gvConsole = new LinkedList();

            temp = gameVerseList.getFirst();

            while(temp != null)
            {
                if(temp.getPlatformType().equalsIgnoreCase("PC Gaming"))
                {
                    gvPCGaming.insertAtFront(temp);
                }
                else
                {
                    gvConsole.insertAtFront(temp);
                }

                temp = gameVerseList.getNext();
            }

            System.out.println("\n");
            System.out.println("====================================================================================================================================================================");
            System.out.println("                                          GAMEVERSE APPLICATION SYSTEM(CUSTOMER WHO CHOOSE PC GAMING)                                                              ");
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-15s%-15s",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION"));

            temp = gvPCGaming.getFirst();

            while(temp != null)
            {
                System.out.println(temp.toString());
                temp = gvPCGaming.getNext();
            }
            System.out.println("====================================================================================================================================================================");
            
            temp = gvConsole.getFirst();

            System.out.println("\n");
            System.out.println("====================================================================================================================================================================");
            System.out.println("                                          GAMEVERSE APPLICATION SYSTEM(CUSTOMER WHO CHOOSE CONSOLE)                                                              ");
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-15s%-15s",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION"));

            while(temp != null)
            {
                System.out.println(temp.toString());
                temp = gvConsole.getNext();
            }
            // 5) Calculate and display the total of payment for each category which is PC gaming and consoles which the payment method using cash.
            double totPaymentPC = 0.0, totPaymentCons = 0.0;
            temp = gameVerseList.getFirst();
            while (temp != null) 
            {
                if (temp.getPaymentType().equalsIgnoreCase("Cash") && temp.getPlatformType().equalsIgnoreCase("PC Gaming")) 
                {
                    totPaymentPC += temp.calculatePrice();
                }
                else if (temp.getPaymentType().equalsIgnoreCase("Cash") && temp.getPlatformType().equalsIgnoreCase("Console"))
                {
                    totPaymentCons += temp.calculatePrice();
                }
                temp = gameVerseList.getNext();
            }
            System.out.println("====================================================================================================================================================================");
       
            System.out.println("\n");
            System.out.println("\n=============================================================");
            System.out.println("               TOTAL PAYMENT FOR EACH PLATFORM                 ");
            System.out.println("=============================================================");
            System.out.println("TOTAL PAYMENT FOR PC GAMING: RM " + df.format(totPaymentPC));
            System.out.println("TOTAL PAYMENT FOR CONSOLES: RM " + df.format(totPaymentCons));
            System.out.println("=============================================================");
            
            
            // 6) Remove the record of the customer that does not subscribe the GameVerse monthly subscription and store the data into new LinkedList called gvNonSubscribe. 
            LinkedList gvNonSubscribe = new LinkedList();
            temp = gameVerseList.getFirst();
            while(!gameVerseList.isEmpty())
            {
                temp = gameVerseList.removeFromBack();

                if(temp.getSubscription().equalsIgnoreCase("No"))
                {
                    gvNonSubscribe.insertAtBack(temp);
                }
            }

            System.out.println("\n");
            System.out.println("\n====================================================================================================================================================================");
            System.out.println("                                                                  GAMEVERSE APPLICATION SYSTEM(NON-SUBSCRIBER)                                                              ");
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
            System.out.println("====================================================================================================================================================================");
            temp = gvNonSubscribe.getFirst();
            while (temp != null) 
            {
                System.out.println(temp.toString());
                temp = gvNonSubscribe.getNext();
            }
            System.out.println("====================================================================================================================================================================");

            scan.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

