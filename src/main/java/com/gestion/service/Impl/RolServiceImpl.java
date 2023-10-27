
package com.gestion.service.Impl;
import com.gestion.model.Rol;
import com.gestion.repository.RolRepository;
import com.gestion.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }


    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol añadirRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public Rol editarRol(Rol rol, Long id) {
        // Implementa la lógica de edición del rol
        return rolRepository.save(rol);
    }
}
