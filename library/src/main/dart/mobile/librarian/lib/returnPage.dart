import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:librarian/consts.dart';
import 'package:librarian/loginPage.dart';
import 'package:librarian/menu.dart';
import 'package:librarian/myAppBar.dart';
import 'package:librarian/qrCodeScanner.dart';
import 'package:librarian/returnSuccessPage.dart';
import 'package:librarian/services/api/api.dart';
import 'package:librarian/services/booksService.dart';
import 'package:librarian/services/borrowingsService.dart';
import 'package:provider/provider.dart';

class ReturnPage extends StatefulWidget {
  const ReturnPage({Key? key}) : super(key: key);

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
          child: Text('Książki do zwrotu',
              textAlign: TextAlign.center, style: midSmall),
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
        onPressed: () {
          Navigator.of(context).push(MaterialPageRoute(
            builder: (context) => const QRCodeScanner(),
          ));
        },
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
        ));
  }

  getButtonConfirm() {
    return Consumer2<BooksService, BorrowingsService>(
        builder: (context, booksService, borrowingsService, child) {
      return ElevatedButton.icon(
          label: Text('Potwierdź odebranie zwrotu'),
          icon: Icon(
            FontAwesomeIcons.clipboardList,
            color: Colors.white,
            size: 30.0,
          ),
          onPressed: () => pressedButton(booksService, borrowingsService),
          style: ButtonStyle(
            foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
          ));
    });
  }
    pressedButton(
      BooksService booksService, BorrowingsService borrowingsService) {
    //AppUser? user = booksService.user;
    //if (user != null) {
      borrowingsService.returnBorrowing(booksService.bookCopies, (data){
        onSuccess(booksService);
        print(data);}
        );
     
    //}
  }
  onSuccess(BooksService booksService){
    booksService.clean();
    navigateTo(context, const ReturnSuccessPage());
  }

  navigateTo(context, component) {
    Navigator.push(
        context, MaterialPageRoute<void>(builder: (context) => component));
  }

  getSingleBook(Book book, BookAvailability bookAvailability) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
      child: Column(children: [
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Tytuł: ${book.title}', textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text(
                  'Autor: ${book.authors[0].name} ${book.authors[0].surname}',
                   textAlign: TextAlign.left, style: smallerFont)),
        Align(
            alignment: Alignment.centerLeft,
            child:
                Text('Status:oddana w terminie', textAlign: TextAlign.left, style: smallerFont)),
      ]),
    );
  }


  getBooks() {
    return Consumer<BooksService>(builder: (context, booksService, child) {
      return Column(
          children: List.generate(booksService.books.length, (index) {
        return getSingleBook(
            booksService.books[index], booksService.booksAvailAbility[index]);
      }));
    });
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
