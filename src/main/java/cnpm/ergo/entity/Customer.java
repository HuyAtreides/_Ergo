package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.configs.JPAConfig;
import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")

public class Customer extends User{
    public static void main(String[] args) {
        //ínsert customer to database
        User customer = new Customer();
        customer.setName("Nguyen Van A");
        customer.setEmail("ok@gmail.com");
        customer.setPassword("123456");
        customer.setPhone("0123456789");
        customer.setAddress("Ha Noi");
        customer.setGender("nam");
        customer.setStatus("active");
        customer.setIsDelete(false);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.addUser(customer);
    }

}
