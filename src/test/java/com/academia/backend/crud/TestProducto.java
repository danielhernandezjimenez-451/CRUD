package com.academia.backend.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProducto {
	
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
	
	@Test
	void calcularInversionTotalTresProductosPrecioCompraLoteDiezEsTreinta() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(10.0, 5.0));
		productos.add(new Producto(10.0, 5.0));
		productos.add(new Producto(10.0, 5.0));
		assertEquals(30, Producto.calcularInversionTotal(Optional.of(productos)));
	}
	
	
	@Test
	void calcularInversionTotalSinProductosDaCero() {
		LinkedList <Producto> productos = new LinkedList<>();
		assertEquals(0, Producto.calcularInversionTotal(Optional.of(productos)));
	}
	
	@Test
	void calcularInversionTotalProductosNullDaCero() {
		LinkedList <Producto> productos = null;
		assertThrows(NullPointerException.class, () -> Producto.calcularInversionTotal(Optional.of(productos)));
	}
	
	@Test
	void calcularGananciaEsperadaTresProductosConGananciaLoteTreintaDaNoventa() {
		LinkedList <Producto> productos = new LinkedList<>();
		productos.add(new Producto(10.0, 30));
		productos.add(new Producto(10.0, 30));
		productos.add(new Producto(10.0, 30));
		assertEquals(90, Producto.calcularGananciaEsperada(Optional.of(productos)));
	}
	
	@Test
	void calcularGananciaSinProductosDaCero() {
		LinkedList <Producto> productos = new LinkedList<>();
		assertEquals(0, Producto.calcularGananciaEsperada(Optional.of(productos)));
	}
	
	@Test
	void calcularGananciaConProductosNullDaCero() {
		LinkedList <Producto> productos = null;
		assertEquals(0, Producto.calcularGananciaEsperada(Optional.ofNullable(productos)));
	}
	
	
}
