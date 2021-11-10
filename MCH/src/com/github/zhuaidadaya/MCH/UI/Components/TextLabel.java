package com.github.zhuaidadaya.MCH.UI.Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextLabel extends JLabel{
    Color background;
    Color foreground;
    Border border;

    @Override
    public JToolTip createToolTip() {
        JToolTip jt = super.createToolTip();
        jt.setBackground(background == null ? Color.GRAY : background);
        jt.setForeground(foreground == null ? Color.WHITE : foreground);
        jt.setBorder(border == null ? BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY) : border);
        jt.updateUI();
        return jt;
    }

    public void setNewTooltipBackground(Color background) {
        this.background = background;
    }

    public void setNewTooltipForeground(Color foreground) {
        this.foreground = foreground;
    }

    public void setNewTooltipBorder(Border border) {
        this.border = border;
    }

    public void setNewTooltipBorder(Color borderColor) {
        this.border = BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor);
    }
}