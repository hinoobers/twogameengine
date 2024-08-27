package org.hinoob.tge.util;

public class CollisionUtils {

    public static boolean isColliding(DimensionBox d1, DimensionBox d2) {
        return d1.x < d2.x + d2.width && d1.x + d1.width > d2.x && d1.y < d2.y + d2.height && d1.y + d1.height > d2.y;
    }
}
