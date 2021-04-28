package com.jscisco.lom.domain;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.state.AutoexploreState;
import com.jscisco.lom.domain.state.DefaultState;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Tile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoexploreStateTest {

    @Test
    public void whenGettingTheNextAction_andThereAreNoUnexploredTiles_thenWeSetTheStateBackToDefault_andReturnNull() {
        // Given a hero on a level that has every tile explored
        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());
        Hero hero = testHero(Position.of(1, 1));
        level.addHero(hero);

        level.getTiles().forEach(row -> {
            row.forEach(Tile::explore);
        });

        // When we try to get the next action
        hero.setState(new AutoexploreState(hero));
        Action a = hero.nextAction();

        // Then the hero has their state set back to default
        assertThat(a).isNull();
        assertThat(hero.getState()).isInstanceOf(DefaultState.class);

    }

    @Test
    public void givenALevelWithUnexploredTiles_whenIGetMyNextAction_thenIShouldMoveTowardsThatTile() {

        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());
        Hero hero = testHero(Position.of(1, 1));

        level.addHero(hero);
        hero.setState(new AutoexploreState(hero));

        Action action = hero.nextAction();
        assertThat(action).isNotNull();
    }

    @Test
    public void givenALevelWithUnexploredTiles_ifIMoveEnough_thenIWilLExploreThemAll() {
        Level level = new Level(10, 10, new LevelGeneratorStrategy.EmptyLevelStrategy());
        Hero hero = testHero(Position.of(1, 1));

        level.addHero(hero);
        hero.setState(new AutoexploreState(hero));

        while (true) {
            Action a = hero.nextAction();
            if (a == null) {
                break;
            }
            a.execute();
        }

        assertThat(level.getUnexploredPositions().isEmpty()).isTrue();
        assertThat(hero.getState()).isInstanceOf(DefaultState.class);

    }

    private Hero testHero(Position position) {
        return new Hero.Builder()
                .withPosition(position)
                .build();
    }

}
