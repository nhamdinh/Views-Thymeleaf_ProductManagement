package com.codegym.service;

import com.codegym.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class productDao {
    final static String SELECT_ALL = "SELECT * FROM product_test.tbl_product;";
    final static String INSERT_INTO = "insert into tbl_product(name,price,quantity,description) values (?,?,?,?)";
    final static String DELETE_BY_ID = "delete from tbl_product where id =?";
    final static String GET_BY_ID = "select * from tbl_product where id=?";
    final static String UPDATE_BY_ID = "update tbl_product set name = ?,price=?,quantity=?,description=? where id=?";

    private Connection getDBConnection() {
        Connection conn = null;
        try {
            conn = DBDao.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ArrayList<Product> getProductList() {
        Connection conn = getDBConnection();
        ArrayList<Product> productList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(3));
                product.setQuantity(rs.getInt(4));
                product.setDescription(rs.getString(5));

                productList.add(product);
                System.out.println("da LOAD dc du lieu");

            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void AddProduct(Product product) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println(" da THEM thanh cong");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("da XOA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        Connection conn = getDBConnection();
        Product selectedProduct = new Product();
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                selectedProduct.setId(rs.getInt(1));
                selectedProduct.setName(rs.getString(2));
                selectedProduct.setPrice(rs.getInt(3));
                selectedProduct.setQuantity(rs.getInt(4));
                selectedProduct.setDescription(rs.getString(5));
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("da LAY du theo ID");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedProduct;
    }

    public void updateProductInfo(Product product) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_BY_ID);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("da SUA thanh cong");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
