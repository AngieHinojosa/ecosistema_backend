package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.*;
import com.miprimerspring.nuestroecosistema.repository.*;
import com.miprimerspring.nuestroecosistema.mapper.CarritoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarritoServiceImplTest {

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CarritoMapper carritoMapper;

    @Mock
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PuntosFidelizacionRepository puntosFidelizacionRepository;

    @Test
    public void testObtenerCarritoPorId() {
        Carrito carrito = new Carrito();
        carrito.setCarritoId(1L);
        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));

        CarritoDTO result = carritoService.obtenerCarritoPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCarritoId());
    }

    @Test
    public void testCalcularTotalCarrito() {
        Carrito carrito = new Carrito();
        Producto producto = new Producto();
        producto.setProductoPrecio(100.0);
        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setProducto(producto);
        carritoProducto.setCantidad(2);
        carrito.getProductos().add(carritoProducto);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));

        Double total = carritoService.calcularTotalCarrito(1L);

        assertEquals(200.0, total, 0.01);  // El total debería ser 200.0
    }

    @Test
    void testPagarCarritoConSaldoSuficiente() {
        // Crear mocks
        Usuario usuarioMock = mock(Usuario.class);
        Direccion direccionMock = mock(Direccion.class);
        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(direccionMock);  // Asegúrate de agregar al menos una dirección

        // Configura el mock del repositorio de direcciones
        when(direccionRepository.findByUsuario_UsuarioId(anyInt())).thenReturn(direcciones);  // Mock que devuelve las direcciones

        // Crear un carrito con productos
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuarioMock);  // Asocia el usuario al carrito

        Producto producto = new Producto();
        producto.setProductoPrecio(100.0);  // Precio del producto
        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setProducto(producto);
        carritoProducto.setCantidad(1);  // Cantidad de productos
        carrito.getProductos().add(carritoProducto);  // Agrega el producto al carrito

        // Configura los mocks del repositorio
        when(carritoRepository.findById(anyLong())).thenReturn(Optional.of(carrito));

        // Configurar otros mocks necesarios
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setCuentaSaldo(BigDecimal.valueOf(500.0));

        // Usa el valor adecuado según el test
        when(cuentaBancariaRepository.findByUsuarioId(anyLong())).thenReturn(Collections.singletonList(cuenta));

        // Llamar al método que debe ser probado
        boolean result = carritoService.pagarCarrito(1L, "BANCOSIMPLE");

        // Verificar que el resultado es correcto
        assertTrue(result);
        assertEquals(BigDecimal.valueOf(400.0), cuenta.getCuentaSaldo());  // El saldo debería ser descontado
    }

    @Test
    public void testPagarCarritoConSaldoInsuficiente() {
        // Crear mocks
        Usuario usuarioMock = mock(Usuario.class);
        Direccion direccionMock = mock(Direccion.class);
        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(direccionMock);

        when(direccionRepository.findByUsuario_UsuarioId(anyInt())).thenReturn(direcciones);

        // Crear carrito con productos
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuarioMock);

        Producto producto = new Producto();
        producto.setProductoPrecio(100.0); // El carrito cuesta 100
        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setProducto(producto);
        carritoProducto.setCantidad(1);
        carrito.getProductos().add(carritoProducto);

        when(carritoRepository.findById(anyLong())).thenReturn(Optional.of(carrito));

        // Configurar cuenta con saldo insuficiente
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setCuentaSaldo(BigDecimal.valueOf(50.0)); // Solo tiene 50

        when(cuentaBancariaRepository.findByUsuarioId(anyLong())).thenReturn(Collections.singletonList(cuenta));

        // Verificar que se lanza una excepción al pagar
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carritoService.pagarCarrito(1L, "BANCOSIMPLE");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Saldo insuficiente en la cuenta bancaria.", exception.getMessage());
    }




}