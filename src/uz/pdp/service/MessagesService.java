package uz.pdp.service;

import uz.pdp.db.MessagesRepo;
import uz.pdp.db.UserRepo;
import uz.pdp.entity.Messages;
import uz.pdp.entity.User;
import uz.pdp.util.Input;

import java.util.List;

public class MessagesService {
    static UserRepo userRepo = UserRepo.getInstance();
    static MessagesRepo messagesRepo = MessagesRepo.getInstance();

    public static void start(Integer myCurrentId) {
        int i = 1;
        for (User user : userRepo.findAll()) {
            System.out.println(i + ". " + user.getName());
            i++;
        }
        List<Messages> all = messagesRepo.findAll();
        for (Messages messages : all) {
            System.out.println(messages.getText());
        }
        Integer user = Input.inputInt("Choose: ") - 1;
        String text = Input.inputStr("So'z yozing: ");
        messagesRepo.save(new Messages(myCurrentId, text, user));
        System.out.println("Xabar yuborildi");
    }

}
