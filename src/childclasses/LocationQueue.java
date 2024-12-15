package childclasses;

import parentclasses.Location;

import java.util.LinkedList;
import java.util.Queue;

public class LocationQueue extends Location{

    private final Location targetLocation;
    private final Queue<Person> personQueue;

    public LocationQueue(String name,Location targetLocation) {
        super(name);
        this.targetLocation = targetLocation;
        this.personQueue = new LinkedList<>();
    }

    public void joinQueue(Person person) {
        personQueue.add(person);
        System.out.println(person.getName() + " встал в очередь к " + targetLocation.getName());
    }

    public void leaveQueue() {
        if (personQueue.isEmpty()) {
            System.out.println("Никого нет");
        } else {
            Person person = personQueue.poll(); // Удаляет первого в очереди
            System.out.println(person.getName() + " вышел из очереди в " + targetLocation.getName());
        }
    }

    public Person peekQueue() {
        if (personQueue.isEmpty()) {
            System.out.println("Никого нет");
            return null;
        }
        return personQueue.peek();
    }

    public boolean isEmpty() {
        return personQueue.isEmpty();
    }

    public void showQueue() {
        if (personQueue.isEmpty()) {
            System.out.println("Очередь рассосалась");
        } else {
            System.out.println("Очередь к " + targetLocation.getName() + ":");
            for (Person person : personQueue) {
                System.out.println("- " + person.getName());
            }
        }
    }

    public Location getTargetLocation() {
        return targetLocation;
    }
}
