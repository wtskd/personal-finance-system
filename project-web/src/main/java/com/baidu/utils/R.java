package com.baidu.utils;

import java.util.HashMap;
import java.util.Map;

//主要用于添加，修改，删除操作的返回值
public class R extends HashMap<String, Object> {
    public R() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static R error() {
        return error(1, "操作失败");
}

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R right() {
        return new R();
    }


    public static R right(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R right(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
