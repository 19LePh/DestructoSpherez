//Some imports are not used; may need to be removed
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.beans.property.SimpleStringProperty;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.SimpleObjectProperty;
/**
 * The Graphical User Interface for Destructo-Spherez.
 * 
 * @author Philip Le & Matt Carosielli 
 * @version 1.0
 */
public class GUI extends Application
{
    private static boolean isFinished;
    //Game Elements
    private static final Player player = new Player();
    private static Terrain t;
    private static GameLogic logic = new GameLogic();
    private static final Wall[] walls = {Wall.WALL_1, Wall.WALL_2, Wall.WALL_3, Wall.WALL_4, Wall.WALL_5};
    private static Score score = new Score(walls);

    //Scenes that can be accessed by clicking a button (everything but the score scene)
    private static Scene titleScene, launchScene, catalogScene, equipmentScene, recordsScene, creditsScene, scoreScene;

    private static double width;
    private static double height;
    private final Label[] launchStats = new Label[4]; //Distance, Altitude, Air Time, Speed
    private final Label[] scoreLabels = new Label[7]; //Distance, Altitude, Air Time, Speed, Walls Destroyed, Score, Reward Money
    private final Label[] recordsLabels = new Label[6]; //Distance, Altitude, Air Time, Speed, Walls Destroyed, Score
    private final Label[] catalogLabels = new Label[4]; //Title, status, balance
    private final Label[] equipmentLabels = new Label[4]; //Title, status, balance

    private final Text[] titles = new Text[4]; //Titles for Title, Records, Score, and Credits screens

    //Catalog Buttons
    private static Button purchase;
    private static Button equip;
    private static Upgrades selected;

    private static Stage stage;

    private static SimpleStringProperty balance;
    private static SimpleStringProperty specs;
    private static SimpleStringProperty equipStatus;
    private static SimpleStringProperty catalogStatus;

    //Score per launch
    private static SimpleStringProperty tempDistance;
    private static SimpleStringProperty tempAltitude;
    private static SimpleStringProperty tempAirTime;
    private static SimpleStringProperty tempVelocity;
    private static SimpleStringProperty tempWalls;
    private static SimpleStringProperty tempScore;
    private static SimpleStringProperty rewardMoney;

    //Top Scores, meant for records scene
    private static SimpleStringProperty topDistance;
    private static SimpleStringProperty topAltitude;
    private static SimpleStringProperty topAirTime;
    private static SimpleStringProperty topVelocity;
    private static SimpleStringProperty topWalls;
    private static SimpleStringProperty topScore;

