import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:reader/consts.dart';
import 'package:reader/loginPage.dart';
import 'package:reader/main.dart';
import 'package:reader/menu.dart';
import 'package:reader/myAppBar.dart';
import 'package:reader/services/api/api.dart';

class MyBooksInfoPage extends StatefulWidget {
  const MyBooksInfoPage({Key? key}) : super(key: key);

  @override
  _MyBooksInfoPageState createState() => _MyBooksInfoPageState();
}

class _MyBooksInfoPageState extends State<MyBooksInfoPage> {
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
          child:
              Text('Moje książki', textAlign: TextAlign.center, style: midFont),
        ),
        Container(
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 50.0),
          //    height: 80.0,
          // color: Colors.yellow,
          child:
              Row(mainAxisAlignment: MainAxisAlignment.spaceEvenly, children: [
            Icon(
              FontAwesomeIcons.info,
              color: mainColor,
              size: 50.0,
            ),
            Container(
              //   padding: const EdgeInsets.fromLTRB(20.0, 20.0, 20.0, 10.0),
              //   height: 300.0,
              width: 260.0,
              child: Text(bookInfo+" 2 \n\n" + bookInfo2+" 3",
                  textAlign: TextAlign.left, style: smallerFont.copyWith(fontSize: 19)),
            ),
          ]),
        ),
        Container(
          decoration: myBooksInfoContainer,
          margin: const EdgeInsets.only(bottom: 30.0),
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          child:
              Text('Wypożyczone', textAlign: TextAlign.center, style: midSmall),
        ),
        getBorrowed(),
        Container(
          decoration: myBooksInfoContainer,
          padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 10.0),
          margin: const EdgeInsets.only(bottom: 30.0),
          child: Text('Zarezerwowane',
              textAlign: TextAlign.center, style: midSmall),
        ),
        getReserved(),
      /*  Container(
          padding: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 20.0),
          child: SizedBox(width: 300, height: 50, child: getButton()),
        )*/
      ],
    ));
  }

  getButton() {
    return ElevatedButton.icon(
        label: Text('Rozpocznij mapowanie'),
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

  getSingleBorrowed(String title, String authors, String date) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Tytuł: ${title}', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Autor: ${authors}', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Data wypożyczenia: $date',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }

  getBorrowed() {
    return Column(
        children: [
      getSingleBorrowed("Nieodnaleziona", "Remigiusz Mróz", "01.02.2022"),
      getSingleBorrowed("Hyperion", "Dan Simmons", "01.02.2022"),
    ]);
  }


  getSingleReserved(String title, String authors, String date, String no) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Tytuł: $title', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Autor: $authors', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Data rezerwacji: $date',
                textAlign: TextAlign.left, style: smallerFont)),
                Align(
            alignment: Alignment.centerLeft,
            child: Text('Numer w kolejce: $no',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }

  getReserved() {
    return Column(
        children: [
          getSingleReserved("Behawiorysta", "Remigiusz Mróz", "02.02.2022", "1" ),
          getSingleReserved("Pułapki myślenia", "Daniel Kahneman", "02.02.2022", "3" ),
        ]
    );
  }

  getBorrowedContainer() {
    return Container(
      padding: const EdgeInsets.fromLTRB(12.0, 40.0, 12.0, 50.0),
      child: Row(mainAxisAlignment: MainAxisAlignment.spaceEvenly, children: [
        Icon(
          FontAwesomeIcons.info,
          color: mainColor,
          size: 50.0,
        ),
        Container(
          width: 260.0,
          child: Text(bookInfo + bookInfo2,
              textAlign: TextAlign.left, style: smallerFont),
        ),
      ]),
    );
  }
}
