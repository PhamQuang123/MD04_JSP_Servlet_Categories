package ra.jsp_servlet_categories.controller;

import jdk.internal.org.jline.utils.OSUtils;
import ra.jsp_servlet_categories.model.Categories;
import ra.jsp_servlet_categories.service.Service;
import ra.jsp_servlet_categories.serviceImp.CategoriesServiceImp;

import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", value = "/CategoriesController")
public class CategoriesController extends HttpServlet {
    private Service<Categories, Integer> categoriesServiceImp;
    private ProductController productController;

    public CategoriesController() {
        categoriesServiceImp = new CategoriesServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void getAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categories> listCategories = categoriesServiceImp.findAll();
        request.setAttribute("listCategories", listCategories);
    }

    public void findCategoriesById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Categories categories = categoriesServiceImp.findElementById(Integer.parseInt(request.getParameter("catalogId")));
        if (categories != null) {
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("views/updateCategories.jsp").forward(request, response);
        }
    }

    public boolean removeCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = categoriesServiceImp.remove(Integer.parseInt(request.getParameter("catalogId")));
        return result;
    }
    public void findCategoriesByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categories> listCategories = CategoriesServiceImp.findElementByName(request.getParameter("catalogName"));
//       boolean result = false;
        if (listCategories == null){
            getAllCategories(request, response);
//            result = true;
        }else{
            request.setAttribute("listCategories", listCategories);
//            request.getRequestDispatcher("views/home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    public boolean createCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Categories categories = new Categories();
        String inputCatalogName = request.getParameter("catalogName");
//        boolean check = CategoriesServiceImp.isExist(inputCatalogName);
        if (!inputCatalogName.equals("")){
            categories.setCatalogName(inputCatalogName);
        }else {
            request.getRequestDispatcher("views/createCategories.jsp").forward(request, response);
        }
        categories.setDescriptions(request.getParameter("descriptions"));
        categories.setCatalogStatus(Boolean.parseBoolean(request.getParameter("catalogStatus")));
        boolean result = categoriesServiceImp.save(categories);
         return  result;
    }

    public boolean updateCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Categories categories = new Categories();
        String inputName = request.getParameter("catalogName");
        boolean result = false;
        categories.setCatalogId(Integer.parseInt(request.getParameter("catalogId")));
        if (!inputName.equals("")){
            categories.setCatalogName(inputName);
            categories.setDescriptions(request.getParameter("descriptions"));
            categories.setCatalogStatus(Boolean.parseBoolean(request.getParameter("catalogStatus")));
          result  = categoriesServiceImp.update(categories);
        }
        return result;
    }



}
