package org.example.controllers;


import org.example.model.Position;
import org.example.service.ConservativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/conservative/position")
public class Conservative {

    @Autowired
    private ConservativeService service;

    @PutMapping("/{id}")
    public synchronized ResponseEntity<?> updatePosition(@RequestBody Position position, @PathVariable("id") int id){
        try {
            List<Position> positions = service.updatePosition(position, id);
            return ResponseEntity.ok().body(positions);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
