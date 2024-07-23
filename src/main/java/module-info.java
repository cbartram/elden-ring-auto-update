module com.elden.ring.mod.manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires org.apache.logging.log4j;

    opens com.elden.ring.mod.manager to javafx.fxml;
    exports com.elden.ring.mod.manager;
    exports com.elden.ring.mod.manager.model;
    opens com.elden.ring.mod.manager.model to javafx.fxml;
}