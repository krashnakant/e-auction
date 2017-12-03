package org.eauction.service;

import org.eauction.service.dto.SubCategoryDTO;
import java.util.List;

/**
 * Service Interface for managing SubCategory.
 */
public interface SubCategoryService {

    /**
     * Save a subCategory.
     *
     * @param subCategoryDTO the entity to save
     * @return the persisted entity
     */
    SubCategoryDTO save(SubCategoryDTO subCategoryDTO);

    /**
     * Get all the subCategories.
     *
     * @return the list of entities
     */
    List<SubCategoryDTO> findAll();

    /**
     * Get the "id" subCategory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SubCategoryDTO findOne(Long id);

    /**
     * Delete the "id" subCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Long count();
}
