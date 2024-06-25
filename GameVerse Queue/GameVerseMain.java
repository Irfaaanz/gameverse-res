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
            DecimalFormat dt = new DecimalFormat("0.00");

            FileReader fr = new FileReader("gvData.txt");
            BufferedReader br = new BufferedReader(fr);

            Queue gameVerseQueue = new Queue();

            String cusID, cusName, paymentType, platformType, platformID, numPhone, dateBook, sub, pStatus;
            int duration;
            String inData = null;

            //1) STORE AND DISPLAY 
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
                gameVerseQueue.enqueue(game);
            }
            br.close();

            GameVerse temp = null;

            Queue tempQ = new Queue();

            //DISPLAY ALL DATA
            System.out.println("\n");

            System.out.println("=====================================================================================================================================================================");
            System.out.println("                                                                  GAMEVERSE APPLICATION SYSTEM                                                               ");
            System.out.println("=====================================================================================================================================================================");
            System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                                "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
            System.out.println("=====================================================================================================================================================================");

            while(!gameVerseQueue.isEmpty())
            {
                temp = (GameVerse)gameVerseQueue.dequeue();

                System.out.println(temp.toString());

                tempQ.enqueue(temp);
            }

            System.out.println("=====================================================================================================================================================================");

            //RESTORE GAMEVERSEQUEUE

            while(!tempQ.isEmpty())
            {
                gameVerseQueue.enqueue(tempQ.dequeue());
            }


            //2)SEARCH AND DISPLAY THE CUSTOMER'S DETAIL
            System.out.println(" ");
            System.out.print("Please enter customer's ID that you want to change the date >> ");
            String cID = scan.next();

            boolean found = false;

            String iDate = " ";

            //&& !found
            while(!gameVerseQueue.isEmpty())
            {
                temp = (GameVerse)gameVerseQueue.dequeue();

                if(temp.getCustomerID().equalsIgnoreCase(cID))
                {
                    found = true;
                    System.out.println("\n");

                    System.out.println("=====================================================================================================================================================================");
                    System.out.println("                                                                  CUSTOMER'S DETAILS                                                               ");
                    System.out.println("=====================================================================================================================================================================");
                    System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                                        "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
                    System.out.println("=====================================================================================================================================================================");
                    System.out.println(temp.toString());
                    System.out.println("=====================================================================================================================================================================");

                    //3) UPDATE CUSTOMER'S DATE BOOKED
                    System.out.print("When do you want to change the date >>");
                    iDate = scan.next();
                    GameVerse gv = new GameVerse(temp.getCustomerID(), temp.getCustomerName(), temp.getPaymentType(), temp.getPlatformType(),temp.getPlatformID(), temp.getPhoneNum(), temp.getDuration(), iDate, temp.getSubscription(), temp.getPaymentStatus());

                    tempQ.enqueue(gv);
                }
                else
                    tempQ.enqueue(temp);
            }

            
            //RESTORE
            while(!tempQ.isEmpty())
            {
                gameVerseQueue.enqueue(tempQ.dequeue());
            }

            if(!found)
                System.out.println("THE ID YOU SEARCH DOES NOT EXIST IN THE DATABASE");
            else 
                while(!gameVerseQueue.isEmpty())
                {
                    temp = (GameVerse)gameVerseQueue.dequeue();
                    if(temp.getCustomerID().equalsIgnoreCase(cID) && temp.getDate().equalsIgnoreCase(iDate));
                        tempQ.enqueue(temp);
                    
                }
                

                while(!tempQ.isEmpty())
            {
                gameVerseQueue.enqueue(tempQ.dequeue());
            }

            System.out.println("-------UPDATED-------");
            while(!gameVerseQueue.isEmpty())
            {
                temp = (GameVerse)gameVerseQueue.dequeue();

                System.out.println(temp.toString());

                tempQ.enqueue(temp);
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            //restore
            while(!tempQ.isEmpty())
            {
                gameVerseQueue.enqueue(tempQ.dequeue());
            }

            //4) SPLIT TO PC GAMING AND CONSOLE

            
            Queue gvPCGaming = new Queue();
            Queue gvConsole = new Queue();

           while(!gameVerseQueue.isEmpty())
           {
                temp = (GameVerse)gameVerseQueue.dequeue();

                if(temp.getPlatformType().equalsIgnoreCase("PC Gaming"))
                {
                    gvPCGaming.enqueue(temp);
                }
                else
                {
                    gvConsole.enqueue(temp);
                }

                tempQ.enqueue(temp);
           }

           //RESTORE GAMEVERSEQUEUE
           while(tempQ.isEmpty())
           {
               gameVerseQueue.enqueue(tempQ.dequeue());
           }

           //DISPLAY GVPCGAMING
           Queue PCTemp = new Queue();
           System.out.println("\n");
           System.out.println("===================================================================================================================================================================");
           System.out.println("CUSTOMER WHO CHOOSE PC GAMING");
           System.out.println("===================================================================================================================================================================");
           System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
           "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
           System.out.println("===================================================================================================================================================================");


           while(!gvPCGaming.isEmpty())
           {
                temp = (GameVerse)gvPCGaming.dequeue();

                System.out.println(temp.toString());

                PCTemp.enqueue(temp);
           }

           System.out.println("====================================================================================================================================================================");

           //RESTORE GVPCGAMING

		while(!PCTemp.isEmpty())
           {
               gvPCGaming.enqueue(PCTemp.dequeue());
           }

           //DISPLAY GVCONSOLE
           Queue ConTemp = new Queue();
           System.out.println("\n");

           System.out.println("====================================================================================================================================================================");
           System.out.println("CUSTOMER WHO CHOOSE CONSOLE");
           System.out.println("====================================================================================================================================================================");
           System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
           "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
           System.out.println("====================================================================================================================================================================");


            while(!gvConsole.isEmpty())
            {
                temp = (GameVerse)gvConsole.dequeue();

                System.out.println(temp.toString());

                ConTemp.enqueue(temp);
            }

           System.out.println("====================================================================================================================================================================");

           //DISPLAY GVCONSOLE

           while(!ConTemp.isEmpty())
           {
               gvConsole.enqueue(ConTemp.dequeue());
           }


           /*5)Calculate and display the total of payment for each category which is PC gaming and 
           consoles which the payment method using cash.*/
           double totPaymentPC = 0.0, totPaymentCons = 0.0;
           while (!gvPCGaming.isEmpty()) 
           {
               temp = (GameVerse)gvPCGaming.dequeue(); // retrieve
               
               if (temp.getPaymentType().equalsIgnoreCase("Cash") && temp.getPlatformType().equalsIgnoreCase("PC Gaming"))
                   totPaymentPC += temp.calculatePrice();

               PCTemp.enqueue(temp);
           }

           while(!PCTemp.isEmpty())
           {
               gvPCGaming.enqueue(PCTemp.dequeue());
           }

           while (!gvConsole.isEmpty()) 
           {
               temp = (GameVerse)gvConsole.dequeue(); // retrieve
               
               if (temp.getPaymentType().equalsIgnoreCase("Cash"))
                   totPaymentCons += temp.calculatePrice();

               PCTemp.enqueue(temp);
           }
           
           while(!ConTemp.isEmpty())
           {
               gvConsole.enqueue(ConTemp.dequeue());
           }

           System.out.println("TOTAL PAYMENT FOR PC GAMING: RM" + df.format(totPaymentPC));
           System.out.println("TOTAL PAYMENT FOR CONSOLES: RM" + df.format(totPaymentCons));

           //6) Remove the record of the customer that does not subscribe the GameVerse monthly subscription and store the data into new LinkedList called gvNonSubscribe. 
           Queue gvNonSubscribe = new Queue();
           while(!gvPCGaming.isEmpty())
           {
               temp = (GameVerse)gvPCGaming.dequeue();

               if(temp.getSubscription().equalsIgnoreCase("No"))
               {
                   gvNonSubscribe.enqueue(temp);
               }
               else
                   PCTemp.enqueue(temp);
           }

           while (!PCTemp.isEmpty()) 
           {
               gvPCGaming.enqueue(PCTemp.dequeue());
           }

           while(!gvConsole.isEmpty())
           {
               temp = (GameVerse)gvConsole.dequeue();

               if(temp.getSubscription().equalsIgnoreCase("No"))
               {
                   gvNonSubscribe.enqueue(temp);
               }
               else 
                   ConTemp.enqueue(temp);
           }

           while (!ConTemp.isEmpty()) 
           {
               gvConsole.enqueue(ConTemp.dequeue());
           }
           
           Queue nonTemp = new Queue();
           System.out.println("\n");

           System.out.println("\n====================================================================================================================================================================");
           System.out.println("                                                                  GAMEVERSE APPLICATION SYSTEM(NON-SUBSCRIBER)                                                              ");
           System.out.println("====================================================================================================================================================================");
           System.out.println(String.format("%-18s%-26s%-18s%-15s%-18s%-15s%-15s%-10s%-15S%-10S",
                                               "CUSTOMER ID", "CUSTOMER NAME", "PAYMENT TYPE", "PLATFORM TYPE", "PLATFORM ID", "NO PHONE", "DURATION", "DATE", "SUBSCRIPTION", "PAYMENT STATUS"));
           System.out.println("====================================================================================================================================================================");
           while (!gvNonSubscribe.isEmpty()) 
           {
               temp = (GameVerse)gvNonSubscribe.dequeue();
               System.out.println(temp.toString());
               nonTemp.enqueue(temp);
           
           }

           while (!gvNonSubscribe.isEmpty()) 
            {
                gvNonSubscribe.enqueue(nonTemp.dequeue());
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
