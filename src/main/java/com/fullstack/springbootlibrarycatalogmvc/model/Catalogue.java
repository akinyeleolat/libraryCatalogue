package com.fullstack.springbootlibrarycatalogmvc.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

/**
 * The type catalogue.
 *
 *
 */
@Entity
@Table(name = "catalogue",uniqueConstraints={@UniqueConstraint(columnNames={"serial_num"})})
@EntityListeners(AuditingEntityListener.class)
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_num", nullable = false, unique = true)
    private String serialNum;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets serial number.
     *
     * @return the serial number
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets serial number.
     *
     * @param serialNum the serial number
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Get title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the serial number
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    // releaseYear
    /**
     * Gets release year.
     *
     * @return the release year
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets Genre
     *
     * @return the genre
     */
    public String getGenre(){
        return genre;
    }

    /**
     *
     * @param genre the genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }



    @Override
    public String toString() {
        // sn title author release_year genre
        return "Catalogue{" +
                "id=" + id +
                ", serialNum='" + serialNum + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", genre='" + genre + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}