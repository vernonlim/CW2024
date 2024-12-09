package dev.vernonlim.cw2024game;

/**
 * Represents a generic 2D vector consisting of x and y components.
 */
public class Vector {
    /**
     * The x component of the vector.
     */
    public double x;

    /**
     * The y component of the vector.
     */
    public double y;

    /**
     * Constructs a new Vector with the specified x and y components.
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a by-value copy of this vector.
     *
     * @return a new Vector with identical x and y components to this vector
     */
    public Vector copy() {
        return new Vector(x, y);
    }

    /**
     * Checks if this vector is equal to another vector by-value.
     *
     * @param vec the vector to compare equality with
     * @return true if the vectors have the same components, false if otherwise
     */
    public boolean isEqualTo(Vector vec) {
        boolean xEquals = Double.compare(x, vec.x) == 0;
        boolean yEquals = Double.compare(y, vec.y) == 0;

        return xEquals && yEquals;
    }

    /**
     * Normalizes this vector to have a magnitude of 1.
     */
    public void normalize() {
        double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        x /= magnitude;
        y /= magnitude;
    }

    /**
     * Scales this vector by a specified scalar.
     *
     * @param scalar the scalar to multiply the vector by
     */
    public void scaleBy(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    /**
     * Ensures that the vector's components are within the specified bounds.
     * <p>
     * x is measured from the left, meaning that 0 is the left of the screen.
     * <p>
     * y is measured from the top, meaning that 0 is the top of the screen.
     *
     * @param left   the minimum x value
     * @param right  the maximum x value
     * @param top    the minimum y value
     * @param bottom the maximum y value
     */
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

    /**
     * Checks if the vector's components are outside the specified bounds.
     * <p>
     * x is measured from the left, meaning that 0 is the left of the screen.
     * <p>
     * y is measured from the top, meaning that 0 is the top of the screen.
     *
     * @param left   the minimum x value
     * @param right  the maximum x value
     * @param top    the minimum y value
     * @param bottom the maximum y value
     * @return true if the vector is outside the specified bounds, false otherwise
     */
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

