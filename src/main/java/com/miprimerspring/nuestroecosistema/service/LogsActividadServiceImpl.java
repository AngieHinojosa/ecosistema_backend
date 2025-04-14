package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
import com.miprimerspring.nuestroecosistema.mapper.LogsActividadMapper;
import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import com.miprimerspring.nuestroecosistema.repository.LogsActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogsActividadServiceImpl implements LogsActividadService{

    private final LogsActividadRepository logsActividadRepository;
    private final LogsActividadMapper logsActividadMapper;

    @Autowired
    public LogsActividadServiceImpl(LogsActividadRepository logsActividadRepository, LogsActividadMapper logsActividadMapper) {
        this.logsActividadRepository = logsActividadRepository;
        this.logsActividadMapper = logsActividadMapper;
    }

    @Override
    public LogsActividadDTO crearLogActividad(LogsActividadDTO logsActividadDTO) {
        LogsActividad logsActividad = logsActividadMapper.toEntity(logsActividadDTO);
        LogsActividad savedLogsActividad = logsActividadRepository.save(logsActividad);
        return logsActividadMapper.toDTO(savedLogsActividad);
    }

    @Override
    public LogsActividadDTO obtenerLogActividadPorId(Long id) {
        LogsActividad logsActividad = logsActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de actividad no encontrado"));
        return logsActividadMapper.toDTO(logsActividad);
    }

    @Override
    public List<LogsActividadDTO> obtenerLogsPorUsuarioId(Integer usuarioId) {
        List<LogsActividad> logs = logsActividadRepository.findByUsuario_UsuarioId(usuarioId);
        return logs.stream()
                .map(logsActividadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogsActividadDTO> obtenerLogsPorAccion(String logAccion) {
        List<LogsActividad> logs = logsActividadRepository.findByLogAccion(logAccion);
        return logs.stream()
                .map(logsActividadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogsActividadDTO> obtenerTodosLogs() {
        List<LogsActividad> logs = logsActividadRepository.findAll();
        return logs.stream()
                .map(logsActividadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LogsActividadDTO actualizarLogActividad(Long id, LogsActividadDTO logsActividadDTO) {
        LogsActividad logsActividadExistente = logsActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de actividad no encontrado"));
        logsActividadExistente = logsActividadMapper.toEntity(logsActividadDTO);
        logsActividadExistente.setLogId(id);  // Mantener el ID
        LogsActividad updatedLogsActividad = logsActividadRepository.save(logsActividadExistente);
        return logsActividadMapper.toDTO(updatedLogsActividad);
    }

    @Override
    public void eliminarLogActividad(Long id) {
        LogsActividad logsActividad = logsActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de actividad no encontrado"));
        logsActividadRepository.delete(logsActividad);
    }
}