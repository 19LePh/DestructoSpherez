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
        primaryStage.setTitle("Destructo Spherez!");
        //Below makes primaryStage fullscreen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        //Buttons!
        Button start = new Button();
        Button shop = new Button();
        start.setStyle("-fx-font: 30 arial");
        shop.setStyle("-fx-font: 30 arial");
        shop.setText("Shop");
        start.setText("Start");
        start.setOnAction(new EventHandler<ActionEvent>()
            { 
                @Override
                public void handle(ActionEvent event) 
                {
                    System.out.println("Destructo Spherez");
                }
            });
        //Creates a background object
        Image img = new Image("landscape.jpg");
        BackgroundImage bgimg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, false, false));
        Background back = new Background(bgimg);
        //Sets the stage
        VBox vbox = new VBox(5); // 5 is the spacing between elements in the VBox
        vbox.getChildren().addAll(start, shop);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);
        root.setBackground(back);
        primaryStage.setScene(new Scene(root, bounds.getWidth(), bounds.getHeight()));
        primaryStage.show();
    }
}