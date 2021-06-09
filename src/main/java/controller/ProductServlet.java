package controller;

import bo.ProductBO;
import bo.ProductBOImpl;
import model.Category;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"", "/product"})
public class ProductServlet extends HttpServlet {
    private static String[] colorList = {"Red", "Green", "Black", "White", "Yellow"};

    ProductBO productBO = new ProductBOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionProduct = request.getParameter("actionProduct");
        if (actionProduct == null) {
            actionProduct = "";
        }
        switch (actionProduct) {
            case "createNewProduct":
                createNewProduct(request, response);
                break;
            case "updateProduct":
                updateProduct(request, response);
                break;
            case "searchProductByName":
                searchProductByName(request, response);
            case "searchProductByPrice":
                searchProductByPrice(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionProduct = request.getParameter("actionProduct");
        if (actionProduct == null) {
            actionProduct = "";
        }
        switch (actionProduct) {
            case "showCreateNewProduct":
                showCreateNewProduct(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "showEditProduct":
                showEditProduct(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productBO.findAllProduct();
        request.setAttribute("productList", productList);

        List<Category> categoryList = productBO.findAllCategory();

        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list-product.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        this.productBO.delete(id);
        listProduct(request, response);
    }

    private void showCreateNewProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = this.productBO.findAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("colorList", colorList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create-new-product.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) {
        Product product = informationProduct(request);

        String message = this.productBO.save(product);

        if (!message.equals("Create Complete !")) {
            request.setAttribute("product", product);

            String[] messages = message.split(",");
            for (String element : messages) {
                if (element.contains("name")) {
                    request.setAttribute("messageName", element);
                }
                if (element.contains("price")) {
                    request.setAttribute("messagePrice", element);
                }
                if (element.contains("quantity")) {
                    request.setAttribute("messageQuantity", element);
                }
            }

            showCreateNewProduct(request, response);
        } else {
            request.setAttribute("messageComplete", message);
            listProduct(request, response);
        }
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = this.productBO.findByID(id);
        request.setAttribute("product", product);

        List<Category> categoryList = this.productBO.findAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("colorList", colorList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit-product.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        Product product = informationProduct(request);
        String id = request.getParameter("id");
        product.setId(id);

        String message = this.productBO.update(product);

        if (!message.equals("Update Complete !")) {

            String[] messages = message.split(",");
            for (String element : messages) {
                if (element.contains("name")) {
                    request.setAttribute("messageName", element);
                }
                if (element.contains("price")) {
                    request.setAttribute("messagePrice", element);
                }
                if (element.contains("quantity")) {
                    request.setAttribute("messageQuantity", element);
                }
            }

            List<Category> categoryList = this.productBO.findAllCategory();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("product", product);
            request.setAttribute("colorList", colorList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit-product.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("messageComplete", message);
            listProduct(request, response);
        }
    }

    private void searchProductByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("nameProduct");
        List<Product> productList = this.productBO.findByName(name);
        request.setAttribute("productList", productList);
        List<Category> categoryList = this.productBO.findAllCategory();
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list-product.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void searchProductByPrice(HttpServletRequest request, HttpServletResponse response) {
        String price = request.getParameter("priceProduct");
        List<Product> productList = this.productBO.findByPrice(price);
        request.setAttribute("productList", productList);
        List<Category> categoryList = this.productBO.findAllCategory();
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list-product.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private Product informationProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        return new Product(name, price, quantity, color, description, category);
    }
}
