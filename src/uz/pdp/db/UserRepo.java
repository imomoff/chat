package uz.pdp.db;

import uz.pdp.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepo implements Repository<User> {

    private final List<User> users;
    private static UserRepo singleton;
    private static final String PATH = "src/uz/pdp/db/user_db.txt";

    private UserRepo(List<User> users) {
        this.users = users;
    }

    public static UserRepo getInstance() {
        if (singleton == null) {
            singleton = new UserRepo(loadData());
        }
        return singleton;
    }

    @SuppressWarnings("unchecked")
    private static List<User> loadData() {
        try (
                InputStream inputStream = new FileInputStream(PATH);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            return (List<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(User user) {
        users.add(user);
        uploadData();
    }

    private void uploadData() {
        try (
                OutputStream outputStream = new FileOutputStream(PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void update(User user, Integer id) {
        Optional<User> userOpt = findById(id);
        if (userOpt.isPresent()) {
            User findUser = userOpt.get();
            findUser.setName(user.getName());
            findUser.setPassword(user.getPassword());
            uploadData();
        } else {
            System.out.println("ID not found");
        }
    }

    private Optional<User> findById(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(User user) {
        users.remove(user);
        uploadData();
    }
}
