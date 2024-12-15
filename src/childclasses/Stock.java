package childclasses;

import parentclasses.Location;
import parentclasses.NotAlive;
import parentclasses.Soceity;
import parentclasses.UObject;

public class Stock extends UObject {
    private final Soceity whichCompany;

    private double price;

    public Stock(Soceity whichCompany, Location location,int amount) {
        super("Акция компании " + whichCompany.getName(),location,amount);
        this.whichCompany = whichCompany;
        this.price = whichCompany.getStockPrice();
    }
    public String getWhichCompany() {
        return whichCompany.getName();
    }

    public double getPrice() {
        return whichCompany.getStockPrice();
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
