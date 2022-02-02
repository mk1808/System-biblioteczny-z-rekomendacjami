import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/restService.dart';

class BorrowingsService extends ChangeNotifier {
  final String _url = "api/borrowings";
  final List<Borrowing> _borrowings = [];
  UnmodifiableListView<Borrowing> get items =>
      UnmodifiableListView(_borrowings);

  Borrowing? _borrowing;
  Borrowing? get item => (_borrowing);

  void create( List<Borrowing> borrowings, void Function(dynamic) onSuccess) {
    RestService rest = RestService();
    List<Map<dynamic, dynamic>> borrowingsJson = borrowings.map((borrowing) => borrowing.toJson()).toList();
    rest.post<dynamic>(
        path: _url, body: borrowingsJson, onSuccess: onSuccess, onError: (j)=>print(j));
  }

  void createBorrowing(List<BookCopy> bookCopies, AppUser user, void Function(dynamic) onSuccess){
    List<Borrowing> borrowings = bookCopies.map((copy) => 
    Borrowing(null, copy, copy.id, user, user.id, null, null, null, null)).toList();
    create(borrowings, onSuccess);

  }

  void onSuccessCreate(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getByUserId(String userId) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/user/${userId}", onSuccess: onSuccessGetByUserId);
  }

  void onSuccessGetByUserId(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getPastByUserId(String userId) {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/user/${userId}/past",
        onSuccess: onSuccessGetPastByUserId);
  }

  void onSuccessGetPastByUserId(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void returnBorrowing(List<BookCopy> bookCopies, void Function(dynamic) onSuccess) {
    RestService rest = RestService();
    String stringIds = "";
    bookCopies.forEach((bookCopy) => stringIds += "&bookCopiesIds=${bookCopy.id}");
    stringIds = stringIds.substring(0, stringIds.length - 1);
    print(stringIds);
    rest.patch<dynamic>(
        path: "${_url}/return?${stringIds}",
        onSuccess: onSuccess,
        onError: (j)=>print(j));
  }

  void onSuccessReturnBorrowing(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }
}
