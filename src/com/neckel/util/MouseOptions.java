package com.neckel.util;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseOptions {
    public static void click(int x, int y) {
        try {
            Robot bot = new Robot();
            bot.mouseMove(x, y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
