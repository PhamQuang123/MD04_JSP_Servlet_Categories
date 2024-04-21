package ra.jsp_servlet_categories.repositoryImp;


import ra.jsp_servlet_categories.model.Categories;
import ra.jsp_servlet_categories.repository.Repository;
import ra.jsp_servlet_categories.util.ConnectionDB;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepositoryImp implements Repository<Categories, Integer> {
    @Override
    public List<Categories> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getAllCategories()}");
            listCategories = new ArrayList<>();
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("catalog_id"));
                categories.setCatalogName(rs.getString("catalog_names"));
                categories.setDescriptions(rs.getString("descriptions"));
                categories.setCatalogStatus(rs.getBoolean("catalog_status"));
                listCategories.add(categories);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }


    @Override
    public boolean save(Categories categories) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        int check = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call createCategories(?,?,?,?)}");
            callSt.setString(1, categories.getCatalogName());
            callSt.setString(2, categories.getDescriptions());
            callSt.setBoolean(3, categories.isCatalogStatus());
            callSt.registerOutParameter(4, Types.INTEGER);
            callSt.execute();
            check = callSt.getInt(4);
            if (check == 1 || check == 0){
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
    public boolean update(Categories categories) {
        boolean result = false;
        int check = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateCategories(?,?,?,?,?)}");
            callSt.setInt(1, categories.getCatalogId());
            callSt.setString(2, categories.getCatalogName());
            callSt.setString(3, categories.getDescriptions());
            callSt.setBoolean(4, categories.isCatalogStatus());
            callSt.registerOutParameter(5, Types.INTEGER);
            callSt.execute();
            check = callSt.getInt(5);
            System.out.println(check);
            if (check == 0 ){
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
    public Categories findElementById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories categories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCategoriesById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                categories = new Categories();
                categories.setCatalogId(rs.getInt("catalog_id"));
                categories.setCatalogName(rs.getString("catalog_names"));
                categories.setDescriptions(rs.getString("descriptions"));
                categories.setCatalogStatus(rs.getBoolean("catalog_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories;
    }

    @Override
    public boolean remove(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories categories = null;
        boolean result = false;
        try {
            categories = findElementById(id);
            boolean catalogStatus = false;
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call deleteCategories(?,?)}");
            callSt.setInt(1, id);
            callSt.setBoolean(2, catalogStatus);
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Categories> findElementByName(String catalogName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCategoriesByName(?)}");
            callSt.setString(1, catalogName);
            listCategories = new ArrayList<>();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("catalog_id"));
                categories.setCatalogName(rs.getString("catalog_names"));
                categories.setDescriptions(rs.getString("descriptions"));
                categories.setCatalogStatus(rs.getBoolean("catalog_status"));
                listCategories.add(categories);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }
}
