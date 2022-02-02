import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:reader/consts.dart';
import 'package:reader/loginPage.dart';
import 'package:reader/menu.dart';
import 'package:reader/myAppBar.dart';

class MainPage extends StatefulWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: const MyAppBar(), body: getBody(), drawer: Menu());
  }

  getBody() {
    return Center(
        child: Column(
      children: [
        Container(
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 20.0),
          height: 180.0,
          child: Text(helloInApp, textAlign: TextAlign.center, style: bigFont),
        ),
        Container(
          padding: const EdgeInsets.fromLTRB(20.0, 20.0, 20.0, 10.0),
          height: 300.0,
          child: Text(loginInfo, textAlign: TextAlign.center, style: midFont),
        ),
        Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 20.0),
          child: SizedBox(width: 300, height: 50, child: getButton()),
        )
      ],
    ));
  }

  getButton() {
    return ElevatedButton.icon(
        label: Text('Zaloguj się'),
        icon: Icon(
          FontAwesomeIcons.clipboardList,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const LoginPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  navigateTo(context, component) {
    Navigator.push(
        context, MaterialPageRoute<void>(builder: (context) => component));
  }
}
