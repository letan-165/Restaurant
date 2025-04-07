import 'package:flutter/material.dart';
import 'package:restaurant/API/DTO/Request/UserRequest.dart';
import 'package:restaurant/API/Service/UserService.dart';
import 'package:restaurant/Widget/Wi_ButtonSign.dart';
import 'package:restaurant/Widget/Wi_Header.dart';

import '../Widget/Wi_FieldText.dart';

class SignUpPage extends StatelessWidget {
  SignUpPage({super.key});
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  final TextEditingController username = TextEditingController();
  final TextEditingController password = TextEditingController();
  final TextEditingController gmail = TextEditingController();
  final TextEditingController phone = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          height: MediaQuery.sizeOf(context).height,
          color: const Color(0xFF4151B3),
          child: SafeArea(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                buttonBack(context),
                const SizedBox(height: 10),
                const WiHeader(title: "SignUp"),
                const SizedBox(height: 20),
                bodyInfo(context),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget buttonBack(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 10),
      child: IconButton(
        onPressed: () => {Navigator.pop(context)},
        icon: const Icon(
          Icons.keyboard_return,
          color: Colors.black,
          size: 30,
        ),
        style: IconButton.styleFrom(
          backgroundColor: Colors.white,
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
                title: "Username:",
                text: "Enter username ",
                controller: username,
              ),
              WiFieldText(
                title: "Password:",
                text: "Enter password",
                obscureText: true,
                controller: password,
              ),
              WiFieldText(
                title: "Gmail:",
                text: "Enter your gmail",
                controller: gmail,
              ),
              WiFieldText(
                title: "Phone:",
                text: "Enter your phone",
                controller: phone,
              ),
              SizedBox(height: 20),
              WiButtonSign(
                text: "Sign Up",
                onPressed: () async {
                  Set<String> roles = {"CUSTOMER"};
                  UserRequest userRequest = UserRequest(
                      username: username.text,
                      password: password.text,
                      roles: roles,
                      phone: phone.text,
                      gmail: gmail.text);
                  if (_formKey.currentState!.validate() &&
                      await signUp(userRequest)) {
                    Navigator.pop(context);
                    ScaffoldMessenger.of(context).showSnackBar(
                      const SnackBar(content: Text("Sign up successful")),
                    );
                  } else {
                    ScaffoldMessenger.of(context).showSnackBar(
                      const SnackBar(content: Text("Sign up false")),
                    );
                  }
                },
              ),
              const SizedBox(height: 40),
            ],
          ),
        ),
      ),
    );
  }
}
