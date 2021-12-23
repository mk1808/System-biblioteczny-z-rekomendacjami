package com.library.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.dto.AppUserDto;
import com.library.model.AppUser;
import com.library.model.Role;
import com.library.repository.AppUserRepository;
import com.library.service.RoleService;
import com.library.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private RoleService roleService; 
    
	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserServiceImpl() {}
	public UserServiceImpl(RoleService roleService, AppUserRepository userRepository,
			BCryptPasswordEncoder bcryptEncoder) {
		super();
		this.roleService = roleService;
		this.userRepository = userRepository;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public AppUser get(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public AppUser create(AppUser entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser update(AppUser entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public AppUser getByMail(String mail) {
		return userRepository.findByMail(mail);
	}
	


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByMail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), getAuthority(user));
	}

	private Set getAuthority(AppUser user) {
        Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	public List findAll() {
		List list = new ArrayList();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	


    public AppUser save(AppUserDto user) {
		AppUser newUser = AppUser.builder()
				.mail(user.getMail())
				.name(user.getName())
				.password(bcryptEncoder.encode(user.getPassword()))
				.build();
		Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        newUser.setRoles(roleSet);
        return userRepository.save(newUser);
    }

}
