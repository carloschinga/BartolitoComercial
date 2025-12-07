package com.bartolito.rrhh.service;

import com.bartolito.rrhh.repository.RRHHRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
public class RRHHService {

    private final RRHHRepository repository;

    public RRHHService(RRHHRepository repository) {
        this.repository = repository;
    }

    public List<Map<String, Object>> obtenerdTurnos() {
        return repository.obtenerTurnos();
    }

    public Map<String, Object> seleccionarTurnoPorCodigo(int codiTurn) {
        List<Map<String, Object>> results = repository.seleccionarTurnoPorCodigo(codiTurn);

        if (results.isEmpty()) {
            throw new NoSuchElementException("El turno con código " + codiTurn + " no fue encontrado.");
            //return Collections.singletonMap("mensaje", "No existe turno con código " + codiTurn);
        }
        return results.get(0);
    }

    public int agregarTurno(String nombTurn, String ingrTurn, String saldTurn) {
        return repository.agregarTurno(nombTurn, ingrTurn, saldTurn);
    }

    public void editarTurno(int codiTurn, String nombTurn, String ingrTurn, String saldTurn) {

        int filasAfectadas = repository.editarTurno(codiTurn, nombTurn, ingrTurn, saldTurn);

        if (filasAfectadas == 0) {
            throw new NoSuchElementException("No se pudo editar el turno. El código " + codiTurn + " no existe.");
        }
    }

    /*====================== SECCIÓN DE LA GESTIÓN HORARIO ======================*/

    public List<Map<String, Object>> obtenerHorario() {
        return repository.obtenerHorario();
    }

    public Map<String, Object> seleccionarHorarioPorCodigo(int codiHora) {
        List<Map<String, Object>> results = repository.seleccionarHorarioPorCodigo(codiHora);

        if (results.isEmpty()) {
            throw new NoSuchElementException("El horario con código " + codiHora + " no fue encontrado.");
            //return Collections.singletonMap("mensaje", "No existe turno con código " + codiTurn);
        }
        return results.get(0);
    }

    public int agregarHorario(String nombHora, Integer usuacrea) {
        return repository.agregarHorario(nombHora, usuacrea);
    }

    public void editarHorario(Integer codiHora, String nombHora, Integer usuamodi) {

        int filasAfectadas = repository.editarHorario(codiHora, nombHora, usuamodi);

        if (filasAfectadas == 0) {
            throw new NoSuchElementException("No se pudo editar el horario. El código " + codiHora + " no existe.");
        }
    }

    /*====================== SECCIÓN DE GESTION DETALLE HORARIO ======================*/

    public List<Map<String, Object>> obtenerHorarioDetalle() {
        return repository.obtenerHorarioDetalle();
    }

    public List<Map<String, Object>> seleccionarHorarioDetallePorCodigo(Integer codiHora) {

        List<Map<String, Object>> results = repository.seleccionarHorarioDetallePorCodigo(codiHora);

//        if (results.isEmpty()) {
//            throw new NoSuchElementException("No se encontraron detalles para el horario con código " + codiHora);
//        }

        return results;
    }

    public int agregarHorarioDetalle(Integer codiHora, Integer codiDia, Integer codiTurn, Integer anulTurn, Integer usuacrea) {
        return repository.agregarHorarioDetalle(codiHora, codiDia, codiTurn, anulTurn, usuacrea);
    }

    public void editarHorarioDetalle(Integer codiHoraDeta, Integer codiHora, Integer codiDia, Integer codiTurn, Integer anulTurn, Integer usuamodi) {

        int filasAfectadas = repository.editarHorarioDetalle(codiHoraDeta, codiHora, codiDia, codiTurn, anulTurn, usuamodi);

        if (filasAfectadas == 0) {
            throw new NoSuchElementException("No se pudo editar el detalle. El código " + codiHoraDeta + " no existe.");
        }
    }

    public void eliminarHorarioDetalle(Integer codiHoraDeta, Integer codiHora, Integer codiDia, Integer codiTurn, Integer usuamodi) {

        int filasAfectadas = repository.editarHorarioDetalle(codiHoraDeta, codiHora, codiDia, codiTurn, 1, usuamodi);

        if (filasAfectadas == 0) {
            throw new NoSuchElementException("No se pudo eliminar (anular) el detalle. El código " + codiHoraDeta + " no existe.");
        }
    }

    /*====================== SECCIÓN DE GESTION DETALLE SEMANAL ======================*/

    public List<Map<String, Object>> listarHorarioSemanal() {
        return repository.listarHorarioSemanal();
    }
}