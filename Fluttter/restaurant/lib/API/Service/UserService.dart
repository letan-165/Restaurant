import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:restaurant/API/DTO/Request/LoginRequest.dart';
import 'package:restaurant/API/DTO/Request/UserRequest.dart';

Future<String> login(LoginRequest loginRequest) async {
  final response = await http.post(
    Uri.parse('http://10.0.2.2:8888/api/v1/user_service/auth/login'),
    headers: {
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode({
      'username': loginRequest.username,
      'password': loginRequest.password,
    }),
  );

  if (response.statusCode == 200) {
    final jsonResponse = jsonDecode(response.body) as Map<String, dynamic>;
    if (jsonResponse['code'] == 1000) {
      return jsonResponse['result'] as String;
    }
  }
  return "";
}

Future<bool> signUp(UserRequest userRequest) async {
  final response = await http.post(
    Uri.parse("http://10.0.2.2:8888/api/v1/user_service/user/public"),
    headers: {
      'Content-Type': 'application/json; charset=UTF-8',
      HttpHeaders.authorizationHeader: "Bearer eyJhbGciOiJIUzUxMiJ9"
          ".eyJpc3MiOiJsZXRhbi5jb20iLCJzdWIiOiI3OTk3ZmQzMC1k"
          "ZmJmLTQxMTAtYjMwNi1jZWU4MmZiNzkxMjUiLCJleHAiOjE3MzYyN"
          "TQxNTAsImlhdCI6MTczNjI1MDU1MCwianRpIjoiMWQ1ODU3ZGMtZmI2"
          "Mi00MTk3LTkzODgtZTg5Y2I4MzRiODVhIiwic2NvcGUiOiJDVVNUT01"
          "FUiBBRE1JTiJ9.DwNSOkVvUe4G3o0GwfIYHPi52_-sMlAO_ubzunRPrj"
          "DLKAhfTT6HV0Tu_R7yyNWEgcg09rp2ESHSU8mhCVyRyA",
    },
    body: jsonEncode({
      "username": userRequest.username,
      "password": userRequest.password,
      "roles": userRequest.roles.toList(),
      "phone": userRequest.phone,
      "gmail": userRequest.gmail
    }),
  );
  if (response.statusCode == 200) {
    final jsonResponse = jsonDecode(response.body) as Map<String, dynamic>;
    if (jsonResponse["code"] == 1000) {
      return true;
    }
  }
  return false;
}
