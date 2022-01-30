import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:reader/services/api/api.dart';
import 'package:reader/services/restService.dart';

class BorrowingsService extends ChangeNotifier {
  final String _url = "api/borrowings";
  final List<Borrowing> _borrowings = [];
  UnmodifiableListView<Borrowing> get items =>
      UnmodifiableListView(_borrowings);

  Borrowing? _borrowing;
  Borrowing? get item => (_borrowing);

  void create(Borrowing borrowing) {
    RestService rest = RestService();
    rest.post<dynamic>(
        path: _url, body: borrowing.toJson(), onSuccess: onSuccessCreate);
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

  void returnBorrowing(List<String> bookCopiesIds) {
    RestService rest = RestService();
    String stringIds = "";
    bookCopiesIds.forEach((id) => stringIds += "&bookCopiesIds=${id}");
    stringIds = stringIds.substring(0, stringIds.length - 1);
    rest.get<dynamic>(
        path: "${_url}/return?${stringIds}",
        onSuccess: onSuccessReturnBorrowing);
  }

  void onSuccessReturnBorrowing(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }
}
