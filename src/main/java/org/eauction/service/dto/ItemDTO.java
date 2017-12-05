package org.eauction.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Item entity.
 */
public class ItemDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String itemTitle;

    @NotNull
    @Size(max = 255)
    private String itemDescription;

    @NotNull
    @Lob
    private byte[] itemImage;
    private String itemImageContentType;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal basePrice;

    private Long subCategoryId;

    private String subCategorySubCategoryName;

    private Long userId;

    private String userLogin;

    private Long saleId;

    private String saleAuctionTitle;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImageContentType() {
        return itemImageContentType;
    }

    public void setItemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategorySubCategoryName() {
        return subCategorySubCategoryName;
    }

    public void setSubCategorySubCategoryName(String subCategorySubCategoryName) {
        this.subCategorySubCategoryName = subCategorySubCategoryName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getSaleAuctionTitle() {
        return saleAuctionTitle;
    }

    public void setSaleAuctionTitle(String saleAuctionTitle) {
        this.saleAuctionTitle = saleAuctionTitle;
    }

    public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemDTO itemDTO = (ItemDTO) o;
        if(itemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + getId() +
            ", itemTitle='" + getItemTitle() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemImage='" + getItemImage() + "'" +
            ", basePrice=" + getBasePrice() +
            "}";
    }
}
