package com.academia.backend.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProductos {
	
	@Test
	void gananciaIndividualPrecioVentaVeintePrecioCompraQuinceEsCinco() {
		assertEquals(5, Producto.calcularGananciaIndividual(20, 15));
	}
	
	@Test
	void obtenerGananciaIndividualPrecioVentaMenorIgualACeroDaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
	        Producto.calcularGananciaIndividual(-3, 5);
	    });
	}
	
	@Test
	void obtenerGananciaIndividualPrecioCompraMenorIgualACeroDaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularGananciaIndividual(5, -3);
		});
	}
	
	@Test
	void obtenerGananciaIndividualPrecioVentaMenorIgualPrecioCompraDaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularGananciaIndividual(2, 3);
		});
	}
	
	@Test
	void cantidadTotalDeTresProductosConDiezUnidadesEsTreinta() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		assertEquals(30, Producto.calcularCantidadTotalProductos(productos));
	}
	
	@Test
	@DisplayName ("Cantidad total de 3 productos con -5 unidades -> -15")
	void cantidadTotalDeTresProductosConMenosCincoUnidadesEsMenosQuince() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(-5));
		productos.add(new Producto(-5));
		productos.add(new Producto(-5));
		assertEquals(-15, Producto.calcularCantidadTotalProductos(productos));
	}
	
	
	
	
}
