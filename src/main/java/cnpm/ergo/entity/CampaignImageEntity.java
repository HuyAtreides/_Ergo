package cnpm.ergo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaignimage")
public class CampaignImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private MarketingCampaignEntity marketingCampaign;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public MarketingCampaignEntity getMarketingCampaign() {
		return marketingCampaign;
	}

	public void setMarketingCampaign(MarketingCampaignEntity marketingCampaign) {
		this.marketingCampaign = marketingCampaign;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
