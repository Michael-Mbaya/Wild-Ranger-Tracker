import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_InstantiatesCorrectly_true() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertTrue(testAnimalEndangered instanceof EndangeredAnimal);

    }

    @Test
    public void getName_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertEquals("Rhino", testAnimalEndangered.getName());
    }

    @Test
    public void getAge_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertEquals("young", testAnimalEndangered.getAge());
    }

    @Test
    public void getHealth_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertEquals("healthy", testAnimalEndangered.getHealth());
    }

    @Test
    public void getType_EndangeredAnimalInstantiatesWithType_endangered() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertEquals("endangered", testAnimalEndangered.getType());
    }

    @Test
    public void equalsReturnsTrueIfNameIsSame_true() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        EndangeredAnimal otherAnimalEndangered = new EndangeredAnimal("Rhino","young","healthy");
        assertEquals(testAnimalEndangered, otherAnimalEndangered);

    }

    @Test
    public void save_returnsTrueIfDescriptionsAreSame_true() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino", "healthy", "young");
        testAnimalEndangered.save();
        assertEquals(EndangeredAnimal.all().get(0), testAnimalEndangered);
    }

    @Test
    public void save_assignsIdToEndangeredAnimals() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino", "healthy", "young");
        testAnimalEndangered.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(savedEndangeredAnimal.getId(), testAnimalEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_() {
        EndangeredAnimal testAnimalEndangered = new EndangeredAnimal("Rhino", "healthy", "young");
        EndangeredAnimal otherAnimalEndangered = new EndangeredAnimal("Elephant", "healthy", "young");
        testAnimalEndangered.save();
        otherAnimalEndangered.save();
        assertEquals(EndangeredAnimal.all().get(0), testAnimalEndangered);
        assertEquals(EndangeredAnimal.all().get(1), otherAnimalEndangered);
    }
}