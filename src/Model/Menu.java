package Model;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class Menu extends Button {

    private String font_path = "src/model/res/kenvector_future.ttf";
    private String Button_released = " -fx-background-color: transparent; -fx-background-image: url('/model/res/MenuButtonReleases.png');";
    private String Button_pressed = " -fx-background-color: transparent; -fx-background-image: url('/model/res/MenuButtonPressed.png');";

    public Menu(String text){

        setFont();
        setText(text);
        setButton_pressed();
        setButton_released();

        setPrefWidth(190);
        initialButtonListeners();
    }

    public Menu(String text,int ck){
        try{
            setFont(Font.loadFont(new FileInputStream(font_path),16));
        }catch( Exception e){
            setFont(Font.font("Verdana",16));
        }
        setText(text);
        setButton_pressed();
        setButton_released();
        setPrefWidth(70);
        initialButtonListeners();


    }


    private void setFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(font_path),23));
        }catch( Exception e){
            setFont(Font.font("Verdana",23));
        }
    }


    private void setButton_pressed(){
        setStyle(this.Button_pressed);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void setButton_released(){
        setStyle(this.Button_released);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
    }

    private void initialButtonListeners(){
        setOnMousePressed(e->{
            if(e.getButton() == MouseButton.PRIMARY){
                setButton_pressed();
            }
        });

        setOnMouseReleased(e->{
            if(e.getButton() == MouseButton.PRIMARY){
                setButton_released();
            }
        });

        setOnMouseEntered(e->{
            setEffect(new DropShadow());
        });

        setOnMouseExited(e->{
            setEffect(null);
        });
    }





}
