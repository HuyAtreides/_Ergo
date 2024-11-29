package cnpm.ergo.DAO.implement;

import cnpm.ergo.entity.Category;
import cnpm.ergo.entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import cnpm.ergo.DAO.implement.*;

import java.util.ArrayList;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IProductDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.ProductImage;

public class ProductDaoImpl implements IProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(product); // Thêm mới sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(product); // Cập nhật sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product); // Xóa sản phẩm
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Product findById(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Product.class, productId); 
    }
    @Override
    public List<Product> findAll(int page, int size) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // Xây dựng JPQL với các join cần thiết
            String jpql = "SELECT p FROM Product p " +
                          "LEFT JOIN p.productImages " +  
                          "LEFT JOIN p.productTypes pt " + 
                          "LEFT JOIN p.category " +      
                          "WHERE p.isDelete = false"; 

            // Tạo truy vấn và phân trang
            List<Product> products = em.createQuery(jpql, Product.class)
                                       .setFirstResult((page - 1) * size)  // Tính toán vị trí bắt đầu
                                       .setMaxResults(size)  // Số lượng sản phẩm trên mỗi trang
                                       .getResultList();
            
            // Ghi log ra console để kiểm tra kết quả
            System.out.println("Fetched " + products.size() + " products on page " + page + ".");

            // In thông tin các sản phẩm (có thể bỏ qua hoặc dùng khi cần debug)
            for (Product product : products) {
            	System.out.println("Product ID: " + product.getProductId(
            			
            			));
                System.out.println("Name: " + product.getName());
                if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
                    for (ProductImage image : product.getProductImages()) {
                        System.out.println("Image: " + image.getProductImage());
                    }
                }
                
                if (product.getProductTypes() != null && !product.getProductTypes().isEmpty()) {
                    for (ProductType type : product.getProductTypes()) {
                        System.out.println("Type: " + type.getColor() + " | Price: " + type.getPrice());
                    }
                }
            }

            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(p) FROM Product p";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); 
    }

    @Override
    public List<Product> searchByName(String name) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :keyword OR p.descript LIKE :keyword";  // Đảm bảo dùng :keyword
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("keyword", "%" + name + "%");  // Đảm bảo dùng "keyword" thay vì "name"
        return query.getResultList(); 
    }


   
    public static void main(String[] args) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        String keyword = "Bàn";  
        List<Product> products = productDao.searchByName(keyword);
        if (products != null && !products.isEmpty()) {
            System.out.println("Sản phẩm tìm thấy:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductId() + " | Tên: " + product.getName() + " | Mô tả: " + product.getDescript());
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào khớp với từ khóa: " + keyword);
        }
    }

     
    }

