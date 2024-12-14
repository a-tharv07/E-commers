package dao;

import models.Product;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Fetch all products from the database
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

    // Add a new product to the database
    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the product was successfully added
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    // Update an existing product in the database
    public boolean updateProduct(int productId, Product updatedProduct) {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, updatedProduct.getName());
            stmt.setDouble(2, updatedProduct.getPrice());
            stmt.setInt(3, updatedProduct.getQuantity());
            stmt.setInt(4, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the product was successfully updated
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    // Remove a product from the database
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the product was successfully deleted
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
}
