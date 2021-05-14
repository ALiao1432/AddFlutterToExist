import 'dart:ui';

import 'package:add_flutter/pages/default_page.dart';
import 'package:add_flutter/pages/my_route_page.dart';
import 'package:add_flutter/pages/pre_warm_page.dart';
import 'package:flutter/material.dart';

void main() {
  print('dart: start!!!');
  print('dart: default route name: ${window.defaultRouteName}');
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Add Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.deepPurple),
      home: Scaffold(
        body: Home(route: window.defaultRouteName),
      ),
    );
  }
}

class Home extends StatelessWidget {
  final String route;

  const Home({@required this.route, Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    print('dart: route: $route');
    switch (route) {
      case '/my_route':
        return MyRoutePage();
      case '/pre_warm_route':
        return PreWarmPage();
      default:
        return DefaultPage();
    }
  }
}
