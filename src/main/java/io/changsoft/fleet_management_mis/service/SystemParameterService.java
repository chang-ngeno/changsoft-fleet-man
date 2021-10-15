package io.changsoft.fleet_management_mis.service;

import io.changsoft.fleet_management_mis.domain.SystemParameter;
import io.changsoft.fleet_management_mis.model.SystemParameterDTO;
import io.changsoft.fleet_management_mis.repos.SystemParameterRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SystemParameterService {

    private final SystemParameterRepository systemParameterRepository;

    public SystemParameterService(final SystemParameterRepository systemParameterRepository) {
        this.systemParameterRepository = systemParameterRepository;
    }

    public List<SystemParameterDTO> findAll() {
        return systemParameterRepository.findAll()
                .stream()
                .map(systemParameter -> mapToDTO(systemParameter, new SystemParameterDTO()))
                .collect(Collectors.toList());
    }

    public SystemParameterDTO get(final Long id) {
        return systemParameterRepository.findById(id)
                .map(systemParameter -> mapToDTO(systemParameter, new SystemParameterDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SystemParameterDTO systemParameterDTO) {
        final SystemParameter systemParameter = new SystemParameter();
        mapToEntity(systemParameterDTO, systemParameter);
        return systemParameterRepository.save(systemParameter).getId();
    }

    public void update(final Long id, final SystemParameterDTO systemParameterDTO) {
        final SystemParameter systemParameter = systemParameterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(systemParameterDTO, systemParameter);
        systemParameterRepository.save(systemParameter);
    }

    public void delete(final Long id) {
        systemParameterRepository.deleteById(id);
    }

    private SystemParameterDTO mapToDTO(final SystemParameter systemParameter,
            final SystemParameterDTO systemParameterDTO) {
        systemParameterDTO.setId(systemParameter.getId());
        systemParameterDTO.setParamCode(systemParameter.getParamCode());
        systemParameterDTO.setParamName(systemParameter.getParamName());
        systemParameterDTO.setDescription(systemParameter.getDescription());
        systemParameterDTO.setParent(systemParameter.getParent() == null ? null : systemParameter.getParent().getId());
        return systemParameterDTO;
    }

    private SystemParameter mapToEntity(final SystemParameterDTO systemParameterDTO,
            final SystemParameter systemParameter) {
        systemParameter.setParamCode(systemParameterDTO.getParamCode());
        systemParameter.setParamName(systemParameterDTO.getParamName());
        systemParameter.setDescription(systemParameterDTO.getDescription());
        if (systemParameterDTO.getParent() != null && (systemParameter.getParent() == null || !systemParameter.getParent().getId().equals(systemParameterDTO.getParent()))) {
            final SystemParameter parent = systemParameterRepository.findById(systemParameterDTO.getParent())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "parent not found"));
            systemParameter.setParent(parent);
        }
        return systemParameter;
    }

}
