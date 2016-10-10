package io.github.ryandmaier.holycow.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Person extends Entity
{
    double aRot;
    double aSpeed;
    private static final double MAX_SPEED = 10;
    private static final double TURN_RADIUS = 5;
    private static final double SPEED_CHANGE = 0.33;
    public Person(int width, int height)
    {
        super(width, height);
        
        facingAngle = 0;
        speed = 2;
        aRot = 0;
        aSpeed = 0;
    }
    
    protected void paintComponent(Graphics g)
    {
        
    }

}
