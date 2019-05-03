package Main;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public interface CreateThings {
    Node createEntity(int x, int y, int w, int h, Color color);
}
