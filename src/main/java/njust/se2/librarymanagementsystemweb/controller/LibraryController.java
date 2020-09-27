package njust.se2.librarymanagementsystemweb.controller;

import njust.se2.librarymanagementsystemweb.pojo.Book;
import njust.se2.librarymanagementsystemweb.pojo.WantedList;
import njust.se2.librarymanagementsystemweb.result.Result;
import njust.se2.librarymanagementsystemweb.result.ResultFactory;
import njust.se2.librarymanagementsystemweb.service.BookService;
import njust.se2.librarymanagementsystemweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    /**
     * 获取书籍列表
     *
     * @return 返回列表 json
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/books")
    public Result list() throws Exception {
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    /**
     * 返回书籍对象，更新状态。
     *
     * @param book 书籍对象
     * @return 更改过后的书籍对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/content/books")
    public Result addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除id操作
     *
     * @param book 书籍对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/content/books/delete")
    public Result delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }


    /**
     * 根据类别查找书籍
     *
     * @param cid 书籍类别
     * @return 书籍列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (cid != 0) {
            return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
        } else {
            return list();
        }
    }

    /**
     * 根据关键字查询书籍
     *
     * @param keywords 关键字
     * @return 书籍列表
     */
    @CrossOrigin
    @GetMapping("/api/search")
    public Result searchResult(@RequestParam("keywords") String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(bookService.list());
        } else {
            return ResultFactory.buildSuccessResult(bookService.Search(keywords));
        }
    }

//    @CrossOrigin
//    @PostMapping("/api/addwantedlist")
//    public Result addWantedlist(@RequestBody Book book, @RequestBody User user) {
//        //读取书籍的信息
////        bookService.SearchById(book.getId());
//        System.out.println(book.getId() + " " + user.getUsername());
//        bookService.addWantedList(book, user);
//        return ResultFactory.buildSuccessResult_p("修改成功", null);
//    }

    @CrossOrigin
    @PostMapping("/api/addwantedlist")
    public Result addWantedlist(@RequestBody WantedList wantedList) {
        //读取书籍的信息
//        bookService.SearchById(book.getId());
        System.out.println(wantedList.getBid()+" "+ wantedList.getUsername());
        bookService.addWantedList(wantedList);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/wantedlist")
    public Result SearchWantedlist(@RequestBody WantedList wantedList) {
        List<WantedList> wantedLists = bookService.ListbyUsername(wantedList.getUsername());
        List<Book> bookList = new ArrayList<Book>();
        for (WantedList wantedList1 : wantedLists) {
            int bid = wantedList1.getBid();
            bookService.SearchById(bid);
            Book book = (Book) bookService.SearchById(bid);
            System.out.println(book.getBookname());
            bookList.add(book);
        }
        return ResultFactory.buildSuccessResult_p("asda",bookList);
    }

    @CrossOrigin
    @PostMapping("/api/getbookbybid")
    public Result GetBookbyBid(@RequestBody Book book) {
        return ResultFactory.buildSuccessResult(bookService.SearchById(book.getId()));
    }



    @CrossOrigin
    @PostMapping("/api/admin/content/books/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folderPath = "D:/workspace/img";
        File imageFolder = new File(folderPath);
        if (file.getOriginalFilename().endsWith(".jpeg")) {
            File f = new File(imageFolder, StringUtils.getRandomString(8) + Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename().length() - 5));
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            try {
                file.transferTo(f);
                return "http://localhost:8998/api/file/" + f.getName();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            File f = new File(imageFolder, StringUtils.getRandomString(8) + Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename().length() - 4));
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            try {
                file.transferTo(f);
                return "http://localhost:8998/api/file/" + f.getName();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

    }


}
