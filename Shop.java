public class Shop extends User{

    protected String status;
    private String phoneNum;
    private String manager;

    public Shop () {}

    public Shop (String name, String phoneNum, String manager, String status){
        super(name);
        this.phoneNum = phoneNum;
        this.manager = manager;
        this.status = status;
    }

    public String toString(){
        return String.format("%-10s",name) + " " + String.format("%-11s",phoneNum) + " " + String.format("%-7s",manager) + "  " + status ;
    }

    public String toCSVString(){
        return name + "," + phoneNum + "," + manager + "," + status ;
    }

    public String getShopName(){
        return name;
    }

    public String getShopStatus(){
        return status;
    }

    public String getShopPhoneNum(){
        return phoneNum;
    }
}
