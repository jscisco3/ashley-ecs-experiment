package com.jscisco.gdx.ecs.components;

import com.artemis.Component;
import com.jscisco.gdx.Position;

public class PositionComponent extends Component {
    private Position position;

    public PositionComponent() {}

    public static PositionComponent withPosition(Position p) {
        PositionComponent component = new PositionComponent();
        component.position = p;
        return component;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
