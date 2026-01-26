package com.academia.backend.crud;

import java.util.LinkedList;

public class Producto {

	private static int contadorDeProductos = 0;
	private static LinkedList<Producto> productos = new LinkedList<>();

	private String id;
	private String nombre;
	private double contenido;
	private double precioCompra;
	private double precioVenta;
	private double ganancia;
	private int unidades;
	
	public Producto(String nombre, double contenido, double precioCompra, double precioVenta, int unidades) {
		contadorDeProductos++;
		id = Integer.toString(contadorDeProductos);
		this.nombre = nombre;
		this.contenido = contenido;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
		ganancia = calcularGananciaIndividual(precioVenta, precioCompra);
	}
	
	public Producto(double precioCompra, double precioVenta, int unidades) {
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
	}
	
	public Producto(int unidades) {
		this.unidades = unidades;
	}

	public String getId(){
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getContenido() {
		return contenido;
	}

	public void setContenido(double contenido) {
		this.contenido = contenido;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
		ganancia = calcularGananciaIndividual(precioVenta, precioCompra);
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
		ganancia = calcularGananciaIndividual(precioVenta, precioCompra);
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	@Override
	public String toString() {
		return String.format("| %-10s| %-20s| %-9s| $%-13.2f| $%-12.2f| $%-12.2f| %-8d|%n", id, nombre, contenido, precioCompra, precioVenta, ganancia, unidades);
	}
	
	public static double calcularGananciaIndividual(double precioVenta, double precioCompra) {
		if (precioVenta <= 0 || precioCompra <= 0 || precioVenta <= precioCompra) {
			throw new IllegalArgumentException("Los precios no pueden ser 0 o menores. La venta no puede ser menor o igual que la compra");
		}else {
			return precioVenta - precioCompra;
		}
	}
	
	public static double calcularGananciaLote(int unidades, double ganancia) {
		return unidades * ganancia;
	}
	
	public static int calcularCantidadTotalProductos(LinkedList<Producto> productos) {
		int totalProductos = 0;
		totalProductos = productos.stream().
				mapToInt(Producto::getUnidades).
				sum();
		return totalProductos;
	}
	
	/*
	public static double calcularInversionTotal(LinkedList<Producto> producto) {
		double inversionTotal = 0;
		inversionTotal = productos.stream().
				map(Producto::getUnidades*getGanancia).
				sum();
	}
	*/
	
	/*
	public static double calcularGananciaTotalEsperada(LinkedList<Producto> productos) {
		double gananciaTotalEs
	}
	*/

}
