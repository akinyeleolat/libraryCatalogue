package com.fullstack.springbootlibrarycatalogmvc.repository;

import com.fullstack.springbootlibrarycatalogmvc.model.Catalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Catalogue repository.
 *
 * @author Oluwatosin Akinyele
 */
@Repository
public interface CatalogueSearchRepository extends JpaRepository<Catalogue, Long> {
    @Query("select cat from Catalogue cat " +
            "where (:title='' or cat.title like %:title%) "
            +"or (:releaseYear='' or cat.releaseYear like %:releaseYear%) "
            +"or (:author='' or cat.author like %:author%) "
     +"or (:genre='' or cat.genre like %:genre%) ")
    Page<Catalogue> advancedSearch(
            @Param("title") String title,
            @Param("releaseYear") String releaseYear,
            @Param("author") String author,
            @Param("genre") String genre,
            Pageable page);
}