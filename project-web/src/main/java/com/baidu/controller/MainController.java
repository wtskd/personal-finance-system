package com.baidu.controller;


import com.baidu.utils.SysMenu;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MainController {


//    跳转到管理员登录成功的后台首页
    @RequestMapping("/toIndex")
    public String toContent(HttpServletRequest request) {
        //保存左侧功能栏
        request.setAttribute("userMenuList",getSysMenu());
        return "content";
    }

    //跳转到普通用户登录成功的后台首页
    @RequestMapping("/toUserIndex")
    public String toUserContent(HttpServletRequest request) {
        //保存左侧功能栏
        request.setAttribute("userMenuList",getUserMenu());
        return "userMenu/userContent";
    }


    //返回欢迎页面
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    //管理员的菜单
    private List<SysMenu> getSysMenu() {
        List<SysMenu> ml = new ArrayList<SysMenu>();
        SysMenu m1 = new SysMenu("system", "系统管理", "layui-icon layui-icon-util", false, null);
        SysMenu m1_1 = new SysMenu("system", "用户管理", "layui-icon layui-icon-set ", true, "/user/toUserList");
        SysMenu m1_2 = new SysMenu("system", "角色管理", "layui-icon layui-icon-set ", true, "/role/toRoleList");
        SysMenu m1_3 = new SysMenu("system", "账户管理", "layui-icon layui-icon-set", true, "/account/toList");
        SysMenu m1_4 = new SysMenu("system", "交易记录", "layui-icon layui-icon-set ", true, "/transaction/toList");
        SysMenu m1_5 = new SysMenu("system", "收支分类", "layui-icon layui-icon-set ", true, "/category/toList");
        SysMenu m1_6 = new SysMenu("system", "预算类型", "layui-icon layui-icon-set ", true, "/budget-type/toList");

        SysMenu m1_7 = new SysMenu("system", "月度收支分析", "layui-icon layui-icon-set", true, "/prod/toView1");
        SysMenu m1_8 = new SysMenu("system", "收支占比分析", "layui-icon layui-icon-set", true, "/prod/toView2");
        m1.getChildMenuList().add(m1_1);
        m1.getChildMenuList().add(m1_2);
        m1.getChildMenuList().add(m1_3);
        m1.getChildMenuList().add(m1_4);
        m1.getChildMenuList().add(m1_5);
        m1.getChildMenuList().add(m1_6);
        m1.getChildMenuList().add(m1_7);
        m1.getChildMenuList().add(m1_8);
        ml.add(m1);

//        再加入第二个二级菜单
        SysMenu m2 = new SysMenu("system", "业务管理", "layui-icon layui-icon-snowflake", false, null);
        SysMenu m2_1 = new SysMenu("system", "部门管理", "layui-icon layui-icon-water", true, null);
        SysMenu m2_2 = new SysMenu("system", "员工管理", "layui-icon layui-icon-user", true, null);
        m2.getChildMenuList().add(m2_1);
        m2.getChildMenuList().add(m2_2);
        ml.add(m2);
        return ml;
    }

    //用户的菜单
    private List<SysMenu> getUserMenu() {
        List<SysMenu> ml = new ArrayList<SysMenu>();
        SysMenu m1 = new SysMenu("system", "财务管理", "layui-icon layui-icon-util", false, null);
        SysMenu m1_1 = new SysMenu("system", "我的账户", "layui-icon layui-icon-set ", true, "/account/toList");
        SysMenu m1_2 = new SysMenu("system", "交易记录", "layui-icon layui-icon-set ", true, "/transaction/toList");
        SysMenu m1_3 = new SysMenu("system", "收支分类", "layui-icon layui-icon-set ", true, "/category/toList");
        SysMenu m1_4 = new SysMenu("system", "月度收支分析", "layui-icon layui-icon-set", true, "/prod/toView1");
        SysMenu m1_5 = new SysMenu("system", "收支占比分析", "layui-icon layui-icon-set", true, "/prod/toView2");

        m1.getChildMenuList().add(m1_1);
        m1.getChildMenuList().add(m1_2);
        m1.getChildMenuList().add(m1_3);
        m1.getChildMenuList().add(m1_4);
        m1.getChildMenuList().add(m1_5);
        ml.add(m1);

        return ml;
    }
}
