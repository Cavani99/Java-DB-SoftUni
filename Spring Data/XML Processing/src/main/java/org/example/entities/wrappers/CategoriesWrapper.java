package org.example.entities.wrappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.entities.Categories;
import org.example.entities.Users;

import java.util.List;

@XmlRootElement(name = "categories")
public class CategoriesWrapper {

    private List<Categories> categories;

    @XmlElement(name = "category")
    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public CategoriesWrapper() {
    }

    public CategoriesWrapper(List<Categories> categories) {
        this.categories = categories;
    }
}
