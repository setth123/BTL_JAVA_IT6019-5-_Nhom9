package backend.controllers;

import backend.models.Account;
import backend.utils.ReadData;

import java.util.List;

public class UserController {
    public static Account login(String username, String password) {
        List<Account> users = ReadData.readAccount("/DemoDB/user-account.txt");
        for (Account a : users) {
            if (a.getTenDangNhap().equals(username) && a.getMatKhau().equals(password)) {
                return a;
            }
        }
        return null;
    }
}
