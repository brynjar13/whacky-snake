package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem er controller fyrir leiðbeiningar um leikinn.
 * Hefur aðferðir til þess að fara aftur á valmynd
 *********************************************************/
public class HelpController {
    @FXML
    private Button fxMenu; // takki til að fara aftur í valmynd


    /**
     * Aðferð sem fer með notandann á valmynd ef ýtt er á takka
     * @param event
     * @throws IOException
     */
    public void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        MenuController mc =  fxmlLoader.getController();
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxMenu.getScene().getWindow();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
