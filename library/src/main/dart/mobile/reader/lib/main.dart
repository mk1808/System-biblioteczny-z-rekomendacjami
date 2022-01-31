import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:reader/consts.dart';
import 'package:reader/mainPage.dart';
import 'package:reader/services/booksService.dart';
import 'package:reader/services/borrowingsService.dart';
import 'package:reader/services/reservationsService.dart';
import 'package:reader/services/usersService.dart';

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
      textButtonTheme:
          TextButtonThemeData(style: TextButton.styleFrom(primary: mainColor)),
      appBarTheme: const AppBarTheme(
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
      ),
    );
  }
}
