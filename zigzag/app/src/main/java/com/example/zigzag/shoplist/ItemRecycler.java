package com.example.zigzag.shoplist;

/**
 * Created by XNOTE on 2018-05-13.
 */

public class ItemRecycler {
    private String num;
    private String name;
    private String age;
    private String style;
    private String img;

    public ItemRecycler(int num, String name, String age, String style, String img) {
        this.num = String.valueOf(num);
        this.name = name;
        this.age = age;
        this.style = style;
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
