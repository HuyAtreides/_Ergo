package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "voucherByPriceId")
public class VoucherByPrice extends Voucher {
    private double lowerbound;

	@Override
	public String toString() {
		return "VoucherByPrice [lowerbound=" + lowerbound + "]";
	}

    
}
