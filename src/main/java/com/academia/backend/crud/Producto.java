package com.academia.backend.crud;

public class Producto {

	private static int contadorDeProductos = 0;

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
		ganancia = precioVenta - precioCompra;
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
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
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

}