    //Title screen background
    private static SimpleObjectProperty<Image> currBack = new SimpleObjectProperty<Image>();
    private static ImageView titleBack = new ImageView();
    private static ImageView launchBack = new ImageView();
    @Override
    public void start(Stage stage)
    {
        //Purchases the default launcher from shop and equips it
        Shop.buy(Shop.upgrades[0][0]);
        Shop.upgrades[0][0].setIsEquipped(player);
        selected = Shop.upgrades[0][0];

        //Sets all stringProperty variables
        specs = new SimpleStringProperty(selected.getSpecs());
        balance = new SimpleStringProperty("Balance: $" + Shop.getBalance());

        tempDistance = new SimpleStringProperty("Distance: " + (int)(score.getDistance()) + " m");
        tempAltitude = new SimpleStringProperty("Altitude: " + (int)(score.getMaxHeight()) + " m");
        tempAirTime = new SimpleStringProperty("Air Time: " + (int)(score.getAirTime()) + " s");
        tempVelocity = new SimpleStringProperty("Speed: " + (int)(score.getMaxVelocity()) + " m/s");
        tempWalls = new SimpleStringProperty(score.tempAchToString());
        tempScore = new SimpleStringProperty("Total Score: " + (int)(score.getScore()));
        rewardMoney = new SimpleStringProperty("Reward Money: $" + score.getRewardMoney());

        topDistance = new SimpleStringProperty("Top Distance: " + (int)(Score.getTopDistance()) + " m");
        topAltitude = new SimpleStringProperty("Top Altitude: " + (int)(Score.getTopHeight()) + " m");
        topAirTime = new SimpleStringProperty("Top Air Time: " + (int)(Score.getTopAirTime()) + " s");
        topVelocity = new SimpleStringProperty("Top Speed: " + (int)(Score.getTopVelocity()) + " m/s");
        topWalls = new SimpleStringProperty(score.permAchToString());
        topScore = new SimpleStringProperty("Top Score: " + (int)(Score.getHighScore()) + " m/s");

        if(player.checkIsEquipped(selected))
        {
            equipStatus = new SimpleStringProperty("Equipped!");
        } else if(!(selected.getIsPurchased()))
        {
            equipStatus = new SimpleStringProperty("You do not own this item");
        } else {
            equipStatus = new SimpleStringProperty("Not Equipped");
        }

        if(selected.getIsPurchased())
        {
            catalogStatus = new SimpleStringProperty("SOLD OUT");
        } else if(selected.getCost() > Shop.getBalance())
        {
            catalogStatus = new SimpleStringProperty("INSUFFICIENT FUNDS");
        } else {
            catalogStatus = new SimpleStringProperty("Not Purchased");
        }

        //Removes stage borders
        stage.initStyle(StageStyle.UNDECORATED);

        //Gets fullscreen dimensions
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double minX = bounds.getMinX();
        double minY = bounds.getMinY();
        width = bounds.getWidth();
        height = bounds.getHeight();

        //Makes stage Fullscreen
        stage.setTitle("Destructo - Spherez!");
        stage.setX(minX);
        stage.setY(minY);
        stage.setWidth(width);
        stage.setHeight(height);

        currBack.set(Terrain.getCurrCopy().getImage());
        
        titleBack.imageProperty().bind(currBack);
        titleBack.setFitWidth(width);
        titleBack.setFitHeight(height);
        
        launchBack.imageProperty().bind(currBack);
        launchBack.setFitWidth(width);
        launchBack.setFitHeight(height);

        //Creates the titles
        for(int i = 0; i < titles.length; i++)
        {
            Text t = new Text();
            t.setFill(Color.GOLD);
            t.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 10.0));
            titles[i] = t;
        }
        titles[0].setText("Destructo - Spherez!");
        titles[1].setText("Records");
        titles[2].setText("Credits");
        titles[3].setText("Score");

        //Creates the labels for launchStats
        for(int i = 0; i < launchStats.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 7.5);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            launchStats[i] = l;
        }
        launchStats[0].textProperty().bind(tempDistance);
        launchStats[1].textProperty().bind(tempAltitude);
        launchStats[2].textProperty().bind(tempAirTime);
        launchStats[3].textProperty().bind(tempVelocity);

        //Creates the labels for catalogLabels
        for(int i = 0; i < catalogLabels.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 7.5);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            catalogLabels[i] = l;
        }
        catalogLabels[0].setText("Catalog");
        catalogLabels[1].textProperty().bind(balance);
        catalogLabels[2].textProperty().bind(specs);
        catalogLabels[3].textProperty().bind(catalogStatus);

        //Creates the labels for equipmentLabels
        for(int i = 0; i < equipmentLabels.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 7.5);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            equipmentLabels[i] = l;
        }
        equipmentLabels[0].setText("Equipment");
        equipmentLabels[1].textProperty().bind(balance);
        equipmentLabels[2].textProperty().bind(specs);
        equipmentLabels[3].textProperty().bind(equipStatus);

        //Creates the labels for score
        for(int i = 0; i < scoreLabels.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 240.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            scoreLabels[i] = l;
        }    
        scoreLabels[0].textProperty().bind(tempDistance);
        scoreLabels[1].textProperty().bind(tempAltitude);
        scoreLabels[2].textProperty().bind(tempAirTime);
        scoreLabels[3].textProperty().bind(tempVelocity);
        scoreLabels[4].textProperty().bind(tempWalls);
        scoreLabels[5].textProperty().bind(tempScore);
        scoreLabels[6].textProperty().bind(rewardMoney);

        //Creates the labels for records
        for(int i = 0; i < recordsLabels.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 240.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            recordsLabels[i] = l;
        }
        recordsLabels[0].textProperty().bind(topDistance);
        recordsLabels[1].textProperty().bind(topAltitude);
        recordsLabels[2].textProperty().bind(topAirTime);
        recordsLabels[3].textProperty().bind(topVelocity);
        recordsLabels[4].textProperty().bind(topWalls);
        recordsLabels[5].textProperty().bind(topScore);

        //Make buttons for title screen, set text, and add functions to each
        Button[] titleButtons = new Button[6];
        for(int i = 0; i < titleButtons.length; i++)
        {
            Button b = new Button("");
            b.setStyle("-fx-font: "  + height / 100.0 + " Helvetica");
            b.setMinWidth(height / 10.0);
            b.setMinHeight(height / 40.0);
            titleButtons[i] = b;
        }
        titleButtons[0].setText("Launch!");
        titleButtons[1].setText("Catalog");
        titleButtons[2].setText("Equipment");
        titleButtons[3].setText("Records");
        titleButtons[4].setText("Credits");
        titleButtons[5].setText("Exit");

        //Launch
        titleButtons[0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    //Set up logic
                    score = new Score(walls);
                    //Refreshes strings for the launch scene
                    tempDistance.set("Distance: " + (int)(score.getDistance()) + " m");
                    tempAltitude.set("Altitude: " + (int)(score.getMaxHeight()) + " m");
                    tempAirTime.set("Air Time: " + (int)(score.getAirTime()) + " s");
                    tempVelocity.set("Speed: " + (int)(score.getMaxVelocity()) + " m/s");

                    ArrayList<Powerups> powerups = new ArrayList<Powerups>();
                    t = new Terrain(powerups, walls);
                    t.randomPowerups(10);
                    double playerMass = player.calculateMass(); //test
                    double playerCrossSectionalArea = player.getCrossSectionalArea();
                    double initialSpeed = player.getLauncher().getPower(); //NEEDS REWORK, NEWTONS != m/s
                    double lift = 0.0;
                    if(player.getMount() != null)
                    {
                        lift = player.getMount().getLift();
                    }
                    double accelerationY = -Terrain.gravity + (lift / playerMass);
                    double angle = player.getLauncher().getAngle();
                    logic = new GameLogic(initialSpeed, angle, 0.0, 10.0, 1.0, accelerationY, playerMass, playerCrossSectionalArea);
                    stage.setScene(launchScene);

                    launchScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if(event.getCode() == KeyCode.ENTER)
                                {
                                    /*isFinished = false;
                                    launch();*/
                                    while(!(logic.get_y() <= 0.0001))
                                    {
                                        logic.init(score);
                                    }

                                    /*try {
                                    Thread.sleep(5000);
                                    } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                    }*/

                                    //Refreshes strings for the score scene
                                    tempDistance.set("Distance: " + (int)(score.getDistance()) + " m");
                                    tempAltitude.set("Altitude: " + (int)(score.getMaxHeight()) + " m");
                                    tempAirTime.set("Air Time: " + (int)(score.getAirTime()) + " s");
                                    tempVelocity.set("Speed: " + (int)(score.getMaxVelocity()) + " m/s");
                                    tempWalls.set(score.tempAchToString());
                                    tempScore.set("Total Score: " + (int)(score.getScore()));
                                    rewardMoney.set("Reward Money: $" + score.getRewardMoney());
                                    stage.setScene(scoreScene);
                                }
                            }
                        });
                }
            });

        //Catalog
        titleButtons[1].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(catalogScene);
                }
            });

        //Equipment
        titleButtons[2].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(equipmentScene);
                }
            });

        //Records
        titleButtons[3].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    //Refreshes top score stats
                    topDistance.set("Top Distance: " + (int)(Score.getTopDistance()) + " m");
                    topAltitude.set("Top Altitude: " + (int)(Score.getTopHeight()) + " m");
                    topAirTime.set("Top Air Time: " + (int)(Score.getTopAirTime()) + " s");
                    topVelocity.set("Top Speed: " + (int)(Score.getTopVelocity()) + " m/s");
                    topWalls.set(score.permAchToString());
                    topScore.set("Top Score: " + (int)(Score.getHighScore()) + " m/s");
                    stage.setScene(recordsScene);
                }
            });

        //Credits
        titleButtons[4].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(creditsScene);
                }
            });

        //Exit
        titleButtons[5].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Platform.exit();
                }
            });

        //Make purchase and equip button
        Button purchase = new Button();
        purchase.setText("Purchase");
        purchase.setStyle("-fx-font: "  + height / 50.0 + " Helvetica");
        purchase.setMinWidth(height / 7.5);
        purchase.setMinHeight(height / 20.0);
        purchase.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Shop.buy(selected);
                    if(selected.getIsPurchased())
                    {
                        catalogStatus.set("SOLD OUT");
                    } else if(selected.getCost() > Shop.getBalance())
                    {
                        catalogStatus.set("INSUFFICIENT FUNDS");
                    } else {
                        catalogStatus.set("Not Purchased");
                    }
                    balance.set("Balance: $" + Shop.getBalance());
                }
            });

        Button equip = new Button();
        equip.setText("Equip");
        equip.setStyle("-fx-font: "  + height / 50.0 + " Helvetica");
        equip.setMinWidth(height / 7.5);
        equip.setMinHeight(height / 20.0);
        equip.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    player.equip(selected);
                    if(player.checkIsEquipped(selected))
                    {
                        equipStatus.set("Equipped!");
                    } else if(!(selected.getIsPurchased()))
                    {
                        equipStatus.set("You do not own this item");
                    } else {
                        equipStatus.set("Not Equipped");
                    }
                }
            });

        //Make upgrades buttons for catalog scene and equipment scene
        Button[] catalogButtons = equipButtons(stage);
        Button[] equipmentButtons = equipButtons(stage);

        //Makes the title scene
        VBox Layout_Title = new VBox(50);
        Layout_Title.getChildren().add(titles[0]);
        for(int i = 0; i < titleButtons.length; i++)
        {
            Layout_Title.getChildren().add(titleButtons[i]);
        }
        Layout_Title.setAlignment(Pos.CENTER);
        titleScene = new Scene(new StackPane(titleBack, Layout_Title), width, height);

        //Makes the launch scene
        VBox Layout_Launch = new VBox(50);
        for(int i = 0; i < launchStats.length; i++)
        {
            Layout_Launch.getChildren().add(launchStats[i]);
        }
        Layout_Launch.setAlignment(Pos.TOP_LEFT);
        launchScene = new Scene(new StackPane(launchBack, Layout_Launch), width, height);

        //Makes the catalog scene
        VBox Layout_Catalog = new VBox();
        VBox Layout_Catalog_Status = new VBox(50);
        for(int i = 0; i < catalogLabels.length; i++)
        {
            Layout_Catalog_Status.getChildren().add(catalogLabels[i]);
        }
        Layout_Catalog_Status.setAlignment(Pos.TOP_LEFT);

        Layout_Catalog.getChildren().add(purchase);
        for(int i = 0; i < catalogButtons.length; i++)
        {
            Layout_Catalog.getChildren().add(catalogButtons[i]);
        }
        Button back_Catalog = backButton(stage, Layout_Title);
        back_Catalog.setStyle("-fx-font: "  + height / 50.0 + " Helvetica");
        back_Catalog.setMinWidth(height / 7.5);
        back_Catalog.setMinHeight(height / 20.0);
        Layout_Catalog.getChildren().add(back_Catalog);
        Layout_Catalog.setAlignment(Pos.CENTER_RIGHT);
        ImageView c = Terrain.getSpace();
        c.setFitWidth(width);
        c.setFitHeight(height);
        catalogScene = new Scene(new StackPane(c, Layout_Catalog_Status, Layout_Catalog), width, height);

        //Makes the equipment scene
        VBox Layout_Equipment = new VBox();
        VBox Layout_Equipment_Status = new VBox(50);
        for(int i = 0; i < catalogLabels.length; i++)
        {
            Layout_Equipment_Status.getChildren().add(equipmentLabels[i]);
        }
        Layout_Equipment_Status.setAlignment(Pos.TOP_LEFT);

        Layout_Equipment.getChildren().add(equip);
        for(int i = 0; i < equipmentButtons.length; i++)
        {
            Layout_Equipment.getChildren().add(equipmentButtons[i]);
        }
        Button back_Equipment = backButton(stage, Layout_Title);
        back_Equipment.setStyle("-fx-font: "  + height / 50.0 + " Helvetica");
        back_Equipment.setMinWidth(height / 7.5);
        back_Equipment.setMinHeight(height / 20.0);
        Layout_Equipment.getChildren().add(back_Equipment);
        Layout_Equipment.setAlignment(Pos.CENTER_RIGHT);
        ImageView d = Terrain.getSpace();
        d.setFitWidth(width);
        d.setFitHeight(height);
        equipmentScene = new Scene(new StackPane(d, Layout_Equipment_Status, Layout_Equipment), width, height);

        //Makes the records scene
        VBox Layout_Records = new VBox(50);
        Layout_Records.getChildren().add(titles[1]);
        for(int i = 0; i < recordsLabels.length; i++)
        {
            Layout_Records.getChildren().add(recordsLabels[i]);
        }
        Layout_Records.setAlignment(Pos.CENTER);
        ImageView e = Terrain.getSpace();
        e.setFitWidth(width);
        e.setFitHeight(height);
        VBox Back_Records = new VBox(1);
        Back_Records.getChildren().add(backButton(stage, Layout_Title));
        Back_Records.setAlignment(Pos.BOTTOM_LEFT);
        recordsScene = new Scene(new StackPane(e, Layout_Records, Back_Records), width, height);

        //Makes the Score scene
        VBox Layout_Score = new VBox(50);
        Layout_Score.getChildren().add(titles[3]);
        for(int i = 0; i < scoreLabels.length; i++)
        {
            Layout_Score.getChildren().add(scoreLabels[i]);
        }
        Layout_Score.setAlignment(Pos.CENTER);
        ImageView f = Terrain.getSpace();
        f.setFitWidth(width);
        f.setFitHeight(height);
        VBox Back_Score = new VBox(1);
        Back_Score.getChildren().add(backButton(stage, Layout_Title));
        Back_Score.setAlignment(Pos.BOTTOM_LEFT);
        scoreScene = new Scene(new StackPane(f, Layout_Score, Back_Score), width, height);

        //Makes the credits scene
        VBox Layout_Credits = new VBox(50);
        Layout_Credits.getChildren().add(titles[2]);
        Label[] creditsLabels = new Label[2];
        for(int i = 0; i < creditsLabels.length; i++)
        {
            Label l = new Label();
            l.setMinWidth(height / 240.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            creditsLabels[i] = l;
        }
        creditsLabels[0].setText("APCS Final Project 2017");
        creditsLabels[1].setText("Creators: Phillip Le & Matt Carosielli");
        for(int i = 0; i < creditsLabels.length; i++)
        {
            Layout_Credits.getChildren().add(creditsLabels[i]);
        }
        Layout_Credits.setAlignment(Pos.CENTER);
        ImageView g = Terrain.getSpace();
        g.setFitWidth(width);
        g.setFitHeight(height);        
        VBox Back_Credits = new VBox(1);
        Back_Credits.getChildren().add(backButton(stage, Layout_Title));
        Back_Credits.setAlignment(Pos.BOTTOM_LEFT);
        creditsScene = new Scene(new StackPane(g, Layout_Credits, Back_Credits), width, height);

        //Create back button here
        stage.setScene(titleScene);
        stage.show();
    }

    public static Button[] equipButtons(Stage stage)
    {
        //Gets the total amount of upgrades in the Shop.upgrades 2D array
        int count = 0;
        for(Upgrades[] a : Shop.upgrades)
        {
            for(Upgrades b : a)
            {
                count++;
            }
        }
        Button[] buttons = new Button[count];
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i] = new Button();
            buttons[i].setStyle("-fx-font: "  + height / 100.0 + " Helvetica");
            buttons[i].setMinWidth(height / 7.5);

            //Gets the upgrade that the button will be assigned to
            int row = i / 4;
            int col = i % 4;
            Upgrades u = Shop.upgrades[row][col];

            //Names the button after its upgrade
            buttons[i].setText(u.getName());

            buttons[i].setOnMouseClicked(new EventHandler<MouseEvent>
                () {

                    @Override
                    public void handle(MouseEvent t) {
                        selected = u;
                        specs.set(u.getSpecs());

                        if(stage.getScene() == catalogScene)
                        {
                            if(selected.getIsPurchased())
                            {
                                catalogStatus.set("SOLD OUT");
                            } else if(selected.getCost() > Shop.getBalance())
                            {
                                catalogStatus.set("INSUFFICIENT FUNDS");
                            } else {
                                catalogStatus.set("Not Purchased");
                            }
                        } else {
                            if(player.checkIsEquipped(selected))
                            {
                                equipStatus.set("Equipped!");
                            } else if(!(selected.getIsPurchased()))
                            {
                                equipStatus.set("You do not own this item");
                            } else {
                                equipStatus.set("Not Equipped");
                            }
                        }
                    }
                });
        }
        return buttons;
    }

    public static Button backButton(Stage stage, VBox layout)
    {
        Button b = new Button();
        b.setStyle("-fx-font: " + height / 120.0 + " Helvetica");
        b.setMinWidth(height / 10.0);
        b.setText("Back");
        b.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Terrain.getTime();
                    currBack.set(Terrain.getCurrCopy().getImage());

                    stage.setScene(titleScene);
                }
            });
        return b;
    }

    public static void main(String[] args) 
    {
        launch(args);
    }

    private void launch(){

        final ScheduledExecutorService scheduler 
        = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
            new Runnable(){

                @Override
                public void run() {
                    if(!(logic.get_y() <= 0.0001)){

                        Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    tempDistance.set("Distance: " + (int)(logic.get_x()) + " m");
                                    tempAltitude.set("Altitude: " + (int)(logic.get_y()) + " m");
                                    tempAirTime.set("Air Time: " + (int)(logic.getTime()) + " s");
                                    tempVelocity.set("Speed: " + (int)(logic.get_vx()) + " m/s");
                                }
                            });

                    }else{
                        scheduler.shutdown();
                        Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    isFinished = true;
                                }
                            });
                    }

                }
            }, 
            1, 
            1, 
            TimeUnit.SECONDS);   
    }
}
