package org.yearsnow.cloud.consumer.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yearsnow.cloud.consumer.user.service.UserService;

/**
 * @author: fatey
 * @date: 2019/3/25
 * @time: 22:49
 * Description:
 */
@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public String getUserInfo(@PathVariable("userId") int userId){
        return userService.getUserMessage(userId);
    }
}
