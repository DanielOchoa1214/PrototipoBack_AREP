package org.example.service;

import org.example.model.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

@Service
public class ConservativeService {

    Position[] positions = new Position[]{new Position(1, 1), new Position(3, 4), new Position(10, 2)};
    private final List<Position> safePositions = Arrays.asList(positions);

    public List<Position> updatePosition(Position position, int id) {
        if(position.checkValidPosition(safePositions.get(id))) {
            safePositions.set(id, position);
        }
        return safePositions;
    }
}
