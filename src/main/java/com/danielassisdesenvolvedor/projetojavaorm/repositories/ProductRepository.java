package com.danielassisdesenvolvedor.projetojavaorm.repositories;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Product;
import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);

    @Query(nativeQuery = true, value = """
			SELECT u.email username, u.password, r.id AS roleId, r.authority
			FROM tb_user u
			INNER JOIN tb_user_role ur ON u.id = ur.user_id
			INNER JOIN tb_role r ON r.id = ur.role_id
			WHERE u.email LIKE CONCAT('%', :email, '%')
		""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
