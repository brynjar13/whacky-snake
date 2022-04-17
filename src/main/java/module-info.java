module hi.verkefni3.vidmot.verkefni1 {
    requires javafx.controls;
    requires javafx.fxml;

    exports hi.verkefni4.vidmot;
    opens hi.verkefni4.vidmot to javafx.fxml;
}