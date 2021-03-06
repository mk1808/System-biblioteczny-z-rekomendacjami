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
TextStyle midSmall =
    const TextStyle(fontSize: 24.0, fontWeight: FontWeight.w500);    
TextStyle smallerFont =
    const TextStyle(fontSize: 23.0, fontWeight: FontWeight.w400);
double iconSize = 40;
Color mainColor = getColor();

Decoration myBooksInfoContainer = BoxDecoration(
  border: BorderDirectional(
    bottom: BorderSide(
      color: Colors.black, 
      width: 2.0)
      ),
      
      );

String helloInApp = "Witamy w aplikacji dla czytelnika!";
String loginInfo =
    "Aby wyświetlić kartę biblioteczną i móc wypożyczać książki, zaloguj się na swoje konto.";
String readerApp="Aplikacja czytelnika";
String bookInfo="Liczba aktualnie wypożyczonych książek: ";
String bookInfo2="Możesz wypożyczyć jeszcze:";