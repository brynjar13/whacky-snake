package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Position;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem býr til býr til Snák
 * setur hæð, breidd og staðsetningu snáksins.
 * hefur aðferðir til þess að setja og fá átt á snák.
 * hefur aðferðir til að stækka snák, færa hann áfram og sjá hvort hann klessir á sjálfan sig
 *********************************************************/
public class Snakur extends Rectangle {

    public Image snakeHeadSprite = new Image(Snakur.class.getResourceAsStream("myndir/tile000.png"));
    public Image snakeBodySprite = new Image(Snakur.class.getResourceAsStream("myndir/tile008.png"));
    public int att;

    private final double snakeSize = 30;
    private double xPos;
    private double yPos;
    private int gameTicks; // hversu oft loopan er buinn að keyrast

    public final List<Position> positions = new ArrayList<>(); // Listi af öllum staðsetningum snáksins
    public final List<Double> rotations = new ArrayList<>(); // Listi af öllum snúningum snáksins
    public final ArrayList<Rectangle> snakeBody = new ArrayList<>(); // Listi af öllum snák pörtunum
    public boolean veggirDrepa;

    /**
     * Smiður fyrir Snákinn.
     * Setur hæð, breidd og staðsetningu fyrir snákinn.
     * setur rétta mynd á snákinn
     */
    public Snakur() {
        this.setHeight(snakeSize);
        this.setWidth(snakeSize);
        this.setFill(new ImagePattern(snakeHeadSprite));
        this.setX(210);
        this.setY(210);
        xPos = this.getLayoutX();
        yPos = this.getLayoutY();
        snakeBody.add(this);
    }

    /**
     * Aðferð sem tekur inn í hvaða átt snákurinn á að snúa og lætur snákinn snúa í þá átt
     * @param att
     */
    public void setAtt(int att) {
        this.att = att;
    }

    /**
     * aðferð til að sjá í hvaða átt snákurinn snýr
     * @return
     */
    public int getAtt() {
        return att;
    }

    /**
     * Aðferð sem hreyfir snákinn í rétta átt
     * Tékkar í hvaða átt snákurinn snýr og fer á áfram í þá átt
     */
    public void afram() {
        if (att == 90) {
            yPos = yPos - snakeSize;
            this.setTranslateY(yPos);
            this.setRotate(180);
            if (yPos < -210 && !veggirDrepa) {
                yPos = 210 - snakeSize;
                this.setTranslateY(yPos);
            }
        }
        if (att == 270) {
            yPos = yPos + snakeSize;
            this.setTranslateY(yPos);
            this.setRotate(360);
            if (yPos > 210 - snakeSize && !veggirDrepa) {
                yPos = -210;
                this.setTranslateY(yPos);
            }
        }
        if (att == 180) {
            xPos = xPos - snakeSize;
            this.setTranslateX(xPos);
            this.setRotate(90);
            if (xPos < -210 && !veggirDrepa) {
                xPos = 210 - snakeSize;
                this.setTranslateX(xPos);
            }
        }
        if (att == 360) {
            xPos = xPos + snakeSize;
            this.setTranslateX(xPos);
            this.setRotate(270);
            if (xPos > 210 - snakeSize && !veggirDrepa) {
                xPos = -210;
                this.setTranslateX(xPos);
            }
        }
    }

    /**
     * Aðferð til þess að sjá hvar snákurinn er á x-ásnum
     * @return double, x-position
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Aðferð til þess að sjá hvar snákurinn er á y-ásnum
     * @return double, y-position
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Aðferð til að setja snákinn á upphafsstað þegar leik er lokið
     */
    public void rightSnakePosition() {
        this.setX(210);
        this.setY(210);
        xPos = 210;
        yPos = 210;
    }

    /**
     * Aðferð sem tekur inn hvort veggir eiga að drepa eða ekki og setur rétt gildi á veggirDrepa breytuna
     * @param b
     */
    public void setVeggirDrepa(boolean b) {
        veggirDrepa = b;
    }

