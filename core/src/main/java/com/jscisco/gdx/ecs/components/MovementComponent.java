package com.jscisco.gdx.ecs.components;

import com.badlogic.ashley.core.Component;
import com.jscisco.gdx.Position;

public class MovementComponent implements Component {

    private final Position position;

    private MovementComponent(Position position) {
        this.position = position;
    }

    public static MovementComponent to(Position position) {
        return new MovementComponent(position);
    }

    public Position getPosition() {
        return position;
    }
}
