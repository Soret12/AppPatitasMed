package com.sena.patitasmed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.patitasmed.entity.CitaMedica;
import com.sena.patitasmed.entity.Mascota;
import com.sena.patitasmed.service.CitaMedicaService;
import com.sena.patitasmed.service.MascotaService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CitaMedicaController {

    @Autowired
    private MascotaService mascotaService;
    
    @Autowired
    private CitaMedicaService citaService;
    
    @GetMapping("/usuario/{idUsuario}")
    public List<CitaMedica> CitasPorUsuario(@PathVariable Integer idUsuario) {

        return citaService.findByUsuario(idUsuario);

    }
    
    @GetMapping("/mascota/{idMascota}")
    public List<CitaMedica> citasPorMascota(@PathVariable Integer idMascota) {

        return citaService.findByMascota(idMascota);

    }
    
    @GetMapping("/{id}")
    public Optional<CitaMedica> buscarPorId(@PathVariable Integer id) {
        return citaService.findById(id);
    }

    @GetMapping("/listar")
    public List<CitaMedica> listarCitas() {
        return citaService.findAll();
    }

    @PostMapping
    public CitaMedica registrarCita(@RequestBody CitaMedica cm) {

        Mascota mascotaBD = mascotaService.findById(cm.getMascota().getIdMascota()).get();

        cm.setMascota(mascotaBD);
        
        cm.setEstado("Programada");

        return citaService.save(cm);
    }

    @PutMapping("/actualizar/{id}")
    public CitaMedica actualizarCita(@RequestBody CitaMedica cm,@PathVariable Integer id) {

        CitaMedica citaBD = citaService.findById(id).get();

        citaBD.setNombreClinica(cm.getNombreClinica());
        citaBD.setNombreVeterinario(cm.getNombreVeterinario());
        citaBD.setFechaCita(cm.getFechaCita());
        citaBD.setObservaciones(cm.getObservaciones());
        citaBD.setEstado(cm.getEstado());

        Mascota mascotaBD = mascotaService.findById(cm.getMascota().getIdMascota()).get();

        citaBD.setMascota(mascotaBD);
        
        if(!citaBD.getEstado().equals("Programada")) {
            throw new RuntimeException(
                "La cita ya no puede modificarse"
            );
        }

        return citaService.save(citaBD);
    }

    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Integer id) {
    	
        citaService.deleteById(id);
        
    }
}
