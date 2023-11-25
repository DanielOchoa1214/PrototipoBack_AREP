package org.example.service;

import org.example.model.Position;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OptimistService {

    Position[] positions = new Position[]{new Position(1, 1), new Position(3, 4), new Position(10, 2)};
    private final List<Position> safePositions = Arrays.asList(positions);

    public void updatePosition(Position position, int id) {
        safePositions.set(id, position);
    }

    public Position getPosition(int id){
        return safePositions.get(id);
    }

    public List<Position> getSafePositions() {
        return safePositions;
    }
}
