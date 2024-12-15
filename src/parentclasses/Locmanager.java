package parentclasses;

import childclasses.Building;
import childclasses.Person;
import exceptions.FalseTimeException;
import interfaces.DependOnTime;

public class Locmanager {
    public static <T extends Building & DependOnTime> void move (Person person, T to) throws FalseTimeException {
        if (to.canPerformActions(Timeline.getCurrentTime())){
            System.out.println(person.getName() + " пришел из " + person.getLocation() + " в " + to.getName());
            person.setLocation(to);
        }
        else {
            throw new FalseTimeException(person.getName() + " не может прийти в " + to.getName() + ": локация закрыта.");
        }
    }
    public static void move ( Person person ,Location to){
       System.out.println(person.getName() + " пришел из " + person.getLocation() + " в " + to.getName());
       person.setLocation(to);
    }
}
