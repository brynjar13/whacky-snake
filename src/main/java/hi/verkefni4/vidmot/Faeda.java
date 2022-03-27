package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Position;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem býr til býr til Faedu.
 * setur rétta hæð, breidd og staðsetningu.
 *********************************************************/
public class Faeda extends Rectangle {

    Image mainFood = new Image(Faeda.class.getResourceAsStream("food/apple.png"));

    private Position position;
    private Random r = new Random();
    private int posx = r.nextInt(14);
    private int posy = r.nextInt(14);
    private String name;

    /**
     * Smiður fyrir fæðu sem setur hana á handahófskenndan stað á borðinu og setur radíus
     */
    public Faeda() {
        name = "matur";
        this.setHeight(30);
        this.setWidth(30);
        position = new Position(posx * 30, posy * 30);
        this.setX(position.getXPos());
        this.setY(position.getYPos());
        this.setFill(new ImagePattern(mainFood));
    }

    public Position getPosition() {
        return position;
    }

    public void faeraFaedu(){
        getRandomSpotForFood();
    }

    public void getRandomSpotForFood(){
        int positionX = r.nextInt(14);
        int positionY = r.nextInt(14);
        this.setX(positionX * 30);
        this.setY(positionY * 30);

        position.setXPos(positionX * 30);
        position.setYPos(positionY * 30);
    }

    public String getName() {
        return name;
    }
}
