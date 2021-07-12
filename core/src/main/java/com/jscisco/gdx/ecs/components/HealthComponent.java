package com.jscisco.gdx.ecs.components;

import com.artemis.Component;

public class HealthComponent extends Component {
    private int current;
    private int max;

    public HealthComponent() {
    }

    public HealthComponent(int max) {
        this.current = max;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void subtract(int damage) {
        this.current -= damage;
    }
}
