package BankProject.Contador.Repository;

import BankProject.Contador.Model.Enums.MesesDoAno;
import BankProject.Contador.Model.TotalAno;
import BankProject.Contador.Model.TotalMes;
import BankProject.Contador.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalAnoRepository extends JpaRepository<TotalAno, Long>{

    @Query("SELECT t FROM TotalAno t WHERE t.ano = :ano and t.user = :usuario")
    TotalAno findByAno(@Param("ano") long ano, @Param("usuario")User user);
    @Query("SELECT t FROM TotalAno t WHERE t.user.id = :id")
    List<TotalAno> findAllByUser(@Param("id") Long id);
}
