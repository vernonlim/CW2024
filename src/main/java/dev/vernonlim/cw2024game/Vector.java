package dev.vernonlim.cw2024game;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector copy() {
        return new Vector(x, y);
    }

    public boolean isEqualTo(Vector vec) {
        boolean xEquals = Double.compare(x, vec.x) == 0;
        boolean yEquals = Double.compare(y, vec.y) == 0;

        return xEquals && yEquals;
    }

    public void normalize() {
        double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        x /= magnitude;
        y /= magnitude;
    }

    public void scaleBy(double scalar) {
        x *= scalar;
        y *= scalar;
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

    public boolean isOutside(double left, double right, double top, double bottom) {
        boolean outside = x < left;

        if (x > right) {
            outside = true;
        }
        if (y < top) {
            outside = true;
        }
        if (y > bottom) {
            outside = true;
        }

        return outside;
    }
}

