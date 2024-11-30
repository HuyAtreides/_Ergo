package cnpm.ergo.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "voucherByPriceId")
@NamedQuery(name = "VoucherByPrice.findAll", query = "SELECT v FROM VoucherByPrice v")

public class VoucherByPrice extends Voucher {
    private double lowerbound;

    // Getters and Setters
}
