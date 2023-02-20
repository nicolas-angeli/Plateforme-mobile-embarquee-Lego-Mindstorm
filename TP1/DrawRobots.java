

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawRobots {
    private final List<RobotInterface> robots;
    private final JFrame frame;

    private static final int offset = 30;
    private static final int robotWidth = 75;
    private static final int robotHeight = 75;

    public DrawRobots() {

        robots = new ArrayList<RobotInterface>();
        frame = new JFrame("EIG-2221 Robot Display");

        final Image nxt = Toolkit.getDefaultToolkit().createImage("nxt.png");
        final Image nxtScaled = nxt.getScaledInstance(robotWidth,robotHeight,Image.SCALE_DEFAULT);

        JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    Graphics2D g2d = (Graphics2D) g;

                    g2d.setColor(Color.BLACK);
                    Stroke stroke = new BasicStroke(1);
                    g2d.setStroke(stroke);

                    g2d.drawLine(offset, offset, frame.getWidth()-offset, offset);

                    Arrow a_axis = new Arrow(5);
                    a_axis.drawArrowHead(g2d, frame.getWidth()-offset, offset, frame.getWidth()-offset+5, offset);

                    g2d.drawLine(offset, offset, offset, getHeight()-offset);
                    a_axis.drawArrowHead(g2d, offset, getHeight()-offset, offset, getHeight()-offset+5);	

                    g2d.setColor(Color.RED);

                    for(RobotInterface r : robots) {

                        AffineTransform at = new AffineTransform();

                        // 4. translate it to the center of the component
                        at.translate(r.getPosX()+offset, r.getPosy()+offset);
                        // 3. do the actual rotation
                        at.rotate(Math.toRadians(-r.getAngle()+90));
                        // 2. scale the image
                        //at.scale(0.5, 0.5);
                        // 1. translate the object to rotate around the center
                        at.translate(-nxtScaled.getWidth(this) / 2, -nxtScaled.getHeight(this) / 2);

                        g2d.drawImage(nxtScaled,at, this);			
                    }

                }	
            };
        panel.setPreferredSize(new Dimension(600,600));
        frame.setContentPane(panel);
        frame.pack();

    }

    public void sleep(final int millis) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(millis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        } catch (InvocationTargetException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public void show() {	
        frame.setVisible(true);
    }

    public void hide() {	
        frame.setVisible(false);
        frame.dispose();
    }

    public void refresh() {
        frame.repaint();
    }

    public void addRobot(final Robot r){
        robots.add(r);
        refresh();
    }

    public void removeRobot(final Robot r) {
        robots.remove(r);
        refresh();
    }

    static class Arrow
    {
        private final Polygon arrowHead = new Polygon ();

        /**
         * Create an arrow.
         *
         * @see https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
         *
         * @param size Size of the arrow to draw.
         */
        public Arrow (int size)
        {
            // create a triangle centered on (0,0) and pointing right
            arrowHead.addPoint (size, 0);
            arrowHead.addPoint (-size, -size);
            arrowHead.addPoint (-size, size);
            //arrowHead.addPoint (0, 0); // Another style
        }

        /**
         * Draw the arrow at the end of a line segment. Drawing the line segment must be done by the caller, using whatever
         * stroke and color is required.
         */
        public void drawArrowHead (Graphics2D g, double x0, double y0, double x1, double y1)
        {
            final AffineTransform tx = AffineTransform.getTranslateInstance (x1, y1);
            tx.rotate (Math.atan2 (y1 - y0, x1 - x0));
            g.fill (tx.createTransformedShape (arrowHead));
        }
    }

}
