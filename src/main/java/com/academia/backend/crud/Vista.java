package com.academia.backend.crud;

import java.util.HashSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;


public class Vista {

	public static Scanner scan = new Scanner(System.in);
	public static  HashSet <String> camposValidos = new HashSet<>(Arrays.asList("codigo", "nombre", "contenido", "precio compra", "precio venta", "ganancia", "unidades"));

	public static void mostrarMenuPrincipal() {
		limpiarPantalla();
		StringBuilder sb = new StringBuilder();
		sb.append("- - - - - - - - - MENU PRINCIPAL - - - - - - - - -\n");
		sb.append("Agregar productos\n");
		sb.append("Consultar productos\n");
		sb.append("Modificar productos\n");
		sb.append("Eliminar productos\n");
		sb.append("Buscar productos\n");
		sb.append("Pronostico economico\n");
		sb.append("Salir\n");
		sb.append("Escribe la accion que deseas realizar");
		System.out.println(sb);
	}

	public static void agregarProductos(){
		String eleccionUsuario;
		do{
			limpiarPantalla();
			System.out.println("Desea agregar mas productos? [s/n]");
			eleccionUsuario = obtenerValorTexto("Opcion");
			if (eleccionUsuario.equals("s"))
				Principal.productos.add(crearProducto());
		}while(!eleccionUsuario.equals("n"));
	}

	public static Producto crearProducto(){
		limpiarPantalla();
		String nombre = obtenerValorTexto("Nombre del producto");
		double contenido = obtenerValorDoble("Contenido del producto");
		double precioCompra = obtenerValorDoble("Precio de Compra del producto");
		double precioVenta = obtenerValorDoble("Precio de Venta del producto");
		int unidades = obtenerValorEntero("Unidades del producto");
		return new Producto(nombre, contenido, precioCompra, precioVenta, unidades);
	}

	public static void mostrarProductos() {
		//Debe recibir el ArrayList de productos
		limpiarPantalla();
		if (Principal.productos.size() == 0) {
			System.out.println("Aun no hay productos para mostrar");
		}else{
			String titulo = String.format("| %-10s| %-20s| %-5s| %-13s| %-13s| %-13s| %-7s|%n", "Codigo", "Nombre", "Contenido", "Precio Compra ", "Precio Venta", "Ganancia", "Unidades");
			System.out.print(titulo);
			System.out.println("-".repeat(102));
			StringBuilder sb = new StringBuilder();
			Principal.productos.stream().
				forEach(producto -> sb.append(producto.toString()));
			System.out.println(sb);
		}
		System.out.println("Presiona enter para volver al menu principal");
		scan.nextLine();
	}

	public static void modificarProductos(){
		if(Principal.productos.size() == 0) {
			System.out.println("Aun no hay productos para modificar, presiona enter para volver al menu principal");
			scan.nextLine();
		}else {
			String eleccionUsuario;
			String id;
			do{
				limpiarPantalla();
				System.out.println("Desea modificar productos? [s/n]");
				eleccionUsuario = obtenerValorTexto("Opcion");
				if (eleccionUsuario.equals("s")) {
					limpiarPantalla();
					id = obtenerValorNumericoEnTexto("Codigo del producto a modificar");
					boolean seModificoProducto = false;
					do{
						limpiarPantalla();
						if(Principal.buscarProducto(id)){
						String campoAModificar = obtenerValorTexto("Campo del producto a modificar");
						seModificoProducto = Principal.modificarProducto(campoAModificar);
						}
					}while(!seModificoProducto);
				}
			}while(!eleccionUsuario.equals("n"));
		}
	}

	public static void eliminarProductos(){
		if(Principal.productos.size() == 0) {
			System.out.println("Aun no hay productos para eliminar, presiona enter para volver al menu principal");
			scan.nextLine();
		}else {
			String eleccionUsuario;
			String eleccionUsuario2;
			String id;
			do{
				limpiarPantalla();
				System.out.println("Desea eliminar productos?");
				eleccionUsuario = obtenerValorTexto("[s/n]");
				if (eleccionUsuario.equals("s")) {
					id = obtenerValorNumericoEnTexto("Codigo del producto a eliminar");
					if (Principal.buscarProducto(id)) {
						System.out.println("Desea eliminar este producto?");
						eleccionUsuario2 = obtenerValorTexto("[s/n]");
						if (eleccionUsuario2.equals("s")) {
							Principal.eliminarProducto();
						}
					}
				}

			}while(!eleccionUsuario.equals("n"));
		}

	}

	public static void buscarProductos(){
		if(Principal.productos.size() == 0) {
			System.out.println("Aun no hay productos para buscar, presiona enter para volver al menu principal");
			scan.nextLine();
		}else {
			String eleccionUsuario;
			boolean campoValido = true;
			if(Principal.productos.size()==0){
				System.out.println("Aún no hay productos para buscar");
			}else{
				do{
					limpiarPantalla();
					System.out.println("Desea realizar una busqueda de productos?");
					eleccionUsuario = obtenerValorTexto("[s/n]");
					if(eleccionUsuario.equals("s")){
						do{
							limpiarPantalla();
							System.out.println("Ingrese el campo para filtrar los productos");
							String campo = obtenerValorTexto("la opcion [codigo] [nombre] [contenido] [precio compra] [precio venta] [ganancia] [unidades]");
							if(camposValidos.contains(campo)){
								buscarPorCampo(campo);
								campoValido = true;
							}else{
								campoValido = false;
							}
						}while(!campoValido);				
					}
				}while(!eleccionUsuario.equals("n"));
			}
		}
	}
	

