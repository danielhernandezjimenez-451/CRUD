package com.academia.backend.crud;

import java.util.LinkedList;
import java.util.Optional;
import java.awt.Dimension;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Principal {
	
	public static LinkedList<Producto> productos = new LinkedList<>();
	public static String seleccionUsuario = "";
	public static Scanner scan = new Scanner(System.in);
	public static Producto productoAModificar;
	public static Optional<Producto> productoAModificarOpt;
	public static Producto productoSeleccionado;
	
	public static void main(String[] args) {
		/*
		Producto p1 = new Producto("Coca Cola", 355, 8.5, 12, 10);
		Producto p2 = new Producto("Doritos", 56, 10.50, 18, 30);
		productos.add(p1);
		productos.add(p2);
		*/
			
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
		productoAModificarOpt = productos.stream().
				filter(producto -> producto.getId().equals(id)).
				findFirst();
		productoEncontrado = productoAModificarOpt.isPresent();
		if (productoEncontrado) {
			productoAModificar = productoAModificarOpt.get();
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
				productoAModificar.setNombre(Vista.obtenerValorTexto(" el nuevo nombre de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;
			
			case "contenido":
				productoAModificar.setContenido(Vista.obtenerValorDoble(" el nuevo contenido de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				break;

			case "precio compra":
				double precioCompraAnterior = productoAModificar.getPrecioCompra();
				try {
					productoAModificar.setPrecioCompra(Vista.obtenerValorDoble(" el nuevo precio de compra de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				}catch(IllegalArgumentException iae) {
					productoModificado = false;
					productoAModificar.setPrecioCompra(precioCompraAnterior);
					System.out.println("No se modifico el producto");
					System.out.println(iae.getMessage());
				}
				break;
			case "precio venta":
				double precioVentaAnterior = productoAModificar.getPrecioVenta();
				try {
					productoAModificar.setPrecioVenta(Vista.obtenerValorDoble(" el nuevo precio de venta de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
				}catch(IllegalArgumentException iae) {
					productoModificado = false;
					productoAModificar.setPrecioVenta(precioVentaAnterior);
					System.out.println("No se modifico el producto");
					System.out.println(iae.getMessage());
				}
				
				break;
			case "unidades":
				productoAModificar.setUnidades(Vista.obtenerValorEntero(" la nueva cantidad de unidades de " + productoAModificar.getNombre() + " de " + productoAModificar.getContenido()));
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

