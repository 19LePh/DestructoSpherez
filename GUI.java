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
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
/**
 * The Graphical User Interface for Destructo-Spherez.
 * 
 * @author Philip Le & Matt Carosielli 
 * @version 1.0
 */
public class GUI extends Application
{
    //Graphical Elements
    private static Scene titleScene, launchScene, catalogScene, recordsScene, creditsScene, scoreScene;

    private static Background Time_Background; //Backgrounds based on time
    private static Background Pos_Background; //Backgrounds based on player's position and Time_Background

    private static Label shoppingMode, equippingMode;
    private static Label soldOut, insufficientFunds, balance; //only shows if label shoppingMode is visible
    private static Label equipped, notOwned; //only shows if label equipping is visible

    //Player Elements
    private static final Player player = new Player();

    //Full Screen Fields
    private static double minX;
    private static double minY;
    private static double width;
    private static double height;
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        stage.initStyle(StageStyle.UNDECORATED);
        //Purchased default launcher
        Shop.buy(Shop.upgrades[0][0]);
        Shop.upgrades[0][0].equip(player);
        //Fullscreen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        minX = bounds.getMinX();
        minY = bounds.getMinY();
        width = bounds.getWidth();
        height = bounds.getHeight();

        //Creates stage
        stage.setTitle("Destructo - Spherez!");
        stage.setX(minX);
        stage.setY(minY);
        stage.setWidth(width);
        stage.setHeight(height);

        Terrain.getTime();
        ImageView img = Terrain.currImage;
        img.setFitWidth(width);
        img.setFitHeight(height);

        Terrain.setSpace();
        Terrain.getSpace().setFitWidth(width);
        Terrain.getSpace().setFitHeight(height);

        ImageView spaceImg = Terrain.space;
        spaceImg.setFitWidth(width);
        spaceImg.setFitHeight(height);

