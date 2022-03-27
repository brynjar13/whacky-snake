package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    @FXML
    private Button fxMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


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
