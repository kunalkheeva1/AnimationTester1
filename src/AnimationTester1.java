import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class AnimationTester1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        final int iconWidth = 400;
        final int iconHeight = 100;
        final int carWidth= 100;
        final int delay = 100;



        final MovableShape shape = new PlaneShape(0,0,carWidth);

        final CarShape.ShapeIcon icon = new CarShape.ShapeIcon(shape, iconWidth, iconHeight);
        final CarShape.ShapeIcon icon2 = new CarShape.ShapeIcon(shape, iconWidth, iconHeight);
        final CarShape.ShapeIcon icon3 = new CarShape.ShapeIcon(shape, iconWidth, iconHeight);

        final JLabel label = new JLabel(icon);
        final JLabel label1 = new JLabel(icon2);
        final JLabel label2 = new JLabel(icon3);


        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.setSize(500,600);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        Timer t = new Timer(delay, new
                ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        shape.translate(1,0);
                        label.repaint();
                        label1.repaint();
                        label2.repaint();
                    }
                });
        t.start();
    }
}

class PlaneShape implements MovableShape {
    private int x;
    private int y;
    private int width;

    public PlaneShape(int x, int y, int width){
        this.x = x;
        this.y = y;
        this.width= width;
    }

    public void translate(double dx, double dy){
        x+=dx;
        y+=dy;
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double body = new Rectangle2D.Double(x, y+ width/6, width-1, width/6);
        Ellipse2D.Double frontTire = new Ellipse2D.Double(x+width/6, y+width/3, width/6, width/6);
        Ellipse2D.Double rearTire = new Ellipse2D.Double(x+width*2/3, y+width/3, width/6, width/6);

        Point2D.Double r1 = new Point2D.Double(x+width/6,y+width/6);
        Point2D.Double r2 = new Point2D.Double(x+width/3, y);
        Point2D.Double r3 = new Point2D.Double(x+width/3,y);
        Point2D.Double r4 = new Point2D.Double(x+width*5/6, y+width/6);

        Line2D.Double frontGlass = new Line2D.Double(r1,r2);
        Line2D.Double roof = new Line2D.Double(r2, r3);
        Line2D.Double rearGlass = new Line2D.Double(r3,r4);


        //changing the colors of the tires
        graphics2D.setColor(Color.GREEN);
        graphics2D.fill(frontTire);
        graphics2D.setColor(Color.BLUE);
        graphics2D.fill(rearTire);
        graphics2D.setColor(Color.RED);
        graphics2D.fill(body);
        graphics2D.draw(frontGlass);
        graphics2D.draw(roof);
        graphics2D.draw(rearGlass);

        //second car

    }


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

    static class ShapeIcon implements Icon {
        private int width;
        private int height;
        private MovableShape shape;

        public ShapeIcon(MovableShape shape, int width, int height){
            this.width = width;
            this.height = height;
            this.shape = shape;
        }

        public int getIconWidth() {
            return width;
        }
        public int getIconHeight() {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y){
            Graphics2D g2 = (Graphics2D)g;
            shape.draw(g2);
        }


    }

}