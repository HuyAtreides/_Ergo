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

    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @Column(unique = true, nullable = false)
    private String code;

    private double discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private MarketingCampaignEntity marketingCampaign;
    // Getters and Setters
}
