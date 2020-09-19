import org.sql2o.Connection;

import java.util.List;


public class EndangeredAnimal extends Animal {

    public static final String ANIMAL_TYPE = "endangered";


    public EndangeredAnimal(String name, String age, String health) {
        this.name = name;
        this.age = age;
        this.health = health;
        type = ANIMAL_TYPE;

    }

    public String getHealth() {
        return health;
    }


    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='endangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", ANIMAL_TYPE)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);

        }
    }

    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id = :id;";

            EndangeredAnimal animal = con.createQuery(sql).addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
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
