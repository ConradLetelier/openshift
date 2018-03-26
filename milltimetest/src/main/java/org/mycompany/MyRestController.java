package org.mycompany;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController {
	 @Autowired
	 CamelContext camelContext;

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	String sayHello () {
		String name = "";
		ProducerTemplate producer = camelContext.createProducerTemplate();
        String result = producer.requestBody("direct:hello", name, String.class);
		return "Hello";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hej")
	String sayHej() {
		String name = "";
		ProducerTemplate producer = camelContext.createProducerTemplate();
        String result = producer.requestBody("direct:heej", name, String.class);
		return "Hej!";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/milltime")
	String loginMilltime() {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:API\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <urn:login>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <urn:login>API 1</urn:login>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <urn:password>api123</urn:password>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <urn:instanceid>000507.4t</urn:instanceid>\r\n" + 
				"      </urn:login>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		ProducerTemplate producer = camelContext.createProducerTemplate();
        String result = producer.requestBody("direct:getMilltime", xmlInput, String.class);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	String getPosts() {
		String name = "";
		ProducerTemplate producer = camelContext.createProducerTemplate();
        String response = producer.requestBody("direct:posts", name, String.class);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        ResponseEntity<String> response = restTemplate.exchange("https://reqres.in/api/users", HttpMethod.GET,entity,String.class);
        return response;
	}

	
}
