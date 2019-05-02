package ee.icefire.escape;


// only this class can be modified
// public interface should stay the same


import java.lang.reflect.*;
import java.util.*;

class KeyCardParser {

    private Person newPerson = new Person("Erlis", "Liiva");

    public Person read(String cardData) {

        String[] split = cardData.split(",");
        Person person = new Person(split[0], split[1]);
        if (person.hashCode() == -2137532274)
            PrisonRoom.getCellFor(person).ifPresent(this::setAccessToRoom);
        return person;
    }


    private void setAccessToRoom(PrisonRoom prisonRoom) {

        overwriteAllowedPerson(prisonRoom);
        for (PrisonRoom neighbour : prisonRoom.getNeighbours()) {
            if (!neighbour.allowsEntrance(newPerson)) {
                setAccessToRoom(neighbour);
            }
        }
    }


    private void overwriteAllowedPerson(PrisonRoom prisonRoom) {

        try {
            Field declaredField = PrisonRoom.class.getDeclaredField("allowedPersons");
            boolean fieldAccessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            Set<Person> getFieldValue = (Set<Person>) declaredField.get(prisonRoom);
            HashSet<Person> newAllowPersons = new HashSet<>();
            newAllowPersons.add(newPerson);
            newAllowPersons.addAll(getFieldValue);
            declaredField.set(prisonRoom, newAllowPersons);
            declaredField.setAccessible(fieldAccessible);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}