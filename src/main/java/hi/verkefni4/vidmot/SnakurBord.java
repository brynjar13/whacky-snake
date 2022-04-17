package hi.verkefni4.vidmot;

import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Random;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Sérhæfður klasi sem býr til Leikborðið fyrir snake leikinn.
 * hefur aðferðir til þess að birta og fjarlæga hluti á leikborðinu.
 *********************************************************/
public class SnakurBord extends Pane {

    private List<EiturSnakur> eiturSnakar = FXCollections.observableArrayList();
    public Snakur snakur;
    private Random random = new Random();
    private Faeda matur = new Faeda();
    private OfurFaeda ofurMatur = new OfurFaeda();
    private VondFaeda vondurMatur = new VondFaeda();
    private SnakurController snake;
    private int ofurRounds = 1;
    private int vondurRound = 1;

    /**
     * Aðferð til þess að setja snákinn á leikborðið
     *
     */
    public void byrjaLeik() {
        snake = ((SnakurController) getScene().getUserData());
        boolean b = snake.getVeggirDrepa();
        snakur = new Snakur();
        snakur.setVeggirDrepa(b);
        this.getChildren().add(snakur);
    }

    /**
     * Aðferð sem bætir við snák part á leikborðið
     */
    public void addTail() {
        Rectangle r = snakur.vaxa();
        this.getChildren().add(r);
    }

    /**
     * Aðferð til þess að bæta við eitursnákum á leikborðið.
     * @param i
     */
    public void nyirEitursnakar(int i) {
        for (int j = 0; j < i; j++) {
            eiturSnakar.add(new EiturSnakur());
        }
        for (int k = 0; k < eiturSnakar.size(); k++) {
            this.getChildren().add(eiturSnakar.get(k));
        }
    }

    /**
     * Aðferð til þess að eitursnákar hreyfist á borðinu.
     * Tékkar hvort snákur hefur rekist á eitursnák
     * @return
     */
    public boolean aframEiturSnakar() {
        for (int i = 0; i < eiturSnakar.size(); i++) {
            eiturSnakar.get(i).aframEiturSnakar(snakur.getAtt());
            if (eiturSnakar.get(i).erArekstur(snakur)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Aðferð sem setur mat á leikborðið
     */
    public void eldamat() {
        this.getChildren().add(matur);
    }

    /**
     * Aðferð sem setur mat sem er ekki venjulegur matur á leikborðið
     * tekur inn random tölu og ef talan er 2 þá er fæðunni bætt á leikborðið
     * @param i random tala
     * @param f matur
     */
    public void eldaOdruvisiMat(int i, Faeda f) {
        if (i == 2) {
            moveMatur(f);
            this.getChildren().add(f);
        }
    }

    /**
     * Aðferð sem fjarlægir fæðu sem er ekki venjuleg fæða af leikborðinu
     * @param f fæða
     */
    public void hendaOdruvisiMat(Faeda f) {
        this.getChildren().remove(f);
    }

    /**
     * Aðferð sem færir fæðu þegar hún er borðuð.
     * ef hún er í snák er hún færð aftur
     * @param f
     */
    public void moveMatur(Faeda f) {
        f.faeraFaedu();
        while (maturInSnake(f)) {
            f.faeraFaedu();
        }
    }

    /**
     * Hjálparfall sem tékkar hvort að fæðan sé inní snáknum eða ekki
     * tékkar hvort að staðsetningin á fæðunni sé sú sama og einhver staðsetning í snaka parta staðsetningunum,
     * ef svo er skilar true
     * @param f
     * @return
     */
    private boolean maturInSnake(Faeda f){
        int size = snakur.positions.size();
        if(size > 2){
            for (int i = size - snakur.snakeBody.size(); i < size; i++) {
                if(f.getPosition().getXPos() == (snakur.positions.get(i).getXPos())
                        && f.getPosition().getYPos() == (snakur.positions.get(i).getYPos())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Aðferð sem er tékkar hvort snákur borðar mat.
     * Lætur snák vaxa og hækkar stigin fyrir leikinn ef snákur borðar mat
     */
    public void borda() {
        snake = ((SnakurController) getScene().getUserData());
        if (snakur.erBorda(matur)) {
            ofurMaturController();
            vondurMaturController();
            addTail();
            moveMatur(matur);
            snake.stig.meiriStig(10);
        }
        if (this.getChildren().contains(ofurMatur)) {
            if (snakur.erBorda(ofurMatur)) {
                addTail();
                hendaOdruvisiMat(ofurMatur);
                moveMatur(ofurMatur);
                snake.stig.meiriStig(50);
            }
        }
        if (this.getChildren().contains(vondurMatur)) {
            if (snakur.erBorda(vondurMatur)) {
                addTail();
                hendaOdruvisiMat(vondurMatur);
                moveMatur(vondurMatur);
                snake.stig.meiriStig(-30);
            }
        }
    }

    /**
     * Aðferð sem að sér til þess að ofurMatur sé ekki endalaust á borðinu.
     * ef snákur borðar 2 epli á meðan ofurmatur er á borðinu er hann fjarlægður
     */
    private void ofurMaturController() {
        if (this.getChildren().contains(ofurMatur)) {
            ofurRounds++;
        }
        if (ofurRounds % 3 == 0) {
            ofurRounds = 1;
            hendaOdruvisiMat(ofurMatur);
            moveMatur(ofurMatur);
        } else if (!this.getChildren().contains(ofurMatur)) {
            int randomTala = random.nextInt(5);
            eldaOdruvisiMat(randomTala, ofurMatur);
        }
    }

    /**
     * Aðferð sem að sér til þess að vondur matur sé ekki endalaust á borðinu.
     * ef snákur borðar 2 epli á meðan vondur matur er á borðinu er hann fjarlægður
     */
    private void vondurMaturController() {
        if (this.getChildren().contains(vondurMatur)) {
            vondurRound++;
        }
        if (vondurRound % 3 == 0) {
            vondurRound = 1;
            hendaOdruvisiMat(vondurMatur);
            moveMatur(vondurMatur);
        } else if (!this.getChildren().contains(vondurMatur)) {
            int randomTala = random.nextInt(5);
            eldaOdruvisiMat(randomTala, vondurMatur);
        }
    }

    /**
     * Aðferð til þess að hreinsa leikborðið þegar nýr leikur er að byrja
     */
    public void clearBord() {
        snakur.reset();
        this.getChildren().clear();
        eiturSnakar.removeAll(eiturSnakar);
    }
}
