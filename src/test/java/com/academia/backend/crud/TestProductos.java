package com.academia.backend.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.LinkedList;
import org.junit.jupiter.api.Test;

public class TestProductos {
	
	@Test
	void cantidadTotalDeTresProductosConDiezUnidadesEsTreinta() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		assertEquals(30, Producto.calcularCantidadTotalProductos(productos));
	}
	
	@Test
	void cantidadTotalDeTresProductosConMenosCincoUnidadesEsMenosQuince() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(-5));
		productos.add(new Producto(-5));
		productos.add(new Producto(-5));
		assertEquals(-15, Producto.calcularCantidadTotalProductos(productos));
	}
}
