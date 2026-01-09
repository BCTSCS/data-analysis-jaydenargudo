import java.util.*;

public class re {
    public static void main(String[] args) {
        String re = "#\\w\\s";

        String t = "ABBBBBBBA";

        //read posts.txt
        //Step 1 FileOperator obj
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        System.out.println(posts);

        //Iterate each post and fine the #\w
        for (String post : posts) {
            boolean result = post.matches(re);
        }

        boolean result = t.matches(re);

        System.out.println(result);
    }
}