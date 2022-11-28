package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import saffchen.dto.UserDto;
import saffchen.generators.CustomUserGenerator;
import saffchen.generators.LotsOfUsersGenerator;
import saffchen.mapper.UserMapper;
import saffchen.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class UserGeneratorService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CustomUserGenerator userGenerator;
    private final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();
    private ExecutorService threadPool;
    private ConcurrentLinkedQueue<UserDto> users;

    @Scheduled(initialDelay = 3000, fixedRate = 5000)
    //@Async
    public void userGeneratorScheduler() {
        getGeneratedUsers(1003);
        //saveGeneratedUsers(getGeneratedUsers(100));
    }

    public List<UserDto> getGeneratedUsers(int count) {
        int reminder = count % PROCESSOR_COUNT;
        int countPerThread = count / PROCESSOR_COUNT;
        threadPool = Executors.newFixedThreadPool(PROCESSOR_COUNT);

        for (int i = 0; i < PROCESSOR_COUNT; i++) {
            if (i == PROCESSOR_COUNT - 1)
                countPerThread += reminder;
            threadPool.execute(new LotsOfUsersGenerator(users, userGenerator, countPerThread));
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task was finished!");
        //System.out.println("users: " + users.size());
        return users.stream().collect(Collectors.toList());
    }

    @PostConstruct
    void init() {
        this.users = new ConcurrentLinkedQueue<UserDto>();
    }

    public void saveGeneratedUsers(List<UserDto> users) {
        userRepository.saveAllAndFlush(userMapper.userDtoToUserEntityList(users));
    }
}