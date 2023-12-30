package com.sales.accountmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl; // URL of the image

    private String title;    // Optional: Title of the image

    private String format;   // Optional: Image format (jpg, png, etc.)

    private Long size;

    private Date createdTime;

    private Date updatedTime;

}
