# Blogging Platform

A simple Java Swing-based blogging platform that allows users to create, view, and delete blog posts.

## Features

- **Add Blog Posts**: Create new blog entries with title, author, category, and content
- **View All Blogs**: Display all saved blog posts in a formatted list
- **Delete Blogs**: Remove blog posts by title
- **Categories**: Choose from Technology, Travel, Food, and Lifestyle categories
- **Data Persistence**: Blogs are saved to a text file for persistence

## How to Run

1. Navigate to the project directory:
   ```
   cd BloggingPlatform
   ```

2. Compile the Java files:
   ```
   javac src\*.java
   ```

3. Run the application:
   ```
   java src.BlogAppUI
   ```

## Project Structure

```
BloggingPlatform/
├── src/
│   ├── BlogAppUI.java    # Main GUI application
│   ├── Blog.java         # Blog data model
│   └── BlogManager.java  # File operations and data management
├── blogs.txt            # Data storage file (created automatically)
└── README.md           # This file
```

## Requirements

- Java 8 or higher
- No external dependencies required

## Usage

1. **Adding a Blog**: Fill in the title, author, select a category, and write your content. Click "Save Blog".
2. **Viewing Blogs**: Click "View All Blogs" to see all saved entries.
3. **Deleting a Blog**: Enter the exact title of the blog you want to delete and click "Delete".

## Data Storage

Blog data is stored in a simple text file format (`blogs.txt`) with pipe-separated values for easy parsing and portability.