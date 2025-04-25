package com.miprimerspring.nuestroecosistema.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long usuarioId;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public List<ERol> getRoles() {
        return authorities.stream()
                .map(grantedAuthority -> ERol.valueOf(grantedAuthority.getAuthority()))
                .collect(Collectors.toList());
    }

    public static UserDetailsImpl build(Usuario usuario) {
        // Convertimos el enum ERol directamente en authorities
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.name()))  // Cambiado a rol.name() ya que rol es ERol
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                usuario.getUsuarioId(),
                usuario.getUsuarioCorreo(),
                usuario.getUsuarioContrasena(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email; // <- USAMOS EL CORREO COMO USERNAME
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getUsuarioCorreo() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsImpl)) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(usuarioId, that.usuarioId);
    }
}