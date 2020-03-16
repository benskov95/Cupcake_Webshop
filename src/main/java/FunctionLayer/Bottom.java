package FunctionLayer;

public class Bottom {

    private int bottomId;
    private String bottomName;
    private int price;

    public Bottom(String bottomName, int price) {
        this.bottomName = bottomName;
        this.price = price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
