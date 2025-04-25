package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.mapper.MensajeMapper;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.MensajeRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;
    private final MensajeMapper mensajeMapper;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MensajeServiceImpl(MensajeRepository mensajeRepository, MensajeMapper mensajeMapper, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.mensajeMapper = mensajeMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public MensajeDTO crearMensaje(MensajeDTO mensajeDTO) {
        // Obtener el usuario autenticado, probablemente de un contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        // Verificar si el usuario autenticado tiene el rol de soporte (ROLE_ADMIN)
        boolean esSoporte = usuarioAutenticado.getRoles().contains(ERol.ROLE_ADMIN);

        if (!esSoporte) {
            // Si el usuario no tiene el rol adecuado, lanzar una excepción o manejarlo
            throw new RuntimeException("No tienes permiso para enviar mensajes como soporte.");
        }

        // Si es soporte, asignar el receptor como el usuario con el rol de soporte (ROLE_ADMIN)
        Usuario soporte = usuarioRepository.findById(usuarioAutenticado.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));

        // Crear y guardar el mensaje
        Mensaje mensaje = mensajeMapper.toEntity(mensajeDTO);
        mensaje.setReceptor(soporte);

        Mensaje savedMensaje = mensajeRepository.save(mensaje);
        return mensajeMapper.toDTO(savedMensaje);
    }


    @Override
    public MensajeDTO obtenerMensajePorId(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        return mensajeMapper.toDTO(mensaje);
    }

    @Override
    public List<MensajeDTO> obtenerMensajesPorEmisorId(Integer emisorId) {
        List<Mensaje> mensajes = mensajeRepository.findByEmisor_UsuarioIdAndReceptor_UsuarioId(emisorId, 1);
        return mensajes.stream()
                .map(mensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> obtenerMensajesPorReceptorId(Integer receptorId) {
        List<Mensaje> mensajes = mensajeRepository.findByReceptor_UsuarioIdAndEmisor_UsuarioId(receptorId, 1);
        return mensajes.stream()
                .map(mensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> obtenerMensajesPorLeido(Boolean mensajeLeido) {
        List<Mensaje> mensajes = mensajeRepository.findByMensajeLeido(mensajeLeido);
        return mensajes.stream()
                .map(mensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> obtenerTodosMensajes() {
        List<Mensaje> mensajes = mensajeRepository.findAll();
        return mensajes.stream()
                .map(mensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MensajeDTO actualizarMensaje(Long id, MensajeDTO mensajeDTO) {
        Mensaje mensajeExistente = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));

        // Solo actualiza el contenido y campos que pueden cambiar (como leido o fecha, no el emisor/receptor)
        mensajeExistente.setMensajeContenido(mensajeDTO.getMensajeContenido());
        mensajeExistente.setMensajeLeido(mensajeDTO.getMensajeLeido());
        mensajeExistente.setMensajeEnviadoEn(mensajeDTO.getMensajeEnviadoEn());

        Mensaje updatedMensaje = mensajeRepository.save(mensajeExistente);
        return mensajeMapper.toDTO(updatedMensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        mensajeRepository.delete(mensaje);
    }
}