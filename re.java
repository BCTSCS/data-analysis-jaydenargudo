import java.util.*;

public class re {
    public static void main(String[] args) {
        String re = "#\\d{3}-\\d{2}-\\d{4}";
        String re2 = "#[A-Za-z]+";

        String text = "616-33-4567";
        boolean r = text.matches(re);
        // System.err.println(r);

        //read posts.txt
        //Step 1 FileOperator obj
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        // System.out.println(posts);

        //Iterate each post and fine the #\w
        for (String post : posts) {
            boolean result = post.matches(re2);
            System.out.println(post + " : " + result);
        }


        //Check zipcodes to see if they match the pattern
        // String zipPattern = "^\\d{5}(-\\d{4})?$";
        // String[] zipcodes = {"12345-6789", "98765-4321", "8989"};

        // for (String zip : zipcodes){
        //     boolean result = zip.matches(zipPattern);
        //     System.out.println(zip + " is " + result);
        // }

        //Find all words that start with a capital letter
        // String[] words = {"Apple", "banana", "Orange", "grape", "Pineapple"};
        // String capitalWords = "\\b[A-Z][a-zA-Z]*\\b";

        // for (String word : words) {
        //     boolean result = word.matches(capitalWords);
        //     if (result == true) {
        //         System.out.println(word + " is a capitalized word.");
        //     }
        // }


        // boolean result = t.matches(re);

        // System.out.println(result);
    }
}