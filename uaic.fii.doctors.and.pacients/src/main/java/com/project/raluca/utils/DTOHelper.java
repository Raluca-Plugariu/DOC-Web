package com.project.raluca.utils;

import com.project.raluca.model.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DTOHelper<E extends AbstractEntity,D> {
    private static final Logger logger = LoggerFactory.getLogger(DTOHelper.class);
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public DTOHelper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public D convertToDTO(final E entity) {
        D outputDTO = null;
        try {
            outputDTO = dtoClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Problem in getting new instance in order to convert entity to DTO!");
        }

        Optional<E> in = Optional.ofNullable(entity);
        if (in.isPresent())
            BeanUtils.copyProperties(entity, outputDTO);
        else
            throw new IllegalArgumentException("[EntityToDTO]Must not be null");

        return outputDTO;
    }


    public E convertToEntity(final D dto) {
        E outputEntity = null;
        try {
            outputEntity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Problem in getting new instance in order to convert DTO to entity!");
        }
        Optional<D> in = Optional.ofNullable(dto);
        if (in.isPresent())
            BeanUtils.copyProperties(dto, outputEntity);
        else
            throw new IllegalArgumentException("[EntityToDTO]Must not be null");

        return outputEntity;
    }

    public List<D> convertToDtoList(final List<E> entities) {
        List<D> outputList = new ArrayList<>();
        for (E entity : entities) {
          outputList.add(convertToDTO(entity));
        }
        return outputList;
    }


}


