package com.mwz.s1;

import com.mwz.s1.mapper.UserMapper;
import io.seata.core.context.RootContext;
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

    private Random random = new Random();

    @Override
    @Transactional
    public void addUser() {
        log.info("S1 Begin ... xid: " + RootContext.getXID());

        User u = new User();
        u.setName("name" + random.nextInt());
        u.setAge(random.nextInt() % 100);
        u.setEmail("email" + random.nextInt());
        userMapper.insert(u);

//        if (random.nextBoolean()) {
//            throw new RuntimeException("this is a mock Exception");
//        }
    }

}
