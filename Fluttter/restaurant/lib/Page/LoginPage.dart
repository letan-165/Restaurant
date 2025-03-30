import 'package:flutter/material.dart';
import 'package:restaurant/API/DTO/Request/LoginRequest.dart';
import 'package:restaurant/API/Service/UserService.dart';
import 'package:restaurant/Page/HomePage.dart';
import 'package:restaurant/Page/SignUpPage.dart';
import 'package:restaurant/Widget/Wi_ButtonSign.dart';
import 'package:restaurant/Widget/Wi_FieldText.dart';
import 'package:restaurant/Widget/Wi_Header.dart';

class LoginPage extends StatefulWidget {
  LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController username = TextEditingController();
  final TextEditingController password = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          height: MediaQuery.sizeOf(context).height,
          color: const Color(0xFF4151B3),
          child: Column(
            children: [
              const SizedBox(height: 80),
              const WiHeader(title: "Login"),
              const SizedBox(height: 50),
              bodyInfo(context),
            ],
          ),
        ),
      ),
    );
  }

  Widget bodyInfo(BuildContext context) {
    return Expanded(
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(
            top: Radius.circular(50),
          ),
        ),
        child: Form(
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              WiFieldText(
                  controller: username,
                  title: "Username",
                  text: "Enter username "),
              const SizedBox(height: 20),
              WiFieldText(
                controller: password,
                title: "Password",
                text: "Enter password",
                obscureText: true,
              ),
              const SizedBox(height: 10),
              forgotPassword(),
              const SizedBox(height: 10),
              WiButtonSign(
                text: "Login",
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    final LoginRequest request = LoginRequest(
                        username: username.text, password: password.text);
                    final String response = await login(request);
                    if (response != "") {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const HomePage(),
                        ),
                      );
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("Login false")));
                    }
                  } else {
                    ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('Pls enter full form')));
                  }
                },
              ),
              const SizedBox(height: 10),
              otherLogin(),
              const SizedBox(height: 10),
              signUp(context)
            ],
          ),
        ),
      ),
    );
  }

  Widget forgotPassword() {
    return const Align(
      alignment: Alignment.centerRight,
      child: Text(
        "Forgot Password?",
        style: TextStyle(
          color: Color(0xFF4151B3),
          fontStyle: FontStyle.italic,
          decoration: TextDecoration.underline,
        ),
      ),
    );
  }

  Widget otherLogin() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const Text(
          "OR",
          style: TextStyle(
            fontSize: 20,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(width: 20),
        Image.asset(
          "lib/Assets/Images/google.png",
          scale: 10,
        ),
      ],
    );
  }

  TextStyle style = const TextStyle(
    fontSize: 17,
    color: Colors.grey,
    fontWeight: FontWeight.bold,
  );

  Widget signUp(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text("Click on", style: style),
        TextButton(
          onPressed: () => Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => SignUpPage(),
            ),
          ),
          child: const Text(
            "here",
            style: TextStyle(
              color: Color(0xFF4151B3),
              fontStyle: FontStyle.italic,
              fontSize: 17,
              fontWeight: FontWeight.bold,
              decoration: TextDecoration.underline,
              decorationColor: Color(0xFF4151B3),
              decorationThickness: 2,
            ),
          ),
        ),
        Text(
          "if you no account",
          style: style,
        ),
      ],
    );
  }
}
