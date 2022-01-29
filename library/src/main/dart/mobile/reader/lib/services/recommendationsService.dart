import 'dart:collection';

import 'package:flutter/cupertino.dart';
import 'package:reader/services/api/api.dart';

class RecommendationsService extends ChangeNotifier {
  final List<Recommendation> _recommendations = [];

  UnmodifiableListView<Recommendation> get items => UnmodifiableListView(_recommendations);
}
