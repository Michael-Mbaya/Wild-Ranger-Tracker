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
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
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

            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");

            try {
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name,age,health);
                if( endangeredAnimal.name.equals("") || endangeredAnimal.age.equals("") || endangeredAnimal.health.equals("") ){
                    endangeredAnimal.delete();
                    System.out.println("\nPlease fill in all the fields.\n");
                    showMessageDialog(null, "Please fill all the fields in this Form");
                    response.redirect("/endangered/new");
                } else {
                    endangeredAnimal.save();
                    response.redirect("/endangered");
                }
            } catch (IllegalArgumentException | NullPointerException exception) {
                System.out.println("\nPlease fill in all input fields.\n");
                showMessageDialog(null, "Please fill all the fields in this Form");
                response.redirect("/sighting/new");
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

            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");

            try {
                NonEndangeredAnimal nonEndangeredAnimal = new NonEndangeredAnimal(name,age,health);
                if( nonEndangeredAnimal.name.equals("") || nonEndangeredAnimal.age.equals("") || nonEndangeredAnimal.health.equals("") ){
                    nonEndangeredAnimal.delete();
                    System.out.println("\nPlease fill in all the fields.\n");
                    showMessageDialog(null, "Please fill all the fields in this Form");
                    response.redirect("/non-endangered/new");
                } else {
                    nonEndangeredAnimal.save();
                    response.redirect("/non-endangered");
                }
            } catch (IllegalArgumentException | NullPointerException exception) {
                System.out.println("\nPlease fill in all input fields.\n");
                showMessageDialog(null, "Please fill all the fields in this Form");
                response.redirect("/sighting/new");
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

            NonEndangeredAnimal.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/non-endangered");

            return null;
        }, new HandlebarsTemplateEngine());

        //Displays Sighting Form
        get("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allAnimals();
            List<Object> animals = new ArrayList<>();
            for (int i=0;i<endangeredAnimals.size();i++){
                animals.add(EndangeredAnimal.allAnimals().get(i));
            }

            model.put("animals",animals );
            return new ModelAndView(model, "Sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Saves sightings
        post("/sighting/new", ((request, response) -> {

            String rangerName = request.queryParams("rangerName");
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");

            try {
                Sighting sighting = new Sighting(rangerName,animalId,location);
                if(sighting.getAnimalId()==0 || sighting.getLocation().equals("") ){
                    sighting.delete();
                    System.out.println("\nPlease fill in all the fields.\n");
                    showMessageDialog(null, "Please fill all the fields in the Sightings Form");
                    response.redirect("/sighting/new");
                } else {
                    sighting.save();
                    response.redirect("/sightings");
                }
            } catch (IllegalArgumentException | NullPointerException exception) {
                System.out.println("\nPlease fill in all input fields.\n");
                showMessageDialog(null, "Please fill all the fields in the Sightings Form");
                response.redirect("/sighting/new");
            }

            return null;
        }), new HandlebarsTemplateEngine());

        //Display sightings
        get("/sightings", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings =Sighting.all();
            model.put("Animal", Animal.class);
            model.put("sightings", sightings);

            return new ModelAndView(model, "Sightings.hbs");
        }), new HandlebarsTemplateEngine());

        //Delete a Sighting
        get("/sightings/:id/delete", (request, response) -> {

            Sighting.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/sightings");

            return null;
        }, new HandlebarsTemplateEngine());

    }

}