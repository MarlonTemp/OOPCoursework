class Vector {
    int x, y;
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector[] addOffset(Vector[] points, Vector v) {
        Vector[] newPoints = new Vector[points.length];
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = new Vector(points[i].x + v.x, points[i].y + v.y);
        }
        return newPoints;
    }
}