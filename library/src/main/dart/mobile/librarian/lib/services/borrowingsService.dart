import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';

class BorrowingsService extends ChangeNotifier {
  final List<Borrowing> _borrowings = [];

  UnmodifiableListView<Borrowing> get items => UnmodifiableListView(_borrowings);
}
