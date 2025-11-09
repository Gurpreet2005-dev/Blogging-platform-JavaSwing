package src;

import java.io.*;
import java.util.*;

public class BlogManager {

    private static final String FILE_PATH = "blogs.txt";

    public static void saveBlog(Blog blog) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(blog.getTitle() + "|" + blog.getAuthor() + "|" + 
                        blog.getCategory() + "|" + blog.getContent() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return blogs;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4);
                if (parts.length == 4) {
                    blogs.add(new Blog(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public static void deleteBlog(String title) {
        List<Blog> blogs = getAllBlogs();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Blog blog : blogs) {
                if (!blog.getTitle().equals(title)) {
                    writer.write(blog.getTitle() + "|" + blog.getAuthor() + "|" + 
                                blog.getCategory() + "|" + blog.getContent() + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
