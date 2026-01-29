// import java.util.ArrayList;
// import java.util.Scanner;
// import java.io.File;
// import java.io.IOException;

// public class ReviewCollector
// {
//     private ArrayList<ProductReview> reviewList;
//     private ArrayList<String> productList;

//     public ReviewCollector(){
//         reviewList = new ArrayList<>();
//         productList = new ArrayList<>();
//     }

//     public void loadReviewsFromFile(String filename) throws IOException{
//         Scanner file = new Scanner(new File(filename));

//         while (file.hasNextLine()){
//             String line = file.nextLine();

//             if (line.startsWith("Product: ")){
//                 String productName = line.substring(9); // after "Product: "

//                 if (!file.hasNextLine()){
//                     break;
//                 }

//                 String reviewLine = file.nextLine();
//                 String reviewText = reviewLine.substring(8); // after "Review: "

//                 addReview(new ProductReview(productName, reviewText));
//             }
//         }

//         file.close();
//     }

//     public void addReview(ProductReview prodReview){
//         reviewList.add(prodReview);

//         String name = prodReview.getName();
//         if (!productList.contains(name))
//         {
//             productList.add(name);
//         }
//     }

//     public int getNumGoodReviews(String prodName){
//         int count = 0;
//         for (ProductReview pr : reviewList){
//             if (pr.getName().equals(prodName) && pr.getReview().toLowerCase().contains("best")){
//                 count++;
//             }
//         }
//         return count;
//     }


//     public static void main(String[] args) throws IOException{
//         ReviewCollector reviews = new ReviewCollector();
//         reviews.loadReviewsFromFile("product.txt");
//         System.out.println(reviews.getNumGoodReviews("Sony WH-1000XM5"));
//     }
// }

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReviewCollector {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
        ArrayList<String> lines = FileOperator.getStringList("product.txt");
        
        String productName = "";
        String reviewtext = "";

        for (String line : lines) {
            if (line.startsWith("Product: ")) {
                productName = line.substring(9); 
                productList.add(productName);
            } else if (line.startsWith("Review: ")) {
                reviewtext = line.substring(8);
                reviewList.add(new ProductReview(productName, reviewtext));
            }
        }
     }
    
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
    }  

     public double getSentiments(String wordSearch) {
        ArrayList<String> lines = FileOperator.getStringList("sentiments.txt");
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");
        
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1);
                Double value = Double.parseDouble(matcher.group(2)); 

                if(word.equalsIgnoreCase(wordSearch)){
                    return value;
                }

                // Print the result
                // System.out.println(word + "   ----  " + value);
   
            }
        }
        return 0.0;
    }
    
    public int getNumGoodReviews(String prodName){

        int count = 0;

        for (ProductReview pr : reviewList) {
            if (pr.getName().equalsIgnoreCase(prodName)) {

                String review = pr.getReview().toLowerCase();
                String[] words = review.split("[^a-zA-Z]+");

                double score = 0;

                for (String word : words) {
                    score += getSentiments(word);
                }

                if (score > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ReviewCollector rc = new ReviewCollector();

        System.out.println("Good reviews for iPhone 14 Pro: " + rc.getNumGoodReviews("iPhone 14 Pro"));
        System.out.println("Good reviews for Samsung Galaxy Buds: " + rc.getNumGoodReviews("Samsung Galaxy Buds"));        
        System.out.println("Good reviews for Nintendo Switch OLED: " + rc.getNumGoodReviews("Nintendo Switch OLED"));
}

  
}
