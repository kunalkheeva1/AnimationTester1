import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class AnimationTester1 {

}

 class CarShape implements MovableShape{
    private int x;
    private int y;
    private int width;

    public CarShape(int x, int y, int width){
        this.width= width;
        this.y = y;
        this.x = x;
    }

    public void translate(double dx, double dy){
        x+=dx;
        y+=dy;
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double body = new Rectangle2D.Double(x, y+width/6, width-1, width/6);

        Ellipse2D.Double tire1 = new Ellipse2D.Double(x+width/6, y+width/3, width/6, width/6);
        Ellipse2D.Double tire2 = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,width / 6, width / 6);


        Point2D.Double r1 = new Point2D.Double(x+width/6, y+width/6); //for windshields
        Point2D.Double r2 = new Point2D.Double(x+width/3, y);   //roof
        Point2D.Double r3  = new Point2D.Double(x+width*2/3, y);
        Point2D.Double r4 = new Point2D.Double(x+width*5/6, y+width/6);

        Line2D.Double frontGlass = new Line2D.Double(r1, r2);
        Line2D.Double roof = new Line2D.Double(r2, r3);
        Line2D.Double backGlass = new Line2D.Double(r3,r4);

        graphics2D.draw(body);
        graphics2D.draw(tire1);
        graphics2D.draw(tire2);
        graphics2D.draw(frontGlass);
        graphics2D.draw(roof);
        graphics2D.draw(backGlass);
    }


}