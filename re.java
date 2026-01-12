import java.util.*;

public class re {
    public static void main(String[] args) {
        String re = "#\\w\\s";

        String t = "ABBBBBBBA";

        //read posts.txt
        //Step 1 FileOperator obj
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        // System.out.println(posts);

        //Iterate each post and fine the #\w
        // for (String post : posts) {
        //     boolean result = post.matches(re);
        //     System.out.println(post + " : " + result);
        // }


        //Check zipcodes to see if they match the pattern
        // String zipPattern = "^\\d{5}(-\\d{4})?$";
        // String[] zipcodes = {"12345-6789", "98765-4321", "8989"};

        // for (String zip : zipcodes){
        //     boolean result = zip.matches(zipPattern);
        //     System.out.println(zip + " is " + result);
        // }

        //Find all words that start with a capital letter
        String[] words = {"Apple", "banana", "Orange", "grape", "Pineapple"};
        String capitalWords = "\\b[A-Z][a-zA-Z]*\\b";

        for (String word : words) {
            boolean result = word.matches(capitalWords);
            if (result == true) {
                System.out.println(word + " is a capitalized word.");
            }
        }


        // boolean result = t.matches(re);

        // System.out.println(result);
    }
}