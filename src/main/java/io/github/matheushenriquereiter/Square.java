package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    public Square(Color color) {
        setPreferredSize(new Dimension(100, 100));
        setBackground(color);
        setBorderPainted(false);
    }
}
