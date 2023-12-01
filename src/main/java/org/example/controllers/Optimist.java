package org.example.controllers;

import org.example.model.Position;
import org.example.service.OptimistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;

@Controller
public class Optimist extends StompSessionHandlerAdapter {

    @Autowired
    SimpMessagingTemplate msgt;

    @Autowired
    private OptimistService service;

    @MessageMapping("/updatePosition.{id}")
    public void handleUpdatePosition(Position position, @DestinationVariable String id) throws Exception {
        System.out.println(service.getSafePositions());
        int id1 = Integer.parseInt(id);
        Position updating = service.getPosition(id1);
        if(updating.checkValidPosition(position)){
            service.updatePosition(position, id1);
        } else {
            msgt.convertAndSend("/topic/rollback", service.getSafePositions());
        }
    }
}
