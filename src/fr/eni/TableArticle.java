package fr.eni;

import fr.eni.bll.ArticleManager;
import fr.eni.bo.Article;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/articles")
public class TableArticle {
    private static List<Article> listArticle = new ArrayList<>();
    ArticleManager am =null;

    @GET
    public List<Article> getAffichageArticles() {
        am = new ArticleManager();
        listArticle = am.affichageArticles("cc", "kkk");
        return listArticle;
    }
}
