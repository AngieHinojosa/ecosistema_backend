package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import com.miprimerspring.nuestroecosistema.repository.LogsActividadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogsActividadServiceImpl implements LogsActividadService{

    @Autowired
    private LogsActividadRepository logsActividadRepository;

    @Override
    public List<LogsActividad> obtenerTodosLosLogs() {
        return (List<LogsActividad>) logsActividadRepository.findAll();  // Devuelve todos los logs de actividad
    }

    @Override
    public LogsActividad obtenerLogPorId(Long id) {
        Optional<LogsActividad> logsActividad = logsActividadRepository.findById(id);
        return logsActividad.orElse(null);  // Devuelve el log si existe, o null si no
    }

    @Override
    public LogsActividad crearLogActividad(LogsActividad logsActividad) {
        return logsActividadRepository.save(logsActividad);  // Guarda un nuevo log de actividad
    }

    @Override
    public void eliminarLogActividad(Long id) {
        if (logsActividadRepository.existsById(id)) {
            logsActividadRepository.deleteById(id);  // Elimina el log de actividad por su ID
        }
    }
}
