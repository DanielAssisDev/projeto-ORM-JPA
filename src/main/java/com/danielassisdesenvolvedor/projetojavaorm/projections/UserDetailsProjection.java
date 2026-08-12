package com.danielassisdesenvolvedor.projetojavaorm.projections;

public interface UserDetailsProjection {
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
