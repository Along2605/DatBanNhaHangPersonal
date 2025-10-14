package gui;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    private Image background;

    public Home() {
        background = new ImageIcon("img\\thiet-ke-nha-hang-han-quoc.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
