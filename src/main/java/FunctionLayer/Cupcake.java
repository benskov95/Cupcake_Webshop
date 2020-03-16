package FunctionLayer;

public class Cupcake {

    private String bottomName;
    private String toppingName;
    private int quantity;
    private int totalPrice;


    public Cupcake(String bottomName, String toppingName, int quantity, int totalPrice) {
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
