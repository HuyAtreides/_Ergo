package cnpm.ergo.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marketingcampaign")
public class MarketingCampaignEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToOne(mappedBy = "marketingcampaign")
    private Voucher voucher;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @OneToMany(mappedBy = "marketingcampaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignImageEntity> campaignImages;
}
