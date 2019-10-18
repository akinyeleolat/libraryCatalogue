package com.fullstack.springbootlibrarycatalogmvc.controller;

import com.fullstack.springbootlibrarycatalogmvc.exception.ResourceNotFoundException;
import com.fullstack.springbootlibrarycatalogmvc.model.Catalogue;
import com.fullstack.springbootlibrarycatalogmvc.repository.CatalogueRepository;
import com.fullstack.springbootlibrarycatalogmvc.repository.CatalogueSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * The type Catalogue controller.
 *
 * @author Oluwatosin Akinyele
 */
@RestController
@RequestMapping("/")
public class CatalogueApiController {

    @Autowired
    private CatalogueRepository catalogueRepository;
    @Autowired
    private CatalogueSearchRepository catalogueSearchRepository;

    /**
     * Get all catalogues.
     *
     * @return the list
     */
    @GetMapping("/api/v1/catalogues")
    public Page<Catalogue> getAllCatalogue(Pageable page) {
        return catalogueRepository.findAll(page);
    }

    /**
     * Gets catalogue by id.
     *
     * @param id the catalogue id
     * @return the catalogue by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/api/v1/catalogues/{id}")
    public ResponseEntity<Catalogue> getUsersById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Catalogue catalogue =
                catalogueRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Catalogue not found on :: " + id));
        return ResponseEntity.ok().body(catalogue);
    }

    /**
     * Create catalogue.
     *
     * @param catalogue the catalogue
     * @return the catalogue
     */
    @PostMapping("/api/v1/catalogues")
    public Catalogue createCatalogue(@Valid @RequestBody Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    /**
     * Filter catalogue.
     *
     *
     * @return the all catalogue if no params else filter catalogue
     */
    @GetMapping("/api/v1/catalogue/filter")
    public Page<Catalogue> filterCatalogue(
            @Param("title") String title,
            @Param("releaseYear") String releaseYear,
            @Param("author") String author,
            @Param("genre") String genre,
            Pageable page
    ){
        if(null==title && null==releaseYear && null==author && null==genre){
            return catalogueRepository.findAll(page);
        }
        else {
            return catalogueSearchRepository.advancedSearch(title, releaseYear, author, genre, page);
        }
    }
}