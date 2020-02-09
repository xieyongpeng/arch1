package com.sishuok.util.varnish;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;

public class PurgeVarnish {
	public void purge(String url) {
		HttpClient client = new HttpClient();
		HttpMethod method = new PurgeMethod(url);

		try {
			int status = 0;
			status = client.executeMethod(method);
			System.out.println("status===" + status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
	}

	public static void main(String[] args) {
		PurgeVarnish t = new PurgeVarnish();
		t.purge("http://192.168.174.3:1111/index.jsp");
	}
}

class PurgeMethod extends HttpMethodBase {
    public PurgeMethod() {
        super();
        setFollowRedirects(true);
    }
    public PurgeMethod(String url) {
        super(url);
        setFollowRedirects(true);
    }
    public String getName() {
        return "PURGE";
    }
}
