package com.example.edutech.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.edutech.Model.Curso;

@Component
public class ControlStockRepository {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso controlStock(int id) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso cursoExistente = optionalCurso.get();
            int cuposActuales = cursoExistente.getCupos();
    
            if (cuposActuales > 0) {
                cursoExistente.setCupos(cuposActuales - 1);
                return cursoRepository.save(cursoExistente);
            } else {
                throw new IllegalStateException("No hay cupos disponibles.");
            }
        } else {
            return null;
        }
    }

    public List<Curso> mostrarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(int id) {
        return cursoRepository.findById(id);
    }
}
