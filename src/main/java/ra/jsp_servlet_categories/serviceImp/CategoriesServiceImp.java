package ra.jsp_servlet_categories.serviceImp;

import ra.jsp_servlet_categories.model.Categories;
import ra.jsp_servlet_categories.repository.Repository;
import ra.jsp_servlet_categories.repositoryImp.CategoriesRepositoryImp;
import ra.jsp_servlet_categories.service.Service;

import java.util.Iterator;
import java.util.List;

public class CategoriesServiceImp implements Service<Categories, Integer> {
    private Repository<Categories, Integer> repository;

    public CategoriesServiceImp() {
        repository = new CategoriesRepositoryImp();

    }

    @Override
    public List<Categories> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean save(Categories categories) {
        return repository.save(categories);
    }

    @Override
    public Categories findElementById(Integer id) {
        return repository.findElementById(id);
    }

    @Override
    public boolean update(Categories categories) {
        return repository.update(categories);
    }

    @Override
    public boolean remove(Integer id) {
        return repository.remove(id);
    }

    public static List<Categories> findElementByName(String catalogName){
        return CategoriesRepositoryImp.findElementByName(catalogName);
    }
//    public static boolean isExist(String catalogName){
//        return CategoriesRepositoryImp.isExist(catalogName);
//    }
}
