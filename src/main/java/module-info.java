module com.elden.ring.mod.eldenringautoupdate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens com.elden.ring.mod.eldenringautoupdate to javafx.fxml;
    exports com.elden.ring.mod.eldenringautoupdate;
}