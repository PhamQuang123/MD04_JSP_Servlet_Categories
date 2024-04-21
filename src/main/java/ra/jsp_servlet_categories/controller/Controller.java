package ra.jsp_servlet_categories.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private CategoriesController categoriesController;
    private ProductController productController;

    public Controller() {
        categoriesController = new CategoriesController();
        productController = new ProductController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "getAll":
                categoriesController.getAllCategories(request, response);
                productController.getAllProduct(request, response);
                request.getRequestDispatcher("views/home.jsp").forward(request, response);
                break;
            case "initUpdate":
                categoriesController.findCategoriesById(request, response);
                break;
            case "delete":
                boolean checkDelete = categoriesController.removeCategories(request, response);
                if (checkDelete) {
                    categoriesController.getAllCategories(request, response);
                    productController.getAllProduct(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("views/error.jsp").forward(request, response);
                }
                break;
            case "deleteProduct":
                boolean resultDeleteProduct = productController.deleteProduct(request, response);
                if (resultDeleteProduct) {
                    productController.getAllProduct(request, response);
                    categoriesController.getAllCategories(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("views/error.jsp").forward(request, response);
                }
                break;
            case "initUpdateProduct":
                productController.findProductById(request, response);

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                boolean result = categoriesController.createCategories(request, response);
                if (result) {
                    categoriesController.getAllCategories(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("views/createCategories.jsp").forward(request, response);
                }

                break;
            case "update":
                boolean resultUpdate = categoriesController.updateCategories(request, response);
                if (resultUpdate) {
                    categoriesController.getAllCategories(request, response);
                    productController.getAllProduct(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    categoriesController.findCategoriesById(request, response);
                }
                break;
            case "search":
                categoriesController.findCategoriesByName(request, response);
                productController.getAllProduct(request, response);
                request.getRequestDispatcher("views/home.jsp").forward(request, response);
                break;
            case "createProduct":
                boolean checkCreateProduct = productController.createProduct(request, response);
                if (checkCreateProduct) {
                    productController.getAllProduct(request, response);
                    categoriesController.getAllCategories(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("views/product/createProduct.jsp").forward(request, response);
                }
                break;
            case "updateProduct":
                boolean resultUpdateProduct = productController.updateProduct(request, response);
                if (resultUpdateProduct) {
                    categoriesController.getAllCategories(request, response);
                    productController.getAllProduct(request, response);
                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
                } else {
                    productController.findProductById(request, response);
                }
                break;
            case "searchProduct":
                if (productController.findProductByPriceRange(request, response)) {
                    categoriesController.getAllCategories(request, response);
                } else {
                    categoriesController.getAllCategories(request, response);
                    productController.getAllProduct(request, response);
                }
                request.getRequestDispatcher("views/home.jsp").forward(request, response);

                break;
        }
    }
}
