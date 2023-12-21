package com.group20.backend.model;

import com.google.cloud.firestore.annotation.DocumentId;

public class Product extends Entity {

    private Category category;

    public Category getProductCategory() {
        return category;
    }

    public void setProductCategory(Category productCategory) {
        this.category = productCategory;
    }
}
