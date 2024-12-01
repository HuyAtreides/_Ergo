package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@NamedQuery(
        name = "VoucherByPrice.findAll",
        query = "SELECT c FROM VoucherByPrice c"
)

@PrimaryKeyJoinColumn(name = "voucherByPriceId")
public class VoucherByPrice extends Voucher {
    private double lowerbound;

    // Getters and Setters
}
