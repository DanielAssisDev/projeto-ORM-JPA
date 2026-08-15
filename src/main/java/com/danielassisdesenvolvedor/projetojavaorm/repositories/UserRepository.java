package com.danielassisdesenvolvedor.projetojavaorm.repositories;

import com.danielassisdesenvolvedor.projetojavaorm.dto.ClientDTO;
import com.danielassisdesenvolvedor.projetojavaorm.dto.UserDTO;
import com.danielassisdesenvolvedor.projetojavaorm.entities.User;
import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
            	SELECT new com.danielassisdesenvolvedor.projetojavaorm.dto.UserDTO(u) FROM User u
            	WHERE UPPER(u.email) LIKE UPPER(CONCAT('%', :email, '%'))
            	""")
    List<UserDTO> searchUserAndRolesByEmailLike(String email);

    @Query(value = """
            SELECT new com.danielassisdesenvolvedor.projetojavaorm.dto.ClientDTO(u) FROM User u
            """)
    ClientDTO searchClient();

    @Query(nativeQuery = true, value = """
            SELECT u.email username, u.password, r.id roleId, r.authority
            			FROM tb_user u
            			INNER JOIN tb_user_role ur ON u.id = ur.user_id
            			INNER JOIN tb_role r ON r.id = ur.role_id
            			WHERE u.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);

}
