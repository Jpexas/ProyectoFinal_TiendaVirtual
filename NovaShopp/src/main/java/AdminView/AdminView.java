
package com.novashop.views;

import com.novashop.controllers.ProductController;
import com.novashop.controllers.ProductController.Product;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.NumberFormat;
import java.util.Locale;

public class AdminView extends BorderPane {

    private final ProductController controlador;

    private TableView<Product> tablaProductos;

    private TextField campoNombre;
    private TextField campoDescripcion;
    private TextField campoPrecio;
    private TextField campoImagen;

    private ComboBox<String> campoCategoria;

    private Label mensaje;
    private Label totalProductos;

    private final String fondo = "#0B1120";
    private final String panel = "#141D32";
    private final String morado = "#8B5CF6";
    private final String blanco = "#FFFFFF";

    public AdminView(ProductController controlador) {

        this.controlador = controlador;

        setStyle("-fx-background-color: " + fondo + ";");

        crearInterfaz();
        actualizarTabla();
    }

    // CONSTRUIR INTERFAZ
    private void crearInterfaz() {

        setTop(crearEncabezado());

        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(25));

        contenido.getChildren().addAll(
                crearResumen(),
                crearFormulario(),
                crearTabla()
        );

        setCenter(contenido);
    }

    // ENCABEZADO
    private HBox crearEncabezado() {

        Label titulo = new Label("NOVASHOP");
        titulo.setTextFill(Color.web(blanco));
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Label subtitulo = new Label("PANEL DE ADMINISTRACIÓN");
        subtitulo.setTextFill(Color.web("#A78BFA"));
        subtitulo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        VBox textos = new VBox(5, titulo, subtitulo);

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        Label administrador = new Label("ADMINISTRADOR");
        administrador.setTextFill(Color.web("#C4B5FD"));
        administrador.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        HBox encabezado = new HBox(
                15,
                textos,
                espacio,
                administrador
        );

        encabezado.setAlignment(Pos.CENTER_LEFT);
        encabezado.setPadding(new Insets(22, 30, 22, 30));

        encabezado.setStyle(
                "-fx-background-color: #141D32;" +
                "-fx-border-color: #303D5B;" +
                "-fx-border-width: 0 0 1 0;"
        );

        return encabezado;
    }

    // RESUMEN
    private VBox crearResumen() {

        Label titulo = new Label("Panel principal");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        totalProductos = new Label();
        totalProductos.setTextFill(Color.web("#C4B5FD"));
        totalProductos.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Label descripcion = new Label("Productos registrados");
        descripcion.setTextFill(Color.web("#AAB4CC"));

        VBox tarjeta = new VBox(8, totalProductos, descripcion);

        tarjeta.setPadding(new Insets(20));
        tarjeta.setMaxWidth(280);

        tarjeta.setStyle(
                "-fx-background-color: #1C2945;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #7048D8;" +
                "-fx-border-radius: 14;"
        );

        VBox resumen = new VBox(15, titulo, tarjeta);

        return resumen;
    }

    // FORMULARIO DE PRODUCTOS
    private VBox crearFormulario() {

        Label titulo = new Label("Gestión de productos");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        campoNombre = crearCampo("Nombre del producto");
        campoDescripcion = crearCampo("Descripción");
        campoPrecio = crearCampo("Precio en COP");
        campoImagen = crearCampo("URL de la imagen");

        campoCategoria = new ComboBox<>();

        campoCategoria.getItems().addAll(
                "Gaming",
                "Audio",
                "Wearables",
                "Accesorios"
        );

        campoCategoria.setPromptText("Seleccionar categoría");
        campoCategoria.setMaxWidth(Double.MAX_VALUE);

        campoCategoria.setStyle(
                "-fx-background-color: #263653;" +
                "-fx-text-fill: white;"
        );

        Button agregar = crearBoton("AGREGAR PRODUCTO", morado);
        Button editar = crearBoton("ACTUALIZAR", "#2563EB");
        Button eliminar = crearBoton("ELIMINAR", "#DC3545");
        Button limpiar = crearBoton("LIMPIAR", "#475569");

        agregar.setOnAction(e -> agregarProducto());
        editar.setOnAction(e -> editarProducto());
        eliminar.setOnAction(e -> eliminarProducto());
        limpiar.setOnAction(e -> limpiarFormulario());

        HBox botones = new HBox(10, agregar, editar, eliminar, limpiar);
botones.setAlignment(Pos.CENTER_LEFT);
botones.setSpacing(10);

        mensaje = new Label("Completa los datos para registrar un producto.");
        mensaje.setTextFill(Color.web("#A78BFA"));

        GridPane campos = new GridPane();

        campos.setHgap(12);
        campos.setVgap(12);

        campos.add(campoNombre, 0, 0);
        campos.add(campoDescripcion, 1, 0);
        campos.add(campoPrecio, 0, 1);
        campos.add(campoCategoria, 1, 1);
        campos.add(campoImagen, 0, 2, 2, 1);

        ColumnConstraints columna1 = new ColumnConstraints();
        columna1.setPercentWidth(50);

        ColumnConstraints columna2 = new ColumnConstraints();
        columna2.setPercentWidth(50);

        campos.getColumnConstraints().addAll(columna1, columna2);

        VBox formulario = new VBox(
                15,
                titulo,
                campos,
                botones,
                mensaje
        );

        formulario.setPadding(new Insets(20));

        formulario.setStyle(
                "-fx-background-color: #141D32;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #303D5B;" +
                "-fx-border-radius: 14;"
        );

        return formulario;
    }

    // TABLA
    private VBox crearTabla() {

        Label titulo = new Label("Inventario de NovaShop");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        tablaProductos = new TableView<>();

        tablaProductos.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        tablaProductos.setPrefHeight(300);

        TableColumn<Product, Number> columnaId =
                new TableColumn<>("ID");

        columnaId.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleIntegerProperty(
                        dato.getValue().getId()
                )
        );

        TableColumn<Product, String> columnaNombre =
                new TableColumn<>("Producto");

        columnaNombre.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleStringProperty(
                        dato.getValue().getNombre()
                )
        );

        TableColumn<Product, String> columnaCategoria =
                new TableColumn<>("Categoría");

        columnaCategoria.setCellValueFactory(
                dato -> new javafx.beans.property.SimpleStringProperty(
                        dato.getValue().getCategoria()
                )
        );

        TableColumn<Product, String> columnaPrecio =
                new TableColumn<>("Precio");

        columnaPrecio.setCellValueFactory(dato -> {

            NumberFormat formato = NumberFormat.getCurrencyInstance(
                    new Locale("es", "CO")
            );

            String precio = formato.format(
                    dato.getValue().getPrecio()
            );

            return new javafx.beans.property.SimpleStringProperty(precio);
        });

        tablaProductos.getColumns().addAll(
                columnaId,
                columnaNombre,
                columnaCategoria,
                columnaPrecio
        );

        tablaProductos.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    if (seleccionado != null) {
                        mostrarProducto(seleccionado);
                    }
                });

        tablaProductos.setStyle(
                "-fx-background-color: #141D32;" +
                "-fx-control-inner-background: #141D32;" +
                "-fx-text-background-color: white;"
        );

        VBox contenedor = new VBox(15, titulo, tablaProductos);

        return contenedor;
    }

    // CREAR CAMPO
    private TextField crearCampo(String texto) {

        TextField campo = new TextField();

        campo.setPromptText(texto);
        campo.setPrefHeight(40);
        campo.setMaxWidth(Double.MAX_VALUE);

        campo.setStyle(
                "-fx-background-color: #263653;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #AAB4CC;" +
                "-fx-background-radius: 8;"
        );

        return campo;
    }

    // CREAR BOTÓN
    private Button crearBoton(String texto, String color) {

        Button boton = new Button(texto);

        boton.setTextFill(Color.WHITE);
        boton.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        boton.setPadding(new Insets(12, 14, 12, 14));

        boton.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        return boton;
    }

    // AGREGAR PRODUCTO
    private void agregarProducto() {

        try {

            String nombre = campoNombre.getText();
            String descripcion = campoDescripcion.getText();
            double precio = Double.parseDouble(
                    campoPrecio.getText().trim()
            );

            String categoria = campoCategoria.getValue();
            String imagen = campoImagen.getText();

            controlador.agregarProducto(
                    nombre,
                    descripcion,
                    precio,
                    categoria,
                    imagen
            );

            actualizarTabla();
            limpiarFormulario();

            mensaje.setText("Producto registrado correctamente.");

        } catch (NumberFormatException ex) {

            mensaje.setText("El precio debe ser un número válido.");

        } catch (IllegalArgumentException ex) {

            mensaje.setText(ex.getMessage());
        }
    }

    // EDITAR PRODUCTO
    private void editarProducto() {

        Product seleccionado = tablaProductos.getSelectionModel()
                .getSelectedItem();

        if (seleccionado == null) {
            mensaje.setText("Selecciona un producto de la tabla.");
            return;
        }

        try {

            double precio = Double.parseDouble(
                    campoPrecio.getText().trim()
            );

            boolean actualizado = controlador.editarProducto(
                    seleccionado.getId(),
                    campoNombre.getText(),
                    campoDescripcion.getText(),
                    precio,
                    campoCategoria.getValue(),
                    campoImagen.getText()
            );

            if (actualizado) {

                actualizarTabla();
                limpiarFormulario();

                mensaje.setText("Producto actualizado correctamente.");
            }

        } catch (NumberFormatException ex) {

            mensaje.setText("El precio debe ser un número válido.");

        } catch (IllegalArgumentException ex) {

            mensaje.setText(ex.getMessage());
        }
    }

    // ELIMINAR PRODUCTO
    private void eliminarProducto() {

        Product seleccionado = tablaProductos.getSelectionModel()
                .getSelectedItem();

        if (seleccionado == null) {
            mensaje.setText("Selecciona un producto para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Eliminar producto");
        confirmacion.setHeaderText("¿Eliminar este producto?");
        confirmacion.setContentText(seleccionado.getNombre());

        confirmacion.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.OK) {

                controlador.eliminarProducto(seleccionado.getId());

                actualizarTabla();
                limpiarFormulario();

                mensaje.setText("Producto eliminado correctamente.");
            }
        });
    }

    // MOSTRAR DATOS EN EL FORMULARIO
    private void mostrarProducto(Product producto) {

        campoNombre.setText(producto.getNombre());
        campoDescripcion.setText(producto.getDescripcion());

        campoPrecio.setText(
                String.valueOf(producto.getPrecio())
        );

        campoCategoria.setValue(producto.getCategoria());

        campoImagen.setText(producto.getImagenUrl());

        mensaje.setText("Producto seleccionado: " + producto.getNombre());
    }

    // LIMPIAR FORMULARIO
    private void limpiarFormulario() {

        campoNombre.clear();
        campoDescripcion.clear();
        campoPrecio.clear();
        campoImagen.clear();

        campoCategoria.setValue(null);

        tablaProductos.getSelectionModel().clearSelection();
    }

    // ACTUALIZAR TABLA
    private void actualizarTabla() {

        ObservableList<Product> productos =
                controlador.obtenerProductos();

        tablaProductos.setItems(productos);

        totalProductos.setText(
                String.valueOf(controlador.obtenerCantidadProductos())
        );
    }
}
