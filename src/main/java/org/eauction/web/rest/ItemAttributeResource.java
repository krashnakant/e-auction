package org.eauction.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.eauction.domain.ItemAttribute;

import org.eauction.repository.ItemAttributeRepository;
import org.eauction.web.rest.errors.BadRequestAlertException;
import org.eauction.web.rest.util.HeaderUtil;
import org.eauction.service.dto.ItemAttributeDTO;
import org.eauction.service.mapper.ItemAttributeMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ItemAttribute.
 */
@RestController
@RequestMapping("/api")
public class ItemAttributeResource {

	private final Logger log = LoggerFactory.getLogger(ItemAttributeResource.class);

	private static final String ENTITY_NAME = "itemAttribute";

	private final ItemAttributeRepository itemAttributeRepository;

	private final ItemAttributeMapper itemAttributeMapper;

	public ItemAttributeResource(ItemAttributeRepository itemAttributeRepository,
			ItemAttributeMapper itemAttributeMapper) {
		this.itemAttributeRepository = itemAttributeRepository;
		this.itemAttributeMapper = itemAttributeMapper;
	}

	/**
	 * POST /item-attributes : Create a new itemAttribute.
	 *
	 * @param itemAttributeDTO
	 *            the itemAttributeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         itemAttributeDTO, or with status 400 (Bad Request) if the
	 *         itemAttribute has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/item-attributes")
	@Timed
	public ResponseEntity<ItemAttributeDTO> createItemAttribute(@Valid @RequestBody ItemAttributeDTO itemAttributeDTO)
			throws URISyntaxException {
		log.debug("REST request to save ItemAttribute : {}", itemAttributeDTO);
		if (itemAttributeDTO.getId() != null) {
			throw new BadRequestAlertException("A new itemAttribute cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		ItemAttribute itemAttribute = itemAttributeMapper.toEntity(itemAttributeDTO);
		itemAttribute = itemAttributeRepository.save(itemAttribute);
		ItemAttributeDTO result = itemAttributeMapper.toDto(itemAttribute);
		return ResponseEntity.created(new URI("/api/item-attributes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /item-attributes : Updates an existing itemAttribute.
	 *
	 * @param itemAttributeDTO
	 *            the itemAttributeDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         itemAttributeDTO, or with status 400 (Bad Request) if the
	 *         itemAttributeDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the itemAttributeDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/item-attributes")
	@Timed
	public ResponseEntity<ItemAttributeDTO> updateItemAttribute(@Valid @RequestBody ItemAttributeDTO itemAttributeDTO)
			throws URISyntaxException {
		log.debug("REST request to update ItemAttribute : {}", itemAttributeDTO);
		if (itemAttributeDTO.getId() == null) {
			return createItemAttribute(itemAttributeDTO);
		}
		ItemAttribute itemAttribute = itemAttributeMapper.toEntity(itemAttributeDTO);
		itemAttribute = itemAttributeRepository.save(itemAttribute);
		ItemAttributeDTO result = itemAttributeMapper.toDto(itemAttribute);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAttributeDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /item-attributes : get all the itemAttributes.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         itemAttributes in body
	 */
	@GetMapping("/item-attributes")
	@Timed
	public List<ItemAttributeDTO> getAllItemAttributes() {
		log.debug("REST request to get all ItemAttributes");
		List<ItemAttribute> itemAttributes = itemAttributeRepository.findAll();
		return itemAttributeMapper.toDto(itemAttributes);
	}

	@GetMapping("/item-attributes/item/{id}")
	@Timed
	public List<ItemAttributeDTO> getAllItemAttributesBySale(@PathVariable Long id) {
		log.debug("REST request to get all ItemAttributes");
		List<ItemAttribute> itemAttributes = itemAttributeRepository.findAllByItem_Id(id);
		return itemAttributeMapper.toDto(itemAttributes);
	}

	/**
	 * GET /item-attributes/:id : get the "id" itemAttribute.
	 *
	 * @param id
	 *            the id of the itemAttributeDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         itemAttributeDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/item-attributes/{id}")
	@Timed
	public ResponseEntity<ItemAttributeDTO> getItemAttribute(@PathVariable Long id) {
		log.debug("REST request to get ItemAttribute : {}", id);
		ItemAttribute itemAttribute = itemAttributeRepository.findOne(id);
		ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(itemAttribute);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemAttributeDTO));
	}

	/**
	 * DELETE /item-attributes/:id : delete the "id" itemAttribute.
	 *
	 * @param id
	 *            the id of the itemAttributeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/item-attributes/{id}")
	@Timed
	public ResponseEntity<Void> deleteItemAttribute(@PathVariable Long id) {
		log.debug("REST request to delete ItemAttribute : {}", id);
		itemAttributeRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
