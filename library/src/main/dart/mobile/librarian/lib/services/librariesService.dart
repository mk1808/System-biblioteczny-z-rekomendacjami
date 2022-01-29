import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';

class LibrariesService extends ChangeNotifier {
  final List<Library> _libraries = [];

  UnmodifiableListView<Library> get items => UnmodifiableListView(_libraries);
}
