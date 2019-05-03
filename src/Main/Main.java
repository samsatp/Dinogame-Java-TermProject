package Main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.ViewManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main extends Application {

    /*private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //private Queue<KeyCode> keyStore = new LinkedList<>();

    public static ArrayList<Node> platforms = new ArrayList<>();
    Player player = new Player();
    private int levelWidth;

    public static Pane gamePane = new Pane();
    public static Pane root = new Pane();
*/

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*initContent();

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> keys.put(e.getCode(),true));
        scene.setOnKeyReleased(e -> keys.put(e.getCode(),false));
        //scene.setOnKeyPressed(e -> keyStore.offer(e.getCode()));


        primaryStage.setTitle("Game Of The Century");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();*/

        try{
            ViewManager manager = new ViewManager();
            primaryStage.setResizable(false);
            primaryStage = manager.getMainStage();
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }







































    public static void main(String[] args) {
        launch(args);
    }
}
