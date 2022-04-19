package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem er controller fyrir valmyndina fyrir leikinn.
 * Hefur aðferðir til þess að byrja leik í mismunandi erfiðleikastigum.
 * Hefur aðferðir til þess að sjá leiðbeiningar og stigatöflu fyrir leikinn.
 * *********************************************************/
public class MenuController implements Initializable {

    private static final int NIDUR=270;
    private static final int UPP=90;
    private static final int HAEGRI=360;
    private static final int VINSTRI=180;

    // Býr til beinan aðgang frá KeyCode og í heiltölu. Hægt að nota til að fletta upp
    // heiltölu fyrir KeyCode
    private final HashMap<KeyCode, Integer> map= new HashMap<KeyCode,Integer>();

    @FXML
    private Button fxButton1;
    @FXML
    private Button fxButton2;
    @FXML
    private Button fxButton3;
    @FXML
    private Button fxButton4;
    @FXML
    private AnchorPane fxBackground;
    @FXML
    private Label fxErfidleikastigLabel;

    /**
     * Aðferð sem byrjar snake leik í erfiðleikastigi 1
     * setur upplýsingar um fjölda eitursnáka og hvort veggir drepa eða ekki
     * @param e
     * @throws IOException
     */
    @FXML
    private void erfidleikastig1(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("SnakurAdal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        SnakurController sc =  fxmlLoader.getController();
        sc.setErfidleikastig(1);
        sc.setVeggirDrepa(false);
        sc.setFjoldieitursnaka(0);
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton1.getScene().getWindow();
        stage.setTitle("Snake");
        orvatakkar(sc, scene);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem byrjar snake leik í erfiðleikastigi 2
     * setur upplýsingar um fjölda eitursnáka og hvort veggir drepa eða ekki
     * @param e
     * @throws IOException
     */
    @FXML
    private void erfidleikastig2(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("SnakurAdal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        SnakurController sc =  fxmlLoader.getController();
        sc.setErfidleikastig(2);
        sc.setVeggirDrepa(true);
        sc.setFjoldieitursnaka(0);
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton2.getScene().getWindow();
        stage.setTitle("Snake");
        orvatakkar(sc, scene);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem byrjar snake leik í erfiðleikastigi 3
     * setur upplýsingar um fjölda eitursnáka og hvort veggir drepa eða ekki
     * @param e
     * @throws IOException
     */
    @FXML
    private void erfidleikastig3(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("SnakurAdal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        SnakurController sc =  fxmlLoader.getController();
        sc.setErfidleikastig(3);
        sc.setVeggirDrepa(false);
        sc.setFjoldieitursnaka(1);
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton3.getScene().getWindow();
        stage.setTitle("Snake");
        orvatakkar(sc, scene);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem byrjar snake leik í erfiðleikastigi 4
     * setur upplýsingar um fjölda eitursnáka og hvort veggir drepa eða ekki
     * @param e
     * @throws IOException
     */
    @FXML
    private void erfidleikastig4(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("SnakurAdal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        SnakurController sc =  fxmlLoader.getController();
        sc.setErfidleikastig(4);
        sc.setVeggirDrepa(true);
        sc.setFjoldieitursnaka(2);
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton4.getScene().getWindow();
        stage.setTitle("Snake");
        orvatakkar(sc, scene);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem keyrist í byrjun
     * setur bakgrunnsmynd á senuna
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(MenuController.class.getResourceAsStream("myndir/snakeBackground.png"));
        BackgroundImage backgroundImage= new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        fxBackground.setBackground(background);
        fxButton1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("Klassískur snákur, þú kemst í gegnum veggi");
            }
        });
        fxButton1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("");
            }
        });
        fxButton2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("Passaðu þig á veggjunum!");
            }
        });
        fxButton2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("");
            }
        });
        fxButton3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("Þú kemst í gegnum veggi, jei! en nú er fugl að reyna að éta þig :(");
            }
        });
        fxButton3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("");
            }
        });
        fxButton4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("Tveir fuglar! Eitraðir veggir! ef þú þorir...");
            }
        });
        fxButton4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fxErfidleikastigLabel.setText("");
            }
        });

    }

    /**
     * Aðferð sem að mappar örvatakka á lyklaborði yfir í heiltölur.
     * setur heiltölu í aðferð sem segir í hvaða átt snákurinn snýr.
     * @param sc
     * @param scene
     */
    private void orvatakkar(SnakurController sc, Scene scene) {
        map.put(KeyCode.UP, UPP);
        map.put(KeyCode.DOWN, NIDUR);
        map.put(KeyCode.LEFT, VINSTRI);
        map.put(KeyCode.RIGHT, HAEGRI);
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                keyEvent -> {
                    if (keyEvent.getCode().isArrowKey()) {
                        sc.setStefna(map.get(keyEvent.getCode()));
                        sc.setKeyPressed(true); // passar að taka ekki við of hröðum takkaslátt
                    } else if (keyEvent.getCode().isWhitespaceKey()) {
                        sc.setjaUppBid(keyEvent);
                    } else if (keyEvent.getCode() == KeyCode.S) {
                        sc.pressStart(keyEvent);
                    } else {
                        keyEvent.consume();
                    }
                });
    }

    /**
     * Aðferð sem fer með notanda á leiðbeininga scene ef ýtt er á "leiðbeiningar" takkan.
     * @param event
     * @throws IOException
     */
    public void sjaHelp(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("Help.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        HelpController hc =  fxmlLoader.getController();
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton4.getScene().getWindow();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem fer með notanda á stigatöflu scene-ið ef að ýtt er á "stigatafla" takkan.
     * @param event
     * @throws IOException
     */
    public void sjaStig(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("Stigatafla.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        StigataflaController stc = fxmlLoader.getController();
        scene.setUserData(fxmlLoader.getController());
        Stage stage = (Stage) fxButton4.getScene().getWindow();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
