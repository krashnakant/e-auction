package org.eauction.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the Item entity.
 */
public class ItemDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8579554017022910111L;

	private Long id;

    @NotNull
    private String itemTitle;

    @NotNull
    @Lob
    private byte[] itemDescription;
    private String itemDescriptionContentType;

    @Lob
    private byte[] itemImage;
    private String itemImageContentType;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal basePrice;

    private Long saleId;

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

    public byte[] getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(byte[] itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescriptionContentType() {
        return itemDescriptionContentType;
    }

    public void setItemDescriptionContentType(String itemDescriptionContentType) {
        this.itemDescriptionContentType = itemDescriptionContentType;
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

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
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
