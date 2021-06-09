package bo;

import common.Validation;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Category;
import model.Product;

import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> findAllProduct() {
        return this.productDAO.findAllProduct();
    }

    @Override
    public Product findByID(String id) {
        return this.productDAO.findByID(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return this.productDAO.findByName(name);
    }

    @Override
    public List<Product> findByPrice(String price) {
        return this.productDAO.findByPrice(price);
    }

    @Override
    public String save(Product product) {

        boolean check = true;
        String message = "";

        if (product.getName().equals("")) {
            check = false;
            message += "Invalid name ! Name is not empty ,";
        }
        if (Validation.regexPrice(product.getPrice())) {
            check = false;
            message += "Invalid price ! Price must be a positive number and greater than 10000000 VND ,";
        }
        if (Validation.regexQuantity(product.getQuantity())) {
            check = false;
            message += "Invalid quantity ! Quantity must be a positive integer and greater than 0 !";
        }

        if (check) {
            message = this.productDAO.save(product);
        }

        return message;

//        return this.productDAO.save(product);
    }

    @Override
    public String update(Product product) {

        boolean check = true;
        String message = "";

        if (product.getName().equals("")) {
            check = false;
            message += "Invalid name ! Name is not empty";
        }
        if (Validation.regexPrice(product.getPrice())) {
            check = false;
            message += "Invalid price ! Price must be a positive number and greater than 10000000 VND ,";
        }
        if (Validation.regexQuantity(product.getQuantity())) {
            check = false;
            message += "Invalid quantity ! Quantity must be a positive integer and greater than 0 !";
        }

        if (check) {
            message = this.productDAO.update(product);
        }

        return message;

//        return this.productDAO.update(product);
    }

    @Override
    public void delete(String id) {
        this.productDAO.delete(id);
    }

    @Override
    public List<Category> findAllCategory() {
        return this.productDAO.findAllCategory();
    }
}
