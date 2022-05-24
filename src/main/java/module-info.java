module pl.pm.raportgenerator2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pl.pm.raportgenerator to javafx.fxml;
    exports pl.pm.raportgenerator;
}