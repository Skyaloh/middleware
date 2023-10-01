package com.credable.scoringmiddleware.service.clientregistration;

import com.credable.scoringmiddleware.service.SystemConfigQueryService;
import com.credable.scoringmiddleware.service.SystemConfigService;
import com.credable.scoringmiddleware.service.clientregistration.request.ClientRegistrationRequest;
import com.credable.scoringmiddleware.service.clientregistration.request.ClientRegistrationResponse;
import com.credable.scoringmiddleware.service.dto.SystemConfigDTO;
import com.credable.scoringmiddleware.util.RestTemplateHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientRegistrationService {


    @Value("${spring.application.name}")
    private String serviceName;

    private String middleWareEndpoint;

    private final SystemConfigQueryService systemConfigQueryService;

    private static final String CLIENT_REGISTRATION_API = "/ap1/v1/client/createClient";
    private final RestTemplateHelper restTemplateHelper;

    private static final String MIDDLEWARE_TRANSACTIONS_API = "/api/middleware/transactions";

    private static final String KEY_SCORE_ENGINE_ACCESS_TOKEN = "SCORE_ENGINE_ACCESS_TOKEN";
    private static final String KEY_MIDDLEWARE_ENDPOINT = "MIDDLEWARE_ENDPOINT";
    private static final String KEY_MAIN_SCORE_ENGINE_ENDPOINT = "MAIN_SCORE_ENGINE_ENDPOINT";
    private static final String KEY_MIDDLEWARE_PASSWORD = "MIDDLEWARE_PASSWORD";
    private static final String KEY_MIDDLEWARE_USERNAME = "MIDDLEWARE_USERNAME";
    private final SystemConfigService systemConfigService;

    public ClientRegistrationService(SystemConfigQueryService systemConfigQueryService, RestTemplateHelper restTemplateHelper, SystemConfigService systemConfigService) {
        this.systemConfigQueryService = systemConfigQueryService;
        this.restTemplateHelper = restTemplateHelper;
        this.systemConfigService = systemConfigService;
    }

    public String registerClient(){
        Optional<SystemConfigDTO> middlewareEndpointOpt = systemConfigQueryService.findOneByCode(KEY_SCORE_ENGINE_ACCESS_TOKEN);
        return middlewareEndpointOpt.map(SystemConfigDTO::getValue).orElseGet(this::serviceCall);
    }
    private String serviceCall(){
        String mainScoreEngineEndpoint = getSystemConfigDTO(KEY_MAIN_SCORE_ENGINE_ENDPOINT).map(SystemConfigDTO::getValue).orElse(null);
        String middlewareUsername = getSystemConfigDTO(KEY_MIDDLEWARE_USERNAME).map(SystemConfigDTO::getValue).orElse(null);
        String middlewarePassword = getSystemConfigDTO(KEY_MIDDLEWARE_PASSWORD).map(SystemConfigDTO::getValue).orElse(null);
        Optional<SystemConfigDTO> middlewareEndpointOpt = getSystemConfigDTO(KEY_MIDDLEWARE_ENDPOINT);

        middlewareEndpointOpt.ifPresent(systemConfigDTO ->
                middleWareEndpoint = systemConfigDTO.getValue()
        );
        ClientRegistrationRequest request = new ClientRegistrationRequest();
        request.setUrl(middleWareEndpoint+MIDDLEWARE_TRANSACTIONS_API);
        request.setName(serviceName);
        request.setUsername(middlewareUsername);
        request.setPassword(middlewarePassword);

        ClientRegistrationResponse clientRegistrationResponse;
        try {
            clientRegistrationResponse = restTemplateHelper.postForEntity(ClientRegistrationResponse.class, "Registering client",
                    mainScoreEngineEndpoint+CLIENT_REGISTRATION_API, request);

            if(clientRegistrationResponse != null && clientRegistrationResponse.getToken() != null){
                saveSystemConfig(KEY_SCORE_ENGINE_ACCESS_TOKEN,clientRegistrationResponse.getToken(),"Score Engine Access Token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to register client: "+e.getMessage());

        }

        return clientRegistrationResponse.getToken();
    }

    private Optional<SystemConfigDTO> getSystemConfigDTO(String code) {
        return systemConfigQueryService.findOneByCode(code);
    }

    private void saveSystemConfig(String key, String value, String name){
        SystemConfigDTO systemConfigDTO = new SystemConfigDTO();
        systemConfigDTO.setCode(key);
        systemConfigDTO.setValue(value);
        systemConfigDTO.setName(name);
        systemConfigService.save(systemConfigDTO);
    }
}
