// import java.util.ArrayList;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// public class ArticleAnalyzer {

//     private ArrayList<String> stopWords;
//     private ArrayList<Article> articles;

//     public ArticleAnalyzer(){
//         stopWords = FileOperator.getStringList("stopwords.txt");
//         System.out.println("Stop words count: " + stopWords.size());
//         articles = new ArrayList<>();
//         System.out.println("Articles count: " + articles.size());
//     }

//     public void addStopWord(String word){
//         stopWords.add(word.toLowerCase());
//     }

//     public void addArticle(Article article){
//         articles.add(article);
//     }

//     public Article parseJson(String jsonLine){
//         String link = "";
//         String headline = "";
//         String category = "";
//         String description = "";
//         String author = "";
//         String date = "";

//         Pattern linkP = Pattern.compile("\"link\"\\s*:\\s*\"(.*?)\"");
//         Pattern headlineP = Pattern.compile("\"headline\"\\s*:\\s*\"(.*?)\"");
//         Pattern categoryP = Pattern.compile("\"category\"\\s*:\\s*\"(.*?)\"");
//         Pattern descriptionP = Pattern.compile("\"short_description\"\\s*:\\s*\"(.*?)\"");
//         Pattern authorP = Pattern.compile("\"authors\"\\s*:\\s*\"(.*?)\"");
//         Pattern dateP = Pattern.compile("\"date\"\\s*:\\s*\"(.*?)\"");

//         Matcher linkM = linkP.matcher(jsonLine);
//         Matcher headlineM = headlineP.matcher(jsonLine);
//         Matcher categoryM = categoryP.matcher(jsonLine);
//         Matcher descriptionM = descriptionP.matcher(jsonLine);
//         Matcher authorM = authorP.matcher(jsonLine);
//         Matcher dateM = dateP.matcher(jsonLine);

//         if (linkM.find()){ 
//             link = linkM.group(1);
//         }
//         if (headlineM.find()){ 
//             headline = headlineM.group(1);
//         }
//         if (categoryM.find()){ 
//             category = categoryM.group(1);
//         }
//         if (descriptionM.find()){ 
//             description = descriptionM.group(1);
//         }
//         if (authorM.find()){ 
//             author = authorM.group(1);
//         }
//         if (dateM.find()){ 
//             date = dateM.group(1);
//         }
//         return new Article(author, category, date, description, headline, link);
// }

//     public String removeStopWords(String text){
//         String result = text.toLowerCase();

//         for (String word : stopWords) {
//             result = result.replaceAll("\\b" + word + "\\b", "");
//         }

//         return result.replaceAll("\\s+", " ").trim();
//     } 
//     public static void main(String[] args) {
//         ArticleAnalyzer analyzer = new ArticleAnalyzer();
//         // ArrayList<String> jsonLines = FileOperator.getStringList("News_Category_Dataset_v3.json");
//         ArrayList<String> jsonLines = FileOperator.getStringList("data.txt");

//         for (String line : jsonLines) {
//             Article article = analyzer.parseJson(line);
//             article.setDescription(analyzer.removeStopWords(article.getDescription()));
//             analyzer.addArticle(article);
//         }

//         for (Article article : analyzer.articles) {
//             System.out.println("Headline: " + article.getHeadline());
//             System.out.println("Description: " + article.getDescription());
//             System.out.println("----------------------------------");
//         }
//     }
// }



import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 

    private static ArrayList<String> words;
    private static ArrayList<Double> values;

    public ArticleAnalyzer(){
        stopWords=FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop Word count"+stopWords.size());
        articles=new ArrayList<>();
        words = new ArrayList<>();
        values = new ArrayList<>();
        System.out.println("Articles count"+articles.size());



    }
    public static void main(String[] args) {
    //    ArticleAnalyzer riano = new ArticleAnalyzer();
    //    ArrayList<String> lines= FileOperator.getStringList("data.txt");
       ArrayList<String> sentiments = FileOperator.getStringList("sentiments.txt");
       ArticleAnalyzer analyzer = new ArticleAnalyzer();
       String regex = "([A-Za-z0-9]+),(-?\\d*\\.\\d+)";

       for (String sentiment : sentiments){
            Pattern l = Pattern.compile(regex);
            Matcher lm = l.matcher(sentiment);
            boolean found = lm.find();
            String word = found ? lm.group(1) : "";
            Double value = found ? Double.parseDouble(lm.group(2)) : 0.0;
            System.out.println(word + " -> " + value);
            words.add(word);
            values.add(value);
       }
        // for(String line : lines){
        //     Article a =riano.parseJson(line);
        //     String clean=riano.removeStopWords(a.getDescription());
        //     a.setDescription(clean);
        //     System.out.println(a);
        //     riano.addArticle(a);
        // }
       


    }

    public void addStopWord(String word){

    }

    public void addArticle(Article article){

    }

    public Article parseJson(String jsonLine){
 
        Article result;
        Pattern l = Pattern.compile("\"link\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher lm =l.matcher(jsonLine); //parameter - line of text
        String lt = lm.find() ? lm.group(1) : ""; //extract the destined part

        
        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher hm =h.matcher(jsonLine); //parameter - line of text
        String ht = hm.find() ? hm.group(1) : ""; //extract the destined part
        
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher cm =c.matcher(jsonLine); //parameter - line of text
        String ct = cm.find() ? cm.group(1) : ""; //extract the destined part

        Pattern d = Pattern.compile("\"short_description\"\\s*:\\s*\"(.*?)\"");  //regex to extract words
        Matcher dm =d.matcher(jsonLine); //parameter - line of text
        String dt = dm.find() ? dm.group(1) : ""; //extract the destined part


         Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher am =a.matcher(jsonLine); //parameter - line of text
        String at = am.find() ? am.group(1) : ""; //extract the destined part

         Pattern t = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher tm =t.matcher(jsonLine); //parameter - line of text
        String tt = tm.find() ? cm.group(1) : ""; //extract the destined part

        
        result=new Article(lt, ht, ct, dt, at, tt);

    return result;
}

 
    public String removeStopWords(String text){
        
        // String result="";

        for(String word : stopWords) {
            text = text.replaceAll("\\b" + word + "\\b", "");
        }
  
       
        return text;


    } //remove stop words from Description


}
