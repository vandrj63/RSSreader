import java.net.URL;
import java.util.Random;
 
import javax.xml.parsers.DocumentBuilder;
 
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;
 
import org.w3c.dom.Element;
 
import org.w3c.dom.Node;
 
import org.w3c.dom.NodeList;
 
 
import javax.swing.*;
 
 
public class RSSReader implements Runnable {
 
String feeds[]={"http://weather.yahooapis.com/forecastrss?w=2502265","http://rss.news.yahoo.com/rss/entertainment","http://www.npr.org/rss/rss.php?id=1014"};
 
 
 
int counter;
 
private URL rssURL;
 
private JTextArea text;
 
 
public RSSReader( JTextArea text) throws Exception {
        counter = 0;
        Random r=new Random();
        int n=Math.abs(r.nextInt())%feeds.length;
this.rssURL = new URL(feeds[n]);
 
this.text = text;
 
text.setText("");
 
 
 
}
 
 
public void setURL(String url) throws Exception {
 
rssURL = new URL(url);
 
}
 
 
public void run() {
 
while (true) {
 
counter++;
 
try {
 
Thread.sleep(5);
 
} catch (InterruptedException e) {
 
}
 
try {
        if(counter%5==0){
              
               Random r=new Random();
               int n=Math.abs(r.nextInt())%feeds.length;
        this.rssURL = new URL(feeds[n]);
        }
 
String tmp = counter + "\n";
 
DocumentBuilder builder = DocumentBuilderFactory.newInstance()
 
.newDocumentBuilder();
 
Document doc = builder.parse(rssURL.openStream());
 
 
NodeList items = doc.getElementsByTagName("item");
 
 
for (int i = 0; i < items.getLength(); i++) {
 
Element item = (Element) items.item(i);
 
tmp += getValue(item, "title");
 
tmp += getValue(item, "description");
 
tmp += getValue(item, "link");
 
tmp += "\n";
 
}
 
text.setText(tmp);
 
} catch (Exception e) {
 
e.printStackTrace();
 
}
 
}
 
}
 
 
public String getValue(Element parent, String nodeName) {
 
return parent.getElementsByTagName(nodeName).item(0).getFirstChild()
 
.getNodeValue();
 
}
 
 
} 