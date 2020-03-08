package com.mwz.s2;

import com.mwz.s2.mapper.UserMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
public class MyServiceImpl implements MyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EchoService echoService;

    private Random random = new Random();

    @Override
    @Transactional
    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    public void addUser() {
        log.info("S2 Begin ... xid: " + RootContext.getXID());

        if (!"success".equals(echoService.addUser())) {
            throw new RuntimeException("s1 add user error");
        }

        User u = new User();
        u.setName("name" + random.nextInt());
        u.setAge(random.nextInt() % 100);
        u.setEmail("email" + random.nextInt());
        userMapper.insert(u);

        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }


    }

}
