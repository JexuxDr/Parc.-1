import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    String nombre;
    int cantidadInicial;
    int unidadesVendidas;

    Producto(String nombre, int cantidadInicial) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.unidadesVendidas = 0;
    }

    int calcularDisponibilidad() {
        return cantidadInicial - unidadesVendidas;
    }

    boolean verificarStock(int cantidadVenta) {
        return calcularDisponibilidad() >= cantidadVenta;
    }

    void duplicarInventario() {
        cantidadInicial *= 2;
    }

    void mostrarInformacion() {
        System.out.println("Producto: " + nombre);
        System.out.println("Cantidad inicial: " + cantidadInicial);
        System.out.println("Unidades vendidas: " + unidadesVendidas);
        System.out.println("Disponibilidad en inventario: " + calcularDisponibilidad());
    }
}

public class GestionInventario {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Registrar venta");
            System.out.println("3. Duplicar inventario de producto agotado");
            System.out.println("4. Mostrar información de inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la cantidad inicial: ");
                    int cantidadInicial = scanner.nextInt();
                    productos.add(new Producto(nombre, cantidadInicial));
                    System.out.println("Producto agregado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del producto vendido: ");
                    nombre = scanner.nextLine();
                    Producto productoEncontrado = null;
                    for (Producto producto : productos) {
                        if (producto.nombre.equals(nombre)) {
                            productoEncontrado = producto;
                            break;
                        }
                    }
                    if (productoEncontrado == null) {
                        System.out.println("Producto no encontrado.");
                    } else {
                        System.out.print("Ingrese la cantidad vendida: ");
                        int cantidadVendida = scanner.nextInt();
                        if (productoEncontrado.verificarStock(cantidadVendida)) {
                            productoEncontrado.unidadesVendidas += cantidadVendida;
                            System.out.println("Venta registrada exitosamente.");
                        } else {
                            System.out.println("Stock insuficiente para realizar la venta.");
                        }
                    }
                    break;
                case 3:
                    for (Producto producto : productos) {
                        if (producto.calcularDisponibilidad() == 0) {
                            producto.duplicarInventario();
                            System.out.println("Inventario duplicado para el producto: " + producto.nombre);
                        }
                    }
                    break;
                case 4:
                    for (Producto producto : productos) {
                        producto.mostrarInformacion();
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
