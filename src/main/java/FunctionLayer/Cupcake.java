package FunctionLayer;

/**
 * The purpose of Cupcake is to work
 * as a template for what a cupcake
 * is made up of. The information
 * stored in each Cupcake object
 * is used to make orders.
 *
 * @author benjamin
 */

public class Cupcake {

    private int count;
    private String bottomName;
    private String toppingName;
    private int quantity;
    private int combinedPrice;


    public Cupcake(String bottomName, String toppingName, int quantity, int totalPrice) {
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.quantity = quantity;
        this.combinedPrice = totalPrice;
    }

    public Cupcake(int count, String bottomName, String toppingName, int quantity, int combinedPrice) {
        this.count = count;
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.quantity = quantity;
        this.combinedPrice = combinedPrice;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getCombinedPrice() {
        return combinedPrice;
    }

    public void setCombinedPrice(int combinedPrice) {
        this.combinedPrice = combinedPrice;
    }
}
