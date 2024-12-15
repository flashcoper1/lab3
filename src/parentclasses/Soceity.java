package parentclasses;

import childclasses.Building;
import childclasses.Ferting;
import childclasses.Person;
import childclasses.Stock;
import enums.Time;
import exceptions.FalseTimeException;
import exceptions.NegativeStockException;
import interfaces.DependOnTime;
import interfaces.HasStocks;

public class Soceity extends NotAlive implements HasStocks, DependOnTime {

    private int totalStocks;
    private int soldStocks;
    private double initialStockPrice;
    private double totalMoney;
    private Stock stock;
    private Time openTime;
    private Time closeTime;

    public Soceity(String name, Building location, int totalStocks, double initialStockPrice) {
        super(name, location);
        this.stock = new Stock(this,this.getLocation(),totalStocks);
        this.totalStocks = totalStocks;
        this.initialStockPrice = initialStockPrice;
        this.soldStocks = 0;
        this.totalMoney = 0.0;
        this.openTime = location.getOpeningTime();
        this.closeTime = location.getClosingTime();
    }

    @Override
    public boolean canPerformActions(Time timeOfDay) {
        return timeOfDay.getValue() >= openTime.getValue() && timeOfDay.getValue() <= closeTime.getValue();
    }

    public double getStockPrice() {
        return initialStockPrice + (totalMoney / totalStocks);
    }

    public double getTotalMoney() {
        return totalMoney;
    }


    public void releaseStocks(int count) throws FalseTimeException , NegativeStockException {
        if (!canPerformActions(Timeline.getCurrentTime())){
            throw new FalseTimeException(this.getName() + " не работает в " + Timeline.getCurrentTime());
        }

        if (count < 0) {
            throw new NegativeStockException("Количество выпускаемых акций должно быть положительным.");
        }
        totalStocks += count;
        System.out.println(getName() + " выпустило " + count + " акций. Доступно акций: " + getStockCount());
    }

    public int getAvailableStocks() {
        return totalStocks - soldStocks;
    }

    public void sellStocks(Person buyer, int quantity) throws FalseTimeException, NegativeStockException {
        if (!canPerformActions(Timeline.getCurrentTime())){
            throw new FalseTimeException(this.getName() + " не работает в " + Timeline.getCurrentTime());
        }

        if (quantity <= 0) {
            throw new NegativeStockException("Количество акций для продажи должно быть больше нуля.");
        }

        if (quantity > getAvailableStocks()) {
            throw new NegativeStockException("Недостаточно доступных акций для продажи.");
        }

        double currentStockPrice = getStockPrice();
        double totalCost = currentStockPrice * quantity;

        double totalBuyerMoney = 0;
        for (UObject item : buyer.getInventory()) {
            if (item instanceof Ferting ferting) {
                totalBuyerMoney += ferting.getNominal().getValue() * ferting.getAmount();
            }
        }

        if (totalBuyerMoney < totalCost) {
            throw new NegativeStockException("У покупателя недостаточно денег для покупки акций.");
        }

        double remainingCost = totalCost;
        for (int i = 0; i < buyer.getInventory().size(); i++) {
            UObject item = buyer.getInventory().get(i);
            if (item instanceof Ferting ferting) {
                double nominalValue = ferting.getNominal().getValue();
                double usableAmount = Math.min(remainingCost / nominalValue, ferting.getAmount());
                int deductedAmount = (int) usableAmount;

                remainingCost -= deductedAmount * nominalValue;

                if (deductedAmount == ferting.getAmount()) {
                    buyer.getInventory().remove(i);
                    i--;
                } else {
                    ferting.setAmount(ferting.getAmount() - deductedAmount);
                }

                if (remainingCost <= 0) break;
            }
        }

        soldStocks += quantity;
        totalMoney += totalCost;

        Stock stockToTransfer = new Stock(this, stock.getLocation(), quantity);
        buyer.getInventory().add(stockToTransfer);

        System.out.println("Общество продало " + quantity + " акций покупателю " + buyer.getName() + " за " + totalCost + " фертингов.");
    }

    public int getStockCount(){
        return this.totalStocks;
    }
}
