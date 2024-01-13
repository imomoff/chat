package uz.pdp.db;

import uz.pdp.entity.Messages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MessagesRepo implements Repository<Messages> {
    private final List<Messages> messagesList;
    private static MessagesRepo singleton;
    List<Messages> msgList = new ArrayList<>();
    static final String PATH = "scr/uz/pdp/db/user_messages_db.txt";

    public MessagesRepo(List<Messages> messages) {
        this.messagesList = messages;
    }

    public static MessagesRepo getInstance() {
        if (singleton == null) {
            singleton = new MessagesRepo(loadData());
        }
        return singleton;
    }

    private static List<Messages> loadData() {
        return null;
    }

    @Override
    public void save(Messages messages) {
        msgList.add(messages);
        uploadData();
    }

    private void uploadData() {
        try (
                OutputStream outputStream = new FileOutputStream(PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(msgList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Messages> findAll() {
        return msgList;
    }

    @Override
    public void update(Messages messages, Integer id) {

    }

    @Override
    public void delete(Messages messages) {
        msgList.remove(messages);
        uploadData();
    }
}
