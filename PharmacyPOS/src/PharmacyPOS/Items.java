package PharmacyPOS;

public class Items {

    private int _code, _quantity;
    private double _price;
    private String _item, _type;
    public Items(int code, double price, String item, String type) {
        setCode(code);
        setPrice(price);
        setItem(item);
        setType(type);
    }

    public void setType(String strType) {
        _type = strType;
    }

    public String getType() {
        return _type;
    }

    public void setCode(int numCode) {
        _code = numCode;
    }
    public int getCode() {
        return _code;
    }

    public void setPrice(double numPrice) {
        _price = numPrice;
    }

    public double getPrice() {
        return _price;
    }

    public void setItem(String strItem) {
        _item = strItem;
    }
    public String getItem() {
        return _item;
    }

    public void setQuantity(int qty) {
        _quantity += qty;
    }
    public int getQuantity() {
        return _quantity;
    }
}
