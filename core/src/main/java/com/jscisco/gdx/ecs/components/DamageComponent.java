package com.jscisco.gdx.ecs.components;

import com.artemis.Component;

public class DamageComponent extends Component {
    private int damage;

    public DamageComponent() {
    }

    public DamageComponent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
