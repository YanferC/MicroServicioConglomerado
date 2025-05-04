package msConglomerado.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import msConglomerado.jpa.postgresql.entity.msConglomeradoEntity;

import java.util.Optional;


public interface msConglomeradoRepository extends JpaRepository<msConglomeradoEntity, String> {
    Optional<msConglomeradoEntity> findByIdConglomerado(String idConglomerado);


}
