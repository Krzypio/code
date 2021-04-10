package com.krzysztof.kuznia.backend.service;

import com.krzysztof.kuznia.backend.entity.Tool;
import com.krzysztof.kuznia.backend.repository.ToolRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ToolService {
    private static final Logger LOGGER = Logger.getLogger(ToolService.class.getName());
    private ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    public List<Tool> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return toolRepository.findAll();
        } else {
            return toolRepository.search(filterText);
        }//else
    }

    public long count() {
        return toolRepository.count();
    }

    public void delete(Tool tool) {
        toolRepository.delete(tool);
    }

    public void save(Tool tool) {
        if (tool == null) {
            LOGGER.log(Level.SEVERE, "Tool is null. Are you sure you have connected your form to the application?");
            return;
        }
        toolRepository.save(tool);
    }

    @PostConstruct
    public void populateTestData() {
        if (toolRepository.count() == 0) {
            toolRepository.saveAll(
                    Stream.of("LKs-5-1-1", "LKy-6-1-3", "LKt-5-4-27")
                            .map(Tool::new)
                            .collect(Collectors.toList()));
        }
    }
}