package bo;

import model.Category;
import model.Product;

import java.util.List;

public interface ProductBO {
    List<Product> findAllProduct();

    Product findByID(String id);

    List<Product> findByName(String name);

    List<Product> findByPrice(String name);

    String save(Product product);

    String update(Product product);

    void delete(String id);

    List<Category> findAllCategory();
}
