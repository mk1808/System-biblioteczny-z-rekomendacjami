import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:librarian/services/api/api.dart';

class RecommendationsService extends ChangeNotifier {
  final List<Recommendation> _recommendations = [];

  UnmodifiableListView<Recommendation> get items => UnmodifiableListView(_recommendations);
}
