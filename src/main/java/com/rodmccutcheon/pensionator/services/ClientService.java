package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.Client;

import java.util.List;

public interface ClientService {

    List<Client> listAllClients();

    Client getClientById(Long id);

    Client saveClient(Client client);

    void deleteClient(Long id);

}
