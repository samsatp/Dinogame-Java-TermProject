package Main;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.ViewManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Player implements CreateThings {

    private Node player;
    private Point2D playerVelocity = new Point2D(0, 0);
    public boolean canJump = true;
    private Image playImage = new Image("res/bend.png");

    public Point2D getPlayerVelocity(){
        return playerVelocity;
    }


    public void setPlayerVelocity(Point2D toSet){
        this.playerVelocity = toSet;
    }


    @Override
    public Node createEntity(int x, int y, int w, int h, Color color) {

        Rectangle entity = new Rectangle(w,h);
        entity.setFill(color);
        ImagePattern imagePatternPlayer = new ImagePattern(playImage);
        entity.setStyle("-fx-background-color: transparent");
        entity.setFill(imagePatternPlayer);
        entity.setTranslateX(x);
        entity.setTranslateY(y);

        ViewManager.gamePane.getChildren().add(entity);
        player=entity;

        return entity;

    }

    public Node getPlayer(){
        return player;
    }

    public void jumpPlayer(){
        if(canJump){
            playerVelocity = playerVelocity.add(0,-30);
            canJump=false;


        }

    }




    public void moveX(int value){
        boolean moveRight = value > 0 ;

        for(Node plat: ViewManager.platforms){

            if(moveRight){
                if((player.getTranslateX()+40==plat.getTranslateX() && player.getTranslateY()-20==plat.getTranslateY())
                ||(player.getTranslateX()+40==plat.getTranslateX() && (plat.getTranslateY()<=player.getTranslateY() && player.getTranslateY() <= plat.getTranslateY()+60))) {
                    //if crash on the same level or during jump -> return
                    return;}
            }
            else{
                //if(player.getTranslateX()==plat.getTranslateX()+60 && player.getTranslateY()==plat.getTranslateY()) return;
                if((player.getTranslateX()-60==plat.getTranslateX() && player.getTranslateY()-20==plat.getTranslateY())
                        ||(player.getTranslateX()-60==plat.getTranslateX() && (plat.getTranslateY()<=player.getTranslateY() && player.getTranslateY() <= plat.getTranslateY()+60))) {
                    //if crash on the same level or during jump -> return
                    return;}
            }

        }

        player.setTranslateX(player.getTranslateX()+(moveRight ? 5:-5));

    }

    /*for(int i =0; i< Math.abs(value);i++){
            for(Node platform: ViewManager.platforms){
                if(player.getBoundsInParent().intersects(platform.getBoundsInParent()) ){
                    if(moveRight){
                        if(player.getTranslateX()+40 == platform.getTranslateX()) return;}
                    else{
                        if(player.getTranslateX() == platform.getTranslateX()+60) return;
                    }

                }
            }
            player.setTranslateX(player.getTranslateX()+(moveRight ? 1:-1));
            System.out.println("player move right");
        }*/


    public void moveY(int value){
        boolean movingDown = value >0;

        for(int i =0; i< Math.abs(value); i++){
            for(Node platform : ViewManager.platforms){
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + 40 == platform.getTranslateY()) {
                            canJump = true;
                            return;
                        }
                    } else {
                        if (player.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY()+(movingDown ? 1:-1));
        }
    }


}
