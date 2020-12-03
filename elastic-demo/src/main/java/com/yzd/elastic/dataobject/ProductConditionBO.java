package com.yzd.elastic.dataobject;

import java.util.List;

/**
 * Created by yzd on 2020/12/3
 */
public class ProductConditionBO {
    /**
     * 商品分类数组
     */
    private List<Category> categories;

    public static class Category {

        /**
         * 分类编号
         */
        private Integer id;
        /**
         * 分类名称
         */
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
