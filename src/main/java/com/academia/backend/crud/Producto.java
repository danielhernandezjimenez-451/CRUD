package com.academia.backend.crud;

import java.util.LinkedList;
import java.util.Optional;

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
	private double precioCompraLote;
	private double gananciaLote;
	
	public Producto() {
		
	}
	
	public Producto(String nombre, double contenido, double precioCompra, double precioVenta, int unidades) {
		contadorDeProductos++;
		id = Integer.toString(contadorDeProductos);
		this.nombre = nombre;
		this.contenido = contenido;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
		ganancia = calcularGananciaIndividual(precioVenta, precioCompra);
		precioCompraLote = calcularPrecioCompraLote(unidades, precioCompra);
		gananciaLote = calcularGananciaLote(unidades, ganancia);
	}
	
	public Producto(double precioCompra, double precioVenta, int unidades) {
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
	}
	
	public Producto(int unidades) {
		this.unidades = unidades;
	}
	
	public Producto(double precioCompraLote, double gananciaLote){
		this.precioCompraLote = precioCompraLote;
		this.gananciaLote = gananciaLote;
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
		precioCompraLote = calcularPrecioCompraLote(unidades, precioCompra);
		gananciaLote = calcularGananciaLote(unidades, ganancia);
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
		ganancia = calcularGananciaIndividual(precioVenta, precioCompra);
		gananciaLote = calcularGananciaLote(unidades, ganancia);
	}
	
	public double getGanancia() {
		return ganancia;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
		precioCompraLote = calcularPrecioCompraLote(unidades, precioCompra);
		gananciaLote = calcularGananciaLote(unidades, ganancia);
	}
	
	public double getPrecioCompraLote() {
		return precioCompraLote;
	}
	
	public double getGananciaLote() {
		return gananciaLote;
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
		if (unidades <= 0 || ganancia <= 0) {
			throw new IllegalArgumentException("La ganancia o las unidades no pueden ser 0");
		}else {
			return unidades * ganancia;
		}
	}
	
	public static double calcularPrecioCompraLote(int unidades, double precioCompra) {
		if (unidades <= 0 || precioCompra <= 0) {
			throw new IllegalArgumentException("El precio de compra o las unidades no pueden ser 0");
		}else {
			return unidades * precioCompra;
		}
	}
	
	public static int calcularCantidadTotalInventario(Optional <LinkedList<Producto>> productosOpt) {
		LinkedList<Producto> productos = productosOpt.orElse(new LinkedList<>());
		int totalProductos = productos.stream().
				mapToInt(producto -> producto.getUnidades()).
				sum();
		return totalProductos;
	}
	
	public static double calcularInversionTotal(Optional<LinkedList<Producto>> productosOpt) {
		LinkedList<Producto> productos = productosOpt.orElse(new LinkedList<>());
		double inversionTotal = productos.stream().
				mapToDouble(producto -> producto.getPrecioCompraLote()). //Esto es lo mismo que la lambda explícita
				sum();
		return inversionTotal;
	}
	
	public static double calcularGananciaEsperada(Optional<LinkedList<Producto>> productosOpt) {
		LinkedList<Producto> productos = productosOpt.orElse(new LinkedList<>());
		double gananciaEsperada = productos.stream().
				mapToDouble(producto -> producto.getGananciaLote()).
				sum();
		return gananciaEsperada;
	}

}
