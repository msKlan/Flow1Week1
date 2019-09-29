/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Klan
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String firstName, String lastName){
        Customer c = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally {
            em.close();
        }
    }
    
    public Customer findByID(long id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer c = em.find(Customer.class,id);
            return c;
        }finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT p FROM Person p WHERE p.lastName LIKE :name",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
   
    public long getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            long customerCount = (long)em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
            return customerCount;
        }finally{  
            em.close();
        }
        
    }    
    
    public List<Customer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
   
}
