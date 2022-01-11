package com.library.service;

import com.library.dto.AppUserDto;
import com.library.model.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends RepositoryService<AppUser>  {
	void delete(long id);
	
	AppUser getByMail(String mail);
	AppUser save(AppUserDto user);

	AppUser updateByAdmin(AppUser user);

	void deactivate(Long id);
}
