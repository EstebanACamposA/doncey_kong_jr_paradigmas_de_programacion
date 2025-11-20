// General functions class.
public class Utils
{
    // Calculates a - b
    public static Vector2D Vector2DDifference(Vector2D a, Vector2D b)
    {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    // Calculates the square of the magnitude of (a - b)
    public static Integer Vector2DSquareDistance(Vector2D a, Vector2D b)
    {
        Vector2D diff = Vector2DDifference(a, b);
        return (diff.x * diff.x) + (diff.y * diff.y);
    }

    // sleep method.
    // time in milliseconds.
    public static void sleep(Integer ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // good practice
        }
    }

    public static Integer Distance(Integer a, Integer b)
    {
        return Math.abs(a-b);
    }

}
