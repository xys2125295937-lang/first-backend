# Spring Boot 用户管理学习项目

使用 Spring Boot 实现用户增删改查，练习分层设计、参数校验和统一异常处理。

## 技术栈

- Java 21
- Spring Boot
- Maven
- JUnit

## 已实现功能

- 按编号查询用户
- 查询用户列表、按姓名搜索
- 新增用户、校验空姓名和重复姓名
- 修改用户姓名
- 删除用户
- 返回统一格式的错误信息

## 运行方式

在 IDEA 中打开项目，运行 FirstProjectApplication。

默认访问地址：http://localhost:8080

也可以在 Windows PowerShell 的项目目录中运行：

```powershell
.\mvnw.cmd spring-boot:run
```

## 接口列表

| 请求方法 | 路径 | 功能 |
|---|---|---|
| GET | /users | 查询用户列表 |
| GET | /users?name=小 | 按姓名搜索 |
| GET | /user/{id} | 按编号查询 |
| POST | /users | 新增用户 |
| PUT | /user/{id} | 修改用户 |
| DELETE | /user/{id} | 删除用户 |

新增和修改用户的请求体示例：

```json
{
  "name": "XiaoWang"
}
```

## 数据存储说明

当前数据保存在内存列表中，尚未接入数据库。
重启程序后，新增和修改的数据不会保留，初始用户会重新加载。