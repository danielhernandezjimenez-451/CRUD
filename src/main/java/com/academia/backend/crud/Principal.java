package com.academia.backend.crud;

import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
	
	public static LinkedList<Producto> productos = new LinkedList<>();
	public static String seleccionUsuario = "";
	public static Scanner scan = new Scanner(System.in);
	public static Producto productoAModificar;
	public static Producto productoSeleccionado;
	
	public static void main(String[] args) {
		Producto p1 = new Producto("Coca Cola", 355, 8.5, 12, 10);
		Producto p2 = new Producto("Doritos", 56, 10.50, 18, 30);
		productos.add(p1);
		productos.add(p2);
			
		do {
			Vista.mostrarMenuPrincipal();
			seleccionUsuario = scan.nextLine().toLowerCase().trim();
			switch (seleccionUsuario) {
				case "agregar":
					Vista.agregarProductos();
					break;

				case "consultar":
					Vista.mostrarProductos();
					break;

				case "modificar":
					Vista.modificarProductos();
					break;

				case "eliminar":
					Vista.eliminarProductos();
					break;
				
				case "buscar":
					Vista.buscarProductos();
					break;
					
				case "pronostico":
					Vista.mostrarPronosticoEconomico();
					break;

				case "salir":
					System.out.println("Hasta luego");
					break;
				default:
					System.out.println("Ingresa una opción válida");
			}
			Vista.limpiarPantalla();
		}while(seleccionUsuario.isBlank() || seleccionUsuario == null || !seleccionUsuario.equalsIgnoreCase("salir"));
	}

	public static boolean buscarProducto(String id){
		boolean productoEncontrado = false;
		for(Producto producto : productos){
			if(producto.getId().equals(id)){
				productoAModificar = producto;
				productoEncontrado = true;
				break;
			}
		}
		if (productoEncontrado) {
			System.out.println("Producto encontrado: " + productoAModificar.getId() + " " + productoAModificar.getNombre() + " " + productoAModificar.getContenido());
		}else{
			System.out.println("No se encontro un producto con id " + id);
		}
		return productoEncontrado;
	}

	public static boolean modificarProducto(String campoAModificar){
		boolean productoModificado = true;
		switch (campoAModificar) {
			case "nombre":
				productoAModificar.setNombre(Vista.obtenerValorTexto("Ingrese el nuevo nombre de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;
			
			case "contenido":
				productoAModificar.setContenido(Vista.obtenerValorDoble("Ingrese el nuevo contenido de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;

			case "precio compra":
				productoAModificar.setPrecioCompra(Vista.obtenerValorDoble("Ingrese el nuevo precio de compra de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;
			case "precio venta":
				productoAModificar.setPrecioVenta(Vista.obtenerValorDoble("Ingrese el nuevo precio de venta de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;
			case "unidades":
				productoAModificar.setUnidades(Vista.obtenerValorEntero("Ingrese la nueva cantidad de unidades de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;
			default:
				System.out.println("Ingresa un campo valido");
				productoModificado = false;
				break;
			
		}
		if (productoModificado) {
			System.out.println("Producto modificado exitosamente");
		}
		System.out.println("Presione enter para continuar");
		scan.nextLine();
		return productoModificado;
	}

	public static void eliminarProducto(){
		productos.remove(productoAModificar);
		System.out.println("Producto eliminado exitosamente, presione enter para continuar");
		scan.nextLine();
	}
	
}

