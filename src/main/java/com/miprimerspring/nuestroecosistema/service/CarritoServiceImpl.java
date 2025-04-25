package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.dto.CarritoProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.CarritoMapper;
import com.miprimerspring.nuestroecosistema.mapper.CarritoProductoMapper;
import com.miprimerspring.nuestroecosistema.model.*;
import com.miprimerspring.nuestroecosistema.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoMapper carritoMapper;

    @Autowired
    private CarritoProductoMapper carritoProductoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PuntosFidelizacionRepository puntosFidelizacionRepository;
    @Override
    public CarritoDTO crearCarrito(CarritoDTO carritoDTO) {
        Long usuarioId = carritoDTO.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setCarritoAgregadoEn(LocalDateTime.now());

        // Agregar productos al carrito
        for (CarritoProductoDTO productoDTO : carritoDTO.getProductos()) {
            Producto producto = productoRepository.findById(productoDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto con ID " + productoDTO.getProductoId() + " no encontrado"));

            CarritoProducto carritoProducto = new CarritoProducto(
                    null,
                    carrito,
                    producto,
                    productoDTO.getCantidad(),
                    LocalDateTime.now()
            );

            carrito.getProductos().add(carritoProducto);
        }

        Carrito carritoGuardado = carritoRepository.save(carrito);
        return carritoMapper.toDTO(carritoGuardado);
    }

    @Override
    public CarritoDTO obtenerCarritoPorId(Long id) {
        Optional<Carrito> carrito = carritoRepository.findById(id);
        return carrito.map(c -> carritoMapper.toDTO(c)).orElse(null);
    }

    @Override
    public List<CarritoDTO> obtenerCarritosPorUsuario(Long usuarioId) {
        List<Carrito> carritos = carritoRepository.findByUsuario_UsuarioId(usuarioId);
        return carritos.stream().map(c -> carritoMapper.toDTO(c)).toList();
    }

    @Override
    public List<CarritoDTO> obtenerTodosCarritos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream().map(c -> carritoMapper.toDTO(c)).toList();
    }

    @Override
    public CarritoDTO actualizarCarrito(Long carritoId, CarritoDTO carritoDTO) {
        Carrito carritoExistente = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Limpiar productos actuales
        carritoExistente.getProductos().clear();

        // Agregar los nuevos productos desde el DTO
        if (carritoDTO.getProductos() != null) {
            for (CarritoProductoDTO productoDTO : carritoDTO.getProductos()) {
                Producto producto = productoRepository.findById(productoDTO.getProductoId())
                        .orElseThrow(() -> new RuntimeException("Producto con ID " + productoDTO.getProductoId() + " no encontrado"));

                CarritoProducto carritoProducto = new CarritoProducto(
                        null,
                        carritoExistente,
                        producto,
                        productoDTO.getCantidad(),
                        LocalDateTime.now()
                );

                carritoExistente.getProductos().add(carritoProducto);
            }
        }

        Carrito carritoActualizado = carritoRepository.save(carritoExistente);
        return carritoMapper.toDTO(carritoActualizado);
    }

    @Override
    public void eliminarCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }

    @Override
    public CarritoDTO agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad) {
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        if (carritoOptional.isPresent()) {
            Carrito carrito = carritoOptional.get();

            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Verificar si el producto tiene suficiente stock
            if (producto.getProductoStock() < cantidad) {
                throw new RuntimeException("No hay suficiente stock para agregar este producto al carrito.");
            }

            CarritoProducto carritoProducto = new CarritoProducto(
                    null,
                    carrito,
                    producto,
                    cantidad,
                    LocalDateTime.now()
            );

            carrito.getProductos().add(carritoProducto);
            carritoRepository.save(carrito);
            return carritoMapper.toDTO(carrito);
        }
        return null;
    }

    @Override
    public CarritoDTO eliminarProductoDelCarrito(Long carritoId, Long productoId) {
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        if (carritoOptional.isPresent()) {
            Carrito carrito = carritoOptional.get();
            System.out.println("Productos antes de la eliminación: " + carrito.getProductos().size());

            // Eliminar el producto con el ID especificado
            carrito.getProductos().removeIf(cp -> cp.getProducto().getProductoId().equals(productoId));

            System.out.println("Productos después de la eliminación: " + carrito.getProductos().size());

            carritoRepository.save(carrito);  // Guardar el carrito actualizado
            return carritoMapper.toDTO(carrito);  // Retornar el carrito actualizado
        }
        return null;  // Si no se encuentra el carrito
    }

    @Override
    public Double calcularTotalCarrito(Long carritoId) {
        Optional<Carrito> carritoOptional = carritoRepository.findById(carritoId);
        if (carritoOptional.isPresent()) {
            Carrito carrito = carritoOptional.get();
            return carrito.getProductos().stream()
                    .mapToDouble(cp -> cp.getProducto().getProductoPrecio() * cp.getCantidad())
                    .sum();
        }
        return null;
    }

    @Override
    public boolean pagarCarrito(Long carritoId, String metodoPago) {
        // 1. Buscar el carrito
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getProductos().isEmpty()) {
            throw new RuntimeException("El carrito está vacío, no se puede procesar el pedido.");
        }

        // 2. Obtener el usuario del carrito
        Usuario usuario = carrito.getUsuario();

        // 3. Verificar que el usuario tenga dirección
        List<Direccion> direcciones = direccionRepository.findByUsuario_UsuarioId(Math.toIntExact(usuario.getUsuarioId()));
        if (direcciones.isEmpty()) {
            throw new RuntimeException("El usuario no tiene direcciones registradas.");
        }
        Direccion direccion = direcciones.get(0); // Tomamos la primera dirección

        // 4. Calcular total
        BigDecimal total = carrito.getProductos().stream()
                .map(cp -> BigDecimal.valueOf(cp.getProducto().getProductoPrecio())
                        .multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 5. Verificar y descontar saldo de la cuenta bancaria (si método es BANCOSIMPLE)
        CuentaBancaria cuentaUtilizada = null;
        if (metodoPago.equalsIgnoreCase("BANCOSIMPLE")) {
            List<CuentaBancaria> cuentas = cuentaBancariaRepository.findByUsuarioId(usuario.getUsuarioId());
            if (cuentas.isEmpty()) {
                throw new RuntimeException("El usuario no tiene cuentas bancarias registradas.");
            }

            cuentaUtilizada = cuentas.get(0); // Usamos la primera cuenta
            if (cuentaUtilizada.getCuentaSaldo().compareTo(total) < 0) {
                throw new RuntimeException("Saldo insuficiente en la cuenta bancaria.");
            }

            // Descontar el monto
            cuentaUtilizada.setCuentaSaldo(cuentaUtilizada.getCuentaSaldo().subtract(total));
            cuentaBancariaRepository.save(cuentaUtilizada);

            // 🔍 Log para depuración
            System.out.println("Método de pago: BANCOSIMPLE");
            System.out.println("Número de cuenta utilizada: " + cuentaUtilizada.getCuentaNumero());
            System.out.println("Saldo restante en la cuenta: " + cuentaUtilizada.getCuentaSaldo());
        } else {
            System.out.println("Método de pago: " + metodoPago);
            System.out.println("No se usó una cuenta bancaria");
        }

        // 6. Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccion(direccion);
        pedido.setPedidoTotal(total);
        pedido.setPedidoMoneda("CLP");
        pedido.setPedidoEstado(EstadoPedido.PENDIENTE);
        pedido.setPedidoMetodoPago(metodoPago.toUpperCase());
        pedido.setPedidoUuidPago(UUID.randomUUID().toString());
        pedido.setPedidoFecha(java.sql.Date.valueOf(LocalDate.now()));
        pedido.setPedidoCreadoEn(java.sql.Timestamp.valueOf(LocalDateTime.now()));

        // 7. Crear detalles del pedido
        List<PedidoDetalle> detalles = carrito.getProductos().stream().map(cp -> {
            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(pedido);
            detalle.setProducto(cp.getProducto());
            detalle.setDetalleCantidad(cp.getCantidad());
            detalle.setDetallePrecioUnitario(cp.getProducto().getProductoPrecio());
            return detalle;
        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);

        // 8. Guardar el pedido
        pedidoRepository.save(pedido);

        // 9. Registrar el pago
        Pago pago = new Pago();
        pago.setPedido(pedido);
        pago.setPagoMonto(total.doubleValue());
        pago.setPagoMetodo(metodoPago.toUpperCase());
        pago.setPagoFecha(Timestamp.valueOf(LocalDateTime.now()));
        pago.setCuenta(cuentaUtilizada); // Puede ser null si no es BANCOSIMPLE

        pagoRepository.save(pago);

        generarPuntosFidelizacion(usuario, total);

        // 11. Eliminar el carrito
        carritoRepository.delete(carrito);

        return true;
    }

    private void generarPuntosFidelizacion(Usuario usuario, BigDecimal monto) {
        // 1. Obtener puntos de fidelización previos
        Optional<PuntosFidelizacion> puntosOptional = puntosFidelizacionRepository.findById(usuario.getUsuarioId());
        PuntosFidelizacion puntos = puntosOptional.orElseGet(() -> {
            PuntosFidelizacion nuevoPunto = new PuntosFidelizacion();
            nuevoPunto.setUsuario(usuario);
            nuevoPunto.setPuntosAcumulados(0);
            return nuevoPunto;
        });

        // 2. Calcular los puntos (suponiendo 1 punto por cada 1000 CLP)
        int puntosGenerados = monto.intValue() / 1000;
        puntos.setPuntosAcumulados(puntos.getPuntosAcumulados() + puntosGenerados);

        // 3. Guardar los puntos acumulados
        puntosFidelizacionRepository.save(puntos);
    }
}

