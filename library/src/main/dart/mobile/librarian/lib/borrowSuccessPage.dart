import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/loginPage.dart';
import 'package:librarian/menu.dart';
import 'package:librarian/myAppBar.dart';

class BorrowSuccessPage extends StatefulWidget {
  const BorrowSuccessPage({Key? key}) : super(key: key);

  @override
  _BorrowSuccessPageState createState() => _BorrowSuccessPageState();
}

class _BorrowSuccessPageState extends State<BorrowSuccessPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: const MyAppBar(), body: getBody(), drawer: Menu());
  }

  getBody() {
    return Center(
        child: Column(children: [
      Container(
        padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 50.0),
        height: 80.0,
        child: Icon(
          FontAwesomeIcons.checkCircle,
          color: Colors.lightGreen,
          size: 50.0,
        ),
      ),
      Container(
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 20.0),
          height: 160.0,
          child: Text('Książki zostały wypożyczone.',
              textAlign: TextAlign.center, style: midFont)),
      Container(
          decoration: myBooksInfoContainer,
          margin: const EdgeInsets.only(bottom: 30.0),
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          child: Text('Podsumowanie', textAlign: TextAlign.center, style: midSmall),
        ),
        getInfo(),
        Container(
          padding: const EdgeInsets.fromLTRB(20.0, 80.0, 20.0, 10.0),
          child: SizedBox(width: 300, height: 50, child: getButton()),
        ),
     
    ]));
  }

   getInfo() {
    return Container(
      padding: const EdgeInsets.fromLTRB(20.0, 30.0, 20.0, 50.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Książki wypożyczone dzisiaj:', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Zakładana data zwrotu:', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Książki wypożyczone łącznie:',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }

    getButton() {
    return ElevatedButton.icon(
        label: Text('Powrót'),
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
