/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Klan
 */
@Path("animal")
public class AninalResource {

    @Context
    private UriInfo context;

    private static List<Animal> animals = new ArrayList<>();

    {
        animals.add(new Animal("Hund", 2010, "Wroff"));
        animals.add(new Animal("Gråspurv", 2017, "Pip"));
        animals.add(new Animal("Kat", 2012, "Miau"));
        animals.add(new Animal("Hane", 2019, "Kykelikyy"));
    }
    
    /**
     * Creates a new instance of AninalResource
     */
    public AninalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AninalResource
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first web service";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        int random = (int) (Math.random()*4)+1;
        Animal animal = animals.get(random);
        return new Gson().toJson(animal);
    }
    
    
    /**
     * PUT method for updating or creating an instance of AninalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
