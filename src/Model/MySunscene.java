package Model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class MySunscene extends SubScene {

    private static final String FontPath = "Model/res/Kenney Rocket Square.ttf";
    private static final String BackgroundImage = "Model/res/panel_beige.png";
    private boolean isClicked = true;


    public MySunscene() {

        super(new AnchorPane(), 400, 300);
        prefWidth(400);
        prefHeight(300);

        javafx.scene.layout.BackgroundImage image = new BackgroundImage(new Image(BackgroundImage, 400, 300, false, true)
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot(); //ref root2 var to that new AnchorPane() declared in super constuctor.
        root2.setBackground(new Background(image));

        setLayoutY(145);
        setLayoutX(1024);
    }

    public void moveSubscene(){

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);


        if(isClicked) {
            transition.setToX(-676);
            isClicked=false;
        }else{
            transition.setToX(0);
            isClicked=true;
        }

        transition.play();

    }


}

