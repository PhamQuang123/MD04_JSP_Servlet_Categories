package ra.jsp_servlet_categories.repositoryImp;

import ra.jsp_servlet_categories.model.Product;
import ra.jsp_servlet_categories.repository.Repository;
import ra.jsp_servlet_categories.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImp implements Repository<Product, String> {

    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("price"));
                product.setTitle(rs.getString("title"));
                product.setCatalogId(rs.getInt("catalog_id"));
                product.setProductStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        int check = 0;
        boolean result = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call createProduct(?,?,?,?,?,?,?)}");

            callSt.setString(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setFloat(3, product.getPrice());
            callSt.setString(4, product.getTitle());

            callSt.setInt(5, product.getCatalogId());
            callSt.setBoolean(6, product.isProductStatus());
            callSt.registerOutParameter(7, Types.INTEGER);
            callSt.execute();
            check = callSt.getInt(7);
            if (check == 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        int check = 0;
        boolean result = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateProduct(?,?,?,?,?,?,?)}");

            callSt.setString(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setFloat(3, product.getPrice());
            callSt.setString(4, product.getTitle());

            callSt.setInt(5, product.getCatalogId());
            callSt.setBoolean(6, product.isProductStatus());
            callSt.registerOutParameter(7, Types.INTEGER);
            callSt.execute();
            check = callSt.getInt(7);
            if (check == 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public Product findElementById(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findProductById(?)}");
            callSt.setString(1,id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()){
                product = new Product();
                product.setProductId(rs.getString("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("price"));
                product.setTitle(rs.getString("title"));
                product.setCatalogId(rs.getInt("catalog_id"));
                product.setProductStatus(rs.getBoolean("product_status"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public boolean remove(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call deleteProduct(?,?)}");
            callSt.setString(1, id);
            callSt.setBoolean(2,false);
            callSt.executeUpdate();
            result = true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }

    public static List<Product> findProductByPriceRange(float fromPrice, float toPrice){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findProductByPriceRange(?,?)}");
            callSt.setFloat(1,fromPrice);
            callSt.setFloat(2, toPrice);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("price"));
                product.setTitle(rs.getString("title"));
                product.setCatalogId(rs.getInt("catalog_id"));
                product.setProductStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }
}