	public static void buscarPorCampo(String campo){
		StringBuilder sb = new StringBuilder("");
		switch (campo) {
			case "codigo":
				String codigo = obtenerValorNumericoEnTexto("el codigo para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getId().equals(codigo)).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), codigo);
				break;

			case "nombre":
				String nombre = obtenerValorTexto("el nombre para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getNombre().equalsIgnoreCase(nombre)).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), nombre);
				break;

			case "contenido":
				double contenido = obtenerValorDoble("el contenido para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getContenido() == contenido).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), String.valueOf(contenido));
				break;
			
			case "precio compra":
				double precioCompra = obtenerValorDoble("el precio de compra para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getPrecioCompra() == precioCompra).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), String.valueOf(precioCompra));
				break;
			
			case "precio venta":
				double precioVenta = obtenerValorDoble("el precio de venta para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getPrecioVenta() == precioVenta).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(),String.valueOf(precioVenta));
				break;
				
			case "ganancia":
				double ganancia = obtenerValorDoble("la ganancia para filtrar productos");
				Principal.productos.stream().
					filter(producto -> producto.getGanancia() == ganancia).
					forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), String.valueOf(ganancia));
				break;

			case "unidades":
				int unidades = obtenerValorEntero("la cantidad de unidades para filtrar productos");
				Principal.productos.stream().
				filter(producto -> producto.getUnidades() == unidades).
				forEach(producto -> sb.append(producto.toString()));
				mostrarProductosFiltrados(campo, sb.toString(), String.valueOf(unidades));
				break;
		}
	}

	public static void mostrarProductosFiltrados(String campo, String productosFiltrados, String valor){
		limpiarPantalla();
		System.out.println("Mostrando productos por " + campo + " = " + valor);
		String titulo = String.format("| %-10s| %-20s| %-5s| %-13s| %-13s| %-13s| %-7s|%n", "Codigo", "Nombre", "Contenido", "Precio Compra ", "Precio Venta", "Ganancia", "Unidades");
		System.out.print(titulo);
		System.out.println("-".repeat(102));
		System.out.println(productosFiltrados);
		System.out.println("Presiona enter para continuar");
		scan.nextLine();
	}
	
	public static void mostrarPronosticoEconomico() {
		limpiarPantalla();
		System.out.println("$ $ $ $ $ $ $ $ $ PRONOSTICO ECONOMICO $ $ $ $ $ $ $ $ $");
		System.out.println("Productos totales en el inventario: " + Producto.calcularCantidadTotalInventario(Optional.of(Principal.productos)));
		System.out.println("Inversión total: $" + Producto.calcularInversionTotal(Optional.of(Principal.productos)));
		System.out.println("Ganancia esperada: $" + Producto.calcularGananciaEsperada(Optional.of(Principal.productos)));
		System.out.println("Presiona enter para continuar");
		scan.nextLine();
	}


	public static String obtenerValorNumericoEnTexto(String valorAObtener){
		String valorTexto;
		boolean continuar = true;
		do{
			System.out.println("Ingresa " + valorAObtener + ":");
			valorTexto = scan.nextLine().trim();
			try{
				Integer.parseInt(valorTexto);
				continuar = false;
			}catch(NumberFormatException nfe){
				System.out.println("El id ingresado debe ser un numero");
				continuar = true;
			}
		}while(valorTexto.isBlank() || valorTexto == null || continuar);
		return valorTexto;
	}

	public static String obtenerValorTexto(String valorAObtener){
		String valorTexto;
		do{
			System.out.println("Ingresa " + valorAObtener + ":");
			valorTexto = scan.nextLine().trim();
		}while(valorTexto.isBlank() || valorTexto == null);
		return valorTexto;
	}

	public static int obtenerValorEntero(String valorAObtener){
		int valorEntero = 0;
		boolean repetir = false;
		do{
			String valorTexto = obtenerValorTexto(valorAObtener);
			try{
				valorEntero = Integer.parseInt(valorTexto);
				if(valorEntero <= 0)
					throw new NumberFormatException();
				else
					repetir = false;
			}catch(NumberFormatException nfe){
				System.out.println(valorAObtener + " debe ser una cantidad mayor o igual a 1");
				repetir = true;
			}
		}while(repetir);
		return valorEntero;
	}

	public static double obtenerValorDoble(String valorAObtener){
		double valorDoble = 0;
		boolean repetir = false;
		do{
			String valorTexto = obtenerValorTexto(valorAObtener);
			try{
				valorDoble = Double.parseDouble(valorTexto);
				if(valorDoble <= 0)
					throw new NumberFormatException();
				else
					repetir = false;
			}catch(NumberFormatException nfe){
				System.out.println(valorAObtener + " debe ser una cantidad mayor que 0");
				repetir = true;
			}
		}while(repetir);
		return valorDoble;
	}

	public static void limpiarPantalla(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
