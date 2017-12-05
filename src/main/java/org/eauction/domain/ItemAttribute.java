package org.eauction.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A ItemAttribute.
 */
@Entity
@Table(name = "item_attribute")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAttribute extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "app_value", length = 255, nullable = false)
    private String value;

    @ManyToOne(optional = false)
    @NotNull
    private Attribute attribute;

    @ManyToOne(optional = false)
    @NotNull
    private Item item;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public ItemAttribute value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public ItemAttribute attribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Item getItem() {
        return item;
    }

    public ItemAttribute item(Item item) {
        this.item = item;
        return this;
    }

    public void setItem(Item item) {
        this.item = item;
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
        ItemAttribute itemAttribute = (ItemAttribute) o;
        if (itemAttribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAttribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAttribute{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
