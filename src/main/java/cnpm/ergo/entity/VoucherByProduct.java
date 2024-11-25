package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VoucherByProduct extends Voucher {
    @OneToOne
    private Product product;
    // Getters and Setters
}
