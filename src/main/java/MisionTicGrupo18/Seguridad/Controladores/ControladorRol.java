package MisionTicGrupo18.Seguridad.Controladores;


import MisionTicGrupo18.Seguridad.Modelos.Rol;
import MisionTicGrupo18.Seguridad.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class ControladorRol {

    @Autowired
    private RepositorioRol miRepostirioRol;

    @GetMapping("")
    public List<Rol> index(){
        return this.miRepostirioRol.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody Rol infoRol){
        return this.miRepostirioRol.save(infoRol);
    }

    @GetMapping("{id}")
    public Rol show(@PathVariable String id){
        Rol rolActual = this.miRepostirioRol
                .findById(id)
                .orElse(null);
        return rolActual;
    }

    @PutMapping("{id}")
    public Rol update(@PathVariable String id, @RequestBody Rol infoRol){
        Rol rolActual = this.miRepostirioRol.findById(id).orElse(null);
        if (rolActual != null){
            rolActual.setDescripcion(infoRol.getDescripcion());
            rolActual.setNombre(infoRol.getNombre());
            return this.miRepostirioRol.save(rolActual);
        }else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol rolActual = this.miRepostirioRol
                .findById(id).orElse(null);
        if (rolActual != null){
            this.miRepostirioRol.delete(rolActual);
        }
    }
}
