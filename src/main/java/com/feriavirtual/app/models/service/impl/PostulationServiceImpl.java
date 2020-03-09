package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Postulation;
import com.feriavirtual.app.models.repository.IPostulationRepository;
import com.feriavirtual.app.models.service.IPostulationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PostulationServiceImpl implements IPostulationService {

    private final IPostulationRepository postulationRepository;

    public PostulationServiceImpl(IPostulationRepository postulationRepository) {
        this.postulationRepository = postulationRepository;
    }


    @Override
    public List<Postulation> getAll() {
        return postulationRepository.findAll();
    }

    @Override
    public List<Postulation> geTendersNotAssigned(Long id) {
        return null;
    }

    @Override
    public List<Postulation> getPostulationByTenderId(Long id) {
        return postulationRepository.findByTenderId(id);
    }

    @Override
    public Postulation save(Postulation postulation) {
        return postulationRepository.save(postulation);
    }

    @Override
    public Postulation findById(Long id) {
        return postulationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public void delete(Long id) {
      postulationRepository.delete(findById(id));
    }
}
