package co.com.usta.renttyj.models.services.client;

import co.com.usta.renttyj.entity.Client;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    List<Client> findAll();

    void delete(Long id);

    Client findById(Long id) throws Exception;
}
