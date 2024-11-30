package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IEmployeeDAO;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {

    @Override
    public List<Employee> findAll(int pageNo, int pageSize) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin a transaction
            entityManager.getTransaction().begin();

            // Create a query to find all employees
            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);

            // Set the first result and max results for pagination
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);

            // Get the list of employees
            List<Employee> employees = query.getResultList();

            // Commit the transaction
            entityManager.getTransaction().commit();

            return employees;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
    @Override
    public void insert(Employee employee) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Employee employee) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin transaction
            entityManager.getTransaction().begin();

            // Merge the updated employee object
            entityManager.merge(employee);

            // Commit the transaction
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int employeeId) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin transaction
            entityManager.getTransaction().begin();

            // Find the employee by ID
            Employee employee = entityManager.find(Employee.class, employeeId);
            if (employee != null) {
                // Set isDelete to true
                employee.setIsDelete(true);
                // Merge the changes
                entityManager.merge(employee);
            }

            // Commit the transaction
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public Employee findById(int employeeId) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Find employee by ID
            return entityManager.find(Employee.class, employeeId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Employee> findAll() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin a transaction
            entityManager.getTransaction().begin();

            // Use named query to find all employees
            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
            List<Employee> employees = query.getResultList();

            // Commit the transaction
            entityManager.getTransaction().commit();

            return employees;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Employee> searchByName(String name) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin transaction
            entityManager.getTransaction().begin();

            // Create a query to search employees by name (case insensitive)
            List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(:name)", Employee.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();

            // Commit the transaction
            entityManager.getTransaction().commit();

            return employees;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int count() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            // Begin transaction
            entityManager.getTransaction().begin();

            // Create a query to count the number of employees
            Long count = entityManager.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.isDelete = false", Long.class)
                    .getSingleResult();

            // Commit the transaction
            entityManager.getTransaction().commit();

            return count.intValue();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
