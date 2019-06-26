package query;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryWord {
	private String word = new String();
    public String Query(String w) throws ClientProtocolException, IOException {
    	this.word = w;
    	String meaning = new String();
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpGet getWordMean = new HttpGet("http://dict.youdao.com/search?q=" + word + "&keyfrom=dict.index");
        CloseableHttpResponse response = httpClient.execute(getWordMean);//?????????????
        String result = EntityUtils.toString(response.getEntity());
        response.close();
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"trans-container\">.*?<ul>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1?????????????????<div>??

        if (m1.find()) {
            String means = m1.group();//??????????????????
            Pattern getChinese = Pattern.compile("(?m)<li>(.*?)</li>"); //(?m)?????????
            Matcher m2 = getChinese.matcher(means);

            //System.out.println("????:");
            while (m2.find()) {
            	meaning = m2.group(1);
                //??Java??(.*?)???1????????group(1)
                //System.out.println("\t" + m2.group(1));
            }
        } 
        else {
            meaning = "0";
        }
        
        return meaning;
    }
    
    public QueryWord() {
    	;
    }
}

