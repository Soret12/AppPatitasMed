package com.sena.patitasmed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.patitasmed.entity.Mascota;
import com.sena.patitasmed.repository.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    MascotaRepository mascotaRepository;
    
    @Override
    public List<Mascota> findByUsuario(Integer idUsuario){
    	// TODO Auto-generated method stub
        return mascotaRepository.findByUsuarioIdUsuario(idUsuario);

    }
    
	@Override
	public Optional<Mascota> findById(Integer id) {
		// TODO Auto-generated method stub
		return mascotaRepository.findById(id);
	}

    @Override
    public List<Mascota> findAll() {
    	// TODO Auto-generated method stub
        return mascotaRepository.findAll();
    }
   

    @Override
    public Mascota save(Mascota m) {
    	// TODO Auto-generated method stub
        return mascotaRepository.save(m);
    }


    @Override
    public void deleteById(Integer id) {
    	// TODO Auto-generated method stub
        mascotaRepository.deleteById(id);
    }

}

