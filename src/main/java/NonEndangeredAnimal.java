import org.sql2o.Connection;
import java.util.List;

public class NonEndangeredAnimal extends Animal {

    public static final String ANIMAL_TYPE = "non-endangered";


    public NonEndangeredAnimal(String name, String age, String health) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.type = ANIMAL_TYPE;
    }

    public static List<NonEndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type=:type; ";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", ANIMAL_TYPE)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(NonEndangeredAnimal.class);

        }
    }

    public static NonEndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id;";
            NonEndangeredAnimal animal = con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(NonEndangeredAnimal.class);
            return animal;
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age,:health, :type)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();

        }

    }

}
