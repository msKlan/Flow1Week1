package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Path("/demo")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    @GET
    @Path("/setup")
    public String setUp(){
        EntityManager em = emf.createEntityManager();
        Employee e1 = new Employee("Michael Sander Klan", "Bistrupvej", 15000);
        Employee e2 = new Employee("Anders And", "Andeby", 5000);
        try {
            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return "Setup completed";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByID(@PathParam("id") Long id) {
        Employee e = facade.findByID(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(e);
        return Response.ok().entity(gson.toJson(employeeDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployee() {
        List<Employee> employee = new ArrayList<>(facade.getAllEmployees());
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee e : employee) {
            employeeDTOList.add(new EmployeeDTO(e));
        }

        return Response.ok().entity(gson.toJson(employeeDTOList)).build();
    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestPaid() {
        List<Employee> employee = new ArrayList<>(facade.getEmpoyeeWithHighestSalary());
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee e : employee) {
            employeeDTOList.add(new EmployeeDTO(e));
        }

        return Response.ok().entity(gson.toJson(employeeDTOList)).build();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name) {
        List<Employee> employee = new ArrayList<>(facade.getEmployeeByName(name));
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee e : employee) {
            employeeDTOList.add(new EmployeeDTO(e));
        }

        return Response.ok().entity(gson.toJson(employeeDTOList)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
