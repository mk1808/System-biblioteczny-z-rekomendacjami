import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/restService.dart';

class LibrariesService extends ChangeNotifier {
  final String _url = "api/libraries";
  final List<Library> _libraries = [];

  UnmodifiableListView<Library> get items => UnmodifiableListView(_libraries);

  void getContact() {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/contact", onSuccess: onSuccessGetContact);
  }

  void onSuccessGetContact(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getTerms() {
    RestService rest = RestService();
    rest.get<dynamic>(
        path: "${_url}/terms", onSuccess: onSuccessGetTerms);
  }

  void onSuccessGetTerms(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }
}