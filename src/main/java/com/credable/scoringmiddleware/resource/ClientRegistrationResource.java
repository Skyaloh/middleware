package com.credable.scoringmiddleware.resource;

import com.credable.scoringmiddleware.service.clientregistration.ClientRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientRegistrationResource {

    private final Logger log = LoggerFactory.getLogger(ClientRegistrationResource.class);

    private final ClientRegistrationService clientRegistrationService;

    public ClientRegistrationResource(ClientRegistrationService clientRegistrationService) {
        this.clientRegistrationService = clientRegistrationService;
    }

    @GetMapping("/middleware/access-token")
    public ResponseEntity<String> registerClient(){
        log.debug("REST request to fetch ");
        String accessToken = clientRegistrationService.registerClient();
        return ResponseEntity.ok().body(accessToken);
    }
}
