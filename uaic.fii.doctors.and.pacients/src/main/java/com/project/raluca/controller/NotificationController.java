package com.project.raluca.controller;

import com.project.raluca.dto.NotificationDTO;
import com.project.raluca.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "notification"
)
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(final NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "getAll"
    )
    public List<NotificationDTO> getAll() {
        return notificationService.getAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "create"
    )
    public ResponseEntity<?> create(@RequestBody NotificationDTO notificationDTO) {
        notificationService.create(notificationDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "get/{id}"
    )
    public NotificationDTO getNotification(@PathVariable final int id) {
        return notificationService.get(id);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        notificationService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
