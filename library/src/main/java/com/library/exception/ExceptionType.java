package com.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionType {
	ENTITY_NOT_FOUND("not.found"),
	DUPLICATE_ENTITY("duplicate"),
	ENTITY_EXCEPTION("entity");

	String value;

}
