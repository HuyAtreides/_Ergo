package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "voucherByProductId")
public class VoucherByProduct extends Voucher {
    @ManyToMany
    @JoinTable(
            name = "voucher_product_type",
            joinColumns = @JoinColumn(name = "voucherByProductId"),
            inverseJoinColumns = @JoinColumn(name = "typeId")
    )
    private List<ProductType> productTypes;
    // Getters and Setters
}

