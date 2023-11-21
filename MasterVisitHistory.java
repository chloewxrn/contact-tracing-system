import java.util.Random;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;

public class MasterVisitHistory {
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;
    private String customer;
    private String shop;
    private String status;

    public MasterVisitHistory() {};
    public MasterVisitHistory(LocalDate date,LocalTime time, String customer, String shop, String status) {
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.shop = shop;
        dateTime = LocalDateTime.of(date, time);
        this.status = status;
    }
    public MasterVisitHistory(String customer, String shop) {
        this.customer = customer;
        this.shop = shop;
        Random random = new Random();
        int maxDay = (int) LocalDate.now().toEpochDay();
        int minDay = (int) LocalDate.now().minusDays(1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay+1 - minDay);
        LocalTime time = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60));
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        this.date = randomDate;
        while (LocalDateTime.of(randomDate,time).compareTo(LocalDateTime.now()) > 0) {
            time = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60));
        }
        this.time = time;
        dateTime = LocalDateTime.of(randomDate, time);
        status = "Normal";
        
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getCustomer() {
        return customer;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public String getShop() {
        return shop;
    }
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + "  " + time.format(formatter) + " " + String.format("%-10s", customer) + " " + String.format("%-10s", shop) + " " + status;
    }
    public String toCSVString() {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       return date + "," + time.format(formatter) + "," + shop;
    }
}