package hi.verkefni4.vidmot;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**********************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: stillir hversu stórt scene er. Loadar .fxml skrá sem er notuð fyrir útlit.
 * Hefur aðferð til þess að mappa orvatakka í heiltölur
 * Keyrir forritið.
 *
 **********************************************/
public class SnakurApplication extends Application {

    @Override

    /**
     * Stillir hversu stórt scene er. Loadar SnakurAdal-view.fxml skrá sem er notuð fyrir útlit.
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        MenuController mc =  fxmlLoader.getController();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem keyrir forritið
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}