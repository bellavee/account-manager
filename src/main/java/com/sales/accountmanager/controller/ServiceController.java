package com.sales.accountmanager.controller;

import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.constant.PathConstant;
import com.sales.accountmanager.entity.Image;
import com.sales.accountmanager.model.ApiResult;
import com.sales.accountmanager.repository.ImageRepository;
import com.sales.accountmanager.service.AccountService;
import com.sales.accountmanager.service.CloudinaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(PathConstant.API)
public class ServiceController {

    private final CloudinaryService cloudinaryService;
    public ServiceController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping(PathConstant.UPLOAD)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        try {
            ApiResult<?> result = this.cloudinaryService.upload(file, id);
            if (result.getResCode().equals(ApiConstant.ResCode.SUCCESS)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Event update failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
