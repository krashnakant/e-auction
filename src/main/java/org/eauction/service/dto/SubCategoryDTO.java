package org.eauction.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the SubCategory entity.
 */
public class SubCategoryDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1996009547548304930L;

	private Long id;

    @NotNull
    private String subCategoryName;

    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubCategoryDTO subCategoryDTO = (SubCategoryDTO) o;
        if(subCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
            "id=" + getId() +
            ", subCategoryName='" + getSubCategoryName() + "'" +
            "}";
    }
}
