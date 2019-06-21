package com.project.raluca.repository;

import com.project.raluca.model.BookableTime;
import org.springframework.data.repository.CrudRepository;

public interface BookableTimeRepository extends CrudRepository<BookableTime, Integer> {
}
