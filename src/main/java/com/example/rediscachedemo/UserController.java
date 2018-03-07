package com.example.rediscachedemo;

import com.example.rediscachedemo.Repository.UserRepository;
import com.example.rediscachedemo.domain.User;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("queryAll")
    @ResponseBody
    public String queryAll(){

        Jedis jedis = new Jedis("localhost",6379);
        String value = jedis.get("user_list");

        if (value!=null){
            return value;
        }

        List<User> list = userRepository.findAll();
        JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("class");
       value = serializer.serialize(list);
        jedis.set("user_list",value);
        return value;
    }
}
