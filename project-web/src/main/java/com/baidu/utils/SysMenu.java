package com.baidu.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SysMenu {
//    应用名称
    private String name;
//    菜单标题
    private String title;
//    菜单图标
    private String iconClass;
//    是否末级菜单，为true则是末级菜单，否则还有子菜单
    private boolean child;
//    末级菜单的链接地址
    private String href;
//    private String
    private List<SysMenu> childMenuList;

    public SysMenu() {
    }

    public SysMenu(String name, String title, String iconClass, boolean child, String href) {
        this.name = name;
        this.title = title;
        this.iconClass = iconClass;
        this.child = child;
        this.href = href;
        this.childMenuList = new ArrayList<SysMenu>();
    }
}
