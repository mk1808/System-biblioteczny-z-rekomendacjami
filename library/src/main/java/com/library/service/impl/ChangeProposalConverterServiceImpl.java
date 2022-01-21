package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.ChangeProposalDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.ChangeProposal;
import com.library.service.BookConverterService;
import com.library.service.BookCopyConverterService;
import com.library.service.ChangeProposalConverterService;
import com.library.service.UserConverterService;

@Service
public class ChangeProposalConverterServiceImpl implements ChangeProposalConverterService{

	private final BookConverterService bookConverter;
	private final UserConverterService userConverter;
		
		@Autowired
		public ChangeProposalConverterServiceImpl(BookConverterService bookConverter, 
				UserConverterService userConverter) {
			this.bookConverter = bookConverter;
			this.userConverter = userConverter;
		}
	
	@Override
	public ChangeProposalDto toDto(ChangeProposal model) {
		ChangeProposalDto dto = ChangeProposalDto.builder()
				.id(model.getId())
				.value(model.getValue())
				.type(model.getType())
				.status(model.getStatus())
				.book(bookConverter.toDto(model.getBook()))
				.bookId(model.getBook().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				.build();
		return dto;
	}

	@Override
	public ChangeProposal toModel(ChangeProposalDto dto) {
		ChangeProposal model = ChangeProposal.builder()
				.id(dto.getId())
				.value(dto.getValue())
				.type(dto.getType())
				.status(dto.getStatus())		
				.book(Book.builder().id(dto.getBookId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.build();
		return model;
	}

	@Override
	public List<ChangeProposalDto> toDtoList(List<ChangeProposal> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<ChangeProposal> toModelList(List<ChangeProposalDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<ChangeProposalDto> toDtoPage(Page<ChangeProposal> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ChangeProposal> toModelPage(Page<ChangeProposalDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
