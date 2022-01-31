import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
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
    return Scaffold(
      appBar: const MyAppBar(),
      body: const Center(
        child: Text('Hello World'),
      ),
      drawer: Menu()
    );
  }
}
