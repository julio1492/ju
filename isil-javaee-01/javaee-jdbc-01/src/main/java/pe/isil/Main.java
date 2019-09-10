package pe.isil;

import pe.isil.dao.UserDAO;
import pe.isil.pe.isil.business.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        User user = UserDAO.isValidLogin("jose", "1234567");
        System.out.println("user = " + user);

        User user1 = UserDAO.create(new User(null, "AAAAA", "AAAAA"));
        System.out.println("user1 = " + user1);

        List<User> users = UserDAO.findAll();
        System.out.println("users = " + users);
    }
}
