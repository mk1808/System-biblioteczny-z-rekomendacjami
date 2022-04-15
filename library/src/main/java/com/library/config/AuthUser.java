package com.library.config;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class AuthUser extends User {

	public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<SimpleGrantedAuthority> authorities,
			UUID id) {

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}

	public AuthUser(String mail, String password, Collection<SimpleGrantedAuthority> authorities, UUID id) {
		super(mail, password, authorities);
		this.id = id;
	}

	private final UUID id;

}
