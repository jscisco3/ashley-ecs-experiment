package com.jscisco.lom.domain.attribute;

/**
 * A PeriodicEffect is an effect that can modify attribute base values every turn.
 * Example: basic health regeneration, poison, etc.
 */
public class PeriodicEffect extends Effect {

    private AttributeSet attributeSet;

    @Override
    public void apply(AttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    @Override
    public void tick() {
        super.tick();
        for (AttributeModifier modifier : modifiers) {
            this.attributeSet.applyBaseValueModifier(modifier);
        }
    }
}
