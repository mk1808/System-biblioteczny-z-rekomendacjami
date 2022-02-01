import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:librarian/borrowPage.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/loginPage.dart';
import 'package:librarian/menu.dart';
import 'package:librarian/myAppBar.dart';
import 'package:librarian/returnPage.dart';

class AfterLoginPage extends StatefulWidget {
  const AfterLoginPage({Key? key}) : super(key: key);

  @override
  _AfterLoginPageState createState() => _AfterLoginPageState();
}

class _AfterLoginPageState extends State<AfterLoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: const MyAppBar(), body: getBody(), drawer: Menu());
  }
//infoActions="Wybierz odpowiednią akcję i zeskanuj kody QR";
  getBody() {
    return Center(
        child: Column(
      children: [
        Container(
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          height: 90.0,
          child:
              Text(borrowAndReturn, textAlign: TextAlign.center, style: midFont),
        ),
        Container(
              padding: const EdgeInsets.fromLTRB(12.0, 20.0, 12.0, 80.0),
              width: 260.0,
              child: Text(infoActions,
                  textAlign: TextAlign.left, style: smallerFont),
            ),
         
        Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 20.0),
          child: SizedBox(width: 300, height: 50, child: getButton()),
        ),
         Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 20.0),
          child: SizedBox(width: 300, height: 50, child: getButton2()),
        ),
      ],
    ));
  }

  getButton() {
    return ElevatedButton.icon(
        label: Text('Wypożyczenie'),
        icon: Icon(
          FontAwesomeIcons.clipboardList,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const BorrowPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }


  getButton2() {
    return ElevatedButton.icon(
        label: Text('Zwrot'),
        icon: Icon(
          FontAwesomeIcons.clipboardList,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const ReturnPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  navigateTo(context, component) {
    Navigator.push(
        context, MaterialPageRoute<void>(builder: (context) => component));
  }
}
