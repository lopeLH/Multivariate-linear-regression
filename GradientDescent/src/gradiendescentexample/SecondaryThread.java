/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradiendescentexample;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lope
 */
public class SecondaryThread extends Thread {

    Canvas canvas;
    GradientDescent gd;

    public SecondaryThread(Canvas canvas, GradientDescent gd) {
        this.canvas = canvas;
        this.gd = gd;
    }

    @Override
    public void run() {

        while (!gd.iteration()) {
            canvas.repaint();
        }

    }

}
