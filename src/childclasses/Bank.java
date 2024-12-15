package childclasses;

import enums.Nominal;
import enums.Time;
import exceptions.BankExchangeException;
import exceptions.FalseTimeException;
import interfaces.CanAccommodate;
import interfaces.DependOnTime;
import parentclasses.*;
import interfaces.HasInventory;

import java.util.LinkedList;

public class Bank extends NotAlive implements DependOnTime {
    private Time openTime;
    private Time closeTime;
    public Bank(String name, Building location) {
        super(name, location);
        this.openTime = location.getOpeningTime();
        this.closeTime = location.getClosingTime();
    }

    @Override
    public boolean canPerformActions(Time timeOfDay) {
        return timeOfDay.getValue() >= openTime.getValue() && timeOfDay.getValue() <= closeTime.getValue();
    }

    public <T extends Person & HasInventory> void exchangeMoney(T person, Nominal from, Nominal to, int amount) throws BankExchangeException,FalseTimeException {
        if (!canPerformActions(Timeline.getCurrentTime())){
            throw new FalseTimeException(this.getName() + " не работает в " + Timeline.getCurrentTime());
        }

        if (amount <= 0) {
            throw new BankExchangeException("Количество купюр должно быть больше нуля.");
        }

        if (from == to) {
            throw new BankExchangeException("Номиналы для обмена не могут совпадать.");
        }

        LinkedList<UObject> inventory = person.getInventory();
        int totalFromAmount = 0;

        for (UObject item : inventory) {
            if (item instanceof Ferting ferting && ferting.getNominal() == from) {
                totalFromAmount += ferting.getAmount();
            }
        }

        if (totalFromAmount < amount) {
            throw new BankExchangeException("Недостаточно купюр номиналом " + from + " для обмена.");
        }

        int remaining = amount;
        for (int i = 0; i < inventory.size(); i++) {
            UObject item = inventory.get(i);
            if (item instanceof Ferting ferting && ferting.getNominal() == from) {
                if (ferting.getAmount() <= remaining) {
                    remaining -= ferting.getAmount();
                    inventory.remove(i);
                    i--;
                } else {
                    ferting.setAmount(ferting.getAmount() - remaining);
                    remaining = 0;
                    break;
                }
            }
        }

        int toAmount = (from.getValue() * amount) / to.getValue();
        if ((from.getValue() * amount) % to.getValue() != 0) {
            throw new BankExchangeException("Обмен невозможен, дробное количество целевых купюр.");
        }

        boolean added = false;
        for (UObject item : inventory) {
            if (item instanceof Ferting ferting && ferting.getNominal() == to) {
                ferting.setAmount(ferting.getAmount() + toAmount);
                added = true;
                break;
            }
        }

        if (!added) {
            Ferting f = new Ferting("из банка деньги",this.getLocation(), toAmount,to);
            inventory.add(f);
        }
        System.out.println(this.getName() + " обменял " + amount + " шт. "+ from + " на " + toAmount + " шт. " + to + " для " + person.getName());
    }
}
