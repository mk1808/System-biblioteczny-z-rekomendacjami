import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/restService.dart';

class UsersService extends ChangeNotifier {
  final String _url = "/api/users";
  final String _urlAuth = "/api/auth";

  final List<AppUser> _users = [];

  UnmodifiableListView<AppUser> get items => UnmodifiableListView(_users);

  void login(Login login) {
    RestService rest = RestService();
    rest.post<dynamic>(
        path: _urlAuth + "/authenticate",
        body: login.toJson(),
        onSuccess: onSuccessLogin);
  }

  void onSuccessLogin(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void register(AppUser user) {
    RestService rest = RestService();
    rest.post<dynamic>(
        path: _urlAuth + "/register",
        body: user.toJson(),
        onSuccess: onSuccessRegister);
  }

  void onSuccessRegister(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void whoAmI() {
    RestService rest = RestService();
    rest.get<dynamic>(
      path: _urlAuth + "/whoami", 
      onSuccess: onSuccessWhoAmI);
  }

  void onSuccessWhoAmI(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void logout() {
    RestService rest = RestService();
    rest.post<dynamic>(
        path: _urlAuth + "/logout", 
        body: "", 
        onSuccess: onSuccessRegister);
  }

  void onSuccessLogout(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

  void getById(String id) {
    RestService rest = RestService();
    rest.get<dynamic>(
      path: _url + "/" + id, 
      onSuccess: onSuccessGetById);
  }

  void onSuccessGetById(dynamic object) {
    print(object);
    Response response = Response.fromJson(object);
    notifyListeners();
  }

}
