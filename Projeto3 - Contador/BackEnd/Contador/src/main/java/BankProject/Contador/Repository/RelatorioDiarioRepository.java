package BankProject.Contador.Repository;

import BankProject.Contador.Model.RelatorioDiario;
import BankProject.Contador.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioDiarioRepository extends JpaRepository<RelatorioDiario, Long> {
    @Query("SELECT r FROM RelatorioDiario r WHERE r.mes = :valueMes and r.dia = :valueDia and r.ano = :valueAno and r.user = :usuario")
    RelatorioDiario findByDiaMesEAno(@Param("valueMes") String mes, @Param("valueDia") int dia, @Param("valueAno") long ano, @Param("usuario") User usuarioLogado);

    @Query("SELECT r FROM RelatorioDiario r WHERE r.user.id = :id")
    List<RelatorioDiario> findAllByUser(@Param("id") long id);
}
