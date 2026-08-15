package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.UserDTO;
import com.danielassisdesenvolvedor.projetojavaorm.dto.UserMinDTO;
import com.danielassisdesenvolvedor.projetojavaorm.entities.Role;
import com.danielassisdesenvolvedor.projetojavaorm.entities.User;
import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserMinDTO> findByEmail(String email) {
        return userRepository.searchUserAndRolesByEmailLike(email).stream().map(UserMinDTO::new).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> users = userRepository.searchUserAndRolesByEmail(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        User user = new User();
        user.setEmail(users.getFirst().getUsername());
        user.setPassword(users.getFirst().getPassword());
        for (UserDetailsProjection projection : users) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    @Transactional
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional
    public UserDTO getMe(){
        return new UserDTO(authenticated());
    }
}
