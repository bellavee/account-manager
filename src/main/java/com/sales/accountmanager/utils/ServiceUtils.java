package com.sales.accountmanager.utils;

import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.model.ApiResult;
import org.slf4j.Logger;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class ServiceUtils {

    public static <D, E> List<D> convertToDto(List<E> entityList, Function<E, D> converterFunction) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        return entityList.stream().map(converterFunction).collect(Collectors.toList());
    }

    public static <DTO, Entity, ID> ApiResult<DTO> saveGeneric(
            DTO dto,
            Function<DTO, ID> getIdFunction,
            Function<ID, Entity> findEntityFunction,
            Supplier<Entity> createEntitySupplier,
            BiFunction<DTO, Entity, Entity> saveEntityFunction,
            Supplier<List<DTO>> findAllSupplier,
            BiConsumer<Entity, LocalDateTime> setCreatedAtFunction,
            BiConsumer<Entity, LocalDateTime> setUpdatedAtFunction,
            Logger log
    ) {
        ApiResult<DTO> result = new ApiResult<>();
        try {
            ID id = getIdFunction.apply(dto);
            Entity entity;
            LocalDateTime now = LocalDateTime.now();

            if (id != null) {
                entity = findEntityFunction.apply(id);
                setUpdatedAtFunction.accept(entity, now);
            } else {
                entity = createEntitySupplier.get();
                setCreatedAtFunction.accept(entity, now);
                setUpdatedAtFunction.accept(entity, now);
            }

            entity = saveEntityFunction.apply(dto, entity);
            result.setResCode(ApiConstant.ResCode.SUCCESS);
            result.setResDesc(ApiConstant.ResDesc.SUCCESS);
            List<DTO> findAllResult = findAllSupplier.get();
            result.setData(findAllResult);
            log.info("Create/Update: {}", entity);
            return result;
        } catch (Exception e) {
            result.setResCode(ApiConstant.ResCode.EXCEPTION);
            result.setResDesc(ApiConstant.ResDesc.EXCEPTION);
            log.error("Exception: Create/Update", e);
            return result;
        }
    }


    public static <ID, Entity> ApiResult<?> deleteGeneric(
            ID id,
            Function<ID, Entity> findEntityFunction,
            Consumer<Entity> deleteEntityConsumer,
            Logger log,
            String entityName) {

        ApiResult<?> result = new ApiResult<>();
        try {
            Entity entity = findEntityFunction.apply(id);
            if (entity != null) {
                deleteEntityConsumer.accept(entity);
                result.setResCode(ApiConstant.ResCode.SUCCESS);
                result.setResDesc(ApiConstant.ResDesc.SUCCESS);
            } else {
                result.setResCode(ApiConstant.ResCode.FAIL);
                result.setResDesc(ApiConstant.ResDesc.FAIL);
            }
            log.info("Deleted {} with id: {}", entityName, id);
        } catch (Exception e) {
            result.setResCode(ApiConstant.ResCode.EXCEPTION);
            result.setResDesc(ApiConstant.ResDesc.EXCEPTION);
            log.error("Exception: Delete {} with id: {}", entityName, id, e);
        }
        return result;
    }


    public static <ID, E, D> D findEntity(
            ID id,
            Function<ID, E> findEntityFunction,
            Function<E, D> convertToDtoFunction) {
        if (!ObjectUtils.isEmpty(id)) {
            E entity = findEntityFunction.apply(id);
            if (!ObjectUtils.isEmpty(entity)) {
                return convertToDtoFunction.apply(entity);
            }
        }
        return null;
    }


}
