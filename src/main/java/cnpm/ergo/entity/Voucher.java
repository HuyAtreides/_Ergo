package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher")
@NamedQuery(name="Voucher.findAll", query="select c from Voucher c")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucherId;

    private Date dateStart;

    private Date dateEnd;

    private String code;

    private double discount;

    private enum VoucherType {
        PRICE, PRODUCT
    }

    @Enumerated(EnumType.STRING)
    private VoucherType voucherType;

    public VoucherType getVoucherType() {
        return voucherType;

    }


    protected void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    @OneToOne(mappedBy = "voucher")
    private MarketingCampaign marketingCampaign;

    @PrePersist
    @PreUpdate
    private void setVoucherTypeAutomatically() {
        if (this instanceof VoucherByPrice) {
            this.voucherType = VoucherType.PRICE;
        } else if (this instanceof VoucherByProduct) {
            this.voucherType = VoucherType.PRODUCT;
        }
    }
}
