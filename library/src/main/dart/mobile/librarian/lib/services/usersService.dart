import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';

class UsersService extends ChangeNotifier {
  final List<AppUser> _users = [];

  UnmodifiableListView<AppUser> get items => UnmodifiableListView(_users);
}
