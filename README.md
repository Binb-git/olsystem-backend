# ol-res
only for J2EE Course Design

# 前端地址
https://github.com/Binb-git/olsystem-frontend.git

## 项目由SpringBoot + vue.js 搭建 (primary)

## 如何运行

先启动后端，运行主程序**LibrarymanagementsystemWebApplication**。端口为8998。
再启动前端，在前端根目录下，执行命令<code>npm run serve</code>
或者直接点击idea的npm中**serve**命令（**第一次运行要先点击build构建dist文件**）。前端默认打开login目录，
即可访问登陆页面。管理员账号（具有所有权限）为'**admin**,密码为'**123**'。确定后即可跳转到index界面。
如果想看注册界面请输入`localhost:8066/register`进行注册。管理页面为`localhost:8066/admin`，只有管理员的admin是拥有全部功能的，其余的人员只拥有自己对应的权限（待更新）。
