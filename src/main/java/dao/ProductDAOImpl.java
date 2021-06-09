package dao;


import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String SELECT_PRODUCTS_BY_NAME = "select * from product where product_name like ?";
    private static final String SELECT_PRODUCTS_BY_PRICE = "select * from product where price = ?";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where product_id = ?";
    private static final String SELECT_ALL_CATEGORY = "select * from category";
    private static final String CREATE_NEW_PRODUCT = "insert into product values (null, ?, ?, ?, ?, ?, ?)";

    Connection connection = BaseDAO.getConnection();
    @Override
    public List<Product> findAllProduct() {

        List<Product> productList = new ArrayList<>();
        Product product;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("product_id");
                String name = resultSet.getString("product_name");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String idCategory = resultSet.getString("category_id");

                product = new Product(id, name, price, quantity, color, description, idCategory);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return productList;
    }

    @Override
    public List<Product> findByName(String nameNeedSearch) {

        List<Product> productList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME);
            preparedStatement.setString(1, '%' + nameNeedSearch + '%');
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product;
            while (resultSet.next()) {
                String id = resultSet.getString("product_id");
                String name = resultSet.getString("product_name");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String idCategory = resultSet.getString("category_id");

                product = new Product(id, name, price, quantity, color, description, idCategory);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(String priceNeedSearch) {

        List<Product> productList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_PRICE);
            preparedStatement.setString(1, priceNeedSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product;
            while (resultSet.next()) {
                String id = resultSet.getString("product_id");
                String name = resultSet.getString("product_name");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String idCategory = resultSet.getString("category_id");

                product = new Product(id, name, price, quantity, color, description, idCategory);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product findByID(String idNeedFind) {

        Product product = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setString(1, idNeedFind);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("product_id");
                String name = resultSet.getString("product_name");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String idCategory = resultSet.getString("category_id");

                product = new Product(id, name, price, quantity, color, description, idCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public String save(Product product) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPrice());
            preparedStatement.setString(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getIdCategory());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Create Complete !";
    }

    @Override
    public String update(Product product) {

        try {
            CallableStatement callableStatement = connection.prepareCall("call update_product(?,?,?,?,?,?,?)");
            callableStatement.setString(1, product.getId());
            callableStatement.setString(2, product.getName());
            callableStatement.setString(3, product.getPrice());
            callableStatement.setString(4, product.getQuantity());
            callableStatement.setString(5, product.getColor());
            callableStatement.setString(6, product.getDescription());
            callableStatement.setString(7, product.getIdCategory());

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Update Complete !";
    }

    @Override
    public void delete(String idNeedDelete) {

        try {
            CallableStatement callableStatement = connection.prepareCall("call delete_product(?)");
            callableStatement.setString(1, idNeedDelete);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> findAllCategory() {

        List<Category> categoryList = new ArrayList<>();
        Category category;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("category_id");
                String name = resultSet.getString("category_name");

                category = new Category(id, name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }
}
