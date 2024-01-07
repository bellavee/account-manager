package com.sales.accountmanager.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.entity.Image;
import com.sales.accountmanager.model.ApiResult;
import com.sales.accountmanager.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;
    private final AccountService accountService;

    public ApiResult<?> upload(MultipartFile file, Long id) {
        ApiResult<?> result = new ApiResult<>();
        try {
            log.info("{}", file);
            Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setPublicId((String) data.get("public_id"));
            image.setSize((Integer) data.get("bytes"));
            image.setUrl((String) data.get("url"));
            image.setFormat((String) data.get("format"));
            image.setCreatedTime((String) data.get("created_at"));
            imageRepository.save(image);
            accountService.uploadImage(id, image.getPublicId());

            result.setResCode(ApiConstant.ResCode.SUCCESS);
            result.setResDesc(ApiConstant.ResCode.SUCCESS);
            return result;

        } catch (RuntimeException | IOException e) {
            result.setResCode(ApiConstant.ResCode.EXCEPTION);
            result.setResDesc(ApiConstant.ResCode.EXCEPTION);
            log.error("Imange uploading fail: ", e);
            return result;
        }
    }


}
