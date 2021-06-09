package dao;

import model.Category;
import model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAllProduct();

    Product findByID(String id);

    List<Product> findByName(String name);

    List<Product> findByPrice(String price);

    String save(Product product);

    String update(Product product);

    void delete(String id);

    List<Category> findAllCategory();
}
