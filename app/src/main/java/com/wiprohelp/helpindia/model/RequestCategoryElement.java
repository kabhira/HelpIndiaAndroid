package com.wiprohelp.helpindia.model;

import com.wiprohelp.helpindia.utilities.Constants;

/**
 * Created by AB335009 on 12/18/2015.
 */
public class RequestCategoryElement {
    private String categoryId;
    private String categoryName;
    private String categoryIMg;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIMg() {
        return Constants.BASE_URL+categoryIMg;
    }

    public void setCategoryIMg(String categoryIMg) {
        this.categoryIMg = categoryIMg;
    }
}
