package ra.jsp_servlet_categories.serviceImp;

import ra.jsp_servlet_categories.model.Product;
import ra.jsp_servlet_categories.repository.Repository;
import ra.jsp_servlet_categories.repositoryImp.ProductRepositoryImp;
import ra.jsp_servlet_categories.service.Service;

import java.util.List;

public class ProductServiceImp implements Service<Product, String> {
    private Repository<Product, String> repository;

    public ProductServiceImp() {
        repository = new ProductRepositoryImp();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean save(Product product) {

        return repository.save(product);
    }

    @Override
    public Product findElementById(String id) {
        return repository.findElementById(id);
    }

    @Override
    public boolean update(Product product) {
        return repository.update(product);
    }

    @Override
    public boolean remove(String id) {
        return repository.remove(id);
    }

    public List<Product> findProductByPriceRange(float fromPrice, float toPrice){
        return  ProductRepositoryImp.findProductByPriceRange(fromPrice, toPrice);
    }
}