    /**
     * Aðferð sem lætur snákinn vaxa, kallað í þegar hann borðar.
     * Bætir við Rectangle á snákinn í hvert skipti þegar hann vex
     */
    public Rectangle vaxa() {
        Rectangle snakeTail;
        if (att == 360) {
            snakeTail = new Rectangle(
                    snakeBody.get(0).getX() + xPos - snakeSize,
                    snakeBody.get(0).getY() + yPos,
                    snakeSize,snakeSize);
            snakeTail.setFill(new ImagePattern(snakeBodySprite));
            snakeBody.add(snakeTail);
            return snakeTail;
        } else if (att == 180) {
            snakeTail = new Rectangle(
                    snakeBody.get(0).getX() + xPos + snakeSize,
                    snakeBody.get(0).getY() + yPos,
                    snakeSize,snakeSize);
            snakeTail.setFill(new ImagePattern(snakeBodySprite));
            snakeBody.add(snakeTail);
            return snakeTail;
        } else if (att == 270) {
            snakeTail = new Rectangle(
                    snakeBody.get(0).getX() + xPos,
                    snakeBody.get(0).getY() + yPos - snakeSize,
                    snakeSize,snakeSize);
            snakeTail.setFill(new ImagePattern(snakeBodySprite));
            snakeBody.add(snakeTail);
            return snakeTail;
        }
        else if (att == 90) {
            snakeTail = new Rectangle(
                    snakeBody.get(0).getX() + xPos,
                    snakeBody.get(0).getY() + yPos + snakeSize,
                    snakeSize,snakeSize);
            snakeTail.setFill(new ImagePattern(snakeBodySprite));
            snakeBody.add(snakeTail);
            return snakeTail;
        }
        return null;
    }

    /**
     * Aðferð sem tekur inn heiltölu g og setur það gildi á gameticks breytunna
     * @param g
     */
    public void setGameTicks(int g) {
        gameTicks = g;
    }

    /**
     * Aðferð til þess að færa búkinn á snáknum áfram.
     * finnur staðsetningu snáksins og færir hvern búk hluta miðað við þar sem snákurinn var.
     * @param skott
     * @param numer
     */
    private void snakeTailAfram(Rectangle skott, int numer) {
        double yPos = positions.get(gameTicks - numer + 1).getYPos() - skott.getY();
        double xPos = positions.get(gameTicks - numer + 1).getXPos() - skott.getX();
        double rotation = rotations.get(gameTicks - numer + 1);
        skott.setRotate(rotation);
        skott.setTranslateX(xPos);
        skott.setTranslateY(yPos);
    }

    /**
     * Fer í gegnum búkinn á snáknum og kallar í SnakeTailAfram() fyrir hvern.
     */
    public void moveSnakeTail() {
        for (int i = 1; i < snakeBody.size(); i++) {
            snakeTailAfram(snakeBody.get(i), i);
        }
    }

    /**
     * Aðferð til að sjá hvort snákur hefur klesst á sjálfan sig
     * skoðar staðsetninguna á snáknum og fer í gegnum allar staðsetningarnar á búknum.
     * ef staðsetningin á snáknum er sú sama og einhver í búknum þá skilar true.
     * @return boolean
     */
    public boolean erKlessaSjalfanSig() {
        int size = positions.size() - 1;
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(positions.get(size).getXPos() == (positions.get(i).getXPos())
                        && positions.get(size).getYPos() == (positions.get(i).getYPos())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Aðferð sem athugar hvort snákur hefur klesst á vegg.
     * finnur staðsetninguna á snáknum og ef hún er ekki inná leikborðinu er skilað true.
     * @return
     */
    public boolean erKlessaVegg() {
        if (xPos < -210 || xPos > 210 -snakeSize || yPos < -210 || yPos > 210 -snakeSize) {
            return true;
        }
        return false;
    }

    /**
     * Aðferð til að sjá hvort snákur hefur borðað mat
     * finnur staðsetningu á matnum og snáknum og ef hún er sú sama er skilað true
     * @param f faeda
     * @return boolean
     */
    public boolean erBorda(Faeda f) {
        if(xPos + this.getX() == f.getPosition().getXPos() && yPos + this.getY() == f.getPosition().getYPos()){
            return true;
        }
        else return false;
    }

    /**
     * Aðferð sem kallað er í þegar leikur byrjar upp á nýtt.
     * tæmir snák búkinn og allar staðsetningarnar
     * bætir svo við hausnum aftur
     */
    public void reset() {
        gameTicks=0;
        snakeBody.clear();
        positions.clear();
        rotations.clear();
        snakeBody.add(this);
    }
}
