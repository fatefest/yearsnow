package org.yearsnow.cloud.service.user.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: fatey
 * @date: 2019/3/13
 * @time: 17:33
 * Description:
 */
@RestController
public class UserController {

    @GetMapping(value = "/user/{userId}")
    public String index(@PathVariable(name = "userId") int userId) {
        return "hello "+userId+"，this is first message";
    }
}
