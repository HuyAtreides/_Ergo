package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.Voucher;
import cnpm.ergo.entity.VoucherDto;

public interface IVoucherDao {
	Voucher findById(int id);
	List<VoucherDto> voucherByPriceForOder(Order order);
	List<VoucherDto> voucherByPriceNotForOder(Order order);
}
