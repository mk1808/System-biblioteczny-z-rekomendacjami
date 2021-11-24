package com.library.service;

import java.util.List;

public interface RepositoryService<T> extends Service {
	T get(Long id);
	T create(T entity);
	T update(T entity);
	

}