        //Creates Titles
        Text[] titles = new Text[6];
        for(int i = 0; i < 6; i++)
        {
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0f);
            ds.setColor(Color.ORANGE);
            Text t = new Text("");
            t.setFill(Color.GOLD);
            t.setFont(Font.font("Helvetica", FontWeight.BOLD, 200));
            titles[i] = t;
        }
        titles[0].setText("Destructo - Spherez!");
        titles[1].setText("Catalog"); //Unused; remove if willing to renumber
        titles[2].setText("Records");
        titles[3].setText("Credits");
        titles[4].setText("Score");
        titles[5].setText("Equipment");

        //Creates Buttons
        Button[] buttons = new Button[46];
        for(int i = 0; i < 46; i++)
        {
            Button b = new Button("");
            b.setStyle("-fx-font: 30 Helvetica");
            b.setMinWidth(500);
            buttons[i] = b;
        }
        //Title buttons
        buttons[0].setText("Launch!");
        buttons[1].setText("Catalog");
        buttons[2].setText("Records");
        buttons[3].setText("Credits");
        buttons[4].setText("Back"); //Unused; remove if willing to renumber all buttons, and change loop
        //Equipment buttons
        buttons[5].setText("Slingshot");
        buttons[6].setText("Catapult");
        buttons[7].setText("Cannon");
        buttons[8].setText("Alien Launch Pad");
        buttons[9].setText("Dynamite");
        buttons[10].setText("TNT Tower");
        buttons[11].setText("Nuke");
        buttons[12].setText("????");
        buttons[13].setText("Fireball");
        buttons[14].setText("Hot Lead");
        buttons[15].setText("~Radioactive~");
        buttons[16].setText("The Sun");
        buttons[17].setText("Iron Spikeball");
        buttons[18].setText("Steel Spikeball");
        buttons[19].setText("Titanium Spikeball");
        buttons[20].setText("Shield of Gods");
        buttons[21].setText("Multi Bottle Rocket");
        buttons[22].setText("Capsule Rockets");
        buttons[23].setText("Bootleg Fireworks");
        buttons[24].setText("Rechargeable Boosters");
        buttons[25].setText("Giant Bottle Rocket");
        buttons[26].setText("Twin Rockets");
        buttons[27].setText("RBR");
        buttons[28].setText("The One");
        buttons[29].setText("Umbrella");
        buttons[30].setText("Glider"); 
        buttons[31].setText("Super Glider");
        buttons[32].setText("One with the Wind");
        buttons[33].setText("Propeller Hat");
        buttons[34].setText("Prototype H");
        buttons[35].setText("The Chopper");
        buttons[36].setText("Mini UFO");
        //Misc catalog buttons
        buttons[37].setText("Buying");
        buttons[38].setText("Equipping");
        buttons[39].setText("Equipment");
        buttons[40].setText("Back"); //Used at Catalog
        buttons[41].setText("Back"); //Used at Equipment
        buttons[42].setText("Back"); //Used at Records
        buttons[43].setText("Back"); //Used at Credits
        buttons[44].setText("Back"); //Used at Score
        buttons[45].setText("Exit");

        //Action events for title buttons
        buttons[0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Wall[] walls = {Wall.WALL_1, Wall.WALL_2, Wall.WALL_3, Wall.WALL_4, Wall.WALL_5};
                    Score score = new Score(walls);
                    ArrayList<Powerups> powerups = new ArrayList<Powerups>();
                    Terrain t = new Terrain(powerups, walls);
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
                    GameLogic logic = new GameLogic(initialSpeed, angle, 0.0, 10.0, 1.0, accelerationY, playerMass, playerCrossSectionalArea);

                    ImageView img1 = Terrain.currImage;
                    img1.setFitWidth(width);
                    img1.setFitHeight(height);
                    LaunchPane masterLaunch1 = new LaunchPane(new GameLogic());
                    Scene launchScene1 = new Scene(masterLaunch1.getView(), width, height);
                    stage.setScene(launchScene1);
                    //keyPressed
                    stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if(event.getCode() == KeyCode.ENTER)
                                {
                                    Task<Void> task = new Task<Void>() {
                                            @Override 
                                            public Void call() throws Exception {
                                                for (int i=1; i<=10; i++) {
                                                    //Reinitialize launchScene
                                                    ImageView img = Terrain.currImage;
                                                    img.setFitWidth(width);
                                                    img.setFitHeight(height);
                                                    LaunchPane masterLaunch = new LaunchPane(logic);
                                                    Scene launchScene = new Scene(masterLaunch.getView(), width, height);
                                                    stage.setScene(launchScene);
                                                    Thread.sleep(250);
                                                }
                                                return null ;
                                            }
                                        };
                                    new Thread(task).start();
                                    while(!(logic.get_y() <= 0.0001))
                                    {

                                        logic.init(score);
                                    }
                                    //Terrain.updateBackground();
                                    try {
                                        Thread.sleep(5000);
                                    } catch(InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }

                                    //Initialize Score Scene
                                    Terrain.setSpace();
                                    Terrain.getSpace().setFitWidth(width);
                                    Terrain.getSpace().setFitHeight(height);
                                    VBox Layout_Score = new VBox(50);
                                    VBox Layout_Back_Score = new VBox(50);
                                    Layout_Back_Score.getChildren().addAll(buttons[44]);
                                    Layout_Back_Score.setAlignment(Pos.BOTTOM_LEFT);
                                    Layout_Score.getChildren().addAll(titles[4]);
                                    Layout_Score.setAlignment(Pos.TOP_CENTER);
                                    Pane score1 = new StackPane();
                                    score1.getChildren().addAll(Layout_Score, Layout_Back_Score);
                                    ScorePane scorePane = new ScorePane(score);
                                    Pane score2 = scorePane.getView();
                                    StackPane masterScore = new StackPane();
                                    masterScore.getChildren().addAll(score2, score1);
                                    Scene scoreScene = new Scene(masterScore, width, height);
                                    stage.setScene(scoreScene);
                                    Shop.setBalance(score.getRewardMoney());
                                }
                            }
                        });
                }
            });
        buttons[1].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);

                    //Create Rows
                    HBox Layout_Catalog = new HBox(1); //Launchers Path 1
                    Layout_Catalog.setAlignment(Pos.CENTER);
                    Layout_Catalog.getChildren().add(buttons[40]);
                    for(int i = 5; i < 37; i++)
                    {
                        Layout_Catalog.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R1.setPadding(new Insets(.875 * height, width / 2.0, .875 * height, width / 2.0));
                    //Layout_Catalog_R1.relocate(width, 0.5 * (3.75 * height));
                    /*HBox Layout_Catalog_R2 = new HBox(500); //Launchers Path 2
                    for(int i = 9; i < 13; i++)
                    {
                    Layout_Catalog_R2.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R2.setPadding(new Insets(.7 * height, width / 2.0, 0.7 * height, width / 2.0));
                    //Layout_Catalog_R2.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R2.setLayoutY(2.5 * height);
                    HBox Layout_Catalog_R3 = new HBox(500); //Enhancements Path 1
                    for(int i = 13; i < 17; i++)
                    {
                    Layout_Catalog_R3.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R3.setPadding(new Insets(0.55 * height, width / 2.0, 0.55 * height, width / 2.0));
                    //Layout_Catalog_R3.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R3.setLayoutY(1.25 * height);
                    HBox Layout_Catalog_R4 = new HBox(500); //Enhancements Path 2
                    for(int i = 17; i < 21; i++)
                    {
                    Layout_Catalog_R4.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R4.setPadding(new Insets(0.425 * height, width / 2.0, 0.425 * height, width / 2.0));
                    //Layout_Catalog_R4.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R4.setLayoutY(0.0);
                    HBox Layout_Catalog_R5 = new HBox(500); //Boosters Path 1
                    for(int i = 21; i < 25; i++)
                    {
                    Layout_Catalog_R5.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R5.setPadding(new Insets(.3 * height, width / 2.0, .3 * height, width / 2.0));
                    //Layout_Catalog_R5.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R5.setLayoutY(-0.125 * height);
                    HBox Layout_Catalog_R6 = new HBox(500); //Boosters Path 2
                    for(int i = 25; i < 29; i++)
                    {
                    Layout_Catalog_R6.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R6.setPadding(new Insets(.175 * height, width / 2.0, .175 * height, width / 2.0));
                    //Layout_Catalog_R6.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R6.setLayoutY(-0.25 * height);
                    HBox Layout_Catalog_R7 = new HBox(500); //Mount Path 1
                    for(int i = 29; i < 33; i++)
                    {
                    Layout_Catalog_R7.getChildren().add(buttons[i]);
                    }
                    //Layout_Catalog_R7.setPadding(new Insets(.05 * height, width / 2.0, .5 * height, width / 2.0));
                    //Layout_Catalog_R7.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R7.setLayoutY(-0.375 * height);
                    HBox Layout_Catalog_R8 = new HBox(500); //Mount Path 2
                    for(int i = 33; i < 37; i++)
                    {
                    Layout_Catalog_R8.getChildren().add(buttons[i]);
                    }*/
                    //Layout_Catalog_R8.setPadding(new Insets(0.005 * height, width / 2.0, .005 * height, width / 2.0));
                    //Layout_Catalog_R8.setAlignment(Pos.CENTER);
                    //Layout_Catalog_R8.setLayoutY(-0.5 * height);
                    //Layout_Catalog_R8.setPadding(new Insets(height / 2.0, width / 2.0, height / 2.0, width / 2.0));

                    //Combines HBoxes into one VBox
                    //VBox Layout_Catalog_Compiled = new VBox(50);
                    //Layout_Catalog_Compiled.getChildren().addAll(

                    //Creates back button
                    //VBox Layout_Back_Catalog = new VBox(50);
                    //Layout_Back_Catalog.getChildren().addAll(buttons[40]);
                    //Layout_Back_Catalog.setAlignment(Pos.BOTTOM_LEFT);

                    Pane catalog1 = new StackPane();
                    catalog1.getChildren().addAll(Layout_Catalog);

                    CatalogPane catalogPane = new CatalogPane(Launchers.TIER_1_PATH_1, true); //Arbitrary Upgrade; will be changed on button click
                    Pane catalog2 = catalogPane.getView();

                    StackPane masterCatalog = new StackPane();
                    masterCatalog.getChildren().addAll(catalog2, catalog1);

                    Scene catalogScene = new Scene(masterCatalog, width, height);
                    stage.setScene(catalogScene);
                }
            });
        buttons[2].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);
                    VBox Layout_Records = new VBox(50);
                    VBox Layout_Back_Records = new VBox(50);
                    Layout_Back_Records.getChildren().addAll(buttons[42]);
                    Layout_Back_Records.setAlignment(Pos.BOTTOM_LEFT);
                    Layout_Records.getChildren().addAll(titles[2]);
                    Layout_Records.setAlignment(Pos.TOP_CENTER);
                    Pane records1 = new StackPane();
                    records1.getChildren().addAll(Layout_Records, Layout_Back_Records);
                    RecordsPane recordsPane = new RecordsPane();
                    Pane records2 = recordsPane.getView();
                    StackPane masterRecords = new StackPane();
                    masterRecords.getChildren().addAll(records2, records1);
                    Scene recordsScene = new Scene(masterRecords, width, height);
                    stage.setScene(recordsScene);
                }
            });
        buttons[3].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);
                    VBox Layout_Credits = new VBox(50);
                    VBox Layout_Back_Credits = new VBox(50);
                    Layout_Back_Credits.getChildren().addAll(buttons[43]);
                    Layout_Back_Credits.setAlignment(Pos.BOTTOM_LEFT);
                    Layout_Credits.getChildren().addAll(titles[3]);
                    Layout_Credits.setAlignment(Pos.TOP_CENTER);
                    Pane credits1 = new StackPane();
                    credits1.getChildren().addAll(Layout_Credits, Layout_Back_Credits);
                    CreditsPane creditsPane = new CreditsPane();
                    Pane credits2 = creditsPane.getView();
                    StackPane masterCredits = new StackPane();
                    masterCredits.getChildren().addAll(credits2, credits1);
                    Scene creditsScene = new Scene(masterCredits, width, height);
                    stage.setScene(creditsScene);
                }
            });
        //Action Events for all Back buttons
        for(int i = 40; i < 45; i++)
        {
            buttons[i].setOnAction(new EventHandler<ActionEvent>()
                { 
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        Terrain.getTime();
                        ImageView img = Terrain.currImage;
                        img.setFitWidth(width);
                        img.setFitHeight(height);
                        Pane title1 = new StackPane();
                        VBox Layout_Title = new VBox(50);
                        Layout_Title.getChildren().addAll(titles[0], buttons[0], buttons[1], buttons[39], buttons[2], buttons[3], buttons[45]);
                        Layout_Title.setAlignment(Pos.CENTER);
                        title1.getChildren().addAll(Layout_Title);
                        TitlePane titlePane = new TitlePane();
                        Pane title2 = titlePane.getView();
                        TitlePane masterTitle = new TitlePane();
                        masterTitle.getView().getChildren().addAll(title2, title1);
                        Scene titleScene1 = new Scene(masterTitle.getView(), width, height);
                        stage.setScene(titleScene1);
                    }
                });
        }

        //Action events for all Catalog equips
        for(int i = 5; i < 37; i++)
        {
            final int temp = i;
            buttons[i].setStyle("-fx-font: 15 Helvetica");
            buttons[i].setMinWidth(100);
            buttons[i].setMinHeight(30);
            buttons[i].setOnAction(new EventHandler<ActionEvent>()
                { 
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        int num = temp - 5;
                        int row = num / 4;
                        int col = num % 4;
                        Upgrades u = Shop.upgrades[row][col];
                        if(!Shop.buy(u))
                        {
                            if(u.getIsPurchased())
                            {
                                //create new shop scene with sold out label
                            } else {
                                //create new shop scene with insufficist funds label
                            }
                        }
                    }
                });
        }

        //Resize equipment buttons

        //specs.setText(u.getSpecs());
        /*if(shoppingMode.isVisible() && !equippingMode.isVisible())
        {
        //Code for when the player is shopping
        if(!Shop.buy(u))
        {
        if(u.getIsPurchased())
        {
        soldOut.setVisible(true);
        insufficientFunds.setVisible(false);
        } else {
        insufficientFunds.setVisible(true);
        soldOut.setVisible(false);
        }
        }
        } else if(!shoppingMode.isVisible() && equippingMode.isVisible())
        {
        //Code for when the player is equipping
        if(u.getIsPurchased())
        {
        u.equip(player);
        equipped.setVisible(true);
        notOwned.setVisible(false);
        } else {
        notOwned.setVisible(true);
        equipped.setVisible(false);
        }
        }*/
        //Action events for misc catalog buttons
        buttons[37].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    shoppingMode.setVisible(true);
                    equippingMode.setVisible(false);
                }
            });
        buttons[38].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    shoppingMode.setVisible(true);
                    equippingMode.setVisible(false);
                }
            });
        buttons[45].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Platform.exit();
                }
            });

        //Creates Labels
        Label[] labels = new Label[5];
        for(int i = 0; i < 5; i++)
        {
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0f);
            ds.setColor(Color.ORANGE);
            Label l = new Label("text");
            l.setMinWidth(300.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
            labels[i] = l;
        }
        //Labels must be binded in order to constantly update their text
        labels[0].setText("SOLD OUT");
        labels[1].setText("INSUFFICIENT FUNDS");
        labels[2].setText("Equipped!");
        labels[3].setText("You do not own this item");
        labels[4].setText("Purchased");

        //Creates Layouts
        VBox Layout_Title = new VBox(50);
        VBox Layout_Catalog_Args = new VBox(50);
        VBox Layout_Equipment = new VBox(50);

        VBox Layout_Credits = new VBox(50);

        VBox Layout_Records = new VBox(50);

        //Wall of Buttons
        VBox Layout_Back_Catalog = new VBox(50);
        VBox Layout_Back_Equipment = new VBox(50);
        VBox Layout_Back_Records = new VBox(50);
        VBox Layout_Back_Credits = new VBox(50);
        VBox Layout_Back_Score = new VBox(50);
        //Adds Buttons, Text; aligns layout
        Layout_Title.getChildren().addAll(titles[0], buttons[0], buttons[1], buttons[39], buttons[2], buttons[3], buttons[45]);
        Layout_Title.setAlignment(Pos.CENTER);

        Layout_Credits.getChildren().addAll(titles[3]);
        Layout_Credits.setAlignment(Pos.TOP_CENTER);

        Layout_Records.getChildren().addAll(titles[2]);
        Layout_Records.setAlignment(Pos.TOP_CENTER);

        //Back Buttons
        Layout_Back_Catalog.getChildren().addAll(buttons[40]);
        Layout_Back_Catalog.setAlignment(Pos.BOTTOM_LEFT);
        Layout_Back_Equipment.getChildren().addAll(buttons[41]);
        Layout_Back_Equipment.setAlignment(Pos.BOTTOM_LEFT);
        Layout_Back_Records.getChildren().addAll(buttons[42]);
        Layout_Back_Records.setAlignment(Pos.BOTTOM_LEFT);
        Layout_Back_Credits.getChildren().addAll(buttons[43]);
        Layout_Back_Credits.setAlignment(Pos.BOTTOM_LEFT);
        Layout_Back_Score.getChildren().addAll(buttons[44]);
        Layout_Back_Score.setAlignment(Pos.BOTTOM_LEFT);

        //Creates StackPanes
        Pane title1 = new StackPane();
        title1.getChildren().addAll(Layout_Title);

        Pane credits1 = new StackPane();
        credits1.getChildren().addAll(Layout_Credits, Layout_Back_Credits);

        Pane records1 = new StackPane();
        records1.getChildren().addAll(Layout_Records, Layout_Back_Records);

        //Creates PaneBuilder objects
        TitlePane titlePane = new TitlePane();
        Pane title2 = titlePane.getView();

        CreditsPane creditsPane = new CreditsPane();
        Pane credits2 = creditsPane.getView();

        RecordsPane recordsPane = new RecordsPane();
        Pane records2 = recordsPane.getView();

        //Stacks the panes
        TitlePane masterTitle = new TitlePane();
        masterTitle.getView().getChildren().addAll(title2, title1);

        StackPane masterCredits = new StackPane();
        masterCredits.getChildren().addAll(credits2, credits1);

        StackPane masterRecords = new StackPane();
        masterRecords.getChildren().addAll(records2, records1);

        //Creates Scenes
        titleScene = new Scene(masterTitle.getView(), width, height);
        creditsScene = new Scene(masterCredits, width, height);
        recordsScene = new Scene(masterRecords, width, height);

        stage.setScene(titleScene);
        stage.show();
    }
}