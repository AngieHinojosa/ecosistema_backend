package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.MensajeDTO;
import com.miprimerspring.nuestroecosistema.DTO.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.MensajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public List<MensajeDTO> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll().stream()
                .map(this::convertirAMensajeDTO) // Convertir de entidad a DTO
                .collect(Collectors.toList());
    }

    @Override
    public MensajeDTO obtenerMensajePorId(Long id) {
        // Buscar la entidad Mensaje
        return mensajeRepository.findById(id)
                .map(this::convertirAMensajeDTO) // Convertir la entidad Mensaje a un DTO
                .orElse(null);  // O puedes lanzar una excepción personalizada si lo prefieres
    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }

    // Método para convertir Mensaje a MensajeDTO
    public MensajeDTO convertirAMensajeDTO(Mensaje mensaje) {
        return new MensajeDTO(mensaje);  // Convierte una entidad 'Mensaje' a un 'MensajeDTO'
    }

    // Método para convertir MensajeDTO a Mensaje (entidad)
    public Mensaje convertirMensajeDTOaEntidad(MensajeDTO mensajeDTO) {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensajeContenido(mensajeDTO.getMensajeContenido());
        mensaje.setMensajeLeido(mensajeDTO.getMensajeLeido());
        mensaje.setMensajeEnviadoEn(mensajeDTO.getMensajeEnviadoEn());

        // Asegúrate de convertir los usuarios (emisor y receptor) de DTO a entidad
        if (mensajeDTO.getEmisor() != null && mensajeDTO.getReceptor() != null) {
            mensaje.setEmisor(convertirUsuarioDTOaEntidad(mensajeDTO.getEmisor()));  // Convertir emisor DTO a entidad
            mensaje.setReceptor(convertirUsuarioDTOaEntidad(mensajeDTO.getReceptor()));  // Convertir receptor DTO a entidad
        }

        return mensaje;  // Retorna la entidad Mensaje
    }

    // Método para convertir UsuarioDTO a Usuario (entidad)
    private Usuario convertirUsuarioDTOaEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioDTO.getUsuarioId());
        usuario.setUsuarioNombres(usuarioDTO.getUsuarioNombres());
        // Asignar otros campos necesarios
        return usuario;
    }

    public MensajeDTO crearMensaje(MensajeDTO mensajeDTO) {
        Mensaje mensaje = convertirMensajeDTOaEntidad(mensajeDTO); // Convierte el DTO a entidad
        mensaje = mensajeRepository.save(mensaje); // Guardar la entidad

        // Convertir la entidad guardada a DTO y devolverlo
        return convertirAMensajeDTO(mensaje);
    }
}