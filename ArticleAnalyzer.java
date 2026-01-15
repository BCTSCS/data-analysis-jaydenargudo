import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleAnalyzer {

    private ArrayList<String> stopWords;
    private ArrayList<Article> articles;

    public ArticleAnalyzer(){
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop words count: " + stopWords.size());
        articles = new ArrayList<>();
        System.out.println("Articles count: " + articles.size());
    }

    public void addStopWord(String word){
        stopWords.add(word.toLowerCase());
    }

    public void addArticle(Article article){
        articles.add(article);
    }

    public Article parseJson(String jsonLine){
        String link = "";
        String headline = "";
        String category = "";
        String description = "";
        String author = "";
        String date = "";

        Pattern linkP = Pattern.compile("\"link\"\\s*:\\s*\"(.*?)\"");
        Pattern headlineP = Pattern.compile("\"headline\"\\s*:\\s*\"(.*?)\"");
        Pattern categoryP = Pattern.compile("\"category\"\\s*:\\s*\"(.*?)\"");
        Pattern descriptionP = Pattern.compile("\"short_description\"\\s*:\\s*\"(.*?)\"");
        Pattern authorP = Pattern.compile("\"authors\"\\s*:\\s*\"(.*?)\"");
        Pattern dateP = Pattern.compile("\"date\"\\s*:\\s*\"(.*?)\"");

        Matcher linkM = linkP.matcher(jsonLine);
        Matcher headlineM = headlineP.matcher(jsonLine);
        Matcher categoryM = categoryP.matcher(jsonLine);
        Matcher descriptionM = descriptionP.matcher(jsonLine);
        Matcher authorM = authorP.matcher(jsonLine);
        Matcher dateM = dateP.matcher(jsonLine);

        if (linkM.find()){ 
            link = linkM.group(1);
        }
        if (headlineM.find()){ 
            headline = headlineM.group(1);
        }
        if (categoryM.find()){ 
            category = categoryM.group(1);
        }
        if (descriptionM.find()){ 
            description = descriptionM.group(1);
        }
        if (authorM.find()){ 
            author = authorM.group(1);
        }
        if (dateM.find()){ 
            date = dateM.group(1);
        }
        return new Article(author, category, date, description, headline, link);
}

    public String removeStopWords(String text){
        String result = text.toLowerCase();

        for (String word : stopWords) {
            result = result.replaceAll("\\b" + word + "\\b", "");
        }

        return result.replaceAll("\\s+", " ").trim();
    } 
    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        // ArrayList<String> jsonLines = FileOperator.getStringList("News_Category_Dataset_v3.json");
        ArrayList<String> jsonLines = FileOperator.getStringList("data.txt");

        for (String line : jsonLines) {
            Article article = analyzer.parseJson(line);
            article.setDescription(analyzer.removeStopWords(article.getDescription()));
            analyzer.addArticle(article);
        }

        for (Article article : analyzer.articles) {
            System.out.println("Headline: " + article.getHeadline());
            System.out.println("Description: " + article.getDescription());
            System.out.println("----------------------------------");
        }
    }
}