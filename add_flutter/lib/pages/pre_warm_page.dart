import 'package:add_flutter/constants.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:url_launcher/url_launcher.dart';

class PreWarmPage extends StatefulWidget {
  const PreWarmPage({Key key}) : super(key: key);

  @override
  _PreWarmPageState createState() => _PreWarmPageState();
}

class _PreWarmPageState extends State<PreWarmPage> {
  static const platform = const MethodChannel(CHANNEL);
  String _string;

  @override
  void initState() {
    super.initState();
    _string = '';
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('PreWarmPage: $_string'),
          ElevatedButton(
            onPressed: _getString,
            child: Text('Get'),
          ),
        ],
      ),
    );
  }

  Future<void> _getString() async {
    String result = await platform.invokeMethod(CHANNEL_METHOD_GET_URL);
    setState(() {
      _string = result;
    });
    Future.delayed(
      Duration(seconds: 1),
      () async {
        var canLaunchUrl = await canLaunch(result);
        if (canLaunchUrl) {
          launch(result);
        }
      },
    );
  }
}
