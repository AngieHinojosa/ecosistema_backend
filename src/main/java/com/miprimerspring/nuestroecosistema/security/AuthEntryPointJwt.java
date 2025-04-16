package com.miprimerspring.nuestroecosistema.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    // Este método se invoca cuando ocurre un error de autenticación
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Loguea el error de autenticación
        logger.error("Unauthorized error: {}", authException.getMessage());

        // Establece el tipo de respuesta como JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // Establece el estado de la respuesta como 401 (No autorizado)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Crea un mapa con la información del error
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED); // Estado de la respuesta
        body.put("error", "Unauthorized"); // Error de autorización
        body.put("message", authException.getMessage()); // Mensaje de error
        body.put("path", request.getServletPath()); // Ruta que causó el error

        // Convierte el mapa a JSON y lo escribe en el cuerpo de la respuesta
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}