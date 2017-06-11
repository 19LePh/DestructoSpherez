import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.image.ImageView;
public abstract class PaneBuilder
{
    protected Pane view;

    public PaneBuilder()
    {
        view = new StackPane();
    }

    public Pane getView()
    {
        return view;
    }
}
