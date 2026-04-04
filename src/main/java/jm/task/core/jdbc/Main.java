package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Slava", "Pan", (byte) 31);
        userService.saveUser("David", "Kim", (byte) 18);
        userService.saveUser("Alina", "Kim", (byte) 26);
        userService.saveUser("Kimmie", "ThePuppy", (byte) 3);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
