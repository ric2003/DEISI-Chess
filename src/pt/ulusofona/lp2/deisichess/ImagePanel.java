package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImagePanel {
    private BufferedImage image;
    private double scale = 0.2; // Set the initial scale to reduce the size
    private Timer animationTimer;
    private boolean increasing = true;
    private CustomPanel panel;

    public ImagePanel(BufferedImage img) {
        this.image = img;
        panel = new CustomPanel();

        // Set up animation timer
        int delay = 50; // Milliseconds between timer events
        animationTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (increasing) {
                    scale += 0.01;
                }
                panel.repaint();
            }
        });
        animationTimer.start();
    }

    public JPanel getPanel() {
        return panel;
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Gradient effect
                GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.RED);
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Image rendering
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int x = (getWidth() - (int) (scale * imageWidth)) / 2;
                int y = (getHeight() - (int) (scale * imageHeight)) / 4; // Zoom into his face

                g2.translate(x, y); // Translate to the center
                g2.scale(scale, scale);
                g2.drawImage(image, 0, 0, this);
                g2.dispose();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            if (image != null) {
                int width = (int) (image.getWidth() * scale);
                int height = (int) (image.getHeight() * scale);
                return new Dimension(width, height);
            }
            return super.getPreferredSize();
        }
    }
}
