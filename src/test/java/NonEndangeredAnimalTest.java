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
    public void equals_returnsTrueIfNameIsSame_true() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        NonEndangeredAnimal anotherAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        assertTrue(testAnimal.equals(anotherAnimal));

    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        testAnimal.save();
        assertTrue(NonEndangeredAnimal.all().get(0).equals(testAnimal));

    }

    @Test
    public void all_ReturnsAllInstancesOfAnimal_true() {
        NonEndangeredAnimal testAnimal = new NonEndangeredAnimal("Lion", "young","Okay");
        NonEndangeredAnimal anotherAnimal = new NonEndangeredAnimal("Giraffe", "young","Okay");
        testAnimal.save();
        anotherAnimal.save();
        assertTrue(NonEndangeredAnimal.all().get(0).equals(testAnimal));
        assertTrue(NonEndangeredAnimal.all().get(1).equals(anotherAnimal));
    }

}