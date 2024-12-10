package dev.vernonlim.cw2024game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {
    final double EPSILON = 0.001;

    @Test
    @DisplayName("Should make a new copy identical to the original in value, but as a separate object")
    void copy() {
        Vector vec1 = new Vector(1.0, 2.0);
        Vector vec2 = vec1.copy();

        // compares by reference
        assertNotEquals(vec1, vec2);

        // compares by value
        assertEquals(vec1.x, vec2.x, EPSILON);
        assertEquals(vec1.y, vec2.y, EPSILON);
    }

    @Test
    @DisplayName("Should return true if the vectors are numerically equal")
    void isEqualTo() {
        Vector vec1 = new Vector(10.0, 15.0);
        Vector vec2 = new Vector(10.0, 15.0);
        Vector vec3 = new Vector(10.0, 15.01);

        assertTrue(vec1.isEqualTo(vec2));
        assertFalse(vec1.isEqualTo(vec3));
    }

    @Test
    @DisplayName("Should ensure the magnitude of the vector is 1")
    void normalize() {
        Vector vec1 = new Vector(10.0, 15.0);
        vec1.normalize();

        double magnitude = Math.sqrt(Math.pow(vec1.x, 2) + Math.pow(vec1.y, 2));

        assertEquals(1.0, magnitude, EPSILON);
    }

    @Test
    @DisplayName("Should scale the vector by a magnitude")
    void scaleBy() {
        Vector vec1 = new Vector(2.0, 5.0);
        vec1.scaleBy(2.0);

        assertEquals(4.0, vec1.x, EPSILON);
        assertEquals(10.0, vec1.y, EPSILON);
    }

    @Test
    @DisplayName("Should clamp the vector into a region")
    void ensureInBounds() {
        double left = 10.0;
        double right = 50.0;
        double top = 20.0;
        double bottom = 60.0;

        // should be clamped to 10, 20
        Vector topLeft = new Vector(5.0, 10.0);

        // should be clamped to 50, 60
        Vector bottomRight = new Vector(55.0, 65.0);

        topLeft.ensureInBounds(left, right, top, bottom);
        bottomRight.ensureInBounds(left, right, top, bottom);

        assertEquals(10.0, topLeft.x, EPSILON);
        assertEquals(20.0, topLeft.y, EPSILON);

        assertEquals(50.0, bottomRight.x, EPSILON);
        assertEquals(60.0, bottomRight.y, EPSILON);
    }

    @Test
    @DisplayName("Should return true if the vector is outside the given bounds")
    void isOutside() {
        double left = 10.0;
        double right = 50.0;
        double top = 20.0;
        double bottom = 60.0;

        // should both be false
        Vector topLeft = new Vector(5.0, 10.0);
        Vector bottomRight = new Vector(55.0, 65.0);

        // should be true
        Vector inside = new Vector(30.0, 40.0);

        assertTrue(topLeft.isOutside(left, right, top, bottom));
        assertTrue(bottomRight.isOutside(left, right, top, bottom));

        assertFalse(inside.isOutside(left, right, top, bottom));
    }
}