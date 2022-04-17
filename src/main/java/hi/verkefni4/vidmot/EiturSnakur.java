package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Position;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem býr til býr til eitursnák(Örn).
 * setur rétta hæð, breidd og staðsetningu.
 * hefur aðferðir til þess að hreyfa eitursnák(Örn) áfram
 *********************************************************/
public class EiturSnakur extends Snakur {

    public Image croco = new Image(Snakur.class.getResourceAsStream("eagle/tile007.png"));

    private int eiturAtt = 360;
    Random random = new Random();
    int randomTala = random.nextInt(5);

    private double snakeSize = 30;
    private double xPos;
    private double yPos;
    /**
     * Smiður fyrir eitursnáka.
     * Setur staðsetningu, hæð og breidd á eitursnák.
     */
    public EiturSnakur() {
        this.setHeight(40);
        this.setWidth(40);
        this.setFill(new ImagePattern(croco));
        this.setX(0);
        this.setY(0);
        xPos = this.getLayoutX();
        yPos = this.getLayoutY();
        snakeBody.add(this);
    }

    /**
     * Aðferð sem hreyfir eitursnákana áfram.
     * býr til random tölu þegar snákur hreyfir sig og lætur hana stjórna hvert ernirnir hreyfa sig
     * @param att
     */
    public void aframEiturSnakar(int att) {
        if (eiturAtt != att && xPos % 30 == 0 && yPos % 30 == 0) {
            eiturAtt = att;
            randomTala = random.nextInt(4);
        }
        if (randomTala == 0) {
            yPos = yPos - snakeSize;
            this.setTranslateY(yPos);
            this.setRotate(360);
            if (yPos < 0) {
                yPos = 420 - snakeSize;
                this.setTranslateY(yPos);
            }
        }
        if (randomTala == 1) {
            yPos = yPos + snakeSize;
            this.setTranslateY(yPos);
            this.setRotate(180);
            if (yPos > 420 - snakeSize) {
                yPos = 0;
                this.setTranslateY(yPos);
            }
        }
        if (randomTala == 2) {
            xPos = xPos - snakeSize;
            this.setTranslateX(xPos);
            this.setRotate(270);
            if (xPos < 0) {
                xPos = 420 - snakeSize;
                this.setTranslateX(xPos);
            }
        }
        if (randomTala == 3) {
            xPos = xPos + snakeSize;
            this.setTranslateX(xPos);
            this.setRotate(90);
            if (xPos > 420 - snakeSize) {
                xPos = 0;
                this.setTranslateX(xPos);
            }
        }
    }

    /**
     * Aðferð sem tékkar hvort að örninn étur snákinn.
     * Tekur inn snak og tékkar hvort að snakur og örn eru á sama stað, ef svo er skilar true
     * @param snakur
     * @return boolean
     */
    public boolean erArekstur(Snakur snakur) {
        if (xPos - 210 == snakur.getxPos() && yPos -210 == snakur.getyPos()) {
            return true;
        }
        return false;
    }
}
