import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

getColor() {
  Color? c1 = Color(0xffdeb887);
  if (c1 != null) {
    return c1;
  }
  return Colors.orangeAccent;
}

getSecondColor() {
  Color? c1 = Color(0xff6f6657);
  if (c1 != null) {
    return c1;
  }
  return Colors.brown;
}

getCancelColor() {
  Color? c1 = Color(0xdd525452);
  if (c1 != null) {
    return c1;
  }
  return Colors.grey;
}

TextStyle headerFont = const TextStyle(fontSize: 100.0);
TextStyle bigFont = const TextStyle(fontSize: 40.0);
TextStyle midFont =
    const TextStyle(fontSize: 27.0, fontWeight: FontWeight.w600);
TextStyle midSmall =
    const TextStyle(fontSize: 24.0, fontWeight: FontWeight.w500);    
TextStyle smallerFont =
    const TextStyle(fontSize: 23.0, fontWeight: FontWeight.w400);
TextStyle smallestFont =
    const TextStyle(fontSize: 18.0, fontWeight: FontWeight.w400);
double iconSize = 40;
Color mainColor = getColor();
Color secondColor = getSecondColor();
Color cancelColor = getCancelColor();
Decoration myBooksInfoContainer = BoxDecoration(
  border: BorderDirectional(
    bottom: BorderSide(
      color: Colors.black, 
      width: 2.0)
      ),
      
      );

String helloInApp = "Witamy w aplikacji dla bibliotekarza!";
String loginInfo =
    "Aby móc wypożyczać książki i odbierać zwroty, zaloguj się na swoje konto.";
String readerApp="Aplikacja bibliotekarza";
String bookInfo="Liczba aktualnie wypożyczonych książek: ";
String bookInfo2="Możesz wypożyczyć jeszcze:";
String infoActions="Wybierz odpowiednią akcję i zeskanuj kody QR";
String borrowAndReturn="Wypożyczenia i zwroty";
