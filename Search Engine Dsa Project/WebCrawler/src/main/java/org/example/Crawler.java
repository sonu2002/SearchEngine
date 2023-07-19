package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    HashSet<String> urlSet;
    int MAX_DEPTH=2;

    Crawler(){
        urlSet=new HashSet<String>();
    }

    public void getPageTextAndLinks(String url,int depth){
  if(urlSet.contains(url)){
      return;
  }

  if(depth>=MAX_DEPTH){
      return;
  }

  if(urlSet.add(url)){
      System.out.println(url);
  }

  depth++;

  try {

      Document document = Jsoup.connect(url).timeout(5000).get();

//        Indexer works start here
      Indexer indexer=new Indexer(document,url);


      System.out.println(document.title());
      Elements availableLinksOnPage = document.select("a[href]");

      for (Element currentLink : availableLinksOnPage) {
//            here attr converting the Element link into the string representation
          getPageTextAndLinks(currentLink.attr("abs:href"), depth);
      }
  }
  catch(IOException ioException) {
      ioException.printStackTrace();
  }
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();

        crawler.getPageTextAndLinks("http://www.javatpoint.com",1);
    }
}