package hi.verkefni4.vidmot;

import hi.verkefni4.vinnsla.Leikur;
import hi.verkefni4.vinnsla.Position;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.Math.abs;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Viðmótsklasi sem er controller fyrir snake leikinn.
 * Hefur aðferðir til þess að stjórna tímalínu
 *********************************************************/
public class SnakurController implements Initializable {

    private boolean canTurnLeft = true;
    private boolean canTurnRight = true;
    private boolean canTurnUp = true;
    private boolean canTurnDown = true;
    private boolean canTurn = true;

    @FXML
    private SnakurBord snakurBord; // leikborð fyrir leikinn
    @FXML
    private Label fxStig; // label fyrir stig leiksins
    @FXML
    private Label fxPasa; // label sem segir hvort það sé pása eða ekki
    @FXML
    private Label fxHighScore;
    @FXML
    private AnchorPane main;
    private Timeline t;
    private int fjoldieitursnaka;
    private boolean veggirDrepa; // ef true þá er leik lokið ef snákur fer útaf leikborðinu
    private int erfidleikastig;
    private int gameticks;
    private int gamePauseCheck;
    private boolean start = true;
    private boolean keyPressed = false; // breyta sem passar að það sé ekki hægt að ýta á tvo örvatakka áður en snákur hreyfir sig
    public Leikur stig = new Leikur();

    /**
     * Aðferð sem setur stefnu snáks.
     * @param i
     */
    public void setStefna(int i) {
        if (i == 90 && canTurnUp && !keyPressed) {
            canTurnDown = false;
            snakurBord.snakur.setAtt(i);
            canTurnRight = true;
            canTurnLeft = true;
            startSnake();
        }
        if (i == 270 && canTurnDown && !keyPressed) {
            canTurnUp = false;
            snakurBord.snakur.setAtt(i);
            canTurnRight = true;
            canTurnLeft = true;
            startSnake();
        }
        if (i == 180 && canTurnLeft && !keyPressed) {
            canTurnRight = false;
            snakurBord.snakur.setAtt(i);
            canTurnUp = true;
            canTurnDown = true;
            startSnake();
        }
        if (i == 360 && canTurnRight && !keyPressed) {
            canTurnLeft = false;
            snakurBord.snakur.setAtt(i);
            canTurnUp = true;
            canTurnDown = true;
            startSnake();
        }
    }

    public void setKeyPressed(boolean b) {
        keyPressed = b;
    }

    public void setVeggirDrepa(boolean b) {
        veggirDrepa = b;
    }

    public boolean getVeggirDrepa() {
        return veggirDrepa;
    }

    public void setErfidleikastig(int i) {
        erfidleikastig = i;
    }

    /**
     * Aðferð sem lætur notanda vita hvernig hann byrjar leik í fyrsta skipti
     * sem leikurinn er ræstur og byrjar leikinn
     * @param e
     */
    public void pressStart(KeyEvent e) {
        if (e.getCode() == KeyCode.S && start) {
            fxPasa.setText("Ýttu á örvatakka til að fara áfram");
            nyrLeikur(fjoldieitursnaka);
            hefjaLeik(veggirDrepa);
            t.stop();
            snakurBord.snakur.setVeggirDrepa(veggirDrepa);
            start = false;
        }
    }

    public void setFjoldieitursnaka(int i) {
        fjoldieitursnaka = i;
    }

