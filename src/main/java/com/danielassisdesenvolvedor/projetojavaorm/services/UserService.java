package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.UserDTO;
import com.danielassisdesenvolvedor.projetojavaorm.entities.Role;
import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.danielassisdesenvolvedor.projetojavaorm.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findByEmail(String email) {
        return userRepository.searchUserAndRolesByEmailLike(email).stream().map(UserDTO::new).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<UserDetailsProjection> users = userRepository.searchUserAndRolesByEmail(email);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(users.getFirst().getPassword());
        for(UserDetailsProjection projection : users){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
