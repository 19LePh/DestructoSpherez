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
    public void start(Stage primaryStage) 
    {
        Player player = new Player();
        primaryStage.setTitle("Destructo Spherez!");
        //Below makes primaryStage fullscreen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

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

        //Sets the font
        launch.setStyle("-fx-font: 30 Helvetica");
        shop.setStyle("-fx-font: 30 Helvetica");
        records.setStyle("-fx-font: 30 Helvetica");
        credits.setStyle("-fx-font: 30 Helvetica");
        equip.setStyle("-fx-font: 30 Helvetica");

        //Sets the buttons sizes
        launch.setMinWidth(300);
        shop.setMinWidth(300);
        records.setMinWidth(300);
        credits.setMinWidth(300);
        equip.setMinWidth(300);

        launch.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    Terrain t = new Terrain(10, null);
                    //Switch scene
                    GameLogic.init(t, player);
                    //End
                    Terrain.updateBackground();
                }
            });
        //Creates the background
        Image img = Terrain.background;
        BackgroundImage bgimg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Background back = new Background(bgimg);
        //Sets the stage
        VBox vbox = new VBox(50); // 5 is the spacing between elements in the VBox
        vbox.getChildren().addAll(t, launch, shop, equip, records, credits);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);
        root.setBackground(back);
        primaryStage.setScene(new Scene(root, bounds.getWidth(), bounds.getHeight()));
        primaryStage.show();
    }
}