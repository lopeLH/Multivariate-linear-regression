/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradiendescentexample;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author lope
 */
public class Canvas extends javax.swing.JPanel implements MouseListener {

    /**
     * Creates new form Canvas
     */
    ArrayList<Point> points = new ArrayList();
    GradientDescent gd = null;

    public Canvas() {
        initComponents();
        this.addMouseListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paintComponent(Graphics simpleG) {

        Graphics2D g = (Graphics2D) simpleG;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

        g.setColor(Color.red);
        for (Point p : points) {
            g.drawOval(p.x - 1, p.y - 1, 3, 3);
        }

        g.setColor(Color.black);
        for (int i = 0; i < this.getWidth(); i += 10) {
            g.drawLine(i, this.getHeight(), i, this.getHeight() - 4);
        }

        for (int i = 0; i < this.getHeight(); i += 10) {
            g.drawLine(0, i, 5, i);
        }

        g.drawString("" + this.getWidth(), this.getHeight() - 25, this.getWidth() - 10);
        g.drawString("" + this.getHeight(), 10, 15);

        g.setColor(Color.blue);

        if (gd != null) {
            Point last = null;
            DecimalFormat df = new DecimalFormat("#,###,##0.000000");
            g.drawString("mse: " + df.format(gd.meanSquareError()) + " at epoch # " + gd.getEpoch(), this.getWidth() - 240, 15);
            for (int i = 0; i < this.getWidth(); i++) {
                float x[] = new float[31];
                x[0] = (float) i / (float) this.getWidth();
                for(int j = 1; j<31; j++)
                    x[j] = x[0]*x[j-1];
                if (last == null) {
                    last = new Point(i, (int) (this.getHeight() - this.getHeight() * gd.computeEstimate(x)));
                    continue;
                }

                Point p = new Point(i, (int) (this.getHeight() - this.getHeight() * gd.computeEstimate(x)));
                g.drawLine(last.x, last.y, p.x, p.y);
                last = p;

            }
        }
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        points.add(new Point(me.getX(), me.getY()));
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
