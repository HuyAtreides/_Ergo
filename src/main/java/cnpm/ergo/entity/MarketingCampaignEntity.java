package cnpm.ergo.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    @Column(name = "voucher_id")
    private Long voucherId;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @OneToMany(mappedBy = "marketingCampaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignImageEntity> campaignImages;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public List<CampaignImageEntity> getCampaignImages() {
		return campaignImages;
	}

	public void setCampaignImages(List<CampaignImageEntity> campaignImages) {
		this.campaignImages = campaignImages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
