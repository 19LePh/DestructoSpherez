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
    private static Label specs; //Label for both "buying" and "equipping"
    private static Label xPos, yPos, airTime, velocity; //Only for launch
    private static Label maxHeight, maxDistance, maxVelocity, finalAirTime, totalScore, rewardMoney, wallsDestroyed; //Only for score

    private static VBox Layout_title, Layout_Launch, Layout_Records, Layout_Credits, Layout_Score; //For title
    private static VBox Layout_Catalog_Tier1, Layout_Catalog_Tier2, Layout_Catalog_Tier3, Layout_Catalog_Tier4; //For catalog. Features all equipment

    //Player Elements
    private static final Player player = new Player();
    private static Score score;

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

        //Creates Titles
        Text[] titles = new Text[5];
        for(int i = 0; i < 5; i++)
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
        titles[1].setText("Catalog");
        titles[2].setText("Records");
        titles[3].setText("Credits");
        titles[4].setText("Score");

        //Creates Buttons
        Button[] buttons = new Button[39];
        for(int i = 0; i < 39; i++)
        {
            Button b = new Button("");
            b.setStyle("-fx-font: 30 Helvetica");
            b.setMinWidth(300);
            buttons[i] = b;
        }
        //Title buttons
        buttons[0].setText("Launch!");
        buttons[1].setText("Catalog"); 
        buttons[2].setText("Records");
        buttons[3].setText("Credits");
        buttons[4].setText("Back");
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

        //Action events for title buttons
        buttons[0].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(launchScene);
                    Wall[] walls = {Wall.WALL_1, Wall.WALL_2, Wall.WALL_3, Wall.WALL_4, Wall.WALL_5};
                    score = new Score(walls);
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
                    //keyPressed
                    launchScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if(event.getCode() == KeyCode.ENTER)
                                {
                                    while(!(logic.get_y() <= 0.0001))
                                    {
                                        logic.init(score);
                                    }
                                    Terrain.updateBackground();
                                    try {
                                        Thread.sleep(5000);
                                    } catch(InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }

                                    stage.setScene(scoreScene);
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
                    stage.setScene(catalogScene);
                }
            });
        buttons[2].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(recordsScene);
                }
            });
        buttons[3].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(creditsScene);
                }
            });
        buttons[4].setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(titleScene);
                }
            });

        //Action events for all equipment
        for(int i = 5; i < 37; i++)
        {
            final int temp = i;
            buttons[i].setOnAction(new EventHandler<ActionEvent>()
                { 
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        int num = temp - 5;
                        int row = num / 4;
                        int col = num % 4;
                        Upgrades u = Shop.upgrades[row][col];
                        specs.setText(u.getSpecs());
                        if(shoppingMode.isVisible() && !equippingMode.isVisible())
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
                        }
                    }
                });
        }

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

        //Creates Labels
        Label[] labels = new Label[19];
        for(int i = 0; i < 19; i++)
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
        labels[0].setText("You are currently shopping"); shoppingMode = labels[0];
        labels[1].setText("You are currently equipping"); equippingMode = labels[1];
        labels[2].setText("SOLD OUT"); soldOut = labels[2];
        labels[3].setText("INSUFFICIENT FUNDS"); insufficientFunds = labels[3];
        labels[4].setText("Balance: $" + Shop.getBalance()); balance = labels[4];
        labels[5].setText("Equipped!"); equipped = labels[5];
        labels[6].setText("You do not own this item"); notOwned = labels[6];
        labels[7].setText(""); specs = labels[7];
        labels[8].setText("Distance: "); xPos = labels[8];
        labels[9].setText("Height: "); yPos = labels[9];
        labels[10].setText("Air Time: "); airTime = labels[10];
        labels[11].setText("Speed: "); velocity = labels[11];
        labels[12].setText("Max Height: "); maxHeight = labels[12];
        labels[13].setText("Distance: "); maxDistance = labels[13];
        labels[14].setText("Max Speed: "); maxVelocity = labels[14];
        labels[15].setText("Total Score: "); totalScore = labels[15];
        labels[16].setText("Reward Money: "); rewardMoney = labels[16];
        labels[17].setText("Air Time: "); finalAirTime = labels[17];
        labels[18].setText(""); wallsDestroyed = labels[18];

        //Creates Layouts
        Layout_title = new VBox(50); //The input is the spacing between elements in the VBox
        Layout_Launch = new VBox(50);
        Layout_Records = new VBox(50);
        Layout_Credits = new VBox(50);
        Layout_Score = new VBox(50);

        Layout_Catalog_Tier1 = new VBox(50);
        Layout_Catalog_Tier2 = new VBox(50);
        Layout_Catalog_Tier3 = new VBox(50);
        Layout_Catalog_Tier4 = new VBox(50);

        //Add elements to layouts
        Layout_title.getChildren().addAll(titles[0], buttons[0], buttons[1], buttons[2], buttons[3]);
        Layout_Launch.getChildren().addAll(xPos, yPos, airTime, velocity); //NEEDS WORK; does not update during a launch
        /*Layout_Records.getChildren().addAll
        Layout_Credits.getChildren().addAll*/
        Layout_Score.getChildren().addAll(titles[4], maxDistance, maxHeight, maxVelocity, finalAirTime, wallsDestroyed, totalScore, rewardMoney, buttons[4]);

        /*Layout_Catalog_Tier1.getChildren().addAll
        Layout_Catalog_Tier2.getChildren().addAll
        Layout_Catalog_Tier3.getChildren().addAll
        Layout_Catalog_Tier4.getChildren().addAll*/

        //Align VBoxes
        Layout_title.setAlignment(Pos.CENTER);
        Layout_Launch.setAlignment(Pos.TOP_LEFT);
        Layout_Records.setAlignment(Pos.CENTER);
        Layout_Credits.setAlignment(Pos.CENTER);
        Layout_Score.setAlignment(Pos.CENTER);
        /*
        Layout_Catalog_Tier1
        Layout_Catalog_Tier2
        Layout_Catalog_Tier3
        Layout_Catalog_Tier4
         */

        //Creates Background
        BackgroundImage bgimg = new BackgroundImage(Terrain.background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Time_Background = new Background(bgimg);

        //Unique Space Background
        BackgroundImage space = new BackgroundImage(Terrain.space, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Background spaceBackground = new Background(space);

        //Creates StackPanes from layouts
        StackPane Pane_title = new StackPane();
        Pane_title.getChildren().add(Layout_title);
        Pane_title.setBackground(Time_Background);

        StackPane Pane_launch = new StackPane();
        Pane_launch.getChildren().add(Layout_Launch);
        Pane_launch.setBackground(Time_Background);

        StackPane Pane_score = new StackPane();
        Pane_score.getChildren().addAll(Layout_Score);
        Pane_score.setBackground(spaceBackground);

        //Creates Scenes
        titleScene = new Scene(Pane_title, width, height);
        launchScene = new Scene(Pane_launch, width, height);

        /*catalogScene = new Scene(Pane_title, width, height);
        recordsScene = new Scene(Pane_title, width, height);
        creditsScene = new Scene(Pane_title, width, height);*/
        scoreScene = new Scene(Pane_score, width, height);
        
        stage.setScene(titleScene);
        stage.show();
    }

    public static void updateLaunchLabels(GameLogic logic, Score score)
    {
        xPos.setText("Distance: " + (int)(logic.get_x()));
        yPos.setText("Height: " + (int)(logic.get_y()));
        airTime.setText("Air Time: " + (int)(logic.getTime()) + " s");
        finalAirTime.setText("Air Time: " + (int)(logic.getTime()) + " s");
        velocity.setText("Speed: " + (int)(logic.get_vx()) + " m/s");
        maxHeight.setText("Max Height: " + (int)(score.getMaxHeight()) + " m");
        maxDistance.setText("Distance: " + (int)(score.getDistance()) + " m");
        maxVelocity.setText("Max Speed: " + (int)(score.getMaxVelocity()) + " m/s");
        totalScore.setText("Total Score: " + (int)score.getScore());
        rewardMoney.setText("Reward Money: $" + score.getRewardMoney());

        //Code for wallsDestroyed text
        String text = "Walls Destroyed: ";
        if(score.getTempAchievements()[0])
        {
            text += "1!";
        }
        if(score.getTempAchievements()[1])
        {
            text += ", 2!";
        }
        if(score.getTempAchievements()[2])
        {
            text += ", 3!";
        }
        if(score.getTempAchievements()[3])
        {
            text += ", 4!";
        }
        if(text.equals("Walls Destroyed: "))
        {
            text = "Walls Destroyed: None -_-";
        }
        wallsDestroyed.setText(text);
    }
}