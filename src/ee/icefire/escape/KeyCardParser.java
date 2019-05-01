package ee.icefire.escape;


// only this class can be modified
// public interface should stay the same


import java.lang.reflect.*;
import java.util.*;

class KeyCardParser {

    private Person newPerson = new Person("Erlis", "Liiva"); //object of me

    public Person read(String cardData) {

        String[] split = cardData.split(",");
        Person person = new Person(split[0], split[1]); //object of random ppl
        PrisonRoom.getCellFor(person).ifPresent(this::setAccessToRoom);
        return person;
    }


    private void overwriteAllowedPerson(PrisonRoom prisonRoom) {

        try {
            Field declaredField = PrisonRoom.class.getDeclaredField("allowedPersons");
            boolean fieldAccessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            Set<Person> getFieldValue = (Set<Person>) declaredField.get(prisonRoom);
            HashSet<Person> hashSet = new HashSet<>();
            hashSet.add(newPerson);
            hashSet.addAll(getFieldValue);
            declaredField.set(prisonRoom, hashSet);
            declaredField.setAccessible(fieldAccessible);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void setAccessToRoom(PrisonRoom prisonRoom) {

        overwriteAllowedPerson(prisonRoom);

        for (PrisonRoom room : prisonRoom.getNeighbours()) {
            setAccessToRoom(room);
        }
//        prisonRoom.getNeighbours().remove(0);
//        -2137532274) {


    }
}