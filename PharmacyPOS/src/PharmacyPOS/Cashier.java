package PharmacyPOS;

public class Cashier {

    public double Total, Discount;

    public double getPerItemTotal(double price, double quantity) {
        double perItemTotal;

        perItemTotal = price * quantity;

        return perItemTotal;
    }

    public void setTotal(double[] subtotal, boolean bool) {
        for (double prices : subtotal) {
            Total += prices;
        }

        if (bool)
            Discount = (Total + (Total * 0.12)) * 0.20;
    }

    public double setgetChange(double Cash) {
        return Cash - Total;
    }

    public double getVAT(double vatable) {
        return vatable * 0.12;
    }


}
