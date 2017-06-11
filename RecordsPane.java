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
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;
public class RecordsPane extends PaneBuilder
{
    public RecordsPane()
    {
        super();
        VBox Layout_Records = new VBox(50);

        //Create Labels and bind them to certain strings
        Label[] labels = new Label[7];
        for(int i = 0; i < 7; i++)
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
        labels[0].textProperty().bind(new SimpleStringProperty("Top Distance: " + (int)(Score.getTopDistance()) + " m"));
        labels[1].textProperty().bind(new SimpleStringProperty("Top Altitude: " + (int)(Score.getTopHeight()) + " m"));
        labels[2].textProperty().bind(new SimpleStringProperty("Top Air Time: " + (int)(Score.getTopAirTime()) + " s"));
        labels[3].textProperty().bind(new SimpleStringProperty("Top Speed: " + (int)(Score.getTopVelocity()) + " m/s"));
        labels[4].textProperty().bind(new SimpleStringProperty("Top Score: " + (int)(Score.getHighScore()) + " m/s"));
        labels[5].textProperty().bind(new SimpleStringProperty(Score.permAchToString()));
        
        //Add Labels to VBox and align
        Layout_Records.getChildren().addAll(labels[0], labels[1], labels[2], labels[3], labels[4], labels[5]);
        Layout_Records.setAlignment(Pos.CENTER);
        
        view.getChildren().addAll(Terrain.space, Layout_Records);
    }
}
