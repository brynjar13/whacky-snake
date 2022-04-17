package hi.verkefni4.vidmot;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Optional;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem býr til dialog sem spyr leikmann hvort hann vilji halda áfram leik.
 * les inn dialog.fxml sem er útlitið fyrir dialoginn
 *********************************************************/
public class NyrLeikurDialog extends DialogPane {

    /**
     * smiður fyrir dialoginn.
     * les inn dialog.fxml sem er útlit fyrir dialoginn.
     * setur rót og controller. loadar fxml skránna
     */
    public NyrLeikurDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Aðferð sem tékkar hvort leikmaður vill spila annan leik
     * ef vill annan leik, skilar true
     * @return Boolean
     */
    public boolean nyrLeikur() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(this);
        Optional<ButtonType> utkoma = dialog.showAndWait();

        if (utkoma.isPresent() && utkoma.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}
