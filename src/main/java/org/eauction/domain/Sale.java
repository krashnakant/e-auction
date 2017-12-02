package org.eauction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Sale.
 */
@Entity
@Table(name = "sale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "auction_title", nullable = false)
    private String auctionTitle;

    @NotNull
    @Column(name = "app_start", nullable = false)
    private ZonedDateTime start;

    @NotNull
    @Column(name = "app_end", nullable = false)
    private ZonedDateTime end;

    @OneToMany(mappedBy = "sale")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    private Category category;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "sale_accounts",
               joinColumns = @JoinColumn(name="sales_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="accounts_id", referencedColumnName="id"))
    private Set<UserAccount> accounts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public Sale auctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
        return this;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public Sale start(ZonedDateTime start) {
        this.start = start;
        return this;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public Sale end(ZonedDateTime end) {
        this.end = end;
        return this;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Sale items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Sale addItems(Item item) {
        this.items.add(item);
        item.setSale(this);
        return this;
    }

    public Sale removeItems(Item item) {
        this.items.remove(item);
        item.setSale(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Category getCategory() {
        return category;
    }

    public Sale category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<UserAccount> getAccounts() {
        return accounts;
    }

    public Sale accounts(Set<UserAccount> userAccounts) {
        this.accounts = userAccounts;
        return this;
    }

    public Sale addAccounts(UserAccount userAccount) {
        this.accounts.add(userAccount);
        userAccount.getSales().add(this);
        return this;
    }

    public Sale removeAccounts(UserAccount userAccount) {
        this.accounts.remove(userAccount);
        userAccount.getSales().remove(this);
        return this;
    }

    public void setAccounts(Set<UserAccount> userAccounts) {
        this.accounts = userAccounts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sale sale = (Sale) o;
        if (sale.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sale{" +
            "id=" + getId() +
            ", auctionTitle='" + getAuctionTitle() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            "}";
    }
}
