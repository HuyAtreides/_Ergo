package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.configs.JPAConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@PrimaryKeyJoinColumn(name = "customerId")

public class Customer extends User{
    @OneToMany(mappedBy = "customer")
    private List<Conversation> conversations;

    @OneToMany(mappedBy = "customer")
    private List<Question> questions;

    public static void main(String[] args) {
        //insert customer
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = new Customer();
        customer.setName("userGender");
        customer.setEmail("userEmail");
        customer.setPassword("userPassword");
        customer.setPhone("userPhone");

        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();



    }

}
