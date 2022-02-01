import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:reader/consts.dart';
import 'package:reader/menu.dart';
import 'package:reader/myAppBar.dart';
import 'package:reader/myBooksInfoPage.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: const MyAppBar(), body: getBody(), drawer: Menu());
  }

  getBody() {
    return Center(
        child: Column(children: [
      Container(
        padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
        height: 160.0,
        child: Text('Logowanie', textAlign: TextAlign.center, style: midFont),
      ),
      Container(
          padding: const EdgeInsets.fromLTRB(30.0, 10.0, 30.0, 20.0),
          child: getInputLogin()),
      Container(
          padding: const EdgeInsets.fromLTRB(30.0, 10.0, 30.0, 20.0),
          child: getInputPass()),
      Container(
        padding: const EdgeInsets.symmetric(vertical: 50.0, horizontal: 20.0),
        child: SizedBox(width: 300, height: 50, child: getButton()),
      )
    ]));
  }

  getInputLogin() {
    return TextFormField(
        decoration: const InputDecoration(
          hintText: 'Login',
        ),
        validator: (String? value) {
          if (value == null || value.isEmpty) {
            return 'Please enter some text';
          }
          return null;
        });
  }

  getInputPass() {
    return TextFormField(
      decoration: const InputDecoration(
        hintText: 'Hasło',
      ),
      validator: (String? value) {
        if (value == null || value.isEmpty) {
          return 'Please enter some text';
        }
        return null;
      },
    );
  }

  getButton() {
    return ElevatedButton.icon(
        label: Text('Zaloguj'),
        icon: Icon(
          FontAwesomeIcons.clipboardList,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const MyBooksInfoPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  navigateTo(context, component) {
    Navigator.push(
        context, MaterialPageRoute<void>(builder: (context) => component));
  }
}
