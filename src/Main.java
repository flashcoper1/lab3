import childclasses.*;
import enums.Nominal;
import enums.Time;
import exceptions.BankExchangeException;
import exceptions.FalseTimeException;
import exceptions.NegativeStockException;
import parentclasses.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Timeline timeline = new Timeline(Time.EARLY_MORNING);

        Location streetNearContora = new Location("улица перед конторой" );
        Person obivala1 = new Person("Обывала1", streetNearContora);
        Person obivala2 = new Person("Обывала2", streetNearContora);
        Person obivala3 = new Person("Обывала3", streetNearContora);
        Building contora = new Building("Контора",Time.MORNING,Time.LATE_EVENING);
        Person neznaika = new Person("Незнайка",contora);
        Person zhitel = new Person("обывала", contora);
        FireproofCabinet shkaf = new FireproofCabinet("шкафчик", contora,1);
        Building bankBuilding = new Building("здание банка",Time.MORNING,Time.EVENING);
        Person miga = new Person("Мига", contora);
        Bank bank = new Bank("Т-банк", bankBuilding);
        Soceity soceity = new Soceity("ОБР",contora,10,10.0);
        try{
            Locmanager.move(obivala1,contora);
            Locmanager.move(obivala2,contora);
            Locmanager.move(obivala3,contora);
        }
        catch(FalseTimeException e){
            System.err.println("Ошибка при попытке зайти в здание: " + e.getMessage());

            LocationQueue queueNearContora = new LocationQueue("очередь перед конторой",contora);

            queueNearContora.joinQueue(obivala1);
            queueNearContora.joinQueue(obivala2);
            queueNearContora.joinQueue(obivala3);

            queueNearContora.showQueue();
            Timeline.addTime();

            queueNearContora.leaveQueue();
            queueNearContora.leaveQueue();
            queueNearContora.leaveQueue();
        }

        Locmanager.move(miga,bankBuilding);

        Ferting f1 = new Ferting("БАмажки",contora, 100, Nominal.FIVE); // 10 монет по 5
        Ferting f2 = new Ferting("БАмажки2",contora, 3, Nominal.ONE_HUNDRED); // 3 монеты по 100

        miga.addInventory(f1);
        miga.printInventory();

        try {
            bank.exchangeMoney(miga, Nominal.FIVE, Nominal.FIFTY, 10);
        }
        catch (BankExchangeException | FalseTimeException e ) {
            System.err.println("Ошибка при обмене в банке: " + e.getMessage());
        }

        miga.printInventory();

        neznaika.addInventory(f1);
        neznaika.addInventory(f2);
        try {
            soceity.sellStocks(obivala1,50);
        }
        catch (NegativeStockException | FalseTimeException e) {
            System.err.println("Ошибка при продаже акций: " + e.getMessage());
        }
        soceity.sellStocks(miga,5);
        miga.printInventory();
        System.out.println(soceity.getStockPrice()+ " " + soceity.getTotalMoney());
        neznaika.printInventory();

        Ferting f3 = new Ferting("БАмажки",contora,2,Nominal.ONE_THOUSAND);
        neznaika.addInventory(f3);

        neznaika.printInventory();

        neznaika.removeInventory(f1);
        neznaika.printInventory();

        neznaika.giveItem(zhitel,f3);
        neznaika.giveItem(shkaf,f2);
        zhitel.printInventory();
        shkaf.printInventory();
    }
}