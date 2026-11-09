package com.example.first_project;

import com.example.first_project.start.UserResponse;
import com.example.first_project.start.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    @Test
    void shouldUpdateUserName() {
        // 准备：创建一个 Service
        UserService service = new UserService();

        // 执行：修改编号为 1 的用户
        service.updateUser(1L, "LingRui");

        // 验证：重新查询，检查姓名
        UserResponse user = service.getUserById(1L);

        assertEquals("LingRui", user.getName());
    }
}
