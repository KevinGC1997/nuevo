package MisionTicGrupo18.Seguridad.Repositorios;

import MisionTicGrupo18.Seguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol, String> {
}
