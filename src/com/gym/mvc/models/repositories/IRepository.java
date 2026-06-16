package com.gym.mvc.models.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
	void create (T entity);
	List<T> readAll();
	Optional<T> findById(int id);
	void update(T entity);
	void delete(int id);
	
	

}
