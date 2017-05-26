import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        Button start = new Button();
        Button shop = new Button();
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
        StackPane root = new StackPane();
        root.getChildren().add(start);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}