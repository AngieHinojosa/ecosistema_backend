package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.mapper.MensajeMapper;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.repository.MensajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;
    private final MensajeMapper mensajeMapper;

    @Autowired
    public MensajeServiceImpl(MensajeRepository mensajeRepository, MensajeMapper mensajeMapper) {
        this.mensajeRepository = mensajeRepository;
        this.mensajeMapper = mensajeMapper;
    }

    @Override
    public MensajeDTO crearMensaje(MensajeDTO mensajeDTO) {
        Mensaje mensaje = mensajeMapper.toEntity(mensajeDTO);
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
        List<Mensaje> mensajes = mensajeRepository.findByEmisor_UsuarioId(emisorId);
        return mensajes.stream()
                .map(mensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> obtenerMensajesPorReceptorId(Integer receptorId) {
        List<Mensaje> mensajes = mensajeRepository.findByReceptor_UsuarioId(receptorId);
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
        mensajeExistente = mensajeMapper.toEntity(mensajeDTO);
        mensajeExistente.setMensajeId(id);  // Mantener el ID
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