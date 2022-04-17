package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Position;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem býr til býr til OfurFaeda, erfir frá Faeda.
 * setur rétta hæð, breidd og staðsetningu.
 *********************************************************/
public class OfurFaeda extends Faeda{

    Image vondurFood = new Image(Faeda.class.getResourceAsStream("food/pngwing.com.png"));

    private Position position;
    private Random r = new Random();
    private int posx = r.nextInt(14);
    private int posy = r.nextInt(14);

    /**
     * Smiður fyrir fæðu sem setur hana á handahófskenndan stað á borðinu og setur radíus.
     * setur rétta mynd á ofurfæðu.
     */
    public OfurFaeda() {
        this.setHeight(30);
        this.setWidth(30);
        position = new Position(posx * 30, posy * 30);
        this.setX(position.getXPos());
        this.setY(position.getYPos());
        this.setFill(new ImagePattern(vondurFood));
    }
}
