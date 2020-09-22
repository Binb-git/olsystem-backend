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

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult_p("用户状态更新成功", null);
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {

        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult_p("重置密码成功", null);
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult_p("修改用户信息成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/admin/user/delete")
    public Result delete(@RequestBody User user) throws Exception {
        userService.deleteById(user.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }
}
