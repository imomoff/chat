package uz.pdp.db;

import uz.pdp.entity.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesRepo implements Repository<Messages> {
    private final List<Messages> messages;
    private static MessagesRepo singleton;
    private static final String PATH = "src/uz/pdp/db/messages_db.txt";

    private MessagesRepo(List<Messages> messages) {
        this.messages = messages;
    }

    public static MessagesRepo getInstance() {
        if (singleton == null) {
            singleton = new MessagesRepo(loadData());
        }
        return singleton;
    }

    @SuppressWarnings("unchecked")
    private static List<Messages> loadData() {
        try (
                InputStream inputStream = new FileInputStream(PATH);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            return (List<Messages>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Messages message) {
        messages.add(message);
        uploadData();
    }

    private void uploadData() {
        try (
                OutputStream outputStream = new FileOutputStream(PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(messages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Messages> findAll() {
        return messages;
    }

    @Override
    public void update(Messages message, Integer id) {
        
    }
 
    @Override
    public void delete(Messages messages1) {
        messages.remove(messages1);
        uploadData();
    }
}
