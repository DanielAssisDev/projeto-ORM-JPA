package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;

public class UserDTO {
    private String name;
    private String password;
    private Long roleId;
    private String authority;

    public UserDTO() {
    }

    public UserDTO(UserDetailsProjection udp) {
        name = udp.getUsername();
        password = udp.getPassword();
        roleId = udp.getRoleId();
        authority = udp.getAuthority();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
