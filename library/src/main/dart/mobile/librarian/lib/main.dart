import 'package:flutter/material.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/mainPage.dart';
import 'package:librarian/services/booksService.dart';
import 'package:librarian/services/borrowingsService.dart';
import 'package:librarian/services/reservationsService.dart';
import 'package:librarian/services/usersService.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (context) => BooksService()),
          ChangeNotifierProvider(create: (context) => UsersService()),
          ChangeNotifierProvider(create: (context) => BorrowingsService()),
          ChangeNotifierProvider(create: (context) => ReservationsService())
        ],
        child: MaterialApp(
          title: 'Reader App',
          theme: getTheme(),
          home: const MainPage(),
        ));
  }

  getTheme() {
    return ThemeData(
        primaryColor: Colors.white,
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            primary: mainColor,
          ),
        ),
        textButtonTheme: TextButtonThemeData(
            style: TextButton.styleFrom(primary: mainColor)),
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.white,
          foregroundColor: Colors.black,
        ),
        inputDecorationTheme: InputDecorationTheme(
          focusedBorder: UnderlineInputBorder(
              borderSide: BorderSide(color: mainColor, width: 2.0)),
        ));
  }
}
