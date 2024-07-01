package com.mycompany.isp392.category;

import java.util.List;

public class CategoryUtils {
    public static boolean isCategorySelected(List<CategoryDTO> selectedCategories, int categoryID) {
        for (CategoryDTO category : selectedCategories) {
            if (category.getCategoryID() == categoryID) {
                return true;
            }
        }
        return false;
    }
}
