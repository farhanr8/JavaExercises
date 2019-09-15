package assignment3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrape {
	
	public static void bonusMain(String[] args) throws IOException {
		boolean scrape = true;
		
		String content = null;
	
		Document doc = Jsoup.connect("http://www.ece.utexas.edu/people/faculty/edison-thomaz").get();
		
		Elements paragraphs = doc.select("div[class = block-content] p");
		
		// Scrape only the first paragraph
	    for(Element p : paragraphs) {
	        if(scrape) {
	        	System.out.println("Scraped input: " + p.text() + "\n");
	        
	        	content = p.text();
	        	scrape = false;
	        }
	    }
	    
	    String path = "src/assignment3/BonusCorpus1.txt";
	    Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
	   
	   
		final GraphPoet nimoy = new GraphPoet(new File("src/assignment3/BonusCorpus1.txt"));
        System.out.println(nimoy.poem(new File("src/assignment3/BonusInput.txt")));
	  }
	
}
