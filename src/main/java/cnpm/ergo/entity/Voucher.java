package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucherId;

    private Date dateStart;

    private Date dateEnd;

    private String code;

    private double discount;

    @OneToOne(mappedBy = "voucher")
    private MarketingCampaign marketingCampaign;

    // Getters and Setters
}
