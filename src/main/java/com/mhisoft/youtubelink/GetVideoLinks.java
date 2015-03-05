package com.mhisoft.youtubelink;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetVideoLinks {

             //<a href="/watch?v=QNJL6nfu__Q&amp;list=PLBF29177C5D2545EF&amp;index=3" class="playlist-video clearfix  spf-link ">

    String outFile ="S:\\projects\\mhisoft\\youtube-link\\src\\michael-youtube-page-urls.txt";
    BufferedWriter writer = null;
    static final String prefix = "https://www.youtube.com";

    public GetVideoLinks() {
        try {
            writer = new BufferedWriter(new FileWriter(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFile(String inputFile) {
        try {
            Document doc = Jsoup.parse(new File(inputFile), "UTF-8");
            Elements links = doc.select("a[href]"); // a with href
            for (Element link : links) {
                if (link.hasClass("playlist-video")) {
                    String url = link.attributes().get("href");
                    if (url.startsWith("/watch?")) {
                        writer.write(prefix + url);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        final GetVideoLinks getVideoLinks = new GetVideoLinks();
        String inputFile = "S:\\projects\\mhisoft\\youtube-link\\src\\michael-youtube-page.txt";
        getVideoLinks.parseFile(inputFile);

    }
}
