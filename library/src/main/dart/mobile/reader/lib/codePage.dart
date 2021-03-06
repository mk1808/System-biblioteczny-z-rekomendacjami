import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:reader/consts.dart';
import 'package:reader/menu.dart';
import 'package:reader/myAppBar.dart';

class CodePage extends StatefulWidget {
  const CodePage({Key? key}) : super(key: key);

  @override
  _CodePageState createState() => _CodePageState();
}

class _CodePageState extends State<CodePage> {
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
        child: Text('Konto', textAlign: TextAlign.center, style: midFont),
      ),
      getInfo(),
      getQR()
    ]));
  }

  getInfo() {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Mail: jNowak@test.com.pl', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Imię: Jan', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Nazwisko: Nowak',
                textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child: Text('Numer konta: 3503',
                textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }

  getQR() {
    return Container(
        padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
        child: QrImage(
          data: "a5edd677-14a9-449b-ab3f-76742762c5f1",
          version: QrVersions.auto,
          size: 400.0,
        )
        );
  }
}
