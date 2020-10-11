package njust.se2.librarymanagementsystemweb.controller;

import njust.se2.librarymanagementsystemweb.pojo.User;
import njust.se2.librarymanagementsystemweb.result.Result;
import njust.se2.librarymanagementsystemweb.result.ResultFactory;
import njust.se2.librarymanagementsystemweb.service.AdminUserRoleService;
import njust.se2.librarymanagementsystemweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }

    @PostMapping("/api/userinfo")
    public Result listUserByUsername(@RequestBody User user) {
        return ResultFactory.buildSuccessResult_p("查询成功", userService.getByName(user.getUsername()));
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult_p("用户状态更新成功", null);
    }

    @PutMapping("/api/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult_p("重置密码成功", null);
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        System.out.println(requestUser.getUsername() + requestUser.getName() + requestUser.getEmail() + requestUser.getPhone());
        if ((requestUser.getUsername().equals("")) || requestUser.getName().equals("") || requestUser.getEmail().equals("") ||
        requestUser.getPhone().equals("")) {
            return ResultFactory.buildFailResult("表单必须填写完整");
        } else {
            userService.editUser(requestUser);
            return ResultFactory.buildSuccessResult_p("修改用户信息成功", null);
        }
    }

    @CrossOrigin
    @PostMapping("/api/admin/user/delete")
    public Result delete(@RequestBody User user) throws Exception {
        userService.deleteById(user.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }
}
