package com.PracticeProject.Parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConnectParse
{
	public JSONArray httpgetMethod ( URI uri )
	{
		JSONArray jarray = null ;
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(uri); 
			httpget.addHeader("X-Parse-Application-Id", new ParseConfig().getApplicationID());
			httpget.addHeader("X-Parse-REST-API-Key", new ParseConfig().getRestKey());
			
			HttpResponse httpResponse = httpclient.execute(httpget);
			StringBuilder sb=new StringBuilder();                                                                                                                                                           
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(httpResponse.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				sb.append(line);
				//System.out.println("sb " + line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(sb.toString());
			JSONObject jsonObject = (JSONObject) obj;
			jarray=(JSONArray) jsonObject.get("results");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return jarray ;
	}
	public void httppostMethod(URI uri , StringEntity input)
	{
		try
		{
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost post = new HttpPost(uri);
			post.addHeader("X-Parse-Application-Id", new ParseConfig().getApplicationID());
			post.addHeader("X-Parse-REST-API-Key", new ParseConfig().getRestKey());

			post.setEntity(input);

			HttpResponse httpResponse = httpclient.execute(post);
			StringBuilder sb=new StringBuilder();                                                                                                                                                           
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(httpResponse.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				sb.append(line);
				System.out.println("sb " + line);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public JSONArray httpputMethod(URI uri , StringEntity input)
	{
		try
		{
			HttpClient httpclient = new DefaultHttpClient();

			HttpPut httpPut=new HttpPut(uri);
			httpPut.addHeader("X-Parse-Application-Id", new ParseConfig().getApplicationID());
			httpPut.addHeader("X-Parse-REST-API-Key", new ParseConfig().getRestKey());
			httpPut.addHeader("Content-Type","application/json");

			httpPut.setEntity(input);
			HttpResponse httpResponse = httpclient.execute(httpPut);
			StringBuilder sb=new StringBuilder();                                                                                                                                                           
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(httpResponse.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				sb.append(line);
				System.out.println(sb);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public void httpdeleteMethod(URI uri)
	{
		try 
		{
			HttpClient httpclient = new DefaultHttpClient();

			HttpDelete httpdelete = new HttpDelete(uri );
			httpdelete.addHeader("X-Parse-Application-Id", new ParseConfig().getApplicationID());
			httpdelete.addHeader("X-Parse-REST-API-Key", new ParseConfig().getRestKey());
			httpdelete.addHeader("Content-Type","application/json");

			HttpResponse httpResponse = httpclient.execute(httpdelete);
			StringBuilder sb=new StringBuilder();                                                                                                                                                           
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(httpResponse.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				sb.append(line);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	public static void main(String[] args) throws SQLException 
	{
	/*	Connection connection = null;
		new CompanyDao(connection).getCompanyAliases("Hyclone; 
		dharmacon rna technologies; 
		dharmacon; 
		nanodrop technologies' Thermo Scientific;
		 Thermo Scientific;
		  nanodrop technologies;
		   nunc; 
		   labvision; 
		   Finnzymes");*/
	}
}