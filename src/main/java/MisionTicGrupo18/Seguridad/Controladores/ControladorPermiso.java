package MisionTicGrupo18.Seguridad.Controladores;


import MisionTicGrupo18.Seguridad.Modelos.Permiso;
import MisionTicGrupo18.Seguridad.Modelos.PermisoRoles;
import MisionTicGrupo18.Seguridad.Modelos.Rol;
import MisionTicGrupo18.Seguridad.Repositorios.RepositorioPermiso;
import MisionTicGrupo18.Seguridad.Repositorios.RepositorioPermisoRoles;
import MisionTicGrupo18.Seguridad.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class ControladorPermiso {

    @Autowired
    private RepositorioPermiso miRepostirioPermiso;

    @GetMapping("")
    public List<Permiso> index(){
        return this.miRepostirioPermiso.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permiso create(@RequestBody Permiso infoPermiso){
        return this.miRepostirioPermiso.save(infoPermiso);
    }

    @GetMapping("{id}")
    public Permiso show(@PathVariable String id){
        Permiso permisoActual = this.miRepostirioPermiso
                .findById(id)
                .orElse(null);
        return permisoActual;
    }

    @PutMapping("{id}")
    public Permiso update(@PathVariable String id, @RequestBody Permiso infoPermiso){
        Permiso permisoActual = this.miRepostirioPermiso.findById(id).orElse(null);
        if (permisoActual != null){
            permisoActual.setUrl(infoPermiso.getUrl());
            permisoActual.setMetodo(infoPermiso.getMetodo());
            return this.miRepostirioPermiso.save(permisoActual);
        }else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permiso permisoActual = this.miRepostirioPermiso
                .findById(id).orElse(null);
        if (permisoActual != null){
            this.miRepostirioPermiso.delete(permisoActual);
        }
    }

}
