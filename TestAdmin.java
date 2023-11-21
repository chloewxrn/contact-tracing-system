import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestAdmin {
    public static void main (String[] args) throws IOException {
        Admin[] admins = new Admin[2];
        admins[0] = new Admin("chin", "123", "Chin Pei Wern");
        admins[1] = new Admin("ong", "456", "Ong Jia Xuan");
        ArrayList<Shop> shopsList = new ArrayList<>();
        shopsList = Admin.viewShopList();
        ArrayList<MasterVisitHistory> masterVisitHistoryList = new ArrayList<>();
        masterVisitHistoryList = Admin.readHistoryFromFile();
        logIn(admins);
        int index;
        do {
            index = menu(); 
            if (index == 1)
                displayCustomerList(shopsList);
            else if (index == 2)
                displayShopList(shopsList);
            else if (index == 3)
                displayMasterVisitHistory(masterVisitHistoryList,shopsList);
            else if (index == 4)
                addRandomVisitHistory(masterVisitHistoryList, shopsList); 
            else if (index == 5) {
                System.out.println("You have successfully logged out!");
            }
        } while (index != 5 );       
    }
    public static int menu() {
        Scanner input = new Scanner (System.in);
        System.out.println ("Please choose an option: ");
        System.out.println ("1. List of customers \n2. List of shops \n3. Master visit history" +
                            " \n4. Add 30 random visits to master visit history" + " \n5. Log out");
        int index = input.nextInt();
        while (index < 1 || index > 5) {
            System.out.println ("Invalid input! Enter between the range of 1-5");
            index = input.nextInt();
        }
        return index;
    }
    public static void displayCustomerList(ArrayList<Shop> shopsList) throws IOException{
        Scanner input = new Scanner (System.in);
        System.out.println ("Please choose a shop to display list of customers for");
        for (int i = 0; i < shopsList.size(); i++) {
            Shop shops = shopsList.get(i);
            System.out.println (i+1 + ". "+ shops.name);
        }
        int shopIndex = input.nextInt();
        while (shopIndex < 1 || shopIndex > shopsList.size()) {
            System.out.println ("Error! Please choose from 1 to 4");
            shopIndex = input.nextInt();
        }
        ArrayList<Customer> customers = new ArrayList<>();
        for (int j = 0; j < shopsList.size(); j++) {
        if ( j+1 == shopIndex) 
            customers = Admin.viewCustomerList(shopsList.get(j).name, customers);
        }
        System.out.println ("List of Customers");
        System.out.printf ("%-3s %-10s %-11s Status\n", "No", "Name", "Phone");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf ("%2d. ", i+1);
            System.out.println (customers.get(i));
        }
        promptEnterKey();
    }
    public static void displayShopList(ArrayList<Shop> shopsList) throws IOException{
        System.out.println ("List of Shops");
        System.out.printf (" No %-10s %-11s Manager Status\n", "Name", "Phone");
        for (int i = 0; i < shopsList.size(); i++) {
            System.out.printf ("%2d. ", i+1);
            System.out.println (shopsList.get(i));
        }
        promptEnterKey();
    }
    public static void displayMasterVisitHistory(ArrayList<MasterVisitHistory> masterVisitHistoryList, ArrayList<Shop> shopList) throws IOException {
        Scanner input = new Scanner (System.in);
        masterVisitHistoryList.sort((o1,o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        System.out.println ("Master visit history: ");
        System.out.printf ("%3s %-10s  %-8s Customer   Shop\n","No", "Date", "Time");
        for (int i = 0; i < masterVisitHistoryList.size(); i++) {
            System.out.printf ("%2d. ", i+1);
            System.out.println (masterVisitHistoryList.get(i));
        }
        System.out.println ("Is there a customer you wish to flag? (Y/N)");
        String option = input.next();
        while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")) {
            System.out.println("Invalid input! Please re-enter");
            option = input.next();
        }
        if (option.equalsIgnoreCase("Y")) {
            System.out.println ("Please enter the index of the customer: ");
            int custIndex = input.nextInt();
            while (custIndex > masterVisitHistoryList.size() || custIndex < 1) {
                System.out.println("Invalid index. Please re-enter");
                custIndex = input.nextInt();
            }
            Admin.changeStatus(custIndex, masterVisitHistoryList, shopList);
            System.out.println ("Status changed successfully!");
        }
        promptEnterKey();
    }
    public static void addRandomVisitHistory(ArrayList<MasterVisitHistory> masterVisitHistoryList, ArrayList<Shop> shopList) throws IOException {
        ArrayList<MasterVisitHistory> randomVisitHistory = new ArrayList<>();
        ArrayList<Customer> randomCustomers = new ArrayList<>();
        randomCustomers = Admin.viewCustomerList("", randomCustomers);
        randomVisitHistory = Admin.addRandom(randomCustomers, shopList);
        masterVisitHistoryList.addAll(randomVisitHistory);
        masterVisitHistoryList.sort((o1,o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        System.out.println("30 random visits are successfully added into Master Visit History!");
        promptEnterKey();
    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue");
        Scanner input = new Scanner(System.in);
        input.nextLine();
     }
    public static void logIn(Admin[] admins) {
        Scanner input = new Scanner (System.in);
        System.out.println ("Hello. Welcome, admin!");
        System.out.print ("Please enter your username: ");
        String username = input.nextLine();
        System.out.print ("Please enter your password: ");
        String password = input.nextLine();
        boolean flag = false;
        while (flag == false) {
            for (int i = 0; i < admins.length; i++) {
                if (admins[i].logIn(password, username)) {
                    flag = true;
                    System.out.println ("Log in success!");
                    System.out.println ("Welcome, " + admins[i].getName());
                }
            }
            if (flag != true) {
                System.out.println ("Error! Username/Password incorrect.");
                System.out.print ("Please re-enter your username: ");
                username = input.nextLine();
                System.out.print ("Please re-enter your password: ");
                password = input.nextLine();
            }
        }
    }
}