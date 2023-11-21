import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat; 
import java.util.Date;

    /**
     * This class represents a customer.
     */
public class Customer extends User {

    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private String phoneNum;
    private String password;
    private String shop;
    private String date = df.format(ldt);
    private String time;

    /**
     *  Represents the risk status of customer
     */
    protected String status;
   
    /**
     *  Contructs a customer with name, phone number, password, shop, date, time and risk status.
     */
    public Customer(){}

    /**
     *  Constructs a customer with the specified name, phone number, risk status, date, time and name of shop.
     *  @param name The name of customer.
     *  @param phoneNum The phone number of customer.
     *  @param status The risk status of customer.
     *  @param date The date of customer visiting shops.
     *  @param time The time of customer visiting shops.
     *  @param shop The name of shop that visited by customer.
     */
    public Customer (String name, String phoneNum, String status, String date, String time, String shop){
        super(name);
        this.phoneNum = phoneNum;
        this.status = status;
        this.date = date;
        this.time = time;
        this.shop = shop;
    }

    /**
     * Constructs a customer with the specified name, phone number, risk status, date and time. 
     * @param name The name of customer.
     * @param phoneNum The phone number of customer.
     * @param status The risk status of customer.
     * @param date The date of customer visiting shops.
     * @param time The time of customer visiting shops.
     */
    public Customer (String name, String phoneNum, String status, String date, String time){
        super(name);
        this.phoneNum = phoneNum;
        this.status = status;
        this.date = date;
        this.time = time;

    }

    /**
     *  Constructs a customer with the specified name, phone number, password and risk status.
     *  @param name The name of customer.
     *  @param phoneNum The phone number of customer.
     *  @param password The password of customer.
     *  @param status The risk status of customer.
     */
    public Customer (String name, String phoneNum, String password, String status){
        super(name);
        this.phoneNum = phoneNum;
        this.password = password;
        this.status = status;

    }

    /**
     * Constructs a customer with specified name, phone number and risk status.
     * @param name The name of customer.
     * @param phoneNum The phone number of customer.
     * @param status The risk status of customer.
     */
    public Customer (String name, String phoneNum, String status){
        super(name);
        this.phoneNum = phoneNum;
        this.status = status;
    }

    /**
     * Returns the name, phone number and risk status of customer.
     * @return String representing the name, phone number and risk status of customer.
     */
    public String toString(){
        return String.format("%-10s",name) + " " + String.format("%-11s",phoneNum) + " " + status ;
    }

    /**
     * Returns the customer's name, phone number, password and risk status.
     * @return String representing the name, phone number, password and risk status of customer.
     */
    public String toCSVString(){
        return name + "," + phoneNum + "," + password + "," + status;
    }

    /**
     *  Returns the customer's name, phone number, date, time and visited shop's name.
     *  @return String representing the name, phone number, password and risk status of customer.
     */
    public String toCSVStringCustomer(){
        return name + "," + phoneNum + "," + status + "," + date + "," + time + "," + shop;
    }
    
    /**
     *  Sets the name of shop that visited by customer.
     *  @param shop A String containing the name of shop visited by customer.
     */
    public void setVisitedShopName(String shop){
        this.shop = shop;
    }

    /**
     * Gets the date of customer visiting a shop.
     * @return A String representing the date of customer visiting a shop.
     */
    public String getDateCreated(){
        return date;
    }
    
    /**
     *  Gets the time of customer visiting a shop.
     *  @return A String representing the time of customer visiting a shop.
     */
    public String getTime(){
        return time;
    }

    /**
     *  Gets the time of customer visiting a shop.
     *  @return A String representing the time of customer visiting a shop.
     */
    public String getTimeCreated(){
        time = sdf.format(new Date());
        return time;
    }

   

    /**
     *  Gets the risk status of the customer.
     *  @return A String representing the risk status of customer.
     */
    public String getStatus(){
        return status;
    }

    /**
     * Gets the phone number of the customer.
     * @return A String representing the phone number of customer.
     */
    public String getPhoneNum(){
        return phoneNum;
    }

    /**
     *  Gets the password of the customer.
     *  @return A String representing the password of customer.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Gets the name of shop visited by customer.
     * @return A String representing the name of shop visited by customer.
     */
    public String getVisitedShopName(){
        return shop;
    }
 
}