    /**
     * setur Bakgrunn á leikborðið.
     * Setur bind á stigin þannig að það breytist þegar snákurinn borðar
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxPasa.setText("Ýttu á s til þess að byrja");
        gameticks = 0;
        Stop[] stops = new Stop[] { new Stop(0, Color.YELLOWGREEN), new Stop(1, Color.GREENYELLOW)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);
        BackgroundFill background_fill2 = new BackgroundFill(lg1, CornerRadii.EMPTY, Insets.EMPTY);
        main.setBackground(new Background(background_fill2));
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        snakurBord.setBackground(background);
        fxStig.textProperty().
                bind(stig.getStig().asString());
    }

    /**
     * Aðferð sem geymir leikjalúppuna.
     * @param walls segir hvort veggir drepa eða ekki
     */
    public void hefjaLeik(boolean walls) {
        if (walls) {
            KeyFrame k = new KeyFrame(Duration.millis(150),
                    e-> {  // lambda fall sem er kallað á þegar ActionEvent atburðurinn er fíraður í lok
                        keyPressed = false;
                        snakurBord.snakur.positions.add(new Position(snakurBord.snakur.getX() + snakurBord.snakur.getxPos(), snakurBord.snakur.getY() + snakurBord.snakur.getyPos()));
                        snakurBord.snakur.rotations.add(snakurBord.snakur.getRotate());
                        snakurBord.snakur.afram();
                        snakurBord.snakur.moveSnakeTail();
                        snakurBord.borda();
                        gameticks++;
                        snakurBord.snakur.setGameTicks(gameticks);
                        if (snakurBord.snakur.erKlessaSjalfanSig() || snakurBord.snakur.erKlessaVegg() || snakurBord.aframEiturSnakar()) {
                            t.stop();
                            Platform.runLater(() -> {
                                try {
                                    synaDialog();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                        }
                    });
            t = new Timeline(k);           // tengjum timeline og tímabilið
            t.setCycleCount(Timeline.INDEFINITE);
            snakurBord.snakur.setAtt(270);
            t.play();
        }
        else {
            KeyFrame k = new KeyFrame(Duration.millis(150),
                    e-> {  // lambda fall sem er kallað á þegar ActionEvent atburðurinn er fíraður í lok
                        keyPressed = false;
                        snakurBord.snakur.positions.add(new Position(snakurBord.snakur.getX() + snakurBord.snakur.getxPos(), snakurBord.snakur.getY() + snakurBord.snakur.getyPos()));
                        snakurBord.snakur.rotations.add(snakurBord.snakur.getRotate());
                        snakurBord.snakur.afram();
                        snakurBord.snakur.moveSnakeTail();
                        snakurBord.borda();
                        gameticks++;
                        snakurBord.snakur.setGameTicks(gameticks);
                        if (snakurBord.snakur.erKlessaSjalfanSig() || snakurBord.aframEiturSnakar()) {
                            t.stop();
                            Platform.runLater(() -> {
                                try {
                                    synaDialog();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                        }
                    });
            t = new Timeline(k);           // tengjum timeline og tímabilið
            t.setCycleCount(Timeline.INDEFINITE);
            snakurBord.snakur.setAtt(270);
            t.play();
        }
    }

    /**
     * Aðferð sem setur fæðu og fjölda eitursnaka á borðið
     * @param fjoldiEiturSnaka int
     */
    public void nyrLeikur(int fjoldiEiturSnaka) {
        snakurBord.nyirEitursnakar(fjoldiEiturSnaka);
        snakurBord.byrjaLeik();
        snakurBord.eldamat();
    }


    /**
     * Aðferð sem er kallað í þegar smellt er á leikjaborðið.
     * Setur leikinn á pásu
     * @param event
     */
    public void setjaUppBid(KeyEvent event) {
        if (event.getCode().isWhitespaceKey()) {
            bida();
        }
    }

    /**
     * Aðferð sem setur leikinn á pásu
     */
    private void bida() {
        if (t.getStatus() == Animation.Status.RUNNING) {
            t.pause();
            fxPasa.setText("PÁSA");
        } else {
            t.play();
            fxPasa.setText("");
        }
    }

    /**
     * Aðferð sem sýnir dialog þeagar leikur er búinn.
     * Byrjar nýjan leik með því að kalla á föll sem upphafsstilla leikjaborðið og stigin.
     * Setur stigin fyrir leikinn á stigatöfluna
     */
    private void synaDialog() throws IOException {
        NyrLeikurDialog d = new NyrLeikurDialog();
        if (d.nyrLeikur()) {
            gameticks = 0;
            snakurBord.clearBord();
            snakurBord.snakur.rightSnakePosition();
            nyrLeikur(fjoldieitursnaka);
            if (Integer.parseInt(fxHighScore.getText()) < stig.getI()) {
                fxHighScore.setText(String.valueOf(stig.getI()));
            }
            stig.resetStig();
            fxPasa.setText("");
            snakurBord.snakur.setAtt(0);
            gamePauseCheck = snakurBord.snakur.getAtt();
            fxPasa.setText("ýttu á örvatakka til að fara áfram");
            canTurnUp = true;
            canTurnDown = true;
            canTurnLeft = true;
            canTurnRight = true;
        }
        else {
            if (stig.getI() > Integer.parseInt(fxHighScore.getText())) {
                printTofile(erfidleikastig, stig.getI());
                menu();
            } else {
                printTofile(erfidleikastig, Integer.parseInt(fxHighScore.getText()));
                menu();
            }
        }
    }

    /**
     * Aðferð sem er kallað í þegar leikmaður vill ekki spila aftur.
     * fer með notanda á valmynd
     * @throws IOException
     */
    public void menu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SnakurApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        MenuController mc =  fxmlLoader.getController();
        Stage stage = (Stage) fxHighScore.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aðferð sem athugar hvort að high score í leiknum sem var að klárast var meira eða minna high score í því erfiðleikastigi.
     * ef high score var hærra þá er það skrifað í skrá.
     *
     * @param erfidleikastig í hvaða erfiðleikastigi leikurinn var
     * @param thisHighScore high score í leiknum
     */
    private void printTofile(int erfidleikastig, int thisHighScore) {
        int currentHighScore = readFile(erfidleikastig);
        File file = new File("erfidleikastig"+erfidleikastig+".txt");
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            if (thisHighScore > currentHighScore) {
                pw.println(thisHighScore);
            } else {
                pw.println(currentHighScore);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hjálparfall sem að les high score úr skrá í viðeigandi erfiðleikastigi
     *
     * @param erfidleikastig
     * @return
     */
    private int readFile(int erfidleikastig) {
        File file = new File("erfidleikastig"+erfidleikastig+".txt");

        try {
            Scanner scan = new Scanner(file);
            int score = scan.nextInt();
            return score;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Aðferð sem tékkar hvort að leikurinn sé stopp eða ekki.
     * ef hann er stop þá er hann settur í gang aftur
     */
    private void startSnake() {
        if (t.getStatus() == Animation.Status.STOPPED
                && gamePauseCheck != snakurBord.snakur.getAtt()) {
            fxPasa.setText("");
            t.play();
        }
    }
}