package io.github.rukins.authorizationserver.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import io.github.rukins.authorizationserver.model.Client;
import io.github.rukins.authorizationserver.repository.ClientRepository;
import io.github.rukins.authorizationserver.security.exception.ClientNotFoundException;
import io.github.rukins.authorizationserver.security.utils.ClientUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = ClientUtils.createClientFromRegisteredClient(registeredClient);

        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        Optional<Client> client = clientRepository.findById(Long.parseLong(id));

        return client
                .map(ClientUtils::createRegisteredClientFromClient)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<Client> client = clientRepository.findByClientId(clientId);

        return client
                .map(ClientUtils::createRegisteredClientFromClient)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }
}
