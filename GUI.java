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
/**
 * The Graphical User Interface for Destructo-Spherez.
 * 
 * @author Philip Le & Matt Carosielli 
 * @version 1.0
 */
public class GUI extends Application
{
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
        Shop.upgrades[0][0].setIsEquipped(player);
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

        //Terrain.setSpace();
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
            t.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 10.0));
            titles[i] = t;
        }
        titles[0].setText("Destructo - Spherez!");
        titles[1].setText("Catalog"); //Unused; remove if willing to renumber
        titles[2].setText("Records");
        titles[3].setText("Credits");
        titles[4].setText("Score");
        titles[5].setText("Equipment");

        //Creates Buttons
        Button[] buttons = new Button[80];
        for(int i = 0; i < 80; i++)
        {
            Button b = new Button("");
            b.setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
            b.setMinWidth(500);
            buttons[i] = b;
        }
        //Title buttons
        buttons[0].setText("Launch!");
        buttons[1].setText("Catalog");
        buttons[2].setText("Records");
        buttons[3].setText("Credits");
        buttons[4].setText("Back"); //Unused; remove if willing to renumber all buttons, and change loop
        //Equipment buttons for catalog
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
        buttons[37].setText("Buying"); //Unused; remove if willing to renumber
        buttons[38].setText("Equipping"); //Unused; remove if willing to renumber
        buttons[39].setText("Equipment");
        buttons[40].setText("Back"); //Used at Catalog
        buttons[41].setText("Back"); //Used at Equipment
        buttons[42].setText("Back"); //Used at Records
        buttons[43].setText("Back"); //Used at Credits
        buttons[44].setText("Back"); //Used at Score
        buttons[45].setText("Exit");
        //Equipment buttons for equipment
        buttons[46].setText("Slingshot");
        buttons[47].setText("Catapult");
        buttons[48].setText("Cannon");
        buttons[49].setText("Alien Launch Pad");
        buttons[50].setText("Dynamite");
        buttons[51].setText("TNT Tower");
        buttons[52].setText("Nuke");
        buttons[53].setText("????");
        buttons[54].setText("Fireball");
        buttons[55].setText("Hot Lead");
        buttons[56].setText("~Radioactive~");
        buttons[57].setText("The Sun");
        buttons[58].setText("Iron Spikeball");
        buttons[59].setText("Steel Spikeball");
        buttons[60].setText("Titanium Spikeball");
        buttons[61].setText("Shield of Gods");
        buttons[62].setText("Multi Bottle Rocket");
        buttons[63].setText("Capsule Rockets");
        buttons[64].setText("Bootleg Fireworks");
        buttons[65].setText("Rechargeable Boosters");
        buttons[66].setText("Giant Bottle Rocket");
        buttons[67].setText("Twin Rockets");
        buttons[68].setText("RBR");
        buttons[69].setText("The One");
        buttons[70].setText("Umbrella");
        buttons[71].setText("Glider"); 
        buttons[72].setText("Super Glider");
        buttons[73].setText("One with the Wind");
        buttons[74].setText("Propeller Hat");
        buttons[75].setText("Prototype H");
        buttons[76].setText("The Chopper");
        buttons[77].setText("Mini UFO");

        buttons[78].setText("Purchase");
        buttons[79].setText("Equip");

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
                    LaunchPane masterLaunch1 = new LaunchPane(height, width, logic);
                    Scene launchScene1 = new Scene(masterLaunch1.getView(), width, height);
                    stage.setScene(launchScene1);
                    //keyPressed
                    stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if(event.getCode() == KeyCode.ENTER)
                                {
                                    /*Task<Void> task = new Task<Void>() {
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
                                    };*/
                                    //new Thread(task).start();

                                    while(!(logic.get_y() <= 0.0001))
                                    {
                                        logic.init(score);
                                        new AnimationTimer() {
                                            @Override
                                            public void handle(long now) {
                                                {
                                                    ImageView img = Terrain.currImage;
                                                    img.setFitWidth(width);
                                                    img.setFitHeight(height);
                                                    LaunchPane masterLaunch = new LaunchPane(height, width, logic);
                                                    Scene launchScene = new Scene(masterLaunch.getView(), width, height);
                                                    stage.setScene(launchScene);
                                                }
                                            }
                                        }.start();
                                    }

                                    try {
                                        Thread.sleep(5000);
                                    } catch(InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }
                                    //Initialize Score Scene
                                    //Terrain.setSpace();
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
                                    ScorePane scorePane = new ScorePane(height, width, score);
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
                    //Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);

                    VBox Layout_Catalog = new VBox(1);
                    Layout_Catalog.setAlignment(Pos.CENTER);
                    Layout_Catalog.getChildren().add(buttons[78]);
                    for(int i = 5; i < 37; i++)
                    {
                        Layout_Catalog.getChildren().add(buttons[i]);
                    }

                    Layout_Catalog.getChildren().add(buttons[40]);
                    Pane catalog1 = new StackPane();
                    catalog1.getChildren().addAll(Layout_Catalog);

                    CatalogPane catalogPane = new CatalogPane(height, width, player, player.getLauncher(), true, false); //Arbitrary Upgrade; will be changed on button click
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
                    //Terrain.setSpace();
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
                    RecordsPane recordsPane = new RecordsPane(height, width);
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
                    //Terrain.setSpace();
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
                    CreditsPane creditsPane = new CreditsPane(height, width);
                    Pane credits2 = creditsPane.getView();
                    StackPane masterCredits = new StackPane();
                    masterCredits.getChildren().addAll(credits2, credits1);
                    Scene creditsScene = new Scene(masterCredits, width, height);
                    stage.setScene(creditsScene);
                }
            });
        buttons[39].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    buttons[41].setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
                    buttons[41].setMinWidth(height / 10.0);
                    //Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);

                    VBox Layout_Equipment = new VBox(1);
                    Layout_Equipment.setAlignment(Pos.CENTER);
                    Layout_Equipment.getChildren().add(buttons[79]);
                    for(int i = 46; i < 78; i++)
                    {
                        Layout_Equipment.getChildren().add(buttons[i]);
                    }

                    Layout_Equipment.getChildren().add(buttons[41]);
                    Pane equipment1 = new StackPane();
                    equipment1.getChildren().addAll(Layout_Equipment);

                    CatalogPane equipmentPane = new CatalogPane(height, width, player, player.getLauncher(), false, false); //Arbitrary Upgrade; will be changed on button click
                    Pane equipment2 = equipmentPane.getView();

                    StackPane masterEquipment = new StackPane();
                    masterEquipment.getChildren().addAll(equipment2, equipment1);

                    Scene equipmentScene = new Scene(masterEquipment, width, height);
                    stage.setScene(equipmentScene);
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
                        TitlePane titlePane = new TitlePane(height, width);
                        Pane title2 = titlePane.getView();
                        TitlePane masterTitle = new TitlePane(height, width);
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
            buttons[i].setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
            buttons[i].setMinWidth(height / 10.0);
            buttons[40].setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
            buttons[40].setMinWidth(height / 10.0);
            buttons[78].setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
            buttons[78].setMinWidth(height / 10.0);
            buttons[i].setOnMouseClicked(new EventHandler<MouseEvent>()
                { 
                    @Override
                    public void handle(MouseEvent t) 
                    {
                        int num = temp - 5;
                        int row = num / 4;
                        int col = num % 4;
                        Upgrades u = Shop.upgrades[row][col];
                        //Terrain.setSpace();
                        Terrain.getSpace().setFitWidth(width);
                        Terrain.getSpace().setFitHeight(height);

                        VBox Layout_Catalog = new VBox(1);
                        Layout_Catalog.setAlignment(Pos.CENTER);
                        buttons[78].setOnMouseClicked(new EventHandler<MouseEvent>
                            () {

                                @Override
                                public void handle(MouseEvent t) {
                                    int num = temp - 5;
                                    int row = num / 4;
                                    int col = num % 4;
                                    Upgrades u = Shop.upgrades[row][col];

                                    //Terrain.setSpace();
                                    Terrain.getSpace().setFitWidth(width);
                                    Terrain.getSpace().setFitHeight(height);

                                    VBox Layout_Catalog = new VBox(1);
                                    Layout_Catalog.setAlignment(Pos.CENTER);
                                    Layout_Catalog.getChildren().add(buttons[41]);
                                    for(int i = 5; i < 37; i++)
                                    {
                                        Layout_Catalog.getChildren().add(buttons[i]);
                                    }
                                    Layout_Catalog.getChildren().add(buttons[78]);
                                    Pane catalog1 = new StackPane();
                                    catalog1.getChildren().addAll(Layout_Catalog);

                                    CatalogPane catalogPane = new CatalogPane(height, width, player, u, true, true); //Arbitrary Upgrade; will be changed on button click
                                    Pane catalog2 = catalogPane.getView();

                                    StackPane masterCatalog = new StackPane();
                                    masterCatalog.getChildren().addAll(catalog2, catalog1);

                                    Scene catalogScene = new Scene(masterCatalog, width, height);
                                    stage.setScene(catalogScene);
                                }
                            });
                        Layout_Catalog.getChildren().add(buttons[78]);
                        for(int i = 5; i < 37; i++)
                        {
                            Layout_Catalog.getChildren().add(buttons[i]);
                        }

                        Layout_Catalog.getChildren().add(buttons[40]);
                        Pane catalog1 = new StackPane();
                        catalog1.getChildren().addAll(Layout_Catalog);

                        CatalogPane catalogPane = new CatalogPane(height, width, player, u, true, false); //Arbitrary Upgrade; will be changed on button click
                        Pane catalog2 = catalogPane.getView();

                        StackPane masterCatalog = new StackPane();
                        masterCatalog.getChildren().addAll(catalog2, catalog1);

                        Scene catalogScene = new Scene(masterCatalog, width, height);
                        stage.setScene(catalogScene);
                    }
                });

        }

        //Action events for all equipment equips
        for(int i = 46; i < 78; i++)
        {
            final int temp = i;
            buttons[i].setStyle("-fx-font: "  + height / 120.0 + " Helvetica");
            buttons[i].setMinWidth(height / 10.0);
            buttons[41].setStyle("-fx-font: 15 Helvetica");
            buttons[41].setMinWidth(height / 10.0);
            buttons[79].setStyle("-fx-font: 15 Helvetica");
            buttons[79].setMinWidth(height / 10.0);
            buttons[i].setOnMouseClicked(new EventHandler<MouseEvent>()
                { 
                    @Override
                    public void handle(MouseEvent t) 
                    {
                        int num = temp - 46;
                        int row = num / 4;
                        int col = num % 4;
                        Upgrades u = Shop.upgrades[row][col];

                        //Terrain.setSpace();
                        Terrain.getSpace().setFitWidth(width);
                        Terrain.getSpace().setFitHeight(height);

                        VBox Layout_Equipment = new VBox(1);
                        Layout_Equipment.setAlignment(Pos.CENTER);
                        buttons[79].setOnMouseClicked(new EventHandler<MouseEvent>
                            () {

                                @Override
                                public void handle(MouseEvent t) {
                                    int num = temp - 46;
                                    int row = num / 4;
                                    int col = num % 4;
                                    Upgrades u = Shop.upgrades[row][col];

                                    //Terrain.setSpace();
                                    Terrain.getSpace().setFitWidth(width);
                                    Terrain.getSpace().setFitHeight(height);

                                    VBox Layout_Equipment = new VBox(1);
                                    Layout_Equipment.setAlignment(Pos.CENTER);
                                    Layout_Equipment.getChildren().add(buttons[41]);
                                    for(int i = 46; i < 78; i++)
                                    {
                                        Layout_Equipment.getChildren().add(buttons[i]);
                                    }

                                    Layout_Equipment.getChildren().add(buttons[79]);
                                    Pane equipment1 = new StackPane();
                                    equipment1.getChildren().addAll(Layout_Equipment);

                                    CatalogPane equipmentPane = new CatalogPane(height, width, player, u, false, true);
                                    Pane equipment2 = equipmentPane.getView();

                                    StackPane masterEquipment = new StackPane();
                                    masterEquipment.getChildren().addAll(equipment2, equipment1);

                                    Scene equipmentScene = new Scene(masterEquipment, width, height);
                                    stage.setScene(equipmentScene);
                                }
                            });
                        Layout_Equipment.getChildren().add(buttons[79]);
                        for(int i = 46; i < 78; i++)
                        {
                            Layout_Equipment.getChildren().add(buttons[i]);
                        }
                        buttons[79].setOnMouseClicked(new EventHandler<MouseEvent>
                            () {

                                @Override
                                public void handle(MouseEvent t) {
                                    int num = temp - 46;
                                    int row = num / 4;
                                    int col = num % 4;
                                    Upgrades u = Shop.upgrades[row][col];

                                    //Terrain.setSpace();
                                    Terrain.getSpace().setFitWidth(width);
                                    Terrain.getSpace().setFitHeight(height);

                                    VBox Layout_Equipment = new VBox(1);
                                    Layout_Equipment.setAlignment(Pos.CENTER);
                                    Layout_Equipment.getChildren().add(buttons[41]);
                                    for(int i = 46; i < 78; i++)
                                    {
                                        Layout_Equipment.getChildren().add(buttons[i]);
                                    }

                                    Layout_Equipment.getChildren().add(buttons[79]);
                                    Pane equipment1 = new StackPane();
                                    equipment1.getChildren().addAll(Layout_Equipment);

                                    CatalogPane equipmentPane = new CatalogPane(height, width, player, u, false, true);
                                    Pane equipment2 = equipmentPane.getView();

                                    StackPane masterEquipment = new StackPane();
                                    masterEquipment.getChildren().addAll(equipment2, equipment1);

                                    Scene equipmentScene = new Scene(masterEquipment, width, height);
                                    stage.setScene(equipmentScene);
                                }
                            });

                        Layout_Equipment.getChildren().add(buttons[41]);                          
                        Pane equipment1 = new StackPane();
                        equipment1.getChildren().addAll(Layout_Equipment);

                        CatalogPane equipmentPane = new CatalogPane(height, width, player, u, false, false);
                        Pane equipment2 = equipmentPane.getView();

                        StackPane masterEquipment = new StackPane();
                        masterEquipment.getChildren().addAll(equipment2, equipment1);

                        Scene equipmentScene = new Scene(masterEquipment, width, height);
                        stage.setScene(equipmentScene);
                    }
                });
        }

        buttons[45].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Platform.exit();
                }
            });

        buttons[78].setStyle("-fx-font: 15 Helvetica");
        buttons[78].setMinWidth(height / 10.0);
        buttons[78].setOnMouseClicked(new EventHandler<MouseEvent>
            () {

                @Override
                public void handle(MouseEvent t) {
                    //Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);

                    VBox Layout_Catalog = new VBox(1);
                    Layout_Catalog.setAlignment(Pos.CENTER);
                    Layout_Catalog.getChildren().add(buttons[41]);
                    for(int i = 5; i < 37; i++)
                    {
                        Layout_Catalog.getChildren().add(buttons[i]);
                    }

                    Layout_Catalog.getChildren().add(buttons[78]);
                    Pane catalog1 = new StackPane();
                    catalog1.getChildren().addAll(Layout_Catalog);

                    CatalogPane catalogPane = new CatalogPane(height, width, player, player.getLauncher(), true, true); //Arbitrary Upgrade; will be changed on button click
                    Pane catalog2 = catalogPane.getView();

                    StackPane masterCatalog = new StackPane();
                    masterCatalog.getChildren().addAll(catalog2, catalog1);

                    Scene catalogScene = new Scene(masterCatalog, width, height);
                    stage.setScene(catalogScene);
                }
            });
        buttons[79].setStyle("-fx-font: 15 Helvetica");
        buttons[79].setMinWidth(height / 10.0);
        buttons[79].setOnMouseClicked(new EventHandler<MouseEvent>
            () {

                @Override
                public void handle(MouseEvent t) {
                    //Terrain.setSpace();
                    Terrain.getSpace().setFitWidth(width);
                    Terrain.getSpace().setFitHeight(height);

                    VBox Layout_Equipment = new VBox(1);
                    Layout_Equipment.setAlignment(Pos.CENTER);
                    Layout_Equipment.getChildren().add(buttons[41]);
                    for(int i = 46; i < 78; i++)
                    {
                        Layout_Equipment.getChildren().add(buttons[i]);
                    }

                    Layout_Equipment.getChildren().add(buttons[79]);
                    Pane equipment1 = new StackPane();
                    equipment1.getChildren().addAll(Layout_Equipment);

                    CatalogPane equipmentPane = new CatalogPane(height, width, player, player.getLauncher(), false, true); //Arbitrary Upgrade; will be changed on button click
                    Pane equipment2 = equipmentPane.getView();

                    StackPane masterEquipment = new StackPane();
                    masterEquipment.getChildren().addAll(equipment2, equipment1);

                    Scene equipmentScene = new Scene(masterEquipment, width, height);
                    stage.setScene(equipmentScene);
                }
            });

        //Create title Scene
        VBox Layout_Title = new VBox(50);

        //Adds Buttons, Text; aligns layout
        Layout_Title.getChildren().addAll(titles[0], buttons[0], buttons[1], buttons[39], buttons[2], buttons[3], buttons[45]);
        Layout_Title.setAlignment(Pos.CENTER);

        //Creates StackPanes
        Pane title1 = new StackPane();
        title1.getChildren().addAll(Layout_Title);

        //Creates PaneBuilder objects
        TitlePane titlePane = new TitlePane(height, width);
        Pane title2 = titlePane.getView();

        //Stacks the panes
        TitlePane masterTitle = new TitlePane(height, width);
        masterTitle.getView().getChildren().addAll(title2, title1);

        //Creates Scenes
        Scene titleScene = new Scene(masterTitle.getView(), width, height);
        stage.setScene(titleScene);
        stage.show();
    }
}