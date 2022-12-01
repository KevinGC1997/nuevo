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
@RequestMapping("/permisos-roles")
public class ControladorPemisoRoles {

    @Autowired
    private RepositorioPermisoRoles miRepostirioPermisoRoles;
    @Autowired
    private RepositorioPermiso miRepostirioPermiso;
    @Autowired
    private RepositorioRol miRepostirioRol;


    @GetMapping("")
    public List<PermisoRoles> index(){
        return this.miRepostirioPermisoRoles.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisoRoles create(@PathVariable String id_rol, @PathVariable String id_permiso){
        PermisoRoles nuevo = new PermisoRoles();
        Rol elRol = this.miRepostirioRol.findById(id_rol).get();
        Permiso elPermiso = this.miRepostirioPermiso.findById(id_permiso).get();
        if(elRol != null && elPermiso != null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.miRepostirioPermisoRoles.save(nuevo);
        }else {
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisoRoles show(@PathVariable String id){
        PermisoRoles permisoRolesActual = this.miRepostirioPermisoRoles
                .findById(id)
                .orElse(null);
        return permisoRolesActual;
    }
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisoRoles update(@PathVariable String id, @PathVariable String id_rol, @PathVariable String id_permiso){
        PermisoRoles permisoRolesActuales = this.miRepostirioPermisoRoles
                .findById(id).orElse(null);
        Rol elRol = this.miRepostirioRol.findById(id_rol).get();
        Permiso elPermiso = this.miRepostirioPermiso.findById(id_permiso).get();
        if(permisoRolesActuales != null && elPermiso != null && elRol != null) {
            permisoRolesActuales.setPermiso(elPermiso);
            permisoRolesActuales.setRol(elRol);
            return this.miRepostirioPermisoRoles.save(permisoRolesActuales);
        }else {
            return null;
        }

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisoRoles permisoRolesActual = this.miRepostirioPermisoRoles.findById(id).orElse(null);
        if(permisoRolesActual != null){
            this.miRepostirioPermisoRoles.delete(permisoRolesActual);

        }
    }

    @GetMapping("validar-permiso/rol/{id_rol}")
    public PermisoRoles getPermiso(@PathVariable String id_rol, @RequestBody Permiso infoPermiso){
        Permiso elPermiso = this.miRepostirioPermiso.getPermiso(infoPermiso.getUrl(), infoPermiso.getMetodo());
        Rol elRol = this.miRepostirioRol.findById(id_rol).orElse(null);
        if(elPermiso != null && elRol != null){
            return this.miRepostirioPermisoRoles.getPermisoRol(elRol.get_id(), elPermiso.get_id());
        }else {
            return null;
        }
    }

}
