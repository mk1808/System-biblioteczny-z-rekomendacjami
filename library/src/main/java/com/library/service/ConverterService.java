package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.dto.BookDto;
import com.library.model.Book;

public interface ConverterService<D, M> extends Service {
	D toDto(M model);
	M toModel(D dto);
	List<D> toDtoList(List<M> models);
	List<M> toModelList(List<D> dtos);
	Page<D> toDtoPage(Page<M> models);
	Page<M> toModelPage(Page<D> dtos);
}
