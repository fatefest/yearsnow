package org.yearsnow.cloud.consumer.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fatey
 * @date: 2019/3/25
 * @time: 23:36
 * Description:
 */
@FeignClient(serviceId = "album-fest")
public interface UserService {

    @RequestMapping(value = "/user/{userId}", method= RequestMethod.GET)
    String getUserMessage(@PathVariable("userId") Integer userId) ;
}
