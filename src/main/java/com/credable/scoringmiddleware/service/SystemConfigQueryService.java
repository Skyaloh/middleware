package com.credable.scoringmiddleware.service;

import com.credable.scoringmiddleware.domain.SystemConfig;
import com.credable.scoringmiddleware.domain.SystemConfig_;
import com.credable.scoringmiddleware.repository.SystemConfigRepository;
import com.credable.scoringmiddleware.service.criteria.SystemConfigCriteria;
import com.credable.scoringmiddleware.service.dto.SystemConfigDTO;
import com.credable.scoringmiddleware.service.helper.QueryService;
import com.credable.scoringmiddleware.service.mapper.SystemConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SystemConfigQueryService extends QueryService<SystemConfig> {
    private final Logger log = LoggerFactory.getLogger(SystemConfigQueryService.class.getName());

    private final SystemConfigMapper systemConfigMapper;

    private final SystemConfigRepository systemConfigRepository;

    public SystemConfigQueryService(SystemConfigMapper systemConfigMapper, SystemConfigRepository systemConfigRepository) {
        this.systemConfigMapper = systemConfigMapper;
        this.systemConfigRepository = systemConfigRepository;
    }


    /**
     * Return a {@link List} of {@link SystemConfigDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SystemConfigDTO> findByCriteria(SystemConfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SystemConfig> specification = createSpecification(criteria);
        return systemConfigMapper.toDto(systemConfigRepository.findAll(specification));
    }

    /**
     * Return a {@link Optional} of {@link SystemConfigDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Optional<SystemConfigDTO> findOneCriteria(SystemConfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SystemConfig> specification = createSpecification(criteria);
        return systemConfigRepository.findOne(specification).map(systemConfigMapper::toDto);
    }

    /**
     * Return a {@link Optional} of {@link SystemConfigDTO} which matches the criteria from the database
     * @param code The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Optional<SystemConfigDTO> findOneByCode(String code) {
        log.debug("find by code : {}", code);
        return systemConfigRepository.findByCode(code).map(systemConfigMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link SystemConfigDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SystemConfigDTO> findByCriteria(SystemConfigCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SystemConfig> specification = createSpecification(criteria);
        return systemConfigRepository.findAll(specification, page)
                .map(systemConfigMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SystemConfigCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SystemConfig> specification = createSpecification(criteria);
        return systemConfigRepository.count(specification);
    }

    /**
     * Function to convert SystemConfigCriteria to a {@link Specification}
     */
    private Specification<SystemConfig> createSpecification(SystemConfigCriteria criteria) {
        Specification<SystemConfig> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SystemConfig_.id));
            }

        }
        return specification;
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
        return systemConfigRepository.findById(id).map(systemConfigMapper::toDto);
    }
}
