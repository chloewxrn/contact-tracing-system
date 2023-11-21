import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestCustomerShop{
    public static void  main (String[] args) throws IOException{
       Scanner input = new Scanner (System.in);
       Customer customer = new Customer();
       ArrayList<Customer> customers = readRegisterFromFile();
       ArrayList<Customer> cus = readHistoryFromFile();
       ArrayList<Shop> shops = readShopFromFile();
      
      
     try{
       WelcomeMenu();
       int userType = input.nextInt();
       switch (userType){
       case 1 : {
           int choice;
           Boolean backToMainMenu = false;
           do{
               MainMenu();
               choice = input.nextInt();
               switch (choice){

            case 1: 
               Boolean phoneNumExisted = false;     
               System.out.println("\n\n                Register");
               System.out.println("------------------------------------------");
               System.out.println("\nPlease enter your details.");
               System.out.print  ("\nName (as per IC/Passport) : ");
               input.nextLine();
               String name = input.nextLine();
               System.out.print  ("\nPhone Number              : ");
               String phoneNum = input.nextLine();
               System.out.print  ("\nPassword                  : ");
               String password = input.nextLine();
               String status = "Normal";

               for(int i = 0; i < customers.size(); i++){
                   if (phoneNum.equals(customers.get(i).getPhoneNum())){
                   System.out.println("\nError: This phone number had been registered.\nPlease try again");
                   input.nextLine();
                   phoneNumExisted = true;   
                }backToMainMenu = true;
            }

                  if(phoneNumExisted == false){
                  customers.add (new Customer(name, phoneNum, password, status));
                  saveRegisterToFile(customers);
                  System.out.println("\nRegistration completed!");
                  System.out.println("Press ENTER to proceed to the Main Menu.");
                  input.nextLine();
                }backToMainMenu = true; 
            break;
                    
            case 2:
                {
                Boolean accExistance = false;
                int action;
                System.out.println("\n\n                  Log in");
                System.out.println("------------------------------------------");
                System.out.println("\nPlease enter your details as per registered.\n");
                System.out.print  ("Phone Number  : ");
                input.nextLine();
                phoneNum = input.nextLine();
                System.out.print  ("\nPassword      : ");
                password = input.nextLine();
                while(accExistance != true){
                for(int i = 0; i < customers.size(); i++){
                    if (phoneNum.equals(customers.get(i).getPhoneNum()) && password.equals(customers.get(i).getPassword())){
                    System.out.println("\nWelcome back, " + customers.get(i).getName() + "!");
                    input.nextLine();
                    accExistance = true;
                    int in = i;
                    System.out.println("Press ENTER to proceed to HOME.");
                    input.nextLine();
                    Boolean backToHome = false;
                    do{
                        subMenu();
                        action = input.nextInt();
                        switch(action){
                            case 1: {
                                checkIn();
                                int place   = input.nextInt();
                                name        = customers.get(in).getName();
                                phoneNum    = customers.get(in).getPhoneNum();
                                status      = customers.get(in).getStatus();
                                String date = customer.getDateCreated();
                                String time = customer.getTimeCreated();
                        
                                  if (place == 1){
                                   customers.get(in).setVisitedShopName("Tesco");
                                   String shop = customers.get(in).getVisitedShopName();
                                   cus.add (new Customer(name, phoneNum, status, date, time, shop));
                                   saveHistoryToFile(cus);
                                   System.out.println("\nYou have succesfully checked in at Tesco on "
                                                       + date + " " + time + ".");
                                   input.nextLine();
                                   System.out.println("Press ENTER to go back to HOME"); 
                                   input.nextLine();
                                   backToHome = true;}
                                        
                                  else if (place == 2){
                                   customers.get(in).setVisitedShopName("Aeon");
                                   String shop = customers.get(in).getVisitedShopName();
                                   cus.add (new Customer(name, phoneNum, status, date, time, shop));
                                   saveHistoryToFile(cus);
                                   System.out.println("\nYou have succesfully checked in at Aeon on " 
                                                       + date + " " + time + "."); 
                                   input.nextLine();
                                   System.out.println("Press ENTER to go back to HOME"); 
                                   input.nextLine();
                                   backToHome = true;}
                                             
                                  else if (place == 3){
                                   customers.get(in).setVisitedShopName("Starbucks");
                                   String shop = customers.get(in).getVisitedShopName();
                                   cus.add (new Customer(name, phoneNum, status, date, time, shop));
                                   saveHistoryToFile(cus);
                                   System.out.println("\nYou have succesfully checked in at Starbucks on " 
                                                       +  date + " " + time + ".");
                                   input.nextLine();
                                   System.out.println("Press ENTER to go back to HOME"); 
                                   input.nextLine();
                                   backToHome = true;}
                                                 
                                  else if (place == 4){
                                   customers.get(in).setVisitedShopName("Ikea");
                                   String shop = customers.get(in).getVisitedShopName();
                                   cus.add (new Customer(name, phoneNum, status, date, time, shop));
                                   saveHistoryToFile(cus);
                                   System.out.println("\nYou have succesfully checked in at Ikea on "
                                                      +  date + " " + time + ".");
                                   input.nextLine();
                                   System.out.println("Press ENTER to go back to HOME"); 
                                   input.nextLine();
                                   backToHome = true;}
                                   
                                  else if (place == 0)
                                   backToHome = true;
                            } break;   

                            case 2: {
                                   System.out.println ("\n\n                 History");
                                   System.out.println("------------------------------------------");
                                   System.out.println ("No \t Date \t\t Time \t\t Shop");
                                   int m = 0;
                                   for (int j = 0; j < cus.size(); j++){
                                   if(phoneNum.equals(cus.get(j).getPhoneNum() )) {
                                   System.out.println( (m+1) + "\t" + cus.get(j).getDateCreated() + "\t"
                                                        +  cus.get(j).getTime() + "\t"
                                                        + cus.get(j).getVisitedShopName() );m++;}}
                                   //input.nextLine();
                                   System.out.println("\nPress ENTER to go back to HOME");
                                   input.nextLine();
                                   input.nextLine();
                                   backToHome = true;
                            }break;

                            case 3: {                                       
                                   Boolean covidCase = false;
                                   Boolean covidClose = false;
                                   for (i=0; i < cus.size(); i++){   
                                    if(phoneNum.equals(cus.get(i).getPhoneNum()) && cus.get(i).getStatus().equals("Case")){
                                      covidCase = true;
                                    }

                                    else if (phoneNum.equals(cus.get(i).getPhoneNum()) && cus.get(i).getStatus().equals("Close")){
                                        covidClose = true;
                                    }
                                     
                                   }
                                    if (covidCase == true){
                                    System.out.println ("\n\n                 Risk Status  " ); 
                                    System.out.println("------------------------------------------");
                                    System.out.println ("\nYour risk status is CASE.");
                                    input.nextLine();
                                    System.out.println ("\nPress ENTER to go back to HOME");
                                    input.nextLine();
                                    } backToHome = true;

                                    if (covidCase == false && covidClose == true){
                                    System.out.println ("\n\n                 Risk Status  " ); 
                                    System.out.println("------------------------------------------");
                                    System.out.println ("\nYour risk status is CLOSE.");
                                    input.nextLine();
                                    System.out.println ("\nPress ENTER to go back to HOME");
                                    input.nextLine();
                                    } backToHome = true;
                                
                                    if(covidCase == false && covidClose == false){
                                    System.out.println ("\n\n                Risk Status " ); 
                                    System.out.println("------------------------------------------");
                                    System.out.println ("\nYour risk status is NORMAL." );
                                    //input.nextLine();
                                    System.out.println ("\nPress ENTER to go back to HOME");
                                    input.nextLine();
                                    input.nextLine();
                                    }backToHome = true;
                                
                            }break;
                                              
                            case 0: {
                                   System.out.println ("\nDo you sure you want to log out?");
                                   System.out.println ("\n1. Yes");
                                   System.out.println ("2. No");
                                   System.out.print   ("\nPlease enter your choice : ");
                                   int logout = input.nextInt();

                                   if (logout == 1){
                                     System.out.println ("\nYour account has been successfully logged out.");
                                     System.out.println ("\nPlease take care of your health.");
                                     System.out.println ("Goodbye\n");
                                     System.exit(0);}
                                        
                                   else if (logout == 2){
                                     System.out.println("Press ENTER to go back to home.");
                                     input.nextLine();
                                     backToHome = true;}  
                            }break;

                            default: { 
                                     System.out.println("Invalid choice. Please try again.");
                                     System.out.println("Press ENTER to go back to HOME"); 
                                     input.nextLine();
                                     backToHome = true; 
                            }break;
                        }
                    } while(backToHome == true);                         
            }
         }       
                if (accExistance != true){
                   System.out.println("\nError: User not found!");
                   System.out.println("Please ensure that you have entered the correct phone number and password. ");
                   System.out.println("Please try again.");
                   input.nextLine();
                   System.out.print  ("Phone Number  : ");
                   phoneNum = input.nextLine();
                   System.out.print  ("\nPassword      : ");
                   password = input.nextLine();

            }
        }
        }break;
                
            case 0: {

                exit();
                int exit = input.nextInt();
                if (exit == 1){
                  System.out.println("\nSystem successfully exited.");
                  System.out.println("Please take care of your health.");
                  System.out.println("Goodbye.\n");
                  System.exit(0);}
            
                else if (exit == 2){
                  System.out.println("Press ENTER to go back to the Main Menu.");
                  input.nextLine();
                  backToMainMenu = true;}
        }break;
     
                default: System.out.println("\nError : Invalid choice!");break;
          }   
        } while(backToMainMenu == true);
    }break;

      case 2 : {
            Boolean shopExistance = false;
            System.out.println("\nPlease enter your shop's name. ");
            System.out.print  ("\nShop's name     : ");
            input.nextLine();
            String shopName = input.nextLine();
            while(shopExistance == false){
              for (int i = 0; i < shops.size(); i++){
                if (shopName.equals(shops.get(i).getShopName())){
                  shopExistance = true;
                  System.out.println("\nThe risk status of " + shops.get(i).getShopName() + " is "
                                      + shops.get(i).getShopStatus() + ".");
                  System.out.println("\nPress ENTER to exit the system.");
                  input.nextLine();
                  System.out.println("System successfully exited.");
                  System.out.println("Goodbye.");
                  System.exit(0);}
              }
                if (shopExistance != true){
                  System.out.println ("\nError : Please ensure that you've entered the correct name!");
                  input.nextLine();
                  System.out.println ("Please try again.");
                  input.nextLine();
                  System.out.print   ("Shop's name     : ");
                  shopName = input.nextLine();}
             } 
            }break;
            
       default : System.out.println("\nError : Invalid choice! ");  break;      
    }
 }

 catch (InputMismatchException ex){
     System.out.println("\nError : Input must be numbers only!");
 }
       
 }
 
static void WelcomeMenu(){
    System.out.println("\n\nWelcome to the Covid-19 Contact Tracing System! \n");
    System.out.println("You are...\n");
    System.out.println("1. Customer");
    System.out.println("2. Shop");
    System.out.print  ("\nPlease enter your choice (1..2) : ");

}

static void MainMenu() throws IOException{
       System.out.println("\n\n                Main Menu");
       System.out.println("------------------------------------------");
       System.out.println("1. Register");
       System.out.println("2. Log in");
       System.out.println("0. Exit");
       System.out.print  ("\nPlease enter your choice (0..2): ");
}

static void subMenu() throws IOException{
    System.out.println("\n\n                   Home");
    System.out.println("------------------------------------------");
    System.out.println("1. Check-in");
    System.out.println("2. History");
    System.out.println("3. Status");
    System.out.println("0. Log out");
    System.out.print  ("\nPlease enter your choice (0..3): ");
}

static void checkIn() throws IOException{
    System.out.println("\n\n                 Check In ");
    System.out.println("------------------------------------------");
    System.out.println("1. Tesco");
    System.out.println("2. Aeon");
    System.out.println("3. Starbucks");
    System.out.println("4. Ikea");
    System.out.println("0. Back to Home");
    System.out.print  ("\nPlease enter your choice (0..4): ");
 
}


static void exit() throws IOException{
    System.out.println ("\n\nDo you sure you want to exit?");
    System.out.println ("\n1. Yes");
    System.out.println ("2. No");
    System.out.print   ("\nPlease enter your choice (1..2): ");
}

private static ArrayList<Customer> readRegisterFromFile() throws IOException{
    ArrayList<Customer> customers = new ArrayList<>();
    List<String> lines = Files.readAllLines(Paths.get("Register.csv"));
    for (int i = 0; i < lines.size(); i++){
        String[] items = lines.get(i).split(",");
        customers.add (new Customer(items[0], items[1], items[2], items[3]));
    }
    return customers;
}

private static ArrayList<Customer> readHistoryFromFile() throws IOException{
    ArrayList<Customer> customers = new ArrayList<>();
    List<String> lines = Files.readAllLines(Paths.get("Customers.csv"));
    for (int i = 0; i < lines.size(); i++){
        String[] items = lines.get(i).split(",");
        customers.add (new Customer(items[0], items[1], items[2], items[3], items[4], items[5]));
    }
    return customers;
}


private static void saveRegisterToFile (ArrayList<Customer> customers) throws IOException{
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < customers.size(); i++ ){
        sb.append (customers.get(i). toCSVString() + "\n");
    }
    Files.write(Paths.get("Register.csv") , sb.toString().getBytes());
}

private static void saveHistoryToFile (ArrayList<Customer> customers) throws IOException{
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < customers.size(); i++ ){
        sb.append (customers.get(i). toCSVStringCustomer() + "\n");
    }
    Files.write(Paths.get("Customers.csv") , sb.toString().getBytes());
}

private static ArrayList<Shop> readShopFromFile() throws IOException {
    ArrayList<Shop> shops = new ArrayList<>();
    List<String> lines = Files.readAllLines(Paths.get("Shops.csv"));
    for (int i = 0; i < lines.size(); i++){
        String[] items = lines.get(i).split(",");
        shops.add (new Shop(items[0], items[1], items[2], items[3]));
    }
    return shops;
}
}


