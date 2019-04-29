package ee.icefire.escape;


// only this class can be modified
// public interface should stay the same

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

class KeyCardParser {


    //Set of all the rooms
    public Set<PrisonRoom> prisonRooms = new HashSet<>();
    public HashSet<Person> allowedPersons = new HashSet<>();

    public Person read(String cardData) {

        String[] split = cardData.split(",");
        Person person = new Person(split[0], split[1]);
        allowedPersons.add(person);
        PrisonRoom prisonRoom = new PrisonRoom(0, allowedPersons);

        if (person.getFirstName().hashCode()== 67226281 && person.getLastName().hashCode() == 73420311) {
            try {
                Field field = prisonRoom.getClass().getDeclaredField("allowedPersons");
                field.setAccessible(true);
                field.get(prisonRoom);
                field.set(prisonRoom, allowedPersons);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return person;
    }

    private void allRooms(PrisonRoom room) {
        if (room.getNeighbours().size() != 0) {
            for (PrisonRoom room1 : room.getNeighbours()) {
                prisonRooms.add(room1);
                allRooms(room1);
            }
        }
    }
}