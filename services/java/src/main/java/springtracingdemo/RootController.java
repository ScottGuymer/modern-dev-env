package springdemo;

import java.net.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RootController {

  @RequestMapping("/")
  public Response get() throws Exception {

    int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
    int requestTime = ThreadLocalRandom.current().nextInt(0, 4);

    try {
      if (sleepTime > 900) {
        throw new Exception("I cant wait that long!");
      }
      Thread.sleep(sleepTime);
    } catch (Exception e) {
    }

    URL url = new URL("http://httpbin.org/delay/" + requestTime);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
  
    return new Response(sleepTime, requestTime);
  }
}
