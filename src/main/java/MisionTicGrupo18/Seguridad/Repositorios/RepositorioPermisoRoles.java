package MisionTicGrupo18.Seguridad.Repositorios;

import MisionTicGrupo18.Seguridad.Modelos.PermisoRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioPermisoRoles extends MongoRepository<PermisoRoles, String> {
    @Query("{'rol.$id': ObjectId(?0),'permiso.$id':ObjectId(?1)}")
    PermisoRoles getPermisoRol(String id_rol, String id_permiso);
}
