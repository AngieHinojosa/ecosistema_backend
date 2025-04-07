package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.LogsActividad;

import java.util.List;

public interface LogsActividadService {

    // Obtener todos los logs de actividad
    List<LogsActividad> obtenerTodosLosLogs();

    // Obtener un log de actividad por su ID
    LogsActividad obtenerLogPorId(Long id);

    // Crear un nuevo log de actividad
    LogsActividad crearLogActividad(LogsActividad logsActividad);

    // Eliminar un log de actividad
    void eliminarLogActividad(Long id);
}
