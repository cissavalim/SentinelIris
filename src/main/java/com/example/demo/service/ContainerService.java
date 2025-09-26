package com.example.demo.service;

import com.example.demo.model.entity.ContainerEntity;
import com.example.demo.model.dto.ContainerDTO;
import com.example.demo.model.entity.SensorReadingEntity;
import com.example.demo.repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository containerRepository;

    public List<SensorReadingEntity> findSensorReadingByContainerId(String containerID) {
        return containerRepository.findSensorReadingsByContainerId(containerID);
    }

    public Map<String, Object> findStatusByContainerId(String containerID) {
        return containerRepository.findStatusByContainerId(containerID);
    }

    public void saveContainer(ContainerDTO containerDTO) {
        ContainerEntity containerEntity = new ContainerEntity(
            containerDTO.containerID(),
            containerDTO.description(),
            containerDTO.location()
        );
        containerRepository.saveContainer(containerEntity);
    }
}
