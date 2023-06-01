package com.meme.misc.d230409;

import java.awt.*;

public class CatDrawing extends Frame {
    public static void main(String[] args) {
        new CatDrawing();
    }

    CatDrawing() {
        setSize(400, 400);
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawOval(150, 50, 100, 100); // Head
        g.drawOval(170, 110, 60, 80); // Body
        g.fillOval(185, 70, 15, 15); // Left Eye
        g.fillOval(210, 70, 15, 15); // Right Eye
        g.fillOval(192, 90, 20, 30); // Nose/Mouth
        g.drawPolygon(new int[]{165, 155, 175}, new int[]{65, 85, 85}, 3); // Left Ear
        g.drawPolygon(new int[]{235, 245, 225}, new int[]{65, 85, 85}, 3); // Right Ear
        g.drawRect(170, 130, 20, 40); // Left Leg
        g.drawRect(210, 130, 20, 40); // Right Leg
        g.drawRect(155, 170, 25, 10); // Left Paw
        g.drawRect(195, 170, 25, 10); // Right Paw
        g.drawLine(160, 130, 140, 190); // Left Tail
    }
}