package br.com.fecaf.repository;

import br.com.fecaf.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Query("SELECT v FROM Veiculo v WHERE " +
            "(:marca IS NULL OR v.marca LIKE %:marca%) AND " +
            "(:modelo IS NULL OR v.modelo LIKE %:modelo%) AND " +
            "(:ano IS NULL OR v.ano = :ano) AND " +
            "(:preco IS NULL OR v.preco <= :preco) AND " +
            "(:status IS NULL OR v.status = :status)")
    List<Veiculo> findByFiltros(@Param("marca") String marca,
                                @Param("modelo") String modelo,
                                @Param("ano") Integer ano,
                                @Param("preco") Double preco,
                                @Param("status") String status);
}
