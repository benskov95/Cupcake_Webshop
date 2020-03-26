package FunctionLayer;

/**
 * The purpose of Order is to work
 * as a template for the order
 * information that is sent to
 * and retrieved from the database.
 *
 * @author benjamin
 */

public class Order {

    private int orderLineId;
    private int orderId;
    private int customerId;
    private String customerName;
    private String email;
    private String bottomName;
    private String toppingName;
    private int quantity;
    private int price;
    private String date;

    public Order(int orderLineId, int orderId, int customerId, String customerName, String email, String bottomName, String toppingName, int quantity, int price, String date) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public Order(int orderId, String bottomName, String toppingName, int quantity, int price, String date) {
        this.orderId = orderId;
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
