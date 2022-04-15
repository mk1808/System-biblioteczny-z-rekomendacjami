package com.library.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.config.AuthUser;
import com.library.dto.AppUserDto;
import com.library.model.AppUser;
import com.library.model.Role;
import com.library.model.Address;
import com.library.repository.AppUserRepository;
import com.library.repository.AddressRepository;
import com.library.service.RoleService;
import com.library.service.UserService;

@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private RoleService roleService; 
    
	@Autowired
	private AppUserRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserServiceImpl() {}
	public UserServiceImpl(RoleService roleService, AppUserRepository repository,
			BCryptPasswordEncoder bcryptEncoder, AddressRepository addressRepository) {
		super();
		this.roleService = roleService;
		this.repository = repository;
		this.bcryptEncoder = bcryptEncoder;
		this.addressRepository = addressRepository;
	}

	@Override
	public AppUser get(UUID id) {
		//Address address = addressRepository.getById(null);
		return repository.findById(id).orElse(null);
	}

	@Override
	public AppUser create(AppUser entity) {
		return repository.save(entity);
	}

	@Override
	public AppUser update(AppUser entity) {
		return repository.save(entity);
	}
	
	@Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public AppUser getByMail(String mail) {
		return repository.findByMail(mail);
	}
	


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = repository.findByMail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new AuthUser(user.getMail(), user.getPassword(), getAuthority(user), user.getId());
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
		repository.findAll().iterator().forEachRemaining(list::add);
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
        return repository.save(newUser);
    }
    
    public AppUser save1(AppUser user) {
    	
	Address address = addressRepository.save(user.getAddress());
		Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        user.setAddress(address);
        return repository.save(user);
    }
    
	@Override
	public AppUser updateByAdmin(AppUser entity) {
		return repository.save(entity);
	}
	@Override
	public void deactivate(UUID id) {
		AppUser user = get(id);
		user.setDezactivationDate(LocalDateTime.now());
		update(user);
		
		
	}

}
