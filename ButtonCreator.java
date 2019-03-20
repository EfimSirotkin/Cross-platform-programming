package com.epam;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class ButtonCreator {
    Button member;
    ButtonCreator(String text, String style, Paint color, double width, double height)
    {
        member = new Button(text);
        member.setPrefSize(width,height);
        member.setStyle(style);
        member.setTextFill(color);
    }
    Button getButton(){
        return this.member;
    }
}
