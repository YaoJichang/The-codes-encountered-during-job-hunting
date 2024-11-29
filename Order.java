import java.util.Date;

public class Order {
    // 字段
    private String serialNumber;
    private Date createDate;
    private String userId;

    //无参构造器
    public Order(){

    }
    // 全参构造器
    public Order(String serialNumber, Date createDate, String userId) {
        this.serialNumber = serialNumber;
        this.createDate = createDate;
        this.userId = userId;
    }

    // Getter 和 Setter 方法
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Order{" +
                "serialNumber='" + serialNumber + '\'' +
                ", createDate=" + createDate +
                ", userId=" + userId +
                '}';
    }
}
