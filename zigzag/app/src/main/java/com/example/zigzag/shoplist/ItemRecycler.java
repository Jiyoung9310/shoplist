package com.example.zigzag.shoplist;

/**
 * Created by XNOTE on 2018-05-13.
 */

public class ItemRecycler {
    private String name;
    private String age;
    private String style;

    public ItemRecycler(String name, String age, String style) {
        this.name = name;
        this.age = age;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
