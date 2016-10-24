package com.smmpay.common.request;

import java.io.File;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smmpay.common.author.AccessToken;
import com.smmpay.common.author.Authory;
import com.smmpay.common.encrypt.Base64;
import com.smmpay.common.encrypt.CertificateCoder;
import com.smmpay.common.encrypt.DesCrypt;
import com.smmpay.common.encrypt.MD5;
import com.smmpay.inter.AuthorService;

public class RequestDataProxy {

	public static boolean getAccessToken(AuthorService authorService){
		Properties pro = null;
		try{
			pro = PropertiesLoaderUtils.loadAllProperties("key.properties");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String key = pro.getProperty("secretKey_pre");
		String id = pro.getProperty("secretId_pre");
		Authory.secretKey = key;
		Authory.secretId = id;
		String json = authorService.getToken(key, id);
		//System.out.println("json:"+json);
		JSONObject jsonToken = JSONObject.parseObject(json);
		if(jsonToken.get("isValid") != null && jsonToken.getString("isValid").equals("1")){
			Authory.token = new AccessToken(jsonToken.getString("accessToken"),jsonToken.getString("uuid"),
					jsonToken.getLong("expireTime"),jsonToken.getInteger("isValid"));
			return true;
		}
		return false;
	}
	
	public static Map<String,String> getRequestParam(String requestJsonStr,String signStr) throws Exception{
		try{
			//签名串
			//String signStr = "3130005135467579"+"100004"+"已审核"+"3130005135467579";
			//获得证书路径
			
			 String basePath = RequestDataProxy.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	         basePath = URLDecoder.decode(basePath,"utf-8");       
	         System.out.println("basePaht:"+basePath);
	         String filePath = "smm_pay_admin.keystore";
			//得到签名
			String sign = CertificateCoder.sign(signStr.getBytes("utf-8"), filePath, "www.smm.cn", "smm_123");
			//key加密
			byte[] jsonStr = DesCrypt.encryptMode(requestJsonStr.getBytes("utf-8"));
			//得到DKey
			byte[] dKey = CertificateCoder.encryptByPrivateKey(DesCrypt.desStr.getBytes("utf-8"), filePath, "www.smm.cn", "smm_123");
			//加密后的字串
			//String encryptStr = Base64.encode(encryptByte);
			//得到MD5
			String MD5Str = MD5.md5(Base64.getBase64(dKey) + Base64.getBase64(jsonStr));
			Map<String,String> map = new LinkedHashMap<String,String>();
			
			if(Authory.token != null){
				//String token = Authory.token.getUserId() + Authory.token.getVeryfyStatus() + Authory.token.getExpireTime();
				map.put("dKey", Base64.getBase64(dKey));//请求服务
				map.put("sign", sign);
				map.put("MD5Str", MD5Str);
				map.put("jsonStr", Base64.getBase64(jsonStr));
				map.put("token", Authory.secretKey+"&"+Authory.secretId+"&"+Authory.token.getUuid()+"&"+Authory.token.getExpireTime());
			    System.out.println("验证成功的map对象"+JSON.toJSONString(map));
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return null;
		
	}
}
