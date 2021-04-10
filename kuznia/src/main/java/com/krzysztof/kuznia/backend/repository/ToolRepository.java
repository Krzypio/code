package com.krzysztof.kuznia.backend.repository;

import com.krzysztof.kuznia.backend.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    @Query("select t from Tool t "+
            "where lower(t.name) like lower(concat('%', :searchTerm, '%'))")
    List<Tool> search(@Param("searchTerm") String filterText);
}
