package fr.afcepf.ai103.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean
@ViewScoped
public class JsonBean 
{


	private URL url;
	private String libelleProd;
	private String urlImage;
	private String quantite;
	
	
	public JsonBean()
	{
		
	}
	
	
	
	public void recupDonneesProduit(String codeBar)
	{		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = envoyerRequete(codeBar);
		JsonNode jsonNode = null;
		try 
		{
			jsonNode = objectMapper.readTree(json);
			libelleProd = jsonNode.get("product").get("generic_name_fr").asText();
			quantite = jsonNode.get("product").get("quantity").asText();
			urlImage = jsonNode.get("product").get("image_front_thumb_url").asText();
		} 
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	
	
	
	public URL genererURL(String codeBar)
	{
		String urlCodeBar = "https://fr.openfoodfacts.org/api/v0/produit/" + codeBar + ".json";
		
		try { url = new URL(urlCodeBar); } 
		catch (MalformedURLException e) { e.printStackTrace(); }
		return url;
	}
	
	
	public String envoyerRequete(String codeBar)
	{
	URL url = null;
    HttpURLConnection urlConnection = null;
    String result = null;
    try 
    {

    	url = genererURL(codeBar);
    	
    	urlConnection = (HttpURLConnection) url.openConnection(); // Open
        InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
        result = readStream(in); // Read stream
    }
    	catch (MalformedURLException e) { e.printStackTrace(); }
    	catch (IOException e) { e.printStackTrace(); }
    	finally { if (urlConnection != null)
    	urlConnection.disconnect(); }
    
    	return result;
	}
	
	
	
	private String readStream(InputStream is) throws IOException {

	    StringBuilder sb = new StringBuilder();  

	    BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);  

	    for (String line = r.readLine(); line != null; line =r.readLine())
	    {  
	        sb.append(line);  
	    }  

	    is.close();  
	    return sb.toString();
	}
	
	



	public String getLibelleProd() {
		return libelleProd;
	}


	public void setLibelleProd(String libelleProd) {
		this.libelleProd = libelleProd;
	}


	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public String getQuantite() {
		return quantite;
	}


	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}
	
	
	
}
