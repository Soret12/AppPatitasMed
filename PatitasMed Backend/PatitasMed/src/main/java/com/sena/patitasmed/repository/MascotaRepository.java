package com.sena.patitasmed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.patitasmed.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
	
	List<Mascota> findByUsuarioIdUsuario(Integer idUsuario);

}

