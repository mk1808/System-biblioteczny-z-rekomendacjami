import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/restService.dart';

class BooksService extends ChangeNotifier {
  final String _url = "api/books";

  final List<Book> books = [];
  final List<BookCopy> bookCopies = [];
  final List<BookAvailability> booksAvailAbility = [];
  AppUser? user;
  UserAvailability? userAvailability;

  void getById(String id) {
    RestService rest = RestService();
    rest.get<dynamic>(path: "${_url}/${id}", onSuccess: onSuccessGetById);
  }

  void onSuccessGetById(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getFiltered(int pageNo, int size) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/filtered?pageNo=${pageNo}&&size=${size}",
        onSuccess: onSuccessGetFiltered);
  }

  void onSuccessGetFiltered(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getBookCopiesByBookId(String id) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/${id}/bookCopies",
        onSuccess: onSuccessGetBookCopiesByBookId);
  }

  void onSuccessGetBookCopiesByBookId(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getAvailabilityByBookId(String id) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/${id}/availability",
        onSuccess: onSuccessGetBookCopiesByBookId);
  }

  void onSuccessGetAvailabilityByBookId(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void canBorrowBookCopy(String bookCopyId, String userId) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/bookCopies/${bookCopyId}/users/${userId}/canBorrow",
        onSuccess: onSuccessCanBorrowBookCopy,
        onError: (e) => print(e));
  }

  void onSuccessCanBorrowBookCopy(dynamic object) {
    Response response = Response.fromJson(object);
    CanBorrowBook canBorrowBook = CanBorrowBook.fromJson(response.content);
    Book? book = canBorrowBook.book;
    BookAvailability? bookAvailAbility = canBorrowBook.bookAvailabilityDto;
    BookCopy? bookCopy = canBorrowBook.bookCopy;
    if (book != null && bookAvailAbility != null && bookCopy != null) {
      books.add(book);
      booksAvailAbility.add(bookAvailAbility);
      bookCopies.add(bookCopy);
    }
    if (canBorrowBook.user != null) {
      user = canBorrowBook.user;
      userAvailability = canBorrowBook.userAvailabilityDto;
    }
    notifyListeners();
  }

  void clean() {
    books.clear();
    bookCopies.clear();
    booksAvailAbility.clear();
    user = null;
    userAvailability = null;
  }

  void getBookByBookCopy(String bookCopyId) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/bookCopies/${bookCopyId}",
        onSuccess: onSuccessGetBookByBookCopy);
  }

  void onSuccessGetBookByBookCopy(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }
}
