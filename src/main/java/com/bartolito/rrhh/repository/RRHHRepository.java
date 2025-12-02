package com.bartolito.rrhh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RRHHRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String string;


    /*====================== SECCIÓN DE LA GESTIÓN TURNOS ======================*/

    public List<Map<String, Object>> obtenerTurnos() {
        String sql = "EXEC sp_bart_rrhh_asis_turno_listar";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> seleccionarTurnoPorCodigo(int codiTurn) {
        String sql = "EXEC sp_bart_rrhh_asis_turno_seleccionar ?";

        return jdbcTemplate.queryForList(sql, codiTurn);
    }

    public int agregarTurno(String nombTurn, String ingrTurn, String saldTurn) {
        String sql = "EXEC sp_bart_rrhh_asis_turno_agregar ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, nombTurn, ingrTurn, saldTurn);
    }

    public int editarTurno(int codiTurn, String nombTurn, String ingrTurn, String saldTurn) {
        String sql = "EXEC sp_bart_rrhh_asis_turno_editar ?, ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, codiTurn, nombTurn, ingrTurn, saldTurn);
    }

    /*====================== SECCIÓN DE LA GESTIÓN HORARIO ======================*/

    public List<Map<String, Object>> obtenerHorario() {
        String sql = "EXEC sp_bart_rrhh_asis_horario_listar";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> seleccionarHorarioPorCodigo(int codiHora) {
        String sql = "EXEC sp_bart_rrhh_asis_horario_seleccionar ?";

        return jdbcTemplate.queryForList(sql, codiHora);
    }

    public int agregarHorario(String nombHora, Integer usuacrea) {
        String sql = "EXEC sp_bart_rrhh_asis_horario_agregar ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, nombHora, usuacrea);
    }

    public int editarHorario(Integer codiHora, String nombHora, Integer usuamodi) {
        String sql = "EXEC sp_bart_rrhh_asis_horario_editar ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, codiHora, nombHora, usuamodi);
    }

    /*====================== SECCIÓN DE GESTION DETALLE HORARIO ======================*/

    public List<Map<String, Object>> obtenerHorarioDetalle() {
        String sql = "SELECT * FROM view_bart_rrhh_asis_horario_detalle";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> seleccionarHorarioDetallePorCodigo(Integer codiHora) {
        String sql = "SELECT * FROM view_bart_rrhh_asis_horario_detalle WHERE codiHora = ?";

        return jdbcTemplate.queryForList(sql, codiHora);
    }

    public int agregarHorarioDetalle(Integer codiHora, Integer codiDia, Integer codiTurn, Integer anulTurn, Integer usuacrea) {
        String sql = "EXEC sp_bart_rrhh_horario_detalle_agregar ?, ?, ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, codiHora, codiDia, codiTurn, anulTurn, usuacrea);
    }

    public int editarHorarioDetalle(Integer codiHoraDeta, Integer codiHora, Integer codiDia, Integer codiTurn, Integer anulTurn, Integer usuamodi) {
        String sql = "EXEC sp_bart_rrhh_horario_detalle_editar ?, ?, ?, ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, codiHoraDeta, codiHora, codiDia, codiTurn, anulTurn, usuamodi);
    }


    /*====================== SECCIÓN DE GESTION DETALLE SEMANAL ======================*/

    public List<Map<String, Object>> listarHorarioSemanal() {
        String sql = "SELECT * FROM view_bart_rrhh_asis_horario_semanal";

        return jdbcTemplate.queryForList(sql);
    }
}
