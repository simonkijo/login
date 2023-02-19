module com.simonkijo.login {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.simonkijo.login to javafx.fxml;
    exports com.simonkijo.login;
}