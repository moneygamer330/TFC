module tfc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    exports controller;
    opens controller to javafx.fxml;
    exports main;
    exports model;
    opens model to javafx.fxml;
    opens main to javafx.fxml;
}