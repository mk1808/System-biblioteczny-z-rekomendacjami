import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/restService.dart';

class ReservationsService extends ChangeNotifier {
  final String _url = "api/reservations";

  final List<Reservation> _reservation = [];

  UnmodifiableListView<Reservation> get items =>
      UnmodifiableListView(_reservation);

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
}
