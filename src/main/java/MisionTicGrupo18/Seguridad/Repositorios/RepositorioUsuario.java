package MisionTicGrupo18.Seguridad.Repositorios;

import MisionTicGrupo18.Seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
    @Query("{'correo': ?0}")
    public Usuario getUserByEmail(String correo);


}
