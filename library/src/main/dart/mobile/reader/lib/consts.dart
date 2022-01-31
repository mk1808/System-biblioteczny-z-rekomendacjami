import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

getColor(){
  Color? c1 = Color(0xffdeb887);
  if (c1 != null) {
    return c1;
  }
  return Colors.orangeAccent;
}

Color mainColor = getColor();
