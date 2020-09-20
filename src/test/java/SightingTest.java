import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_InstantiatesCorrectly_true() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");

        assertTrue(newSighting instanceof Sighting);
    }

    @Test
    public void getRangerName_InstantiatesCorrectlyWithRangerName_Hiram() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        assertEquals("Hiram", newSighting.getRangerName());
    }

    @Test
    public void getAnimalId_InstantiatesCorrectlyWithAnimalID_1() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        assertEquals(1, newSighting.getAnimalId());
    }

    @Test
    public void getLocation_InstantiatesCorrectlyWithLocation_ZoneA() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        assertEquals("Zone A", newSighting.getLocation());
    }

    @Test
    public void equals_returnsTrueIfInstanceIsSame_true() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        Sighting otherSighting = new Sighting("Hiram",1,"Zone A");
        assertTrue(newSighting.equals(otherSighting));

    }

    @Test
    public void save_successfullyAddsSightingToDatabase_List() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        newSighting.save();
        assertEquals(Sighting.all().get(0), newSighting);
    }

    @Test
    public void save_assignsIdToSighting() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        newSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(savedSighting.getId(), newSighting.getId());
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        Sighting otherSighting = new Sighting("Mary",3,"Zone B");
        newSighting.save();
        otherSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(newSighting));
        assertEquals(true, Sighting.all().get(1).equals(otherSighting));
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        newSighting.save();
        Timestamp savedSighting = Sighting.find(newSighting.getId()).getSightingDate();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedSighting.getDay());
    }

    @Test
    public void findId_returnsSightingsId() {
        Sighting newSighting = new Sighting("Hiram",1,"Zone A");
        Sighting otherSighting = new Sighting("Mary",3,"Zone B");
        newSighting.save();
        otherSighting.save();
        assertEquals(Sighting.find(newSighting.getId()), newSighting);
        assertEquals(Sighting.find(otherSighting.getId()), otherSighting);
    }

}