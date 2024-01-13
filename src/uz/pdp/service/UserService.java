package uz.pdp.service;

import uz.pdp.db.UserRepo;
import uz.pdp.entity.Messages;
import uz.pdp.entity.User;
import uz.pdp.util.Input;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    static Integer myCurrentId = null;
    static UserRepo userRepo = UserRepo.getInstance();
    public static void register() {

        String username = Input.inputStr("Enter username: ");
        String password = Input.inputStr("Enter password (a-z, 0-9): ");
        String repeatPass = Input.inputStr("Enter repaet password: ");
        String timeZone = chooseCountryForSaveTimeZone();
        Pattern pattern = Pattern.compile("\\w+[0-9]");
        Matcher matcher = pattern.matcher(password);
        boolean matches = matcher.matches();
        if (matches) {
            boolean check = checkAviableUser(username);
            if (!check) {
                if (password.equals(repeatPass)) {
                    userRepo.save(new User(username, password, timeZone));
                    System.out.println("SUCCESS!");
                }
            }
        } else {
            System.out.println("Parolni tartibga javob bermaydi!");
        }
    }

    private static String chooseCountryForSaveTimeZone() {
        System.out.print("""
                1 - UZBEKISTAN
                2 - America/New-York
                3 - Japan
                Choose:\s""");
        Scanner scanner = new Scanner(System.in);
        int line = scanner.nextInt();
        String[] array = {"Asia/Tashkent", "America/New_York", "Asia/Tokyo"};
        return array[line - 1];
    }

    private static boolean checkAviableUser(String username) {
        List<User> all = userRepo.findAll();
        for (User user : all) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public static void logIn() {
        String username = Input.inputStr("Enter login: ");
        String password = Input.inputStr("Enter password: ");
        boolean checkUserPassForLogin = checkUserPassForLogin(username, password);
        if (checkUserPassForLogin) {
            MessagesService.start(myCurrentId);
        } else {
            return;
        }
    }

    private static boolean checkUserPassForLogin(String username, String password) {
        for (User user : userRepo.findAll()) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                myCurrentId = user.getId();
                return true;
            }
        }
        return false;
    }
}
