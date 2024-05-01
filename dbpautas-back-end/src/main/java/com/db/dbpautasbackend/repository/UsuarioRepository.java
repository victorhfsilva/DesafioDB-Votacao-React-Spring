package com.db.dbpautasbackend.repository;

import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.model.info.UserDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT NEW com.db.dbpautasbackend.model.info.impl.UserDetailsInfoImpl(u.cpf, u.senha, u.papel) FROM Usuario u WHERE u.cpf = :cpf")
    Optional<UserDetailsInfo> findUserDetailsByCpf(String cpf);

    Optional<Usuario> findByCpf(String cpf);

}
