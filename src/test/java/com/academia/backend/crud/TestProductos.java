package com.academia.backend.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProductos {
	
	@Test
	void obtenerGananciaIndividualPrecioVentaVeintePrecioCompraQuinceEsCinco() {
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
	void calcularGananciaLoteDiezUnidadesGananciaCincoDaCincuenta() {
		assertEquals(50, Producto.calcularGananciaLote(10, 5));
	}
	
	@Test
	void calcularGananciaLoteCeroUnidadesLanzaExcepcion(){
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularGananciaLote(0, 10);
		});
	}
	
	@Test
	void calcularGananciaLoteCeroGananciaLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularGananciaLote(10, 0);
		});
	}
	
	//costo lote
	@Test
	void calcularPrecioCompraLoteDiezUnidadesPrecioCompraCincoDaCincuenta() {
		assertEquals(50, Producto.calcularPrecioCompraLote(10, 5));
	}
	
	@Test
	void calcularPrecioCompraLoteCeroUnidadesLanzaExcepcion(){
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularPrecioCompraLote(0, 10);
		});
	}
	
	@Test
	void calcularPrecioCompraLotePrecioCompraCeroLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			Producto.calcularGananciaLote(10, 0);
		});
	}
	
	@Test
	void cantidadTotalDeTresProductosConDiezUnidadesEsTreinta() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		productos.add(new Producto(10));
		assertEquals(30, Producto.calcularCantidadTotalInventario(Optional.of(productos)));
	}
	
	@Test
	void cantidadTotalDeInventarioSinProductosDaCero() {
		LinkedList <Producto> productos = new LinkedList<>();
		assertEquals(0, Producto.calcularCantidadTotalInventario(Optional.of(productos)));
	}
	
	@Test
	void cantidadTotalDeInventarioProductosNullDaCero() {
		LinkedList <Producto> productos = null;
		assertThrows(NullPointerException.class, () -> Producto.calcularCantidadTotalInventario(Optional.of(productos)));
	}
	
	
	
	
}
