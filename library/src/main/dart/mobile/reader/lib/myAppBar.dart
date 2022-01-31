import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:reader/consts.dart';

class MyAppBar extends StatelessWidget with PreferredSizeWidget {
  const MyAppBar({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AppBar(
        title: Row(
          children: [
            //  MyLogo(),
            Text(readerApp)
          ],
        ),
        backgroundColor: mainColor,
        foregroundColor: Colors.white);
  }

  @override
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}
