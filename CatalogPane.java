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
import javafx.scene.image.ImageView;
public class CatalogPane extends PaneBuilder
{
    //if isBuying is true, the title will be Catalog. if false, title will be Equipment
    public CatalogPane(Player player, Upgrades u, boolean isBuying, boolean clicked)
    {
        super();
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
            labels[i] = l;
        }
        labels[0].setText("Balance: $" + Shop.getBalance());
        labels[1].setText(u.getSpecs());

        //Create Title
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.ORANGE);
        if(isBuying)
        {
            if(clicked)
            {
                Shop.buy(u);
            }
            Text t = new Text("Catalog");
            t.setFill(Color.GOLD);
            t.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
            Layout_Catalog_Balance.getChildren().add(t);
            if(u.getIsPurchased())
            {
                Label a = new Label("SOLD OUT");
                a.setMinWidth(300.0);
                a.setTextFill(Color.GOLD);
                a.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
                Layout_Catalog_Balance.getChildren().add(a);
            } else if(u.getCost() > Shop.getBalance())
            {
                Label b = new Label("INSUFFICIENT FUNDS");
                b.setMinWidth(300.0);
                b.setTextFill(Color.GOLD);
                b.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
                Layout_Catalog_Balance.getChildren().add(b);
            }
        } else {
            Text x = new Text("Equipment");
            x.setFill(Color.GOLD);
            x.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
            Layout_Catalog_Balance.getChildren().add(x);
            if(u.setIsEquipped(player))
            {
                Label c = new Label("Equipped!");
                c.setMinWidth(300.0);
                c.setTextFill(Color.GOLD);
                c.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
                Layout_Catalog_Balance.getChildren().add(c);
            } else {
                Label d = new Label("You do not own this item");
                d.setMinWidth(300.0);
                d.setTextFill(Color.GOLD);
                d.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
                Layout_Catalog_Balance.getChildren().add(d);
            }
        }

        //Add_Labels to VBox and align
        Layout_Catalog_Balance.getChildren().addAll(labels[0]);
        Layout_Catalog_Balance.setAlignment(Pos.TOP_LEFT);

        Layout_Catalog_Specs.getChildren().addAll(labels[1]);
        Layout_Catalog_Specs.setAlignment(Pos.TOP_RIGHT);

        view.getChildren().addAll(Terrain.space, Layout_Catalog_Balance, Layout_Catalog_Specs);
    }
}
