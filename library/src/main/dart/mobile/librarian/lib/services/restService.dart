import 'dart:io';

import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

class RestService {
  static const host = "http://192.168.33.6:8080/";

  dynamic get<T>(
      {required String path,
      void Function(T map)? onSuccess,
      void Function(int statusCode)? onError}) async {
    var url = Uri.parse(host + path);
    var response = await http.get(url);
    onResponse(response, onSuccess, onError);
  }

  dynamic post<T>(
      {required String path,
      required T body,
      void Function(T map)? onSuccess,
      void Function(int statusCode)? onError}) async {
    var url = Uri.parse(host + path);
    var response = await http.post(
      url,
      body: convert.jsonEncode(body),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
    );
    onResponse(response, onSuccess, onError);
  }

  dynamic put<T>(
      {required String path,
      required T body,
      void Function(T map)? onSuccess,
      void Function(int statusCode)? onError}) async {
    var url = Uri.parse(host + path);
    var response = await http.put(
      url,
      body: convert.jsonEncode(body),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
    );
    onResponse(response, onSuccess, onError);
  }

  dynamic patch<T>(
      {required String path,
      void Function(T map)? onSuccess,
      void Function(int statusCode)? onError}) async {
    var url = Uri.parse(host + path);
    var response = await http.patch(url);
    onResponse(response, onSuccess, onError);
  }

  dynamic delete<T>(
      {required String path,
      void Function(T map)? onSuccess,
      void Function(int statusCode)? onError}) async {
    var url = Uri.parse(host + path);
    var response = await http.delete(url);
    onResponse(response, onSuccess, onError);
  }

  void onResponse<T>(http.Response response, void Function(T map)? onSuccess,
      void Function(int statusCode)? onError) {
    if (response.statusCode >= 200 && response.statusCode < 300) {
      var jsonResponse =
          convert.jsonDecode(convert.utf8.decode(response.bodyBytes)) as T;
      onSuccess!(jsonResponse);
    } else {
      onError!(response.statusCode);
    }
  }
}
