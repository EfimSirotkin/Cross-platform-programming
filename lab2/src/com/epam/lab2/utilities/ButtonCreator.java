package com.epam.lab2.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ButtonCreator extends Object {
    private Button memberButton;

    public ButtonCreator(){

        this.memberButton = new Button();
    }

    public ButtonCreator(String buttonText, String buttonStyle){
        this.memberButton = new Button(buttonText);
        this.memberButton.setStyle(buttonStyle);
    }

    public ButtonCreator(String buttonText, String buttonStyle, Paint textFill){
        this(buttonText, buttonStyle);
        this.memberButton.setTextFill(textFill);
    }

    public ButtonCreator(String buttonText, String buttonStyle, Paint textFill, Font fontValue){
        this(buttonText, buttonStyle, textFill);
        this.memberButton.setFont(fontValue);
    }

    public ButtonCreator(String buttonText, String buttonStyle, Paint textFill,Font fontValue, double width, double height) {
        this(buttonText, buttonStyle, textFill, fontValue);
        this.memberButton.setPrefSize(width,height);
    }

    public void setButtonText(String text){
        this.memberButton = new Button(text);
    }

    public Button getButton(){
        return this.memberButton;
    }
}
