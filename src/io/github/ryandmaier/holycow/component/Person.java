package io.github.ryandmaier.holycow.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Person extends Entity
{
    double bodyX;
    double bodyY;
    double bodyW;
    double bodyH;

    private static final double MAX_SPEED = 10;
    private static final double TURN_RADIUS = 5;
    private static final double SPEED_CHANGE = 0.33;

    public Person(int width, int height)
    {
        super(width, height);
        
        facingAngle = 0;
        speed = 2;

        bodyX = 50;
        bodyY = 50;
        bodyW = 20;
        bodyH = bodyW*2;


    }


    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D.Double body = new RoundRectangle2D.Double(bodyX,bodyY,bodyW,bodyH,bodyW/10,bodyH/10);
        Ellipse2D.Double head = new Ellipse2D.Double(bodyX,bodyY-bodyW,bodyW,bodyW);


        g2.setColor(new Color(0xFF7654));
        g2.fill(body);
        g2.fill(head);


        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.BLACK);
        g2.draw(body);
        g2.draw(head);
    }

}
