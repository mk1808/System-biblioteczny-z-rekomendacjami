import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:reader/services/api/api.dart';
import 'package:reader/services/restService.dart';

class BooksService extends ChangeNotifier {
  final String _url = "/api/books";

  final List<Book> _books = [];

  UnmodifiableListView<Book> get items => UnmodifiableListView(_books);

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
        onSuccess: onSuccessCanBorrowBookCopy);
  }

  void onSuccessCanBorrowBookCopy(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
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
