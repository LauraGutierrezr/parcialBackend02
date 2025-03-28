package eci.edu.cvds.parcial02;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RepositorioPagos extends MongoRepository<Pago, String> {
    List<Pago> findByIdUsuario(String idUsuario);
}