import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';

class ReservationsService extends ChangeNotifier {
  final List<Reservation> _reservation = [];

  UnmodifiableListView<Reservation> get items => UnmodifiableListView(_reservation);
}
