import 'package:flutter/material.dart';
import 'package:restaurant/Page/LoginPage.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: LoginPage(),
    );
  }
}

void _showSnackBar(BuildContext context) {

  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => LoginPage()),
  );
  Navigator.pop(context);

  // Hiển thị SnackBar
}



Widget test() {
  return
    ListTile(
      leading: const Icon(), // Icon bên trái
      title: const Text(), // Tiêu đề chính
      subtitle: const Text(), // Phụ đề
      trailing: const Icon(), // Icon bên phải
      tileColor: Colors.white, // Màu nền của ListTile
      selected: true, // Đánh dấu nếu được chọn
      selectedTileColor: Colors.blue.withOpacity(0.2), // Màu nền khi được chọn
      contentPadding: const EdgeInsets.symmetric(), // Padding cho nội dung
      onTap: () {}, // Sự kiện khi nhấn giữ
      dense: false, // Giảm chiều cao ListTile nếu true
      enabled: true, // Nếu false thì vô hiệu hóa ListTile
      shape: const RoundedRectangleBorder(),// Bo góc cho ListTile
    );










