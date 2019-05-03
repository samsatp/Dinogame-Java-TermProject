package view;

import Main.*;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewManager implements CreateThings{

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private static final int menu_button_start_x = 100;
    private static final int menu_button_start_y = 150;

    private Model.MySunscene HelpSubscene;
    private Model.MySunscene StartSubscene;
    private Model.MySunscene ScoreSubscene;

    private Model.MySunscene sceneToHide;

    List<Model.Menu> menuButton;

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public static ArrayList<Node> platforms = new ArrayList<>();
    Player player = new Player();
    private int levelWidth;

    public static Pane gamePane = new Pane();
    public static Pane uiPane = new Pane();
    public static Pane root = new Pane();

    private static boolean isGameWindowIsOpen = false;
    Stage gameStage = new Stage();

    private Image Brick = new Image("view/res/brick.png");

    Image LivingHeart = new Image("view/res/livingHeart.png");
    Image DeadHeart = new Image("view/res/deadHeart.png");
    ImageView H1Live = new ImageView(LivingHeart);
    ImageView H2Live = new ImageView(LivingHeart);
    ImageView H3Live = new ImageView(LivingHeart);
    ImageView H1Dead = new ImageView(DeadHeart);
    ImageView H2Dead = new ImageView(DeadHeart);
    ImageView H3Dead =new ImageView(DeadHeart);
    int DieCount = 0;

    Image BrickCastle = new Image("view/res/brick_Castle.png");
    Label DeathLabel = new Label("YOU'RE DEATH");

    boolean check = false;
    public ViewManager() {

        menuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, 800,600);
        mainStage= new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.setHeight(600); mainStage.setWidth(800);
        mainStage.setTitle("Game Of The Year");

        createButton();
        createBackground();
        createSubScene();
        initGameWindow();

    }

    private void showSubScene(Model.MySunscene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubscene();
        }
        subScene.moveSubscene();
        sceneToHide=subScene;

    }

    public void createSubScene(){
        StartSubscene = new Model.MySunscene();
        mainPane.getChildren().add(StartSubscene);

        HelpSubscene = new Model.MySunscene();
        mainPane.getChildren().add(HelpSubscene);

        ScoreSubscene = new Model.MySunscene();
        mainPane.getChildren().add(ScoreSubscene);

    }


    public Stage getMainStage(){
        return this.mainStage;
    }

    private void addMenuButton(Model.Menu button){
        button.setLayoutX(menu_button_start_x);
        button.setLayoutY(menu_button_start_y + menuButton.size()*100);
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }


    private void createButton(){
        createStartButton();
        createScoreButton();
        createHelpButton();
        createExitButton();

    }

    private void createStartButton(){
        Model.Menu startButton = new Model.Menu("START");
        addMenuButton(startButton);
        startButton.setOnAction(e -> {
            //showSubScene(StartSubscene);
            isGameWindowIsOpen = !isGameWindowIsOpen;
            if(isGameWindowIsOpen) {gameStage.show(); }

            //else gameStage.hide();
            //initGameWindow();
        });
    }

    private void createScoreButton(){
        Model.Menu scoreButton = new Model.Menu("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(e -> {
            showSubScene(ScoreSubscene);
        });
    }

    private void createHelpButton(){
        Model.Menu helpButton = new Model.Menu("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(e -> {
            showSubScene(HelpSubscene);
        });

    }

    private void createExitButton(){
        Model.Menu exitButton = new Model.Menu("EXIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(e -> {
            mainStage.close();
        });
    }


    private void createBackground(){

        Image backgroubdImage = new Image("view/res/background.png"
                ,800,600,false,false);
        BackgroundImage background = new BackgroundImage(backgroubdImage
                , BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));

    }

    public void initGameWindow(){

        initContent();
        Scene gameScene = new Scene(root);

        gameScene.setOnKeyPressed(q -> keys.put(q.getCode(),true));
        gameScene.setOnKeyReleased(q -> keys.put(q.getCode(),false));

        gameStage.setTitle("game Of The Century");
        gameStage.setMaxWidth(1280);
        gameStage.setHeight(765);
        gameStage.setScene(gameScene);
        //gameStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }


    public void initContent(){

        check=!check;

        StackPane Playing = new StackPane();
        Rectangle bg = new Rectangle(1280,720);
        ImagePattern background = new ImagePattern(new Image("view/res/background2.png"));
        bg.setFill(background);
        Playing.getChildren().add(bg);


        HBox Live = new HBox(10);

        StackPane H1 = new StackPane();
        StackPane H2 = new StackPane();
        StackPane H3 = new StackPane();

        H1.getChildren().addAll(H1Dead,H1Live);
        H2.getChildren().addAll(H2Dead,H2Live);
        H3.getChildren().addAll(H3Dead,H3Live);

        Live.getChildren().addAll(H1,H2,H3);
        uiPane.getChildren().add(Live);

        Playing.getChildren().add(uiPane);

        levelWidth = LevelData.LEVEL1[0].length()*60;

        for(int i =0; i< LevelData.LEVEL1.length;i++){
            String line = LevelData.LEVEL1[i];
            for(int q =0; q<line.length();q++){
                switch (line.charAt(q)){
                    case '0':
                        break;
                    case '1':
                        Node PlainPlatform= createEntity(q * 60, i * 60, 60, 60, Color.GREEN);
                        platforms.add(PlainPlatform);
                        break;
                    case '2':
                        Node obs = createEntity(q*60, i*60, 60,60,Color.CRIMSON);
                        platforms.add(obs);
                        break;
                }
            }
        }

        player.createEntity(0, 600, 40, 40, Color.BLUE )
                .translateXProperty().addListener((o,oldVal,newVal) ->  {
            int offset = newVal.intValue();
            if(offset>640 && offset< levelWidth-640){
                gamePane.setLayoutX(-(offset-640));
                //System.out.println("Make player be centered");
            }
        });

        /*Button exitButton = new Button("Exit");
        exitButton.setLayoutX(1200);
        exitButton.setLayoutY(20);*/
        Model.Menu exitButton = new Model.Menu("EXIT",1);
        exitButton.setLayoutX(1200);
        exitButton.setTranslateY(10);
        exitButton.setOnAction(e -> {gameStage.hide();isGameWindowIsOpen=!isGameWindowIsOpen;});
        uiPane.getChildren().add(exitButton);

        root.getChildren().addAll(Playing,gamePane,uiPane);

    }



    @Override
    public Node createEntity(int x, int y, int w, int h, Color color) {

        Rectangle entity = new Rectangle(w,h);
        //entity.setFill(color);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        ImagePattern imagePatternBrick = new ImagePattern(Brick);
        ImagePattern imagePatternBrickCastle = new ImagePattern(BrickCastle);
        entity.setStyle("-fx-background-color: transparent");
        if(color.equals(Color.GREEN)) entity.setFill(imagePatternBrick);
        else entity.setFill(imagePatternBrickCastle);
        gamePane.getChildren().add(entity);

        return entity;

    }

    public boolean isPressed(KeyCode keyCode){
        return keys.getOrDefault(keyCode,false);
        //return the value of key "keyCode"
        //but if there are not have key "keyCode", it'll return false
    }

    public void checkIfFall(){
        if(player.getPlayer().getTranslateY()>=680) {
            if(DieCount==4){
                showDeathLabel();
                DieCount++;
                return;
            }
            DieCount++;
            switch (DieCount){
                case 1:
                    H1Live.toBack();
                    resetPlayerFromDeath();
                    break;
                case 2:
                    H2Live.toBack();
                    resetPlayerFromDeath();
                    break;
                case 3 :
                    H3Live.toBack();
                    resetPlayerFromDeath();
                    break;

            }

        }

    }

    public void checkIfCollide(){
        for(int i =0; i< LevelData.LEVEL1.length;i++){
            String line = LevelData.LEVEL1[i];
            for(int q =0; q<line.length();q++){
                if()

            }
        }
    }


    public void showDeathLabel(){
        DeathLabel.setStyle("-fx-font-family:'Times New Roman',Times,serif; -fx-color: green; -fx-background-color: black");
        VBox DeathWindow = new VBox(10);
        Button OkButton = new Button("OKAY I'M NOOB");
        OkButton.setOnAction(e -> {
            System.out.println("taii");
        });
        DeathWindow.getChildren().addAll(DeathLabel,OkButton);
        DeathWindow.setLayoutX(600);
        DeathWindow.setLayoutY(300);
        DeathWindow.setStyle("-fx-border-color: red;" +
                "-fx-border-insets: 5; -fx-border-width:3; -fx-border-style: solid;");
        uiPane.getChildren().add(DeathWindow);
    }

    public void resetPlayerFromDeath(){
        player.getPlayer().setTranslateX(player.getPlayer().getTranslateX()-100);
        player.getPlayer().setTranslateY(player.getPlayer().getTranslateY()-200);
    }

    public void update(){
        checkIfFall();

        if(isPressed(KeyCode.W) && player.getPlayer().getTranslateY() >= 5){
            player.jumpPlayer();
            //System.out.println("JUMP");
        }
        if(isPressed(KeyCode.A) && player.getPlayer().getTranslateX() >=5){
            player.moveX(-5);
            //System.out.println("<-");
        }
        if(isPressed(KeyCode.D) && player.getPlayer().getTranslateX()+40 <= levelWidth-5){
            player.moveX(5);
           // System.out.println("->");
        }

        if(player.getPlayerVelocity().getY()<10) player.setPlayerVelocity(player.getPlayerVelocity().add(0,1));

        player.moveY((int)player.getPlayerVelocity().getY());

    }


}
