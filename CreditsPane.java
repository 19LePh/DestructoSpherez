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
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;
public class CreditsPane extends PaneBuilder
{
    public CreditsPane(double height, double width)
    {
        super(height, width);
        VBox Layout_Credits = new VBox(50);

        //Create Labels and bind them to certain strings
        Label[] labels = new Label[2];
        for(int i = 0; i < 2; i++)
        {
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0f);
            ds.setColor(Color.ORANGE);
            Label l = new Label("text");
            l.setMinWidth(height / 240.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            labels[i] = l;
        }
        labels[0].setText("APCS Final Project 2017");
        labels[1].setText("Creators: Phillip Le & Matt Carosielli");
        
        //Add Labels to Vbox and align
        Layout_Credits.getChildren().addAll(labels[0], labels[1]);
        Layout_Credits.setAlignment(Pos.CENTER);
        
        view.getChildren().addAll(Terrain.space, Layout_Credits);
    }
}
