package org.eauction.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the Bid entity.
 */
public class BidDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2526486070373526995L;

	private Long id;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal bidPrice;

    private Long itemId;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long userAccountId) {
        this.accountId = userAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BidDTO bidDTO = (BidDTO) o;
        if(bidDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bidDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BidDTO{" +
            "id=" + getId() +
            ", bidPrice=" + getBidPrice() +
            "}";
    }
}
