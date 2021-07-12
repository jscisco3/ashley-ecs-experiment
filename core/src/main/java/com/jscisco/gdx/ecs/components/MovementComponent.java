package com.jscisco.gdx.ecs.components;

import com.artemis.Component;
import com.jscisco.gdx.Position;

public class MovementComponent extends Component {

    private Position position;

    public MovementComponent() {}

    private MovementComponent(Position position) {
        this.position = position;
    }

    public static MovementComponent to(Position position) {
        return new MovementComponent(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
