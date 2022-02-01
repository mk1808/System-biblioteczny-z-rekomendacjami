import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:reader/codePage.dart';
import 'package:reader/consts.dart';
import 'package:reader/mainPage.dart';

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
              title: const Text('Kod QR'),
              onTap: () => navigateTo(context, const CodePage())),
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
