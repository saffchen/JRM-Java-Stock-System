package saffchen.generators;

import saffchen.dto.UserDto;

import java.util.AbstractQueue;

public class LotsOfUsersGenerator implements Runnable {
    private UserGenerator userGenerator;

    private int countOfUsers;

    private AbstractQueue<UserDto> users;

    public LotsOfUsersGenerator(AbstractQueue<UserDto> users, UserGenerator userGenerator, int countOfUsers) {
        this.userGenerator = userGenerator;
        this.countOfUsers = countOfUsers;
        this.users = users;
    }

    @Override
    public void run() {
        System.out.print("count" + countOfUsers + "\n");
        long start = System.currentTimeMillis();
        System.out.println("Start: " + start);
        for (int i = 0; i < countOfUsers; i++)
            users.add(userGenerator.generate());
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start) / 1000);
        System.out.println(users.size());
    }
}
