package dev.vernonlim.cw2024game.elements;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getAngle() {
        return Math.atan(y / x);
    }

    public void scaleBy(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public static Vector fromMagnitudeAngle(double magnitude, double angle) {
        return new Vector(
                magnitude * Math.cos(angle),
                magnitude * Math.sin(angle)
        );
    }

    public void ensureInBounds(double left, double right, double top, double bottom) {
        if (x < left) {
            x = left;
        }
        if (x > right) {
            x = right;
        }
        if (y < top) {
            y = top;
        }
        if (y > bottom) {
            y = bottom;
        }
    }
}

