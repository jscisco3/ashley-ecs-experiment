package com.jscisco.ecs.components;

import com.badlogic.ashley.core.Component;
import com.jscisco.ecs.Position;

public record MovementComponent(Position position) implements Component {

    public static MovementComponent to(Position position) {
        return new MovementComponent(position);
    }

}
