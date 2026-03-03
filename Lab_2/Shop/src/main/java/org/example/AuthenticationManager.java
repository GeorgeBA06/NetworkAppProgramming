package org.example;

public class AuthenticationManager {
    private static final String password = "1234";

    public static boolean checkPass(String password){
        return password.equals(AuthenticationManager.password);
    }
}
