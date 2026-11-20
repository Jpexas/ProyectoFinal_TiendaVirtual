
package com.novashop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductController {

    private final ObservableList<Product> productos;
    private final AtomicInteger siguienteId;

    public ProductController() {
        productos = FXCollections.observableArrayList();
        siguienteId = new AtomicInteger(1);

        cargarProductosIniciales();
    }

    // PRODUCTOS DE EJEMPLO
    private void cargarProductosIniciales() {

        agregarProducto(
                "Audífonos Gaming",
                "Audífonos con sonido envolvente",
                159900,
                "Gaming",
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e"
        );

        agregarProducto(
                "Smartwatch Pro",
                "Reloj inteligente deportivo",
                219900,
                "Wearables",
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30"
        );

        agregarProducto(
                "Parlante Bluetooth",
                "Parlante portátil inalámbrico",
                129900,
                "Audio",
                "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1"
        );

        agregarProducto(
                "Cargador Rápido",
                "Cargador de carga rápida",
                59900,
                "Accesorios",
                "https://images.unsplash.com/photo-1583394838336-acd977736f90"
        );

        agregarProducto(
                "Power Bank",
                "Batería portátil recargable",
                89900,
                "Accesorios",
                "https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5"
        );

        agregarProducto(
                "Control Gamer",
                "Control inalámbrico para videojuegos",
                189900,
                "Gaming",
                "https://images.unsplash.com/photo-1592840496694-26d035b52b48"
        );
    }

    // REGISTRAR PRODUCTO
    public Product agregarProducto(
            String nombre,
            String descripcion,
            double precio,
            String categoria,
            String imagenUrl) {

        if (!datosValidos(nombre, descripcion, precio, categoria)) {
            throw new IllegalArgumentException(
                    "Verifica los datos del producto."
            );
        }

        Product producto = new Product(
                siguienteId.getAndIncrement(),
                nombre.trim(),
                descripcion.trim(),
                precio,
                categoria.trim(),
                imagenUrl == null ? "" : imagenUrl.trim()
        );

        productos.add(producto);

        return producto;
    }

    // CONSULTAR TODOS LOS PRODUCTOS
    public ObservableList<Product> obtenerProductos() {
        return FXCollections.unmodifiableObservableList(productos);
    }

    // BUSCAR PRODUCTO POR ID
    public Product buscarPorId(int id) {

        for (Product producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    // EDITAR PRODUCTO
    public boolean editarProducto(
            int id,
            String nombre,
            String descripcion,
            double precio,
            String categoria,
            String imagenUrl) {

        if (!datosValidos(nombre, descripcion, precio, categoria)) {
            throw new IllegalArgumentException(
                    "Verifica los datos del producto."
            );
        }

        Product producto = buscarPorId(id);

        if (producto == null) {
            return false;
        }

        producto.setNombre(nombre.trim());
        producto.setDescripcion(descripcion.trim());
        producto.setPrecio(precio);
        producto.setCategoria(categoria.trim());
        producto.setImagenUrl(
                imagenUrl == null ? "" : imagenUrl.trim()
        );

        return true;
    }

    // ELIMINAR PRODUCTO
    public boolean eliminarProducto(int id) {

        Product producto = buscarPorId(id);

        if (producto == null) {
            return false;
        }

        return productos.remove(producto);
    }

    // BUSCAR POR NOMBRE O DESCRIPCIÓN
    public ObservableList<Product> buscarProductos(String texto) {

        ObservableList<Product> resultados =
                FXCollections.observableArrayList();

        if (texto == null || texto.trim().isEmpty()) {
            resultados.addAll(productos);
            return resultados;
        }

        String busqueda = texto.trim().toLowerCase(Locale.ROOT);

        for (Product producto : productos) {

            if (producto.getNombre().toLowerCase(Locale.ROOT)
                    .contains(busqueda)
                    || producto.getDescripcion().toLowerCase(Locale.ROOT)
                    .contains(busqueda)
                    || producto.getCategoria().toLowerCase(Locale.ROOT)
                    .contains(busqueda)) {

                resultados.add(producto);
            }
        }

        return resultados;
    }

    // FILTRAR POR CATEGORÍA
    public ObservableList<Product> filtrarPorCategoria(
            String categoria) {

        ObservableList<Product> resultados =
                FXCollections.observableArrayList();

        if (categoria == null
                || categoria.equalsIgnoreCase("Todos")) {

            resultados.addAll(productos);
            return resultados;
        }

        for (Product producto : productos) {

            if (producto.getCategoria()
                    .equalsIgnoreCase(categoria.trim())) {

                resultados.add(producto);
            }
        }

        return resultados;
    }

    // CANTIDAD DE PRODUCTOS
    public int obtenerCantidadProductos() {
        return productos.size();
    }

    // VALIDAR INFORMACIÓN
    private boolean datosValidos(
            String nombre,
            String descripcion,
            double precio,
            String categoria) {

        return nombre != null
                && !nombre.trim().isEmpty()
                && descripcion != null
                && !descripcion.trim().isEmpty()
                && precio > 0
                && Double.isFinite(precio)
                && categoria != null
                && !categoria.trim().isEmpty();
    }

    // MODELO DEL PRODUCTO
    public static class Product {

        private final int id;
        private String nombre;
        private String descripcion;
        private double precio;
        private String categoria;
        private String imagenUrl;

        public Product(
                int id,
                String nombre,
                String descripcion,
                double precio,
                String categoria,
                String imagenUrl) {

            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.categoria = categoria;
            this.imagenUrl = imagenUrl;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getImagenUrl() {
            return imagenUrl;
        }

        public void setImagenUrl(String imagenUrl) {
            this.imagenUrl = imagenUrl;
        }
    }
}
