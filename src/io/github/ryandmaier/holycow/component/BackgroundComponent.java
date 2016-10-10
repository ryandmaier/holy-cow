package io.github.ryandmaier.holycow.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Adam on 10/1/16.
 */
public class BackgroundComponent extends JComponent {

  int width;
  int height;

  public BackgroundComponent(int width, int height) {
    super();
    this.width = width;
    this.height = height;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(new Color(81, 159, 45));
    g2.fill(new Rectangle2D.Double(0,0,width,height));

    /*
    double angleDeg = 0;
    for (int i = 0; i < 8; i++) {

    }
    Ellipse2D.Double rock1 = new Ellipse2D.Double(pW(0.5), pH(0.75), pW(0.1), pH(0.05));
    g2.setColor(new Color(142, 142, 142));
    g2.fill(rock1);
    g2.setColor(Color.black);
    g2.draw(rock1);
    */


  }

  private double pW(double percent) { // get the drawing coordinate as a percentage of the width
    return (double) width * percent;
  }

  private double pH(double percent) { // get the drawing coordinate as a percentage of the width
    return (double) height * percent;
  }



  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension(width, height);
  }

  @Override
  public Dimension getMaximumSize() {
    return new Dimension(width, height);
  }
}
