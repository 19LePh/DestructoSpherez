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
public class ScorePane extends PaneBuilder
{
    public ScorePane(Score score)
    {
        super();
        VBox Layout_Score = new VBox(50);

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
        labels[0].textProperty().bind(new SimpleStringProperty("Distance: " + (int)(score.getDistance()) + " m"));
        labels[1].textProperty().bind(new SimpleStringProperty("Altitude: " + (int)(score.getMaxHeight()) + " m"));
        labels[2].textProperty().bind(new SimpleStringProperty("Air Time: " + (int)(score.getAirTime()) + " s"));
        labels[3].textProperty().bind(new SimpleStringProperty("Speed: " + (int)(score.getMaxVelocity()) + " m/s"));
        labels[4].textProperty().bind(new SimpleStringProperty(score.tempAchToString()));
        labels[5].textProperty().bind(new SimpleStringProperty("Total Score: " + (int)(score.getScore())));
        labels[6].textProperty().bind(new SimpleStringProperty("Reward Money: $" + score.getRewardMoney()));
        
        Layout_Score.getChildren().addAll(labels[0], labels[1], labels[2], labels[3], labels[4], labels[5], labels[6]);
        Layout_Score.setAlignment(Pos.CENTER);
        
        view.getChildren().addAll(Terrain.space, Layout_Score);
    }
}
