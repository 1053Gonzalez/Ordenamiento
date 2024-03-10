package ordenamiento;

import javax.swing.*;
import java.awt.*;


public class GraficarBurbuja extends JPanel {

    private static final int CIRCLE_DIAMETER = 50;
    private static final int CIRCLE_SPACING = 10;

    private int[] array;

    public GraficarBurbuja(int[] array) {
        this.array = array;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujaCirculo(g);
    }

    private void dibujaCirculo(Graphics g) {
        int x = 40;
        int y = (getHeight() - CIRCLE_DIAMETER) / 2;

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Ordenamiento Burbuja", 125, 50);
        

        for (int i = 0; i < array.length; i++) {
            g.setColor(new Color(102, 153, 255));
            g.drawOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            g.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            g.setFont(new Font("Arial", Font.BOLD, 16));

            g.setColor(Color.BLACK);
            dibujaTextoCentrado(g, Integer.toString(array[i]), x, y, CIRCLE_DIAMETER);

            x += CIRCLE_DIAMETER + CIRCLE_SPACING;
        }
    }

    private void dibujaTextoCentrado(Graphics g, String text, int x, int y, int width) {
        FontMetrics fm = g.getFontMetrics();
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int textX = x + (width - fm.stringWidth(text)) / 2;
        int textY = y + (ascent + (width - (ascent + descent)) / 2);
        g.drawString(text, textX, textY);
    }

    public void actualizarArray(int[] newArray) {
        array = newArray;
        repaint();
    }
}
