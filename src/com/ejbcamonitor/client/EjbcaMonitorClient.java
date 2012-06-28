/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbcamonitor.client;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Doped
 */
public class EjbcaMonitorClient extends Application {

    public static void main(String[] args) {
        Application.launch(EjbcaMonitorClient.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ejbca_monitor.fxml"), ResourceBundle.getBundle("com/ejbcamonitor/client/ejbca_monitor"));

        stage.setTitle("EJBCA Monitor Client - 0.0.1");
        stage.setWidth(680);
        stage.setHeight(700);
        //stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));

        stage.setScene(SceneBuilder.create().root(root).build());
        stage.show();
    }
}
