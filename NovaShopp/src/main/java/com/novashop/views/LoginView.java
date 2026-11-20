
package com.novashop.views;

import com.novashop.controllers.ProductController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginView extends BorderPane {

    private final TextField correo;
    private final PasswordField contrasena;
    private final Label mensaje;

    // CONTROLADOR COMPARTIDO
    private final ProductController controlador;

    public LoginView() {

        controlador = new ProductController();

        setStyle("-fx-background-color: #F3F4FA;");

        VBox panelIzquierdo = new VBox(22);
        panelIzquierdo.setAlignment(Pos.CENTER);
        panelIzquierdo.setPadding(new Insets(45));
        panelIzquierdo.setPrefWidth(460);

        panelIzquierdo.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #101B3D, #30206D);"
        );

        Label logo = new Label("N");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 55));
        logo.setTextFill(Color.WHITE);
        logo.setAlignment(Pos.CENTER);
        logo.setMinSize(100, 100);

        logo.setStyle(
            "-fx-background-color: #7655E8;" +
            "-fx-background-radius: 25;"
        );

        Label nombre = new Label("NovaShop");
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        nombre.setTextFill(Color.WHITE);

        Label descripcion = new Label("TECNOLOGÍA PARA TU VIDA");
        descripcion.setFont(Font.font("Arial", 14));
        descripcion.setTextFill(Color.web("#C8C5E8"));

        Label frase = new Label(
            "Descubre un mundo de tecnología.\n" +
            "Encuentra lo que necesitas en un solo lugar."
        );

        frase.setFont(Font.font("Arial", 16));
        frase.setTextFill(Color.WHITE);
        frase.setWrapText(true);

        frase.setTextAlignment(
            javafx.scene.text.TextAlignment.CENTER
        );

        panelIzquierdo.getChildren().addAll(
            logo, nombre, descripcion, frase
        );

        VBox formulario = new VBox(17);
        formulario.setAlignment(Pos.CENTER_LEFT);
        formulario.setPadding(new Insets(45));
        formulario.setMaxWidth(430);

        formulario.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 20;"
        );

        Label titulo = new Label("Bienvenido");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titulo.setTextFill(Color.web("#17244B"));

        Label subtitulo = new Label(
            "Inicia sesión para continuar"
        );

        subtitulo.setFont(Font.font("Arial", 14));
        subtitulo.setTextFill(Color.web("#777E91"));

        Label etiquetaCorreo = new Label("Correo electrónico");
        etiquetaCorreo.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        correo = new TextField();
        correo.setPromptText("ejemplo@correo.com");
        correo.setPrefHeight(45);
        correo.setStyle(estiloCampo());

        Label etiquetaContrasena = new Label("Contraseña");
        etiquetaContrasena.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        contrasena = new PasswordField();
        contrasena.setPromptText("Ingresa tu contraseña");
        contrasena.setPrefHeight(45);
        contrasena.setStyle(estiloCampo());

        mensaje = new Label("");
        mensaje.setWrapText(true);
        mensaje.setTextFill(Color.web("#D93636"));

        Button ingresar = new Button("INICIAR SESIÓN");
        ingresar.setMaxWidth(Double.MAX_VALUE);
        ingresar.setPrefHeight(48);
        ingresar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ingresar.setTextFill(Color.WHITE);

        ingresar.setStyle(
            "-fx-background-color: #6045D8;" +
            "-fx-background-radius: 9;" +
            "-fx-cursor: hand;"
        );

        ingresar.setOnAction(event -> validarAcceso());
        contrasena.setOnAction(event -> validarAcceso());

        Label pie = new Label(
            "© 2026 NovaShop | Tienda de tecnología"
        );

        pie.setFont(Font.font("Arial", 11));
        pie.setTextFill(Color.web("#9298A8"));

        formulario.getChildren().addAll(
            titulo,
            subtitulo,
            etiquetaCorreo,
            correo,
            etiquetaContrasena,
            contrasena,
            mensaje,
            ingresar,
            pie
        );

        HBox contenido = new HBox();
        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(35));
        contenido.setSpacing(35);

        contenido.getChildren().addAll(
            panelIzquierdo,
            formulario
        );

        setCenter(contenido);
    }

    private String estiloCampo() {

        return "-fx-background-color: #F5F6FA;" +
               "-fx-border-color: #DDE0EB;" +
               "-fx-border-radius: 8;" +
               "-fx-background-radius: 8;" +
               "-fx-padding: 10;";
    }

    // VALIDAR ACCESO
    private void validarAcceso() {

        String email = correo.getText().trim();
        String password = contrasena.getText();

        if (email.isEmpty() || password.isEmpty()) {

            mensaje.setTextFill(Color.web("#D93636"));
            mensaje.setText(
                "Por favor, completa todos los campos."
            );

            return;
        }

        // ACCESO ADMINISTRADOR
        if (email.equalsIgnoreCase("admin@novashop.com")
                && password.equals("Admin123")) {

            abrirAdministrador();

        // ACCESO CLIENTE
        } else if (email.equalsIgnoreCase("cliente@novashop.com")
                && password.equals("Cliente123")) {

            abrirCatalogo();

        } else {

            mensaje.setTextFill(Color.web("#D93636"));
            mensaje.setText(
                "Correo o contraseña incorrectos."
            );
        }
    }

    // ABRIR PANEL ADMINISTRATIVO
    private void abrirAdministrador() {

        Stage ventana = (Stage) getScene().getWindow();

        AdminView administrador = new AdminView(controlador);

        Scene escena = new Scene(administrador, 1150, 800);

        ventana.setTitle("NovaShop - Panel de Administración");
        ventana.setScene(escena);
        ventana.setMinWidth(950);
        ventana.setMinHeight(650);
        ventana.centerOnScreen();
        ventana.show();
    }

    // ABRIR CATÁLOGO DEL CLIENTE
    private void abrirCatalogo() {

        Stage ventana = (Stage) getScene().getWindow();

        CatalogView catalogo = new CatalogView();

        Scene escena = new Scene(catalogo, 1100, 750);

        ventana.setTitle("NovaShop - Catálogo");
        ventana.setScene(escena);
        ventana.centerOnScreen();
        ventana.show();
    }
}
