import 'package:flutter/material.dart';
import 'package:restaurant/Widget/Wi_ButtonSign.dart';
import 'package:restaurant/Widget/Wi_FieldText.dart';
import 'package:restaurant/Widget/Wi_Header.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});
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
                const WiHeader(title: "Hi, ADMIN"),
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
        width: MediaQuery.of(context).size.width,
        padding: const EdgeInsets.symmetric(horizontal: 20),
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(
            top: Radius.circular(50),
          ),
        ),
        child: SingleChildScrollView(
          child: Column(
            children: [
              const Text(
                "Manager User",
                style: TextStyle(fontSize: 40, fontWeight: FontWeight.bold),
              ),
              DataTable(
                columns: [
                  headerTable("UserID"),
                  headerTable("Name"),
                  buttonCreate(context),
                ],
                rows: [
                  bodyTable("123456899", "Lee minh tan tan tan "),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  DataColumn headerTable(String text) {
    return DataColumn(
      label: Text(
        text,
        textAlign: TextAlign.left,
        style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
    );
  }

  DataColumn buttonCreate(BuildContext context) {
    return DataColumn(
        label: IconButton(
      onPressed: () => showDialog(
        context: context,
        builder: (BuildContext context) => Dialog(
          child: Container(
            height: 450,
            padding: const EdgeInsets.symmetric(horizontal: 10),
            child: const Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      Icons.account_circle,
                    ),
                    Text(
                      "Profile",
                      style:
                          TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                WiFieldText(title: "ID:", text: "UserID", height: 12),
                WiFieldText(title: "Name:", text: "Username", height: 12),
                WiFieldText(title: "Phone:", text: "Phone", height: 12),
                WiFieldText(title: "Gmail:", text: "Gmail", height: 12),
                SizedBox(height: 20),
                WiButtonSign(text: "Save"),
              ],
            ),
          ),
        ),
      ),
      icon: const Icon(
        Icons.add_circle_outline,
        size: 40,
      ),
    ));
  }

  DataRow bodyTable(String userID, String name) {
    return DataRow(
      cells: [
        DataCell(
          SizedBox(width: 90, child: Text(userID)),
        ),
        DataCell(
          SizedBox(width: 90, child: Text(name)),
        ),
        const DataCell(SizedBox(
          child: Row(
            children: [
              Icon(Icons.account_box),
              Icon(Icons.delete),
            ],
          ),
        )),
      ],
    );
  }
}
