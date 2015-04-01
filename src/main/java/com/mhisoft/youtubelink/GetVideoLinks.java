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
    //  <a href="/watch?v=ecJymYI6MQU&amp;index=4&amp;list=RDz4ZsQCcitfE" class="playlist-video clearfix  spf-link ">

    String outFile ="S:\\projects\\mhisoft\\youtube-link\\src\\playlist.txt";
    BufferedWriter writer = null;
    static final String prefix = "https://www.youtube.com";

    public GetVideoLinks() {
        try {
            writer = new BufferedWriter(new FileWriter(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    //<a href="/watch?v=4-qyuhsS9oE&amp;list=PL3ED03D57E56D7FB4&amp;index=5" class="playlist-video clearfix  spf-link ">
    //<a href="/watch?v=kdWQaecFyyo&amp;list=RDHCA9lw4hVcYwk&amp;index=2" class="playlist-video clearfix  spf-link ">


    public void parseFile(String inputFile) {
        try {
            Document doc = Jsoup.parse(new File(inputFile), "UTF-8");
            Elements links = doc.select("a[href]"); // a with href
            for (Element link : links) {
                if (link.hasClass("spf-link") && link.hasClass("playlist-video")) {
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
                System.out.println("saved to file:" + outFile) ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static String lowQualCmd1 = "z:\\app\\youtube-dl\\youtube-dl.exe -x --audio-format mp3 --audio-quality 128K --no-overwrites -i -a playlist.txt";
    static String highQualCmd = "z:\\app\\youtube-dl\\youtube-dl.exe -x --audio-format mp3 --audio-quality 0 --no-overwrites -i -a playlist.txt";

    public static void main(String[] args) {
        final GetVideoLinks getVideoLinks = new GetVideoLinks();
        String inputFile = "S:\\projects\\mhisoft\\youtube-link\\src\\youtube-html.txt";
        getVideoLinks.parseFile(inputFile);
        System.out.println("128bit audio download");
        System.out.println("\t"+lowQualCmd1);
        System.out.println("High quality audio download:");
        System.out.println(highQualCmd);

    }
}
