import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:librarian/borrowPage.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/mainPage.dart';
import 'package:librarian/returnPage.dart';

class Menu extends StatefulWidget {
  const Menu({Key? key}) : super(key: key);

  @override
  _MenuState createState() => _MenuState();
}

class _MenuState extends State<Menu> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            decoration: BoxDecoration(
              color: mainColor,
            ),
            child: const Text(
              'Aplikacja dla czytelnika',
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
              ),
            ),
          ),
          ListTile(
              leading: const Icon(Icons.star_rounded),
              title: const Text('Strona główna'),
              onTap: () => navigateTo(context, const MainPage())),
          ListTile(
              leading: const Icon(Icons.star_rounded),
              title: const Text('Wypożycz'),
              onTap: () => navigateTo(context, const BorrowPage())),
          ListTile(
              leading: const Icon(Icons.star_rounded),
              title: const Text('Odbierz zwrot'),
              onTap: () => navigateTo(context, const ReturnPage())),
        ],
      ),
    );
  }

  navigateTo(context, widget) {
    Navigator.pop(context);
    Navigator.pushReplacement(
      context,
      MaterialPageRoute<void>(
        builder: (context) {
          return widget;
        },
      ),
    );
  }
}
