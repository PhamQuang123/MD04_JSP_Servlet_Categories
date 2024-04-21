package ra.jsp_servlet_categories.controller;

import ra.jsp_servlet_categories.model.Product;
import ra.jsp_servlet_categories.repositoryImp.ProductRepositoryImp;
import ra.jsp_servlet_categories.service.Service;
import ra.jsp_servlet_categories.serviceImp.ProductServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private Service<Product, String> productServiceImp;

    public ProductController() {
        productServiceImp = new ProductServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void getAllProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> listProducts = productServiceImp.findAll();
        request.setAttribute("listProducts", listProducts);
    }

    public boolean deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        return productServiceImp.remove(request.getParameter("productId"));
    }

    public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productServiceImp.findElementById(request.getParameter("productId"));
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("views/product/updateProduct.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public boolean createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = false;
        Product product = new Product();
        try {
            String inputPrice = request.getParameter("price");
            String inputProductId = request.getParameter("productId");
            String inputProductName = request.getParameter("productName");
            String inputCatalogId = request.getParameter("catalogId");
            String inputTitle = request.getParameter("title");
            if (inputProductId.equals("") || inputProductName.equals("") || Float.parseFloat(inputPrice) <= 0
                    || inputTitle.equals("")) {
                result = false;
            } else {
                product.setProductId(inputProductId);
                product.setProductName(inputProductName);
                product.setPrice(Float.parseFloat(inputPrice));
                product.setTitle(inputTitle);
                product.setCatalogId(Integer.parseInt(inputCatalogId));
                product.setProductStatus(Boolean.parseBoolean(request.getParameter("productStatus")));
                result = productServiceImp.save(product);
            }
        } catch (NumberFormatException ne) {
            result = false;
        }

        return result;
    }

    public boolean updateProduct(HttpServletRequest request, HttpServletResponse response) {
        boolean result = false;
        Product product = new Product();
        try {
            String inputPrice = request.getParameter("price");
            String inputProductId = request.getParameter("productId");
            String inputProductName = request.getParameter("productName");
            String inputCatalogId = request.getParameter("catalogId");
            String inputTitle = request.getParameter("title");
            if (inputProductName.equals("") || Float.parseFloat(inputPrice) <= 0
                    || inputTitle.equals("")) {
                result = false;
            } else {
                product.setProductId(inputProductId);
                product.setProductName(inputProductName);
                product.setPrice(Float.parseFloat(inputPrice));
                product.setTitle(inputTitle);
                product.setCatalogId(Integer.parseInt(inputCatalogId));
                product.setProductStatus(Boolean.parseBoolean(request.getParameter("productStatus")));
                result = productServiceImp.update(product);
            }
        } catch (NumberFormatException ne) {
            result = false;
        }

        return result;
    }

    public boolean findProductByPriceRange(HttpServletRequest request, HttpServletResponse response) {
        String inputFromPrice = request.getParameter("fromPrice");
        String inputToPrice = request.getParameter("toPrice");
        boolean result = false;
        if (!inputToPrice.equals("") && !inputFromPrice.equals("")
                && Float.parseFloat(inputFromPrice) > 0 && Float.parseFloat(inputToPrice) > 0
                && Float.parseFloat(inputToPrice) > Float.parseFloat(inputFromPrice)) {
            List<Product> listProducts = ProductRepositoryImp.findProductByPriceRange(Float.parseFloat(inputFromPrice), Float.parseFloat(inputToPrice));
            request.setAttribute("listProducts", listProducts);
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
