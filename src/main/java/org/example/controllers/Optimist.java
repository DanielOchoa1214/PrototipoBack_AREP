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
    public void handleUpdatePosition(@DestinationVariable int id, Position position) throws Exception {
        Position updating = service.getPosition(id);
        if(updating.checkValidPosition(position)){
            service.updatePosition(position, id);
        } else {
            msgt.convertAndSend("/topic/rollback", service.getSafePositions());
        }
    }
}
