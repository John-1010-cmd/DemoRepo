package com.example.oauthserver;

import com.example.oauthserver.app.ArticleApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    ArticleApp articleApp;

    @Test
    public void testAddArticle() {
        articleApp.addArticle("444");
    }

    @Test
    public void testVoteArticle() {
        articleApp.voteArticle(3,"222");
        articleApp.voteArticle(4,"222");
        articleApp.voteArticle(4,"333");
    }

    @Test
    public void testArticlesSortedByVote() {
        articleApp.getCurrentDate();
        articleApp.articlesSortedByVote();
    }

    @Test
    public void testAddArticleAutoTransation() {
        articleApp.addArticleAutoTransation("555");
    }

    @Test
    public void testAddArticleTransation() {
        articleApp.addArticleTransation("444");
    }

    @Test
    public void test(){
        System.out.println("hello~");
    }
}
