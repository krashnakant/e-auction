package org.eauction.service.dto;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Sale entity.
 */
public class SaleDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8351100225044442513L;

	private Long id;

    @NotNull
    private String auctionTitle;

    @NotNull
    private ZonedDateTime start;

    @NotNull
    private ZonedDateTime end;

    private Long categoryId;

    private Set<UserAccountDTO> accounts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<UserAccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<UserAccountDTO> userAccounts) {
        this.accounts = userAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SaleDTO saleDTO = (SaleDTO) o;
        if(saleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), saleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SaleDTO{" +
            "id=" + getId() +
            ", auctionTitle='" + getAuctionTitle() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            "}";
    }
}
