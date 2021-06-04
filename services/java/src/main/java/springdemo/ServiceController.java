package springdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceController {

  @RequestMapping("/{service}")
  public WrappedResponse get(@PathVariable("service") String service) {

    WrappedResponse response = new WrappedResponse();

    try {
      response.setResponse(sendGet(String.format("http://%s/", service)));
    } catch (Exception e) {
      response.setResponse("Unable to talk to service " + service);
    }

    return response;
  }

  private String sendGet(String url) throws Exception {

    URL obj = new URL(url);
      
    Map<String, String> map = new HashMap<String, String>();
    
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    map.forEach((key,value) -> con.setRequestProperty(key, value));
    
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
    in.close();
    
    return response.toString();
  }
}
