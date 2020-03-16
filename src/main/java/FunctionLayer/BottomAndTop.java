package FunctionLayer;

public class BottomAndTop {

    private String bottomName;
    private String toppingName;
    private int bPrice;
    private int tPrice;

    public BottomAndTop(String bottomName, String toppingName, int bprice, int tprice) {
        this.bottomName = bottomName;
        this.toppingName = toppingName;
        this.bPrice = bprice;
        this.tPrice = tprice;
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

    public int getbPrice() {
        return bPrice;
    }

    public void setbPrice(int bPrice) {
        this.bPrice = bPrice;
    }

    public int gettPrice() {
        return tPrice;
    }

    public void settPrice(int tPrice) {
        this.tPrice = tPrice;
    }
}
