package com.flipone.enrayaversus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class IP extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip);
        
        getCurrentIP();
        
	}

	public void getCurrentIP () {
		
		final TextView ip = (TextView)findViewById(R.id.Ip);
	    ip.setText("Espera porfavor...");  
	    try {
	            HttpClient httpclient = new DefaultHttpClient();
	            //HttpGet httpget = new HttpGet("http://whatismyip.everdot.org/ip");
	            //HttpGet httpget = new HttpGet("http://whatismyip.com.au/");
	            // HttpGet httpget = new HttpGet("http://www.whatismyip.org/");
	            //HttpGet httpget = new HttpGet("http://www.whatismyip.net/");
	          HttpGet httpget = new HttpGet("http://api.externalip.net/ip/");
	            HttpResponse response;

	            response = httpclient.execute(httpget);

	            //Log.i("externalip",response.getStatusLine().toString());

	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                    long len = entity.getContentLength();
	                    if (len != -1 && len < 1024) {
	                            String str=EntityUtils.toString(entity);
	                            //Log.i("externalip",str);
	                ip.setText(str);
	                    } else {
	                            ip.setText("Error.");
	                            //debug
	                            //ip.setText("Response too long or error: "+EntityUtils.toString(entity));
	                            //Log.i("externalip",EntityUtils.toString(entity));
	                    }            
	            } else {
	                    ip.setText("Null:"+response.getStatusLine().toString());
	            }

	    }
	    catch (Exception e)
	    {
	        ip.setText("Error");
	    }

	}
	
	

}
