package njust.se2.librarymanagementsystemweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("main")
public class HelloController {

    public Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(path = "map")
    public Map hello() {
        return new HashMap<String, String>() {
            {
                for (int i = 0; i < 5; i++) {
                    put("name" + i, "学号:" + i);
                }
                put("name", "张三");
                put("sex", "男");
            }
        };
    }

    @GetMapping(path = "str")
    public String testGetStr() {
        return "ok";
    }

    @GetMapping(path = "int")
    public int testGetint() {
        return 1;
    }
}
