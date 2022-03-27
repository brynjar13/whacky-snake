package hi.verkefni4.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Vinnsluklasi sem heldur utan um Stig hvers leiks.
 * hefur lista af stigum hvers leiks
 *********************************************************/
public class Leikur {

    public List<Integer> listiAfStigum = FXCollections.observableArrayList();
    private SimpleIntegerProperty stig;
    private int points = 0;

    /**
     * Smiður fyrir leik sem setur stig fyrir leikinn sem SimpleIntegerProperty til að hægt sá að nota bind á það
     */
    public Leikur() {
        stig = new SimpleIntegerProperty(points);
    }

    /**
     * Aðferð sem hækkar stigin, kallað í þegar snákur borðar
     */
    public void meiriStig(int i) {
        points += i;
        stig.setValue(points);
    }

    /**
     * Aðferð til þess að fá stiginn fyrir leikinn
     * @return stig
     */
    public SimpleIntegerProperty getStig() {
        return stig;
    }

    /**
     * Aðferð til að fá stigin í fyrir leikinn á heiltölu formi
     * @return stig(int)
     */
    public int getI() {
        return points;
    }

    /**
     * Aðferð til þess að núllstilla stig þegar nýr leikur er að hefjast
     */
    public void resetStig() {
        listiAfStigum.add(points);
        points = 0;
        stig.setValue(0);
    }
}
