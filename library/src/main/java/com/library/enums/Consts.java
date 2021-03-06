package com.library.enums;

public final class Consts {
	public static final String BOOK_CTRL = "com.library.api.controller.BooksController";
	public static final String USER_CTRL= "com.library.api.controller.UsersController";
	public static final String BORROWING_CTRL= "com.library.api.controller.BorrowingsController";
	
	public static final String RESPONSE= "org.springframework.http.ResponseEntity<com.library.response.Response<";
	public static final String LIST_ELEM_DTO= "com.library.dto.UserListElementDto";
	public static final String OPINION_DTO= "com.library.dto.OpinionDto";
	public static final String BORROWING_DTO= "com.library.dto.BorrowingDto";
	public static final String LIST_BORROWING_DTO= "java.util.List<"+BORROWING_DTO+">";
	public static final String CHANGE_PROPOSAL_DTO= "com.library.dto.ChangeProposalDto";
	public static final String LIST_CHANGE_PROPOSAL_DTO= "java.util.List<"+CHANGE_PROPOSAL_DTO+">";

}
