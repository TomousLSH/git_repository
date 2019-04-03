package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Greet;
import com.virus.helloword.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:
 */ 
@RestController
public class GreetController {
	
	private static final String templete = "Hello,%s";
	private final AtomicLong atomicLong = new AtomicLong();

	@Autowired
	PersonService personService;

	/**
	 * @Description
	 * @Author: lsh
	 * @Date: 2019/3/31 0031
	 * @Param: [name]
	 * @Return: com.example.demo.bean.Greet
	 * @Exception:
	 *
	 */
	@RequestMapping("/greeting")
	public Greet greeting(@RequestParam(value="name",defaultValue="Word") String name){

		Greet greet = new Greet();
		greet.setId(atomicLong.incrementAndGet());
		greet.setContent(String.format(templete, name));
		return greet;
	}

	@RequestMapping("/test1")
	public String test(HttpServletRequest request){
		personService.sayHello();
		String remoteAddr = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int port= request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String requestedSessionId = request.getRequestedSessionId();
		StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();

		HashMap<String, Object> map = new HashMap<>();
		map.put("remoteAddr", remoteAddr);
		map.put("remoteHost", remoteHost);
		map.put("port", port);
		map.put("remoteUser", remoteUser);
		map.put("requestedSessionId", requestedSessionId);
		map.put("requestURL", requestURL);
		map.put("requestURI", requestURI);

		return JSON.toJSONString(map);
	}


	/**
	 * 通过访问的Ip地址得到mac地址
	 * @param ip
	 * @return mac
	 */
	public String getMacByIp(String ip){
		String macAddress = "";
		try {
			java.lang.Process process = Runtime.getRuntime().exec("nbtstat -A "+ip);
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String str = "";
			while ((str=input.readLine())!=null){
				str = str.toUpperCase();
				if(str.indexOf("MAC ADDRESS")>1){
					int start = str.indexOf("=");
					macAddress = str.substring(start+1,str.length()).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  macAddress;
	}

	public String getCurrentTime(){
		//使用Date
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(d);
		return  s;
	}


}
