package com.project.raluca.repository;

import com.project.raluca.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>{
    public Authorities findByUsername(String username);
}