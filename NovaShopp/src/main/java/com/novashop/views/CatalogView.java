
package com.novashop.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CatalogView extends BorderPane {

    private final String[][] productos = {
        {
            "Audífonos Gaming",
            "Audio inalámbrico de alta calidad",
            "89900", "Audio",
            "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=700&q=85"
        },
        {
            "Smartwatch Pro",
            "Tecnología inteligente para tu día",
            "159900", "Wearables",
            "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=700&q=85"
        },
        {
            "Parlante Bluetooth",
            "Sonido portátil y potente",
            "119900", "Audio",
            "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?auto=format&fit=crop&w=700&q=85"
        },
        {
            "Cargador Rápido",
            "Carga eficiente para tus dispositivos",
            "49900", "Accesorios",
            "https://images.unsplash.com/photo-1583394838336-acd977736f90?auto=format&fit=crop&w=700&q=85"
        },
        {
            "Power Bank",
            "Energía portátil donde quieras",
            "79900", "Accesorios",
            "https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?auto=format&fit=crop&w=700&q=85"
        },
        {
            "Control Gamer",
            "Precisión para tus partidas",
            "129900", "Gaming",
            "https://images.unsplash.com/photo-1592840496694-26d035b52b48?auto=format&fit=crop&w=700&q=85"
        }
    };

    private final GridPane rejilla = new GridPane();
    private final Label contadorCarrito = new Label("🛒 0");
    private final Label mensaje = new Label();
    private final TextField buscador = new TextField();

    private int cantidadCarrito = 0;
    private String categoriaActual = "Todos";

    public CatalogView() {

        setStyle("-fx-background-color: #090D19;");

        crearEncabezado();
        crearContenido();
        mostrarProductos();
    }

    private void crearEncabezado() {

        HBox encabezado = new HBox(20);
        encabezado.setAlignment(Pos.CENTER_LEFT);
        encabezado.setPadding(new Insets(18, 35, 18, 35));

        encabezado.setStyle(
            "-fx-background-color: #10172B;" +
            "-fx-border-color: transparent transparent #34265C transparent;"
        );

        Label logo = new Label("N");
        logo.setMinSize(45, 45);
        logo.setAlignment(Pos.CENTER);
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        logo.setTextFill(Color.WHITE);

        logo.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #A855F7, #4263EB);" +
            "-fx-background-radius: 12;"
        );

        Label nombre = new Label("NovaShop");
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        nombre.setTextFill(Color.WHITE);

        HBox marca = new HBox(12, logo, nombre);
        marca.setAlignment(Pos.CENTER_LEFT);

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        contadorCarrito.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        contadorCarrito.setTextFill(Color.web("#C4B5FD"));
        contadorCarrito.setPadding(new Insets(12, 17, 12, 17));

        contadorCarrito.setStyle(
            "-fx-background-color: #211C3C;" +
            "-fx-background-radius: 10;"
        );

        Button salir = new Button("Cerrar sesión");

        salir.setTextFill(Color.WHITE);
        salir.setStyle(
            "-fx-background-color: #252D43;" +
            "-fx-background-radius: 9;" +
            "-fx-padding: 12 17;" +
            "-fx-cursor: hand;"
        );

        salir.setOnAction(e -> {

            Stage ventana = (Stage) getScene().getWindow();

            ventana.setScene(
                new Scene(new LoginView(), 1100, 750)
            );

            ventana.setTitle("NovaShop - Iniciar sesión");
            ventana.centerOnScreen();
        });

        encabezado.getChildren().addAll(
            marca, espacio, contadorCarrito, salir
        );

        setTop(encabezado);
    }

    private void crearContenido() {

        VBox contenido = new VBox(23);
        contenido.setPadding(new Insets(35, 45, 40, 45));

        Label etiqueta = new Label("NOVA TECHNOLOGY STORE");
        etiqueta.setTextFill(Color.web("#A78BFA"));
        etiqueta.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label titulo = new Label("ELEVA TU EXPERIENCIA");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        Label descripcion = new Label(
            "Descubre tecnología diseñada para superar tus expectativas."
        );

        descripcion.setTextFill(Color.web("#AAB4CC"));
        descripcion.setFont(Font.font("Arial", 14));

        VBox textosBanner = new VBox(12,
            etiqueta, titulo, descripcion
        );

        Label iconoBanner = new Label("NOVA // TECH");
        iconoBanner.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        iconoBanner.setTextFill(Color.web("#C4B5FD"));

        Region espacioBanner = new Region();
        HBox.setHgrow(espacioBanner, Priority.ALWAYS);

        HBox banner = new HBox(
            textosBanner, espacioBanner, iconoBanner
        );

        banner.setAlignment(Pos.CENTER_LEFT);
        banner.setPadding(new Insets(32));

        banner.setStyle(
            "-fx-background-color: linear-gradient(to right, #211747, #12254C);" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #49367F;" +
            "-fx-border-radius: 18;"
        );

        Label tituloProductos = new Label("EXPLORA NUESTRO CATÁLOGO");
        tituloProductos.setTextFill(Color.WHITE);
        tituloProductos.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        buscador.setPromptText("Buscar productos...");
        buscador.setPrefHeight(45);
        buscador.setMaxWidth(420);

        buscador.setStyle(
            "-fx-background-color: #171F34;" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: #8490AA;" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: #394665;" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 12;"
        );

        buscador.textProperty().addListener(
            (obs, anterior, nuevo) -> mostrarProductos()
        );

        HBox barra = new HBox(buscador);
        barra.setAlignment(Pos.CENTER_LEFT);

        HBox filtros = new HBox(10);
        filtros.setAlignment(Pos.CENTER_LEFT);

        String[] categorias = {
            "Todos", "Gaming", "Audio",
            "Wearables", "Accesorios"
        };

        for (String categoria : categorias) {

            Button boton = new Button(categoria);

            boton.setOnAction(e -> {
                categoriaActual = categoria;
                actualizarFiltros(filtros);
                mostrarProductos();
            });

            filtros.getChildren().add(boton);
        }

        actualizarFiltros(filtros);

        rejilla.setHgap(22);
        rejilla.setVgap(22);

        mensaje.setTextFill(Color.web("#A78BFA"));
        mensaje.setFont(Font.font("Arial", 13));

        contenido.getChildren().addAll(
            banner,
            tituloProductos,
            barra,
            filtros,
            mensaje,
            rejilla
        );

        ScrollPane scroll = new ScrollPane(contenido);
        scroll.setFitToWidth(true);
        scroll.setStyle(
            "-fx-background: #090D19;" +
            "-fx-background-color: #090D19;"
        );

        setCenter(scroll);
    }

    private void actualizarFiltros(HBox filtros) {

        for (javafx.scene.Node nodo : filtros.getChildren()) {

            Button boton = (Button) nodo;

            boolean seleccionado =
                boton.getText().equals(categoriaActual);

            boton.setTextFill(Color.WHITE);

            boton.setStyle(
                seleccionado
                ? "-fx-background-color: #7548E8;" +
                  "-fx-background-radius: 9;" +
                  "-fx-padding: 11 18;" +
                  "-fx-font-weight: bold;" +
                  "-fx-cursor: hand;"
                : "-fx-background-color: #171F34;" +
                  "-fx-background-radius: 9;" +
                  "-fx-padding: 11 18;" +
                  "-fx-text-fill: #B7C0D8;" +
                  "-fx-cursor: hand;"
            );
        }
    }

    private void mostrarProductos() {

        rejilla.getChildren().clear();

        String busqueda = buscador.getText()
            .trim().toLowerCase();

        int columna = 0;
        int fila = 0;
        int encontrados = 0;

        for (String[] producto : productos) {

            boolean coincideNombre =
                producto[0].toLowerCase().contains(busqueda);

            boolean coincideCategoria =
                categoriaActual.equals("Todos") ||
                producto[3].equals(categoriaActual);

            if (!coincideNombre || !coincideCategoria) {
                continue;
            }

            VBox tarjeta = crearTarjeta(producto);

            rejilla.add(tarjeta, columna, fila);

            columna++;

            if (columna == 3) {
                columna = 0;
                fila++;
            }

            encontrados++;
        }

        if (encontrados == 0) {

            Label vacio = new Label(
                "No encontramos productos."
            );

            vacio.setTextFill(Color.web("#AAB4CC"));
            vacio.setFont(Font.font("Arial", 15));

            rejilla.add(vacio, 0, 0);
        }
    }

    private VBox crearTarjeta(String[] producto) {

        VBox tarjeta = new VBox(12);

        tarjeta.setPadding(new Insets(16));
        tarjeta.setAlignment(Pos.CENTER_LEFT);
        tarjeta.setPrefWidth(275);
        tarjeta.setMinHeight(345);
        tarjeta.setMaxWidth(Double.MAX_VALUE);

        tarjeta.setStyle(
            "-fx-background-color: #141D32;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #303D5B;" +
            "-fx-border-radius: 16;"
        );

        // CONTENEDOR DE IMAGEN

        StackPane marcoImagen = new StackPane();

        marcoImagen.setMinHeight(165);
        marcoImagen.setPrefHeight(165);
        marcoImagen.setMaxWidth(Double.MAX_VALUE);

        marcoImagen.setStyle(
            "-fx-background-color: #202A46;" +
            "-fx-background-radius: 12;"
        );

        Label cargando = new Label("NOVA // PRODUCT");
        cargando.setTextFill(Color.web("#A78BFA"));
        cargando.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Image imagen = new Image(
            producto[4],
            500,
            300,
            true,
            true,
            true
        );

        ImageView fotografia = new ImageView(imagen);

        fotografia.setFitWidth(240);
        fotografia.setFitHeight(165);
        fotografia.setPreserveRatio(true);
        fotografia.setSmooth(true);

        imagen.errorProperty().addListener((obs, anterior, error) -> {
            if (error) {
                cargando.setText("IMAGEN NO DISPONIBLE");
                fotografia.setVisible(false);
            }
        });

        marcoImagen.getChildren().addAll(cargando, fotografia);

        imagen.progressProperty().addListener((obs, anterior, progreso) -> {
            if (progreso.doubleValue() >= 1 && !imagen.isError()) {
                cargando.setVisible(false);
            }
        });

        // INFORMACIÓN DEL PRODUCTO

        Label categoria = new Label(
            producto[3].toUpperCase()
        );

        categoria.setTextFill(Color.web("#A78BFA"));
        categoria.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        Label nombre = new Label(producto[0]);
        nombre.setTextFill(Color.WHITE);
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label descripcion = new Label(producto[1]);
        descripcion.setTextFill(Color.web("#AAB4CC"));
        descripcion.setFont(Font.font("Arial", 12));
        descripcion.setWrapText(true);

        int valor = Integer.parseInt(producto[2]);

        Label precio = new Label(
            "$" + String.format("%,d", valor) + " COP"
        );

        precio.setTextFill(Color.web("#B69CFF"));
        precio.setFont(Font.font("Arial", FontWeight.BOLD, 19));

        Button agregar = new Button("＋  AGREGAR AL CARRITO");

        agregar.setMaxWidth(Double.MAX_VALUE);
        agregar.setPrefHeight(42);
        agregar.setTextFill(Color.WHITE);

        agregar.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        agregar.setStyle(
            "-fx-background-color: linear-gradient(to right, #7548E8, #4263EB);" +
            "-fx-background-radius: 9;" +
            "-fx-cursor: hand;"
        );

        agregar.setOnAction(e -> {

            cantidadCarrito++;

            contadorCarrito.setText(
                "🛒 " + cantidadCarrito
            );

            mensaje.setText(
                producto[0] + " agregado al carrito."
            );
        });

        tarjeta.getChildren().addAll(
            marcoImagen,
            categoria,
            nombre,
            descripcion,
            precio,
            agregar
        );

        tarjeta.setOnMouseEntered(e ->
            tarjeta.setStyle(
                "-fx-background-color: #1C2945;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #9565FF;" +
                "-fx-border-radius: 16;"
            )
        );

        tarjeta.setOnMouseExited(e ->
            tarjeta.setStyle(
                "-fx-background-color: #141D32;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #303D5B;" +
                "-fx-border-radius: 16;"
            )
        );

        return tarjeta;
    }
}
