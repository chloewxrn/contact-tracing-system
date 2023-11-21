import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.*;

public class Admin extends User{
    private String username;
    private String password;
    public Admin(){};
    public Admin(String username, String password, String name) {
        super(name);
        this.username = username;
        this.password = password;
    }
    public static ArrayList<MasterVisitHistory> readHistoryFromFile() throws IOException {
        ArrayList<MasterVisitHistory> visitHistoryList  = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("Customers.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            LocalDate date = LocalDate.parse(items[3]);
            LocalTime time = LocalTime.parse(items[4]);
            visitHistoryList.add (new MasterVisitHistory(date, time, items[0],items[5], items[2]));
        }
        return visitHistoryList;
    }

    public static ArrayList<Customer> viewCustomerList(String shop, ArrayList<Customer> customerList) throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Customers.csv"));
        if (shop.equals("")) {
          for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            customers.add (new Customer(items[0], items[1], items[2]));
          }
        }
        else {
          for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
              if (items[5].equals(shop))
                customers.add (new Customer(items[0], items[1], items[2], items[3], items[4]));
          }
          for (int i = 0; i < customers.size() - 1; i++) {
            for (int j = i + 1; j < customers.size(); j++) {
                if (!customers.get(j).name.equals(customers.get(i).name))
                    continue;
                else {
                    if (customers.get(i).status.equalsIgnoreCase("Case") || customers.get(j).status.equalsIgnoreCase("Case")) 
                        customers.get(i).status = "Case";
                    else if (customers.get(i).status.equalsIgnoreCase("Close") || customers.get(j).status.equalsIgnoreCase("Close"))
                        customers.get(i).status = "Close";
                    customers.remove(j);
                    j--;
                }
            } 
          }
        }
        return customers; 
    }
    public static ArrayList<Shop> viewShopList() throws IOException {
        ArrayList<Shop> shops = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Shops.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            shops.add (new Shop(items[0], items[1], items[2], items[3]));
        }
        return shops;
    }
    public static void changeStatus (int custIndex, ArrayList<MasterVisitHistory> historyList, ArrayList<Shop> shopList) throws IOException {
        MasterVisitHistory flaggedCustomer = historyList.get(custIndex-1);
        flaggedCustomer.setStatus("Case");
        for (int j = 0; j < historyList.size(); j++) {
            Period diff = Period.between(flaggedCustomer.getDate(), historyList.get(j).getDate());
            if (flaggedCustomer.getCustomer().equals(historyList.get(j).getCustomer()) 
                && flaggedCustomer.getDateTime().compareTo(historyList.get(j).getDateTime()) <= 0) {
                if (flaggedCustomer.getDateTime().compareTo(historyList.get(j).getDateTime()) < 0) {
                    if (diff.getMonths() > 0 || diff.getDays() >= 1)
                        continue;
                }
                historyList.get(j).setStatus("Case");
                for (int m = 0; m < historyList.size(); m++) {
                if (historyList.get(j).getShop().equals(historyList.get(m).getShop())
                    && historyList.get(j).getDate().isEqual(historyList.get(m).getDate())) {
                    if ((historyList.get(j).getTime().getHour() - historyList.get(m).getTime().getHour() == 1 && 
                        historyList.get(m).getTime().getMinute() - historyList.get(j).getTime().getMinute() >= 0) ||
                        (historyList.get(m).getTime().getHour() - historyList.get(j).getTime().getHour() == 1 && 
                        historyList.get(j).getTime().getMinute() - historyList.get(m).getTime().getMinute() >= 0) ||
                        (historyList.get(m).getTime().getHour() - historyList.get(j).getTime().getHour() == 0)) {
                        if  (historyList.get(j).getCustomer().equals(historyList.get(m).getCustomer()))
                            continue;
                        else
                            historyList.get(m).setStatus("Close");
                        }
                }
            } 
            } 
        }
    
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < shopList.size(); k++) {
            for (int i = 0; i < historyList.size(); i++) {
                if ( historyList.get(i).getStatus().equals("Case") && shopList.get(k).name.equals(historyList.get(i).getShop()))
                        shopList.get(k).status = "Case";
            }
            sb.append (shopList.get(k).toCSVString() + "\n");
        }
        Files.write(Paths.get("Shops.csv"), sb.toString().getBytes());

        StringBuilder sb1 = new StringBuilder();
        ArrayList<Customer> customers = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Customers.csv"));
        for (int k = 0; k < lines.size(); k++) {
            String[] items = lines.get(k).split(",");
            LocalTime time = LocalTime.parse(items[4]);
            for (int i = 0; i < historyList.size(); i++) {
                if (historyList.get(i).getCustomer().equals(items[0]) && historyList.get(i).getShop().equals(items[5])
                    && historyList.get(i).getTime().equals(time)) {
                    if (historyList.get(i).getStatus().equalsIgnoreCase("Case"))
                        items[2] = "Case";
                    else if (historyList.get(i).getStatus().equalsIgnoreCase("Close"))
                        items[2] = "Close";
                    else if (historyList.get(i).getStatus().equalsIgnoreCase("Normal"))
                        items[2] = "Normal";
                }
            }
            customers.add (new Customer(items[0], items[1], items[2], items[3], items[4], items[5]));
            sb1.append (customers.get(k).toCSVStringCustomer() + "\n");
        }
        Files.write(Paths.get("Customers.csv"), sb1.toString().getBytes());
    }

    public static ArrayList<MasterVisitHistory> addRandom(ArrayList<Customer> randomCustomerList, ArrayList<Shop> shopList) throws IOException {
        ArrayList<MasterVisitHistory> randomVisitHistory = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Customer randomCustomer = randomCustomerList.get(random.nextInt(randomCustomerList.size()));
            Shop randomShop = shopList.get(random.nextInt(shopList.size()));
            randomVisitHistory.add(new MasterVisitHistory(randomCustomer.name, randomShop.name));
            sb.append (randomCustomer.name + "," + randomCustomer.getPhoneNum() + ",Normal," + randomVisitHistory.get(i).toCSVString() + "\n");
        }
        String file = "Customers.csv";
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        //bw.write("\n");
        bw.write(sb.toString());
        bw.close();
        return randomVisitHistory;
    }
    public boolean logIn(String password, String username) {
        return (this.password.equals(password) && this.username.equals(username));
    }
    @Override
    public String toString() {
        return name + " " + username + " " + password;
    }
}