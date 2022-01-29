import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:reader/services/api/api.dart';

class ReservationsService extends ChangeNotifier {
  final List<Reservation> _reservation = [];

  UnmodifiableListView<Reservation> get items => UnmodifiableListView(_reservation);
}
