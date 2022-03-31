package com.library.service;

import com.library.dto.AppUserDto;
import com.library.model.AppUser;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public interface UserService extends RepositoryService<AppUser>  {
	
	AppUser getByMail(String mail);
	
	AppUser save(AppUserDto user);
	AppUser save1(AppUser user);

	AppUser updateByAdmin(AppUser user);

	void deactivate(UUID id);

	void delete(UUID id);
}
