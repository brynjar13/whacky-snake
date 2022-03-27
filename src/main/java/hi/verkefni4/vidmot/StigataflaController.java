package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StigataflaController implements Initializable {

    @FXML
    private Label fxScore1;
    @FXML
    private Label fxScore2;
    @FXML
    private Label fxScore3;
    @FXML
    private Label fxScore4;
    @FXML
    private Button fxMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            birtaStig(2);
            birtaStig(1);
            birtaStig(3);
            birtaStig(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void birtaStig(int erfidleikastig) throws IOException{
        File file = new File("erfidleikastig"+erfidleikastig+".txt");
        Scanner scan = new Scanner(file);
        int highScore = scan.nextInt();
        if (erfidleikastig == 1) {
            fxScore1.setText(String.valueOf(highScore));
        }
        if (erfidleikastig == 2) {
            fxScore2.setText(String.valueOf(highScore));
        }
        if (erfidleikastig == 3) {
            fxScore3.setText(String.valueOf(highScore));
        }
        if (erfidleikastig == 4) {
            fxScore4.setText(String.valueOf(highScore));
        }
    }
}
