# Women's Retail Management System
This is a basic Retail Management System. Users can add, remove, update, and search clothing through this program. This Java-based program uses the Apache Derby database to store categories and clothing items.

## Features

- **User Authentication**: Login system for secure access.
- **Category Management**: Add, update, delete, and view categories.
- **Clothing Item Management**: Manage clothing items with fields like size, color, price, brand, quantity, and category.
- **Discount Management**: Apply discounts by category.
- **Search Functionality**: You can search for clothing items by ID, name, size, color, price, brand, or category.
- **GUI Interface**: An intuitive user interface built with Swing provides a seamless user experience.

## Project Structure

```plaintext
Retail-Management-System/
├── src/
│   ├── wretailsystem/
│   │   ├── Main.java                  # Main entry point
│   │   ├── Category.java              # Model for Category
│   │   ├── CategoryDAO.java           # Data Access Object for Category
│   │   ├── CategoryService.java       # Service for Category
│   │   ├── CategoryUI.java            # UI for Category operations
│   │   ├── ClothingItem.java          # Model for Clothing Item
│   │   ├── ClothingDAO.java           # Data Access Object for Clothing Item
│   │   ├── ClothingService.java       # Service for Clothing Item
│   │   ├── ClothingUI.java            # UI for Clothing operations
│   │   ├── DiscountManagementGUI.java # UI for applying discounts
│   │   ├── SearchGUI.java             # UI for searching items
│   │   ├── Password.java              # User authentication class
│   ├── Test/
│   │   └── ServiceTests.java          # JUnit tests for services
├── .git/                              # Git version control
├── README.md                          # Project description and instructions
└── users.txt                          # File containing user credentials
