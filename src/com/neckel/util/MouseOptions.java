package com.neckel.util;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseOptions {
    private Robot bot = null;
    private static MouseOptions instance = null;
    private MouseOptions(){

    }

    public static MouseOptions getInstance(){
        if(instance == null){
            try {
                instance = new MouseOptions();
                instance.bot = new Robot();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return instance;
    }

    public void click(int x, int y) {
        try {
            instance.bot.mouseMove(x, y);
            instance.bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            instance.bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
