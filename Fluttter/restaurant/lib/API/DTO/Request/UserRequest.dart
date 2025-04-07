class UserRequest {
  final String username;
  final String password;
  final Set<String> roles;
  final String phone;
  final String gmail;

  UserRequest(
      {required this.username,
      required this.password,
      required this.phone,
      required this.gmail,
      required this.roles});
}
