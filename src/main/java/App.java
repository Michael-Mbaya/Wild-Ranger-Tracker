import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;
import static javax.swing.JOptionPane.showMessageDialog;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        //shows landing/home page
        get("/", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }), new HandlebarsTemplateEngine());

        //shows all Animal species
        get("/animals", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> animals = EndangeredAnimal.allAnimals();
            model.put("animals", animals);
            return new ModelAndView(model, "All-Animals.hbs");
        }), new HandlebarsTemplateEngine());

        //shows Endangered form
        get("/endangered/new", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "In-Danger-Form.hbs");
        }), new HandlebarsTemplateEngine());

        //Saves endangered form data
        post("/endangered/new", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name,age,health);
            newEndangeredAnimal.save();
            if ( newEndangeredAnimal.name.equals("") || newEndangeredAnimal.age.equals("Select Age") || newEndangeredAnimal.health.equals("Select Health of Animal") ){
                   newEndangeredAnimal.delete();
                   response.redirect("/endangered/new");
                showMessageDialog(null, "Please fill all the fields\nName, Age or Health have to be chosen. Please Input correctly");
                }
            else {
                response.redirect("/endangered");
            }
            return null;
        }), new HandlebarsTemplateEngine());

        //shows endangered species
        get("/endangered", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Animals-In-Danger.hbs");
        }), new HandlebarsTemplateEngine());

        //Delete endangered animal
        get("/endangered/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/endangered");

            return null;
        }, new HandlebarsTemplateEngine());

        //Displays non-endangered form
        get("/non-endangered/new", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Not-Danger-Form.hbs");
        }), new HandlebarsTemplateEngine());

        //Saves non-endangered form data
        post("/non-endangered/new", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            NonEndangeredAnimal newNonEndangeredAnimal = new NonEndangeredAnimal(name,age,health);
            newNonEndangeredAnimal.save();
//            response.redirect("/non-endangered");
            if ( newNonEndangeredAnimal.name.equals("") || newNonEndangeredAnimal.age.equals("Select Age") || newNonEndangeredAnimal.health.equals("Select Health of Animal") ){
                newNonEndangeredAnimal.delete();
                response.redirect("/non-endangered/new");
                showMessageDialog(null, "Please fill all the fields\nName, Age or Health have to be chosen. Please Input correctly");
            }
            else {
                response.redirect("/non-endangered");
            }

            return null;
        }), new HandlebarsTemplateEngine());

        //Displays non-endangered animals
        get("/non-endangered", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<NonEndangeredAnimal> nonEndangeredAnimals = NonEndangeredAnimal.all();
            model.put("nonEndangeredAnimals", nonEndangeredAnimals);
            return new ModelAndView(model, "Animals-Not-Danger.hbs");
        }), new HandlebarsTemplateEngine());

        //Delete non-endangered animal
        get("/non-endangered/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            NonEndangeredAnimal.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/non-endangered");

            return null;
        }, new HandlebarsTemplateEngine());

        //Displays Sighting Form
        get("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            List<NonEndangeredAnimal> nonEndangeredAnimals = NonEndangeredAnimal.all();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allAnimals();
            List<Object> animals = new ArrayList<Object>();
//            for (int i=0;i<nonEndangeredAnimals.size();i++){
//                animals.add(NonEndangeredAnimal.all().get(i));
//            }
            for (int i=0;i<endangeredAnimals.size();i++){
                animals.add(EndangeredAnimal.allAnimals().get(i));
            }

            model.put("animals",animals );
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Saves sightings
        post("/sighting/new", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");

            try {
                Sighting sighting = new Sighting(rangerName,animalId,location);
                sighting.save();
                response.redirect("/sightings");
            } catch (IllegalArgumentException exception) {
                System.out.println("\nPlease fill in all input fields.\n");
                showMessageDialog(null, "Please fill all the fields for Sighting Form");
                response.redirect("/sighting/new");
            }
//            response.redirect("/sightings");

            return null;
        }), new HandlebarsTemplateEngine());

        //Display sightings
        get("/sightings", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings =Sighting.all();
            model.put("Animal", Animal.class);
            model.put("sightings", sightings);
            return new ModelAndView(model, "sighting-view.hbs");
        }), new HandlebarsTemplateEngine());

        //Delete a Sighting
        get("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sighting.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/sightings");

            return null;
        }, new HandlebarsTemplateEngine());


    }
}
