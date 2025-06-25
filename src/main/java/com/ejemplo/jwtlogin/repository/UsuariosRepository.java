package com.ejemplo.jwtlogin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejemplo.jwtlogin.entity.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    @Query(value = "SELECT 'l' AS de, u.usecod, u.useusr, u.grucod, g.grudes, " +
            "s.siscod, s.sisent, su.codalm_inv, a.central, u.usenam " +
            "FROM usuarios u " +
            "LEFT JOIN grupos g ON u.grucod = g.grucod " +
            "INNER JOIN sistema_usuario su ON su.usecod = u.usecod " +
            "INNER JOIN fa_almacenes a ON a.codalm = su.codalm_inv " +
            "INNER JOIN sistema s ON s.siscod = a.siscod " +
            "WHERE u.useusr = :useusr AND u.usepas = :usepas", nativeQuery = true)
    List<Object[]> validarUsuario(@Param("useusr") String useusr, @Param("usepas") String usepas);

    @Query(value = "SELECT 'l' AS de, u.usecod, u.useusr, u.grucod, g.grudes, " +
            "s.siscod, s.sisent, su.codalm_inv, a.central, u.usenam " +
            "FROM usuarios u " +
            "LEFT JOIN grupos g ON u.grucod = g.grucod " +
            "INNER JOIN sistema_usuario su ON su.usecod = u.usecod " +
            "INNER JOIN fa_almacenes a ON a.codalm = su.codalm_inv " +
            "INNER JOIN sistema s ON s.siscod = a.siscod " +
            "WHERE u.useusr = :useusr", nativeQuery = true)
    List<Object[]> validarUsuarioByUsername(@Param("useusr") String useusr);


    @Query(value = "SELECT 'b' AS de, b.usecod, b.useusr, b.grucod, 'Bartolito' AS grudes, " +
               "a.siscod, s.sisent, b.codalm " +
               "FROM usuarios_bartolito b " +
               "INNER JOIN fa_almacenes a ON a.codalm = b.codalm " +
               "INNER JOIN sistema s ON s.siscod = a.siscod " +
               "WHERE b.useusr = :useusr AND b.usepas = :usepas AND b.estado = 'S'", 
       nativeQuery = true)
        List<Object[]> validarUsuarioBartolito(@Param("useusr") String useusr, @Param("usepas") byte[] usepas);


    // Método adicional si necesitas buscar solo por username
    @Query(value = "SELECT useusr FROM usuarios WHERE useusr = :username", nativeQuery = true)
    Optional<String> findByUsername(@Param("username") String username);
}
