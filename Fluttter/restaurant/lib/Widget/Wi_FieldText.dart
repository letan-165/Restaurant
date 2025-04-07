import 'package:flutter/material.dart';

class WiFieldText extends StatefulWidget {
  final String title;
  final String text;
  final bool obscureText;
  final double height;
  final TextEditingController? controller;

  const WiFieldText({
    super.key,
    required this.title,
    required this.text,
    this.obscureText = false,
    this.controller,
    this.height = 16,
  });

  @override
  State<WiFieldText> createState() => _WiFieldTextState();
}

class _WiFieldTextState extends State<WiFieldText> {
  late bool _obscureText;
  @override
  void initState() {
    super.initState();
    _obscureText = widget.obscureText;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          widget.title,
          style: const TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 20,
          ),
        ),
        TextFormField(
          controller: widget.controller,
          obscureText: _obscureText,
          validator: (value) {
            if (value == "") {
              return "Pls enter '${widget.title}'";
            }
          },
          decoration: InputDecoration(
            label: Text(widget.text),
            border: const OutlineInputBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(15),
              ),
            ),
            focusedBorder: const OutlineInputBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(15),
              ),
              borderSide: BorderSide(
                color: Color(0xFF4151B3),
                width: 2,
              ),
            ),
            contentPadding:
                EdgeInsets.symmetric(vertical: widget.height, horizontal: 10),
            suffixIcon: widget.obscureText
                ? IconButton(
                    icon: Icon(
                      _obscureText ? Icons.visibility_off : Icons.visibility,
                      color: const Color(0xFF4151B3),
                    ),
                    onPressed: () {
                      setState(() {
                        _obscureText = !_obscureText;
                      });
                    },
                  )
                : null,
          ),
        ),
      ],
    );
  }
}
