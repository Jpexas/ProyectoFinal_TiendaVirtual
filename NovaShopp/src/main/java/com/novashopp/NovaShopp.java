
package com.novashop;

import com.novashop.views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NovaShopp extends Application {

    @Override
    public void start(Stage stage) {

        LoginView login = new LoginView();

        Scene scene = new Scene(login, 1100, 720);

        stage.setTitle("NovaShop - Iniciar sesión");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(650);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
