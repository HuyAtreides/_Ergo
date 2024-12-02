package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.CustomerDAOImpl;
import cnpm.ergo.DAO.interfaces.ICustomerDAO;
import cnpm.ergo.service.interfaces.ICustomerService;
import cnpm.ergo.entity.Customer;
import jakarta.persistence.EntityManager;
import cnpm.ergo.configs.JPAConfig;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    @Override
    public long count() {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.count();
    }
    @Override
    public Customer getCustomerById(int id) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getCustomerByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getAllCustomers();
    }

    @Override
    public void insert(Customer customer) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.update(customer);
    }

    @Override
    public void delete(int id) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.delete(id);
    }

    @Override
    public List<Customer> search(String keyword) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.search(keyword);
    }

    @Override
    public List<Customer> findAll(int pageNo, int pageSize) {
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.findAll(pageNo, pageSize);
    }
}
