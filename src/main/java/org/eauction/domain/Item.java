package org.eauction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "item_title", length = 255, nullable = false)
    private String itemTitle;

    @NotNull
    @Size(max = 255)
    @Column(name = "item_description", length = 255, nullable = false)
    private String itemDescription;

    @NotNull
    @Lob
    @Column(name = "item_image", nullable = false)
    private byte[] itemImage;

    @Column(name = "item_image_content_type", nullable = false)
    private String itemImageContentType;

    @NotNull
    @DecimalMin(value = "1")
    @Column(name = "base_price", precision=10, scale=2, nullable = false)
    private BigDecimal basePrice;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAttribute> itemAttributes = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private SubCategory subCategory;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    private Sale sale;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Item itemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Item itemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public Item itemImage(byte[] itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImageContentType() {
        return itemImageContentType;
    }

    public Item itemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
        return this;
    }

    public void setItemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public Item basePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Set<ItemAttribute> getItemAttributes() {
        return itemAttributes;
    }

    public Item itemAttributes(Set<ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
        return this;
    }

    public Item addItemAttributes(ItemAttribute itemAttribute) {
        this.itemAttributes.add(itemAttribute);
        itemAttribute.setItem(this);
        return this;
    }

    public Item removeItemAttributes(ItemAttribute itemAttribute) {
        this.itemAttributes.remove(itemAttribute);
        itemAttribute.setItem(null);
        return this;
    }

    public void setItemAttributes(Set<ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Item subCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public User getUser() {
        return user;
    }

    public Item user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sale getSale() {
        return sale;
    }

    public Item sale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
        Item item = (Item) o;
        if (item.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", itemTitle='" + getItemTitle() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemImage='" + getItemImage() + "'" +
            ", itemImageContentType='" + getItemImageContentType() + "'" +
            ", basePrice=" + getBasePrice() +
            "}";
    }
}
