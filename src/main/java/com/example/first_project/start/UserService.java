package com.example.first_project.start;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<UserResponse> users = new CopyOnWriteArrayList<>( List.of(
            new UserResponse(1L, "羚锐"),
            new UserResponse(2L, "小明"),
            new UserResponse(3L, "小红")
    ));

    private final AtomicLong nextId = new AtomicLong(4);

    public UserResponse getUserById(Long id) {
        if (id < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "用户编号必须大于零"
            );
        }

        for (UserResponse user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "用户不存在"
            );
    }

    public List<UserResponse> getUsers(String name) {
        if (name == null || name.isBlank()) {
            return users;
        }
        List<UserResponse> result = new ArrayList<>();

        for(UserResponse user : users){
            if (user.getName().contains(name)) {
                result.add(user);
            }
        }
        return result;
    }

    public synchronized UserResponse createUser(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "姓名不能为空"
            );
        }

        String normalizedName = name.strip();

        for (UserResponse user : users) {
            if (user.getName().equals(normalizedName)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "姓名已存在"
                );
            }
        }

        Long id = nextId.getAndIncrement();
        UserResponse user = new UserResponse(id, normalizedName);

        users.add(user);

        return user;
    }

    public synchronized void deleteUser(Long id) {
        if (id <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "用户编号必须大于0"
            );
        }

        boolean removed = users.removeIf(
                user -> user.getId().equals(id)
        );

        if (!removed) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "用户不存在"
            );
        }
    }

    public synchronized UserResponse updateUser(Long id, String name) {
        // 复用已有方法，检查编号并确认用户存在
        UserResponse existing = getUserById(id);

        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "姓名不能为空"
            );
        }

        String normalizedName = name.strip();

        for (UserResponse user : users) {
            if (!user.getId().equals(id)
                    && user.getName().equals(normalizedName)) {

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "姓名已存在"
                );
            }
        }

        UserResponse updated =
                new UserResponse(id, normalizedName);

        int index = users.indexOf(existing);
        users.set(index, updated);

        return updated;
    }
}
