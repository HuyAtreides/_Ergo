package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IAdministratorDAO;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Administrator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AdministratorDAOImpl implements IAdministratorDAO {
    @Override
    public Administrator getAdministrator(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // Create a typed query to find the administrator by email
            TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a WHERE a.email = :email", Administrator.class);
            query.setParameter("email", email);

            // Retrieve the result (assuming email is unique, we use getSingleResult)
            Administrator administrator = query.getSingleResult();
            return administrator;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        AdministratorDAOImpl administratorDAO = new AdministratorDAOImpl();
        Administrator administrator = administratorDAO.getAdministrator("anhQuang@gmail.com");
        if (administrator != null) {
            System.out.println(administrator.getEmail());
            System.out.println(administrator.getPassword());
        } else {
            System.out.println("Administrator not found.");
        }
    }
}
