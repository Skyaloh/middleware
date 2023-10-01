package com.credable.scoringmiddleware.service;

import com.credable.scoringmiddleware.domain.SystemConfig;
import com.credable.scoringmiddleware.repository.SystemConfigRepository;
import com.credable.scoringmiddleware.service.dto.SystemConfigDTO;
import com.credable.scoringmiddleware.service.mapper.SystemConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SystemConfigService {

    private final Logger log = LoggerFactory.getLogger(SystemConfigService.class);

    private final SystemConfigRepository systemConfigRepository;

    private final SystemConfigMapper systemConfigMapper;

    private final SystemConfigQueryService systemConfigQueryService;

    public SystemConfigService(SystemConfigRepository systemConfigRepository, SystemConfigMapper systemConfigMapper, SystemConfigQueryService systemConfigQueryService) {
        this.systemConfigRepository = systemConfigRepository;
        this.systemConfigMapper = systemConfigMapper;
        this.systemConfigQueryService = systemConfigQueryService;
    }

    /**
     * Save a systemConfig.
     *
     * @param systemConfigDTO the entity to save
     * @return the persisted entity
     */
    public SystemConfigDTO save(SystemConfigDTO systemConfigDTO) {
        log.debug("Request to save SystemConfig : {}", systemConfigDTO);

        SystemConfig systemConfig = systemConfigMapper.toEntity(systemConfigDTO);
        systemConfig = systemConfigRepository.save(systemConfig);
        return systemConfigMapper.toDto(systemConfig);
    }

    /**
     * Get all the systemConfigs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SystemConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SystemConfigs");
        return systemConfigRepository.findAll(pageable)
                .map(systemConfigMapper::toDto);
    }


    /**
     * Get one systemConfig by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SystemConfigDTO> findOne(Long id) {
        log.debug("Request to get SystemConfig : {}", id);
        return systemConfigRepository.findById(id)
                .map(systemConfigMapper::toDto);

    }

    /**
     * Delete the systemConfig by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SystemConfig : {}", id);
        systemConfigRepository.deleteById(id);
    }
}


