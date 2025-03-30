class UserResponse {
  final String userID;
  final String username;
  final String password;

  const UserResponse(
      {required this.userID, required this.username, required this.password});

  factory UserResponse.fromJson(Map<String, dynamic> json) {
    return switch (json) {
      {
        'userID': String userID,
        'username': String username,
        'password': String password,
      } =>
        UserResponse(
          userID: userID,
          username: username,
          password: password,
        ),
      _ => throw const FormatException('Failed to load User.'),
    };
  }
}
