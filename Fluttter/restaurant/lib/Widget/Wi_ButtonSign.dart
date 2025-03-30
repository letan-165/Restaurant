import 'package:flutter/material.dart';

class WiButtonSign extends StatelessWidget {
  final String text;
  final VoidCallback? onPressed;
  const WiButtonSign({
    super.key,
    required this.text,
    this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: onPressed,
      style: TextButton.styleFrom(
        minimumSize: const Size(200, 30),
        backgroundColor: const Color(0xFF4151B3),
      ),
      child: Text(
        text,
        style: const TextStyle(color: Colors.white, fontSize: 20),
      ),
    );
  }
}
