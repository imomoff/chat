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
        while (true) {
            int i = 0;
            for (User user : userRepo.findAll()) {
                if (!user.getId().equals(myCurrentId)) {
                    continue;
                }
                System.out.println(i + ". " + user.getName());
                i++;
            }
            Integer chooseUserForWrite = Input.inputInt("Choose: ") - 1;
            System.out.println("CHAT: ");
            for (Messages messages : messagesRepo.findAll()) {
                if (messages.getFromId().equals(myCurrentId)) {
                    System.out.println(messages.getText());
                }
            }
            String text = Input.inputStr("Xabar yozing: ");
            messagesRepo.save(new Messages(myCurrentId, text, chooseUserForWrite));
            System.out.println("xabar yuborildi");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
