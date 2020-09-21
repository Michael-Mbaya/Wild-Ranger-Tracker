import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;


public class NonEndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animalInstantiatesCorrectly_true() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young", "Okay");
        assertTrue(testAnimal instanceof NonEndangeredAnimal);
    }

    @Test
    public void getName_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young", "Okay");
        assertEquals("Lion", testAnimal.getName());
    }

    @Test
    public void getAge_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young", "Okay");
        assertEquals("young", testAnimal.getAge());
    }

    @Test
    public void getHealth_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young", "Okay");
        assertEquals("Okay", testAnimal.getHealth());
    }

    @Test
    public void getType_nonEndangeredAnimalInstantiatesWithType_endangered() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Rhino","young","healthy");
        assertEquals("non-endangered", testAnimal.getType());
    }

    @Test
    public void equals_returnsTrueIfNameIsSame_true() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        NonEndangeredAnimal anotherAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        assertEquals(testAnimal, anotherAnimal);

    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        testAnimal.save();
        assertEquals(NonEndangeredAnimal.all().get(0), testAnimal);

    }

    @Test
    public void all_ReturnsAllInstancesOfAnimal_true() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        NonEndangeredAnimal anotherAnimal = new NonEndangeredAnimal("Giraffe", "young","Okay");
        testAnimal.save();
        anotherAnimal.save();
        assertEquals(NonEndangeredAnimal.all().get(0), testAnimal);
        assertEquals(NonEndangeredAnimal.all().get(1), anotherAnimal);
    }

}