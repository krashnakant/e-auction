package org.eauction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Attribute entity.
 */
public class AttributeDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6387932447283416858L;

	private Long id;

    private String attributeName;

    private Long subCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttributeDTO attributeDTO = (AttributeDTO) o;
        if(attributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeDTO{" +
            "id=" + getId() +
            ", attributeName='" + getAttributeName() + "'" +
            "}";
    }
}
