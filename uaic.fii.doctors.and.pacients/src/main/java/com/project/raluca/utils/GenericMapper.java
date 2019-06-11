package com.project.raluca.utils;
import com.project.raluca.model.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericMapper<E extends AbstractEntity,D> {

    private static ModelMapper modelMapper = new ModelMapper();
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Autowired
    public GenericMapper(Class<E> entityClass, Class<D> dtoClass){
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public  D convertToDTO  (E entity, Class<D> clazz){
        if(entity ==null)
            return null;
        return modelMapper.map(entity, clazz);
    }

    public E convertToEntity(D dto, Class<E> clazz){
        return modelMapper.map(dto,clazz);
    }

    public List<D> convertToDtoList(List<E> entities, Class<D> clazz){
        List<D> outputList = new ArrayList<>();
        for (E entity : entities) {
            outputList.add(convertToDTO(entity,clazz));
        }
        return outputList;
    }

}
