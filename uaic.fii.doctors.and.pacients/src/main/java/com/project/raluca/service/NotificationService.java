package com.project.raluca.service;


import com.project.raluca.repository.NotificationRepository;
import com.project.raluca.utils.GenericMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.project.raluca.dto.NotificationDTO;
import com.project.raluca.model.Notification;
import com.project.raluca.utils.DTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {


    private final NotificationRepository notificationRepository;
    private GenericMapper dtoHelper = new GenericMapper(Notification.class, NotificationDTO.class);

    @Autowired
    public NotificationService(final NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }


    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public NotificationDTO get(final int id) {
        return (NotificationDTO)dtoHelper.convertToDTO(notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),NotificationDTO.class);
    }

    public List<NotificationDTO> getAll() {
      return  dtoHelper.convertToDtoList(StreamSupport.stream(notificationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()),NotificationDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final NotificationDTO notification) {
        notificationRepository.save((Notification)dtoHelper.convertToEntity(notification,Notification.class));
    }

    public void delete(final int id) {
        notificationRepository.deleteById(id);
    }
}
