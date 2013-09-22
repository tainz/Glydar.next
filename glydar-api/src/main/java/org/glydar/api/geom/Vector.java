package org.glydar.api.geom;

public interface Vector<N extends Number, V extends Vector<N, V>> extends Comparable<V> {

    double length();

    double distance(V other);

    V add(V other);

    V subtract(V other);

    V multiply(V other);

    V divide(V other);

    IntVector2 toIntVector2();

    IntVector3 toIntVector3();

    LongVector3 toLongVector3();

    FloatVector3 toFloatVector3();
}
