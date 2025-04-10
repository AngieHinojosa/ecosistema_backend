package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.repository.DescuentoAplicadoRepository;
import com.miprimerspring.nuestroecosistema.repository.DescuentosExternosRepository;
import com.miprimerspring.nuestroecosistema.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DescuentoAplicadoServiceImpl implements DescuentoAplicadoService {

    @Autowired
    private DescuentoAplicadoRepository descuentoAplicadoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DescuentosExternosRepository descuentosExternosRepository;

    @Override
    public List<DescuentoAplicadoDTO> obtenerTodosLosDescuentosAplicados() {
        List<DescuentoAplicado> descuentos = descuentoAplicadoRepository.findAll();
        return descuentos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id) {
        Optional<DescuentoAplicado> descuentoAplicado = descuentoAplicadoRepository.findById(id);
        return descuentoAplicado.map(this::convertirADTO).orElse(null);
    }

    @Override
    public DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO) {
        // Convertir DTO a entidad
        DescuentoAplicado descuentoAplicado = convertirAEntidad(descuentoAplicadoDTO);

        // Guardar la entidad
        DescuentoAplicado descuentoGuardado = descuentoAplicadoRepository.save(descuentoAplicado);

        // Devolver el DTO correspondiente
        return convertirADTO(descuentoGuardado);
    }

    @Override
    public DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO) {
        // Comprobar si el descuento existe
        if (descuentoAplicadoRepository.existsById(id)) {
            // Convertir DTO a entidad
            DescuentoAplicado descuentoAplicado = convertirAEntidad(descuentoAplicadoDTO);
            descuentoAplicado.setDescuentoAplicadoId(id);

            // Guardar la entidad actualizada
            DescuentoAplicado descuentoActualizado = descuentoAplicadoRepository.save(descuentoAplicado);

            // Devolver el DTO actualizado
            return convertirADTO(descuentoActualizado);
        }
        return null;
    }

    @Override
    public void eliminarDescuentoAplicado(Long id) {
        if (descuentoAplicadoRepository.existsById(id)) {
            descuentoAplicadoRepository.deleteById(id);
        }
    }

    // Método para convertir la entidad a DTO
    private DescuentoAplicadoDTO convertirADTO(DescuentoAplicado descuentoAplicado) {
        DescuentoAplicadoDTO dto = new DescuentoAplicadoDTO();
        dto.setDescuentoAplicadoId(descuentoAplicado.getDescuentoAplicadoId());
        dto.setDescuentoMonto(descuentoAplicado.getDescuentoMonto());
        dto.setDescuentoCodigo(descuentoAplicado.getDescuentoCodigo());
        dto.setPedidoId(descuentoAplicado.getPedido().getPedidoId());
        dto.setDescuentoId(descuentoAplicado.getDescuento().getDescuentoId());
        return dto;
    }

    // Método para convertir DTO a entidad
    private DescuentoAplicado convertirAEntidad(DescuentoAplicadoDTO dto) {
        DescuentoAplicado descuentoAplicado = new DescuentoAplicado();
        descuentoAplicado.setDescuentoMonto(dto.getDescuentoMonto());
        descuentoAplicado.setDescuentoCodigo(dto.getDescuentoCodigo());

        // Asignar entidades de Pedido y DescuentosExternos desde sus IDs
        Optional<Pedido> pedido = pedidoRepository.findById(dto.getPedidoId());
        Optional<DescuentosExternos> descuento = descuentosExternosRepository.findById(dto.getDescuentoId());

        if (pedido.isPresent() && descuento.isPresent()) {
            descuentoAplicado.setPedido(pedido.get());
            descuentoAplicado.setDescuento(descuento.get());
        }

        return descuentoAplicado;
    }
}
