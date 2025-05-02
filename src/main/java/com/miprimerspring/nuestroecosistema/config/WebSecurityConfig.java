package com.miprimerspring.nuestroecosistema.config;

import com.google.cloud.storage.HttpMethod;
import com.miprimerspring.nuestroecosistema.security.AuthEntryPointJwt;
import com.miprimerspring.nuestroecosistema.security.AuthTokenFilter;
import com.miprimerspring.nuestroecosistema.security.JwtUtils;
import com.miprimerspring.nuestroecosistema.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtUtils jwtUtils;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Permitir orígenes específicos o cualquier origen (para desarrollo)
        corsConfig.addAllowedOrigin("http://localhost:5173"); // o "*" para permitir todos
        corsConfig.addAllowedMethod("*");  // Permite todos los métodos (GET, POST, PUT, DELETE, etc.)
        corsConfig.addAllowedHeader("*");  // Permite todos los encabezados (Authorization, Content-Type, etc.)
        corsConfig.setAllowCredentials(true);  // Permitir credenciales (como cookies o Authorization header)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Aplica CORS a todas las rutas

        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(withDefaults())
                // Configuración de las rutas públicas (Swagger y otras)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/ui",
                                "/configuration/security"
                        ).permitAll()

                        .requestMatchers("/api/auth/**").permitAll() // Rutas de autenticación pública
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/roles/nuevo").permitAll()
                        .requestMatchers("/error").permitAll()

                        // Rutas protegidas por roles
                        // Producto


                        .requestMatchers("/producto/todos").permitAll()
                        .requestMatchers("/producto/nuevo").hasAnyRole("VENDEDOR", "ADMIN") // Vendedores y Admin pueden crear productos
                        .requestMatchers("/producto/lista").hasAnyRole("USER", "ADMIN", "VENDEDOR") // Todos pueden ver productos
                        .requestMatchers("/producto/{id}").hasAnyRole("USER", "ADMIN", "VENDEDOR") // Todos pueden obtener productos por ID
                        .requestMatchers("/producto/estado/**").hasAnyRole("USER", "ADMIN", "VENDEDOR")
                        .requestMatchers("/producto/nombre/**").hasAnyRole("USER", "ADMIN", "VENDEDOR")
                        .requestMatchers("/producto/categoria/**").hasAnyRole("USER", "ADMIN", "VENDEDOR")
                        .requestMatchers("/producto/eliminar/**").hasAnyRole("ADMIN", "VENDEDOR")
                        .requestMatchers("/producto/actualizar/**").hasAnyRole("ADMIN", "VENDEDOR")

                        // Carrito
                        .requestMatchers("/carritos/nuevo").hasRole("USER") // Solo usuarios autenticados pueden crear carritos
                        .requestMatchers("/carritos/usuario/**").hasRole("USER") // Obtener carritos por usuario (puede ser el mismo usuario)
                        .requestMatchers("/carritos/{id}").hasRole("USER") // Obtener carrito por id (ya haces validación dentro del método)
                        .requestMatchers("/carritos/{id}/**").hasRole("USER") // Operaciones dentro de un carrito: agregar producto, pagar, eliminar, etc.
                        .requestMatchers("/carritos").hasRole("ADMIN") // Solo el admin puede ver todos los carritos

                        // Categoría
                        .requestMatchers("/categorias/nuevo").hasRole("ADMIN") // Solo ADMIN puede crear categorías
                        .requestMatchers("/categorias").hasAnyRole("USER", "ADMIN") // Todos los usuarios pueden ver categorías
                        .requestMatchers("/categorias/{id}").hasAnyRole("USER", "ADMIN") // Todos los usuarios pueden ver una categoría por id
                        .requestMatchers("/categorias/{id}/editar").hasRole("ADMIN") // Solo ADMIN puede editar categorías
                        .requestMatchers("/categorias/{id}/eliminar").hasRole("ADMIN") // Solo ADMIN puede eliminar categorías

                        // Rutas de usuario (modificar según lo que tengas en UsuarioRestController)
                        .requestMatchers("/usuarios/registro").permitAll() // El registro está abierto a todos
                        .requestMatchers("/usuarios/{id}").hasRole("USER") // Solo el usuario o ADMIN puede ver la información del usuario
                        .requestMatchers("/usuarios/rol/{rol}").hasRole("ADMIN") // Solo ADMIN puede listar usuarios por rol
                        .requestMatchers("/usuarios/eliminar/{id}").hasRole("ADMIN") // Solo ADMIN puede eliminar usuarios

                        // Pedido
                        .requestMatchers("/pedido/nuevo").hasRole("USER") // Crear pedidos: cualquier usuario registrado
                        .requestMatchers("/pedido/todos").hasRole("ADMIN") // Solo admin puede ver todos los pedidos
                        .requestMatchers("/pedido/estado/**").hasAnyRole("ADMIN", "VENDEDOR") // Admin y vendedores pueden ver pedidos por estado
                        .requestMatchers("/pedido/fecha/**").hasAnyRole("ADMIN", "VENDEDOR") // Admin y vendedores pueden ver pedidos por fecha
                        .requestMatchers("/pedido/usuario/**").hasRole("USER") // Usuarios pueden ver sus propios pedidos
                        .requestMatchers("/pedido/{id}").hasAnyRole("USER", "ADMIN", "VENDEDOR") // Ver detalles por ID
                        .requestMatchers("/pedido/{id}/**").hasRole("ADMIN") // Actualizar/eliminar pedidos: solo admin

                        // Direccion
                        .requestMatchers("/direcciones/nueva").hasRole("USER") // Solo usuarios autenticados pueden crear direcciones
                        .requestMatchers("/direcciones/{id}").hasRole("USER") // El usuario puede ver su propia dirección
                        .requestMatchers("/direcciones/usuario/{usuarioId}").hasRole("USER") // Ver direcciones de un usuario específico
                        .requestMatchers("/direcciones/activa").hasRole("USER") // Ver direcciones activas
                        .requestMatchers("/direcciones").hasRole("ADMIN") // Solo ADMIN puede ver todas las direcciones
                        .requestMatchers("/direcciones/{id}/editar").hasRole("USER") // Solo el propietario de la dirección o ADMIN pueden editarla
                        .requestMatchers("/direcciones/{id}/eliminar").hasRole("USER") // Solo el propietario de la dirección o ADMIN pueden eliminarla

                        // El resto de las rutas requieren autenticación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}