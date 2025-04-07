import 'package:flutter/material.dart';

class WiHeader extends StatelessWidget {
  final String title;
  const WiHeader({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        Text(
          title,
          style: const TextStyle(fontSize: 40, color: Colors.white),
        ),
        Container(
          width: 100,
          height: 100,
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("lib/Assets/Images/logo.png"),
              fit: BoxFit.cover,
            ),
          ),
        ),
      ],
    );
  }
}
