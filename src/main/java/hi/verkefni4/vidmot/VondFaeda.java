package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Position;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.util.Random;

public class VondFaeda extends Faeda {

    Image vondurFood = new Image(Faeda.class.getResourceAsStream("food/image.png"));

    private Position position;
    private Random r = new Random();
    private int posx = r.nextInt(14);
    private int posy = r.nextInt(14);
    private String name;

    public VondFaeda() {
        name = "vondurmatur";
        this.setHeight(30);
        this.setWidth(30);
        position = new Position(posx * 30, posy * 30);
        this.setX(position.getXPos());
        this.setY(position.getYPos());
        this.setFill(new ImagePattern(vondurFood));
    }

    public String getName() {
        return name;
    }
}
