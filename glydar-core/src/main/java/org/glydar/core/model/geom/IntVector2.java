package org.glydar.core.model.geom;

import java.util.Arrays;

public class IntVector2 implements Vector<Integer, IntVector2> {

    private final int x;
    private final int y;

    public IntVector2() {
        this(0, 0);
    }

    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public IntVector2 setX(int newX) {
        return new IntVector2(newX, y);
    }

    public IntVector2 setY(int newY) {
        return new IntVector2(x, newY);
    }

    public IntVector2 add(int value) {
        return new IntVector2(x + value, y + value);
    }

    public IntVector2 add(int ox, int oy) {
        return new IntVector2(x + ox, y + oy);
    }

    @Override
    public IntVector2 add(IntVector2 other) {
        return new IntVector2(x + other.x, y + other.y);
    }

    public IntVector2 subtract(int value) {
        return new IntVector2(x - value, y - value);
    }

    public IntVector2 subtract(int ox, int oy) {
        return new IntVector2(x - ox, y - oy);
    }

    @Override
    public IntVector2 subtract(IntVector2 other) {
        return new IntVector2(x - other.x, y - other.y);
    }

    public IntVector2 multiply(int value) {
        return new IntVector2(x * value, y * value);
    }

    public IntVector2 multiply(int ox, int oy) {
        return new IntVector2(x * ox, y * oy);
    }

    @Override
    public IntVector2 multiply(IntVector2 other) {
        return new IntVector2(x * other.x, y * other.y);
    }

    public IntVector2 divide(int value) {
        return new IntVector2(x / value, y / value);
    }

    public IntVector2 divide(int ox, int oy) {
        return new IntVector2(x / ox, y / oy);
    }

    @Override
    public IntVector2 divide(IntVector2 other) {
        return new IntVector2(x / other.x, y / other.y);
    }

    private int lengthSq() {
        return x * x + y * y;
    }

    @Override
    public double length() {
        return Math.sqrt(lengthSq());
    }

    @Override
    public double distance(IntVector2 other) {
        return x - other.x * y - other.y;
    }

    @Override
    public int compareTo(IntVector2 other) {
        return lengthSq() - other.lengthSq();
    }

    @Override
    public IntVector2 toIntVector2() {
        return this;
    }

    @Override
    public IntVector3 toIntVector3() {
        return new IntVector3(x, y, 0);
    }

    @Override
    public LongVector3 toLongVector3() {
        return new LongVector3(x, y, 0);
    }

    @Override
    public FloatVector3 toFloatVector3() {
        return new FloatVector3(x, y, 0);
    }

    @Override
    public String toString() {
        return "IntVector2(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] { x, y });
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IntVector2)) {
            return false;
        }

        IntVector2 other = (IntVector2) object;
        return x == other.x && y == other.y;
    }
}
