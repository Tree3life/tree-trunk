package life.tree3.trunk.controller;

import life.tree3.trunk.pojo.dto.UserDto;
import life.tree3.trunk.pojo.entity.SysUser;
import life.tree3.trunk.service.SysUserService;
import life.tree3.trunk.settings.response.RespResult;
import life.tree3.trunk.settings.response.ResponseCode;
import life.tree3.trunk.settings.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/2 0002 14:20 </p>
 */
@Slf4j
@RequestMapping("/app")
@RestController
public class AppController {
    @Autowired
    private SysUserService userService;

    @PostMapping("/login")
    public RespResult login(@RequestBody SysUser user) {
        Objects.requireNonNull(user);
        String tempPwd = user.getPassword();
        user.setPassword(null);

        List<UserDto> dbUsers = userService.queryAll(user);
        if (CollectionUtils.isEmpty(dbUsers)) {
            return RespResult.failure(ResponseCode.USER_NOT_EXIST);
        }
        SysUser dbUser = dbUsers.get(0);
        if (!dbUser.getPassword().equals(tempPwd)) {
            return RespResult.failure(ResponseCode.USER_LOGIN_ERROR);
        }
        UserDto userDto = userService.queryUserInfo(dbUser.getId());
        userDto.setPassword(null);
        return RespResult.success(userDto);
    }
}
