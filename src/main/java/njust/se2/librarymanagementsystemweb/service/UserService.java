package njust.se2.librarymanagementsystemweb.service;

import njust.se2.librarymanagementsystemweb.dao.UserDao;
import njust.se2.librarymanagementsystemweb.dto.UserDTO;
import njust.se2.librarymanagementsystemweb.pojo.AdminRole;
import njust.se2.librarymanagementsystemweb.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserDao userdao;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    public List<UserDTO> list() {
        List<User> users = userdao.findAll();

        // Find all roles in DB to enable JPA persistence context.
        // List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    /**
     * 查询是否存在
     *
     * @param username 用户名
     * @return 是否存在 boolean
     */
    public boolean isExist(String username) {
        User user = getByName(username);
        return user != null;
    }

    /**
     * 通过用户名查询对象
     *
     * @param username 用户名
     * @return User对象
     */
    public User getByName(String username) {
        return userdao.findByUsername(username);
    }

    /**
     * 通过用户名密码查询对象
     *
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
    public User get(String username, String password) {
        return userdao.getByUsernameAndPassword(username, password);
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     */
    public void add(User user) {
        userdao.save(user);
    }

    public void updateUserStatus(User user) {
        User userInDB = userdao.findByUsername(user.getUsername());
        userInDB.setEnabled(user.getEnabled());
        userdao.save(userInDB);
    }

    public User resetPassword(User user) {
        User userInDB = userdao.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userdao.save(userInDB);
    }

//    public void editUser(User user) {
//        User userInDB = userdao.findByUsername(user.getUsername());
//        userInDB.setName(user.getName());
//        userInDB.setPhone(user.getPhone());
//        userInDB.setEmail(user.getEmail());
//        userdao.save(userInDB);
//        //adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
//    }

    public void editUser(User user) {
        User userInDB = userdao.findById(user.getId());
        userInDB.setName(user.getName());
        userInDB.setUsername(user.getUsername());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userdao.save(userInDB);
        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }

    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(1);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        if (isExist(username)) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userdao.save(user);

        return 1;
    }

    public void deleteById(int id) {
        userdao.deleteById(id);
    }
}
