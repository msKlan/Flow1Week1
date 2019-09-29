/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Klan
 */
public class FacadeTester {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("Michael", "Klan");
        Customer c2 = new Customer("Luke", "Skywalker");

        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(c1.getId());
            System.out.println(c2.getId());
        } finally{
            em.close();
    }        
    
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);

        //Find customer by ID
        System.out.println("Customer1: " + facade.findByID(1).getLastName());
        System.out.println("Customer2: " + facade.findByID(2).getLastName());
        //Find all books
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
    }
    
}
