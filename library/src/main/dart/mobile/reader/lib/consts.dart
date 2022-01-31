import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

getColor() {
  Color? c1 = Color(0xffdeb887);
  if (c1 != null) {
    return c1;
  }
  return Colors.orangeAccent;
}

TextStyle headerFont = const TextStyle(fontSize: 100.0);
TextStyle bigFont = const TextStyle(fontSize: 40.0);
TextStyle midFont =
    const TextStyle(fontSize: 27.0, fontWeight: FontWeight.w600);
TextStyle smallerFont =
    const TextStyle(fontSize: 20.0, fontWeight: FontWeight.w300);
double iconSize = 40;
Color mainColor = getColor();

String helloInApp = "Witamy w aplikacji dla czytelnika!";
String loginInfo =
    "Aby wyświetlić kartę biblioteczną i móc wypożyczać książki, zaloguj się na swoje konto.";
String readerApp="Aplikacja czytelnika";