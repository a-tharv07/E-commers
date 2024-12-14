import dao.ProductDAO;
import models.Product;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        // Add a new product
        Product newProduct = new Product(0, "Laptop", 75000.00, 10);
        if (productDAO.addProduct(newProduct)) {
            System.out.println("Product added successfully!");
        }

        // Fetch and display all products
        productDAO.getAllProducts().forEach(product -> {
            System.out.println(product.getId() + " - " + product.getName() + " - " + product.getPrice() + " - " + product.getQuantity());
        });

        // Update an existing product
        Product updatedProduct = new Product(0, "Gaming Laptop", 80000.00, 8);
        if (productDAO.updateProduct(1, updatedProduct)) {
            System.out.println("Product updated successfully!");
        }

        // Delete a product
        if (productDAO.deleteProduct(1)) {
            System.out.println("Product deleted successfully!");
        }
    }
}
