package uz.pdp;

import uz.pdp.db.UserRepo;
import uz.pdp.service.UserService;
import uz.pdp.util.Input;

public class Main {
    public static void main(String[] args) {

        UserRepo userRepo = UserRepo.getInstance();

        while (true) {
            displayMenu();
            switch (Input.inputInt("Tanlang: ")) {
                case 1 -> UserService.register();
                case 2 -> UserService.logIn();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("""
                1 - Register
                2 - LogIn""");
    }
}
