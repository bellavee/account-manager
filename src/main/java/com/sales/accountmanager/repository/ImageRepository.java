package com.sales.accountmanager.repository;

import com.sales.accountmanager.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByPublicId(String publicId);

}
