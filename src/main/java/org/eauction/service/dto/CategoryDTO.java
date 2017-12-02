package org.eauction.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the Category entity.
 */
public class CategoryDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2139127096465541230L;

	private Long id;

    @NotNull
    private String categoryName;

    @Lob
    private byte[] categoryImage;
    private String categoryImageContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageContentType() {
        return categoryImageContentType;
    }

    public void setCategoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryDTO categoryDTO = (CategoryDTO) o;
        if(categoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryImage='" + getCategoryImage() + "'" +
            "}";
    }
}
