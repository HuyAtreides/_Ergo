package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IVoucherDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.Voucher;
import cnpm.ergo.entity.VoucherDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VoucherDaoImpl implements IVoucherDao{
	
	
	@Override
	public Voucher findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
        return em.find(Voucher.class, id);
	}



	@Override
	public List<VoucherDto> voucherByPriceForOder(Order order) {
	    EntityManager em = JPAConfig.getEntityManager();

	    String jpql = "SELECT new cnpm.ergo.entity.VoucherDto(vp.lowerbound, vp.voucherId, v.voucherId, v.dateStart, v.dateEnd, v.code, v.discount) " +
	                  "FROM VoucherByPrice vp " +
	                  "JOIN Voucher v ON vp.voucherId = v.voucherId " +  
	                  "WHERE vp.lowerbound <= :totalCost " + 
	                  "AND :orderDate BETWEEN v.dateStart AND v.dateEnd";  

	    TypedQuery<VoucherDto> query = em.createQuery(jpql, VoucherDto.class);
	    query.setParameter("totalCost", order.getTotalCost());
	    query.setParameter("orderDate", order.getOrderDate());  // Truyền orderDate từ Order vào câu truy vấn

	    return query.getResultList();
	}



	@Override
    public List<VoucherDto> voucherByPriceNotForOder(Order order) {
		EntityManager em = JPAConfig.getEntityManager();

		String jpql = "SELECT new cnpm.ergo.entity.VoucherDto(vp.lowerbound, vp.voucherId, v.voucherId, v.dateStart, v.dateEnd, v.code, v.discount) " +
	                "FROM VoucherByPrice vp " +
	                "JOIN Voucher v ON vp.voucherId = v.voucherId " +  
	                "WHERE vp.lowerbound > :totalCost " + 
	                "AND :orderDate BETWEEN v.dateStart AND v.dateEnd";  
	
	  TypedQuery<VoucherDto> query = em.createQuery(jpql, VoucherDto.class);
	  query.setParameter("totalCost", order.getTotalCost());
	  query.setParameter("orderDate", order.getOrderDate());  
	
	  return query.getResultList();
    }
	
	public static void main(String[] args) {
//		VoucherDaoImpl v = new VoucherDaoImpl();
//		OrderDaoImpl od = new OrderDaoImpl();
//		Order o = od.findById(1);
//		//System.out.print(o);
//		List<VoucherDto> list = v.voucherByPriceForOder(o);
//		System.out.print(list);
	}
	
}
