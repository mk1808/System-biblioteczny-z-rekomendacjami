import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:reader/services/api/api.dart';

class BooksService extends ChangeNotifier {
  final List<Book> _books = [];

  UnmodifiableListView<Book> get items => UnmodifiableListView(_books);
}
