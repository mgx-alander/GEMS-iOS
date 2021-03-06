package com.nus.gems;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

import android.os.AsyncTask;
import android.widget.EditText;


public class ServiceController extends AsyncTask<String, Void, String> {
	private final static String SERVICE_URI = "http://invoicesafe.com/main0/Service1.svc/REST";

	@Override
	protected String doInBackground(String... urls) {
		try {

			// Send GET request to <service>/GetPlates
			HttpGet request = new HttpGet(SERVICE_URI + "/GetData1");
			request.setHeader("Accept", "application/json");

			request.setHeader("Content-type", "application/json");

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);

			HttpEntity responseEntity = response.getEntity();

			// Read response data into buffer
			char[] buffer = new char[(int) responseEntity.getContentLength()];
			InputStream stream = responseEntity.getContent();
			InputStreamReader reader = new InputStreamReader(stream);
			reader.read(buffer);
			stream.close();

			//JSONObject for Objects
			//JSONArray for Arrays of Objects
			JSONArray plates = new JSONArray(new String(buffer));

			return plates.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error";
	}

	@Override
	protected void onPostExecute(String result) {
		
	}

}