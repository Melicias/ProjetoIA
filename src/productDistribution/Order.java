package productDistribution;

import java.awt.*;

public class Order {

    public Point position;
    public int boxes;

    public Order(int x, int y, int boxes) {
        position = new Point (x, y);
        this.boxes = boxes;
    }

    public Point getPosition() {
        return position;
    }

    public int getBoxes() {
        return boxes;
    }

    @Override
    public String toString() {
        return "\n" + position.x + "\t" + position.y + "\t" + boxes;
    }
}
