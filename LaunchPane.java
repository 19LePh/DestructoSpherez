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
public class LaunchPane extends PaneBuilder
{
    public LaunchPane(double height, double width, GameLogic logic)
    {
        super(height, width);
        VBox Layout_Launch = new VBox(50);
        
        //Create Labels and bind them to certain strings
        Label[] labels = new Label[6];
        for(int i = 0; i < 6; i++)
        {
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0f);
            ds.setColor(Color.ORANGE);
            Label l = new Label("text");
            l.setMinWidth(300.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, height / 40.0));
            labels[i] = l;
        }
        /*labels[0].setText("Distance: " + (int)(logic.get_x()) + " m");
        labels[1].textProperty().bind(new SimpleStringProperty("Altitude: " + (int)(logic.get_y()) + " m"));
        labels[2].textProperty().bind(new SimpleStringProperty("Air Time: " + (int)(logic.getTime()) + " s"));
        labels[3].textProperty().bind(new SimpleStringProperty("Speed: " + (int)(logic.get_vx()) + " m/s"));*/
        
        labels[0].setText("Distance unavailable"); //replace this with above block of code if implmented
        labels[1].setText("Altitude unavailable");
        labels[2].setText("Air Time unavailable");
        labels[3].setText("Speed unavailable");
        
        labels[4].setText("Press Enter to Launch");
        //labels[5].setText("Press Arrow Keys to rotate"); Not implmented yet
        labels[5].setText("Sprites not made; wait after launch"); //Replace this with above line if implmented
        
        //Add Labels to VBox and align
        Layout_Launch.getChildren().addAll(labels[0], labels[1], labels[2], labels[3], labels[4], labels[5]);
        Layout_Launch.setAlignment(Pos.TOP_LEFT);
        
        view.getChildren().addAll(Terrain.currImage, Layout_Launch);
    }
}
