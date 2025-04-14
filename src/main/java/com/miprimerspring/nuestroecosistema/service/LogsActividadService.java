package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
import com.miprimerspring.nuestroecosistema.model.LogsActividad;

import java.util.List;

public interface LogsActividadService {

    LogsActividadDTO crearLogActividad(LogsActividadDTO logsActividadDTO);
    LogsActividadDTO obtenerLogActividadPorId(Long id);
    List<LogsActividadDTO> obtenerLogsPorUsuarioId(Integer usuarioId);
    List<LogsActividadDTO> obtenerLogsPorAccion(String logAccion);
    List<LogsActividadDTO> obtenerTodosLogs();
    LogsActividadDTO actualizarLogActividad(Long id, LogsActividadDTO logsActividadDTO);
    void eliminarLogActividad(Long id);
}
