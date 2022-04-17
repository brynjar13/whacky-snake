package hi.verkefni4.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**********************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: stillir hversu stórt scene er. Loadar menu.fxml skrá sem er valmynd forritsins.
 * Keyrir forritið.
 **********************************************/
public class SnakurApplication extends Application {

    @Override

    /**
     * Stillir hversu stórt scene er. Loadar menu.fxml skrá sem er valmynd forritsins.
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