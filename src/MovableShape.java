import java.awt.*;

public interface MovableShape {
    void draw(Graphics2D graphics2D);
    void translate(double dx, double dy);
}
