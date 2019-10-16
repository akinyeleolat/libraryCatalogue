package com.fullstack.springbootlibrarycatalogmvc.repository;

import com.fullstack.springbootlibrarycatalogmvc.model.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Catalogue repository.
 *
 * @author Oluwatosin Akinyele
 */
@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {}