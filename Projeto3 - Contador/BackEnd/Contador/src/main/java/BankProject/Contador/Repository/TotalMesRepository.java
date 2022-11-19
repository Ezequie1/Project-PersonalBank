package BankProject.Contador.Repository;

import BankProject.Contador.Model.TotalMes;
import BankProject.Contador.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalMesRepository extends JpaRepository<TotalMes, Long>{

    @Query("SELECT t FROM TotalMes t WHERE t.mes = :valueMes and t.ano = :valueAno and t.user = :usuario")
    TotalMes findByMesEAno(@Param("valueMes") String mes, @Param("valueAno") long ano, @Param("usuario")User user);
    @Query ("SELECT t FROM TotalMes t WHERE t.user.id = :id")
    List<TotalMes> findAllByUser(@Param("id") Long id);
}
