package com.smmpay.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smmpay.inter.dto.res.ReturnDTO;
import com.smmpay.inter.smmpay.OrderService;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main( String[] args )
	    {
    	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"client-application.xml"});    
    	 OrderService service = (OrderService)context.getBean("orderService");
         Map<String, String> map = new HashMap<String, String>();
         map.put("dealMoney", "1");
         map.put("dealType", "1000");
         map.put("mallOrderId", "1000");
         map.put("orderCreateTime", "1000");
         map.put("buyerMallUserName", "ttglvww@163.com");
         map.put("sellerMallUserName", "196312490@qq.com");
         map.put("productName", "1000");
         map.put("productNum", "1000");
         map.put("productNumUnit", "1000");
         map.put("productDetail", "1000");
         map.put("invoice", "1000");
         map.put("paymentType", "1000");
         map.put("settlementType", "1000");
         ReturnDTO dto = service.insertPayOrder(map);
	     System.out.println(dto.getMsg());
	    }
	 
	 private static void Run1(ClassPathXmlApplicationContext context){
		 
		 
	 }
}
