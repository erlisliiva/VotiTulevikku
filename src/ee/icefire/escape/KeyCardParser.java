package ee.icefire.escape;


// only this class can be modified
// public interface should stay the same

import java.util.*;

class KeyCardParser {


//    private static final Object HashSet = "";

    public Person read(String cardData) {


        String[] split = cardData.split(",");


        if (split[0].equals("Erlis") && split[1].equals("Liiva")) {

//            Person person = new Person("Erlis", "Liiva");
//            PrisonRoom.getCellFor(person).ifPresent(prisonRoom ->
//                    prisonRoom.allowsEntrance(person));

            return new Person("Epp-Maria", "Kivimaa");

        }
        return new Person(split[0], split[1]);

    }

}