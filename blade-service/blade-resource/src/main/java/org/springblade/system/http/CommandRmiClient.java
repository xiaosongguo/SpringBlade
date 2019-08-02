package org.springblade.system.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.utils.OkHttpUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;

@Slf4j
@Component
@ConfigurationProperties(prefix = "zwx.command")
@Setter
@Getter
public class CommandRmiClient {

	private String host;
	private String content;
	private int port;

	@PostConstruct
	private void init(){
		log.error("host:{},content:{},port:{}",host,content,port);
	}

	public  void connecTion(String...tables){
		JSONObject object=new JSONObject();
		if(tables==null){
			return;
		}
		JSONArray jsonArray=new JSONArray();
		for(String table:tables){
			jsonArray.add(table);
		}
		try {
			object.put("method", content);
			object.put("tables", jsonArray);
			String resp = sendCommand(object.toString());
			if("OK".equals(resp)){
				log.debug("按相关表发送更新通知完成!|"+content);
			}else{
				log.debug("按相关表发送更新通知失败,返回信息："+resp);
			}
		} catch (JSONException e) {
			log.error("按相关表发送更新通知异常:",e);
		}
	}

	public  String sendCommand(String cmd){
		return sendCommand(cmd, null);
	}
	private  String sendCommand(String cmd,String service){
		String url = "";
		if(StringUtil.isEmpty(service)){
			service="all_service";
		}
		try {
			url = "http://"+host+":" + port + "/"+service+"/?req="+ URLEncoder.encode(cmd, "UTF-8") ;
			log.debug(url);
			String result = OkHttpUtil.get(url, null);
			return result;
		} catch (Exception e) {
			log.error(url+"连接发送信息发生异常",e);
		}
		return null;
	}

}
