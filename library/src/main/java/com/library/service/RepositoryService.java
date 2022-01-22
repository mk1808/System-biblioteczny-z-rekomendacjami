package com.library.service;

import java.util.UUID;

public interface RepositoryService<T> extends Service {
	T get(UUID id);
	T create(T entity);
	T update(T entity);
	

}
