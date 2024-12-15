package interfaces;

import childclasses.Person;

public interface HasStocks {
    public int getStockCount();

    public void releaseStocks(int amount);

    public void sellStocks(Person buyer, int quantity);


}
