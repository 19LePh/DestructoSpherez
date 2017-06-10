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
/**
 * The Graphical User Interface for Destructo-Spherez.
 * 
 * @author Philip Le & Matt Carosielli 
 * @version 1.0
 */
public class GUI extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        Player player = new Player();
        stage.setTitle("Destructo Spherez!");
        //Below makes stage fullscreen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //Creates Title Text
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.ORANGE);
        Text t = new Text("Destructo - Spherez!");
        t.setFill(Color.GOLD);
        t.setFont(Font.font("Helvetica", FontWeight.BOLD, 200));

        //Creates buttons
        Button launch = new Button("Launch!");
        Button shop = new Button("Shop");
        Button equip = new Button("Equip");
        Button records = new Button("Records");
        Button credits = new Button("Credits");
        Button backButton = new Button("Back");

        //Sets the font
        launch.setStyle("-fx-font: 30 Helvetica");
        shop.setStyle("-fx-font: 30 Helvetica");
        records.setStyle("-fx-font: 30 Helvetica");
        credits.setStyle("-fx-font: 30 Helvetica");
        equip.setStyle("-fx-font: 30 Helvetica");
        backButton.setStyle("-fx-font: 30 Helvetica");

        //Changes the buttons to the same size
        launch.setMinWidth(300);
        shop.setMinWidth(300);
        records.setMinWidth(300);
        credits.setMinWidth(300);
        equip.setMinWidth(300);
        backButton.setMinWidth(300);

        //Creates the background
        Image img = Terrain.background;
        BackgroundImage bgimg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Background back = new Background(bgimg);

        //Space Background
        BackgroundImage space = new BackgroundImage(Terrain.space, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Background spaceBackground = new Background(space);

        //Creates the Title Scene and displays it
        VBox vbox = new VBox(50); // 5 is the spacing between elements in the VBox
        vbox.getChildren().addAll(t, launch, shop, equip, records, credits);
        StackPane root1 = new StackPane();
        root1.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);
        root1.setBackground(back);
        Scene titleScene = new Scene(root1, bounds.getWidth(), bounds.getHeight());
        stage.setScene(titleScene);
        stage.show();
        
        //Creates the Launch Scene
        StackPane root2 = new StackPane();
        //root2.getChildren().add(null);
        root2.setBackground(back);
        Scene launchScene = new Scene(root2, bounds.getWidth(), bounds.getHeight());

        //Creates the Shop Scene
        StackPane root3 = new StackPane();
        root3.getChildren().add(backButton);
        root3.setBackground(back);
        Scene shopScene = new Scene(root3, bounds.getWidth(), bounds.getHeight());

        //Creates the Equip Scene
        StackPane root4 = new StackPane();
        root4.getChildren().add(backButton);
        root4.setBackground(spaceBackground);
        Scene equipScene = new Scene(root4, bounds.getWidth(), bounds.getHeight());

        //Creates the Records Scene
        StackPane root5 = new StackPane();
        root5.getChildren().add(backButton);
        root5.setBackground(spaceBackground);
        Scene recordsScene = new Scene(root5, bounds.getWidth(), bounds.getHeight());

        //Creates the Credits Scene
        StackPane root6 = new StackPane();
        root6.getChildren().add(backButton);
        root6.setBackground(spaceBackground);
        Scene creditsScene = new Scene(root6, bounds.getWidth(), bounds.getHeight());

        backButton.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(titleScene);
                }
            });

        //Code for when the buttons are clicked
        launch.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(launchScene);
                    Terrain t = new Terrain(10, null);
                    //Switch scene
                    GameLogic.init(t, player);
                    //End
                    Terrain.updateBackground();
                }
            });
        shop.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(shopScene);
                }
            });
        equip.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(equipScene);
                }
            });
        records.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(recordsScene);
                }
            });
        credits.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    stage.setScene(creditsScene);
                }
            });
    }
}