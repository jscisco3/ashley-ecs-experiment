package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeSetTest {

    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = new AttributeSet();
    }

    @Test
    public void whenHealthIsIncreasedByInstantEffect_thenItCannotBeHigherThanMaxHealth() {
        Effect effect = new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(attributeSet.getHealth())
                        .withMagnitude(1000f)
                        .withOperator(Attribute.Operator.ADD));

        float maxHealthValue = attributeSet.getAttributeValue(attributeSet.getMaxHealth());

        effect.apply(this.attributeSet);

        assertThat(this.attributeSet.getAttributeValue(attributeSet.getHealth())).isEqualTo(maxHealthValue);
        assertThat(this.attributeSet.getHealth().getBaseValue()).isEqualTo(maxHealthValue);
    }
}