import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/loginPage.dart';
import 'package:librarian/menu.dart';
import 'package:librarian/myAppBar.dart';
import 'package:librarian/returnSuccessPage.dart';

class ReturnPage extends StatefulWidget {
  const ReturnPage({ Key? key }) : super(key: key);

  @override
  _ReturnPageState createState() => _ReturnPageState();
}

class _ReturnPageState extends State<ReturnPage> {
  @override
  Widget build(BuildContext context) {
       return Scaffold(appBar: const MyAppBar(), body: getBody(), drawer: Menu());
  }

  getBody() {
    return Center(
        child: ListView(
      children: [
        Container(
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          height: 90.0,
          child: Text('Odbieranie książek',
              textAlign: TextAlign.center, style: midFont),
        ),
        Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 40.0),
          child: SizedBox(width: 300, height: 50, child: getButtonScan()),
        ),
        Container(
          decoration: myBooksInfoContainer,
          margin: const EdgeInsets.only(bottom: 30.0),
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          child: Text('Książki do zwrotu', textAlign: TextAlign.center, style: midSmall),
        ),
        getBooks(),
        Container(
          decoration: myBooksInfoContainer,
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          margin: const EdgeInsets.only(bottom: 30.0),
          child:
              Text('Użytkownik', textAlign: TextAlign.center, style: midSmall),
        ),
        getUser(),
        Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 20.0),
          child: SizedBox(width: 300, height: 50, child: getButtonConfirm()),
        ),
      ],
    ));
  }

  getButtonScan() {
    return ElevatedButton.icon(
        label: Text('Zeskanuj kod'),
        icon: Icon(
          FontAwesomeIcons.qrcode,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const LoginPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  getButtonConfirm() {
    return ElevatedButton.icon(
        label: Text('Potwierdź odebranie zwrotu'),
        icon: Icon(
          FontAwesomeIcons.clipboardList,
          color: Colors.white,
          size: 30.0,
        ),
        onPressed: () => navigateTo(context, const ReturnSuccessPage()),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  navigateTo(context, component) {
    Navigator.push(
        context, MaterialPageRoute<void>(builder: (context) => component));
  }

  getSingleBook() {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Tytuł:', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Autor:', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Status:',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }

  getBooks() {
    return Column(
        children: List.generate(2, (index) {
      return getSingleBook();
    }));
  }

  getUser() {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Imię i nazwisko:',
                textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Aktualnie wypożyczone książki:',
                textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Przetrzymane książki:',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }
}