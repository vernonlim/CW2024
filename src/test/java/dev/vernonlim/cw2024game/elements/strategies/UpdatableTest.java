package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatableTest {
    final double EPSILON = 0.001;
    static ArrayList<Firing> firingStrategies;
    static ArrayList<Movement> movementStrategies;
    static ArrayList<Shielding> shieldingStrategies;

    @BeforeAll
    static void startup() {
        firingStrategies = new ArrayList<>();
        movementStrategies = new ArrayList<>();
        shieldingStrategies = new ArrayList<>();

        firingStrategies.add(new BossFiring());
        firingStrategies.add(new RedEnemyFiring());
        firingStrategies.add(new EnemyPlaneFiring());

        movementStrategies.add(new LinearMovement(new Vector(1.0, 1.0)));
        movementStrategies.add(new BossMovement());
        movementStrategies.add(new EnemyPlaneMovement());

        shieldingStrategies.add(new BossShielding());
    }

    @Test
    @DisplayName("All strategy methods should return the same value unless updateStrategyState is called")
    void testValueInvariance() {
        for (Firing firing : firingStrategies) {
            testFiring(firing);
        }

        for (Movement movement : movementStrategies) {
            testMovement(movement);
        }

        for (Shielding shielding : shieldingStrategies) {
            testShielding(shielding);
        }
    }

    void testFiring(Firing firing) {
        ProjectileCode code1 = firing.getProjectileCode();
        ProjectileCode code2 = firing.getProjectileCode();

        boolean fire1 = firing.shouldFire();
        boolean fire2 = firing.shouldFire();

        assertEquals(code1, code2);
        assertEquals(fire1, fire2);
    }

    void testMovement(Movement movement) {
        Vector move1 = movement.getNextMovement();
        Vector move2 = movement.getNextMovement();

        assertEquals(move1.x, move2.x, EPSILON);
        assertEquals(move1.y, move2.y, EPSILON);
    }

    void testShielding(Shielding shielding) {
        boolean shield1 = shielding.isShielded();
        boolean shield2 = shielding.isShielded();

        assertEquals(shield1, shield2);
    }
}