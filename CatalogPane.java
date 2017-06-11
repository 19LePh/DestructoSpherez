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
public class CatalogPane extends PaneBuilder
{
    protected Upgrades upgrade;

    public CatalogPane(Upgrades u)
    {
        super();
        upgrade = u;
        VBox Layout_Catalog_Balance = new VBox(50);
        VBox Layout_Catalog_Specs = new VBox(50);

        //Creates Labels and bind them to certain strings
        Label[] labels = new Label[2];
        for(int i = 0; i < 2; i++)
        {
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0f);
            ds.setColor(Color.ORANGE);
            Label l = new Label("text");
            l.setMinWidth(300.0);
            l.setTextFill(Color.GOLD);
            l.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
        }
        labels[0].textProperty().bind(new SimpleStringProperty("Balance: $" + Shop.getBalance()));
        labels[1].textProperty().bind(new SimpleStringProperty(upgrade.getSpecs()));

        //Add_Labels to VBox and align
        Layout_Catalog_Balance.getChildren().addAll(labels[0]);
        Layout_Catalog_Balance.setAlignment(Pos.TOP_LEFT);
        
        Layout_Catalog_Specs.getChildren().addAll(labels[1]);
        Layout_Catalog_Specs.setAlignment(Pos.TOP_RIGHT);

        view.getChildren().addAll(Terrain.space, Layout_Catalog_Balance, Layout_Catalog_Specs);
    }
    
    public void setUpgrade(Upgrades u)
    {
        upgrade = u;
    }
}
