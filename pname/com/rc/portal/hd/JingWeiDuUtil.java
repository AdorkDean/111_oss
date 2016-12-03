package com.rc.portal.hd;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.rc.commons.util.InfoUtil;

/**
 * 根据经纬度查询区
 * @author WWF
 * @createTime 2016-4-21 下午3:12:34
 */
public class JingWeiDuUtil {
	public static String shengshiquUrl = InfoUtil.getInstance()
			.getInfo("config", "rc.openapi.rc.address.shengshiquUrl");
	public static String gaodeKey = InfoUtil.getInstance().getInfo("config", "rc.openapi.rc.address.gaodekey");
	
	/**
	 * 根据经纬度查区
	 * 
	 * @param valueOf
	 * @param valueOf2
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getAreaShengShiQu(Double longitude, Double latitude) throws ClientProtocolException, IOException {
		String shengshiqu = "";

		HttpClient httpclient = null;
		HttpGet httpget = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		StringBuffer json = null;
		String expiInfo = null;
		httpclient = new DefaultHttpClient();
		httpget = new HttpGet(shengshiquUrl + "&location=" + longitude + "," + latitude + "&key=" + gaodeKey);
		response = httpclient.execute(httpget);
		entity = response.getEntity();
		if (entity != null) {
			json = new StringBuffer();
			json.append(EntityUtils.toString(entity, "UTF-8"));
			expiInfo = json.toString();
			JSONObject jsonObject = JSONObject.fromObject(expiInfo);
			if (jsonObject != null && jsonObject.get("status") != null
					&& jsonObject.get("status").toString().equals("1") && jsonObject.get("regeocode") != null) {
				String json1 = jsonObject.get("regeocode").toString();
				if (json1 != null && !"".equals(json1)) {
					JSONObject jsonObject2 = JSONObject.fromObject(json1);
					if (jsonObject2 != null && jsonObject2.get("addressComponent") != null) {
						JSONObject jsonObject3 = JSONObject.fromObject(jsonObject2.get("addressComponent").toString());
						System.out.println(jsonObject3.toString());
//							"province":"安徽省","city":"安庆市","citycode":"0556","district":"迎江区"
						if (jsonObject3 != null && jsonObject3.get("province") != null&&!"[]".equals(jsonObject3.get("province").toString())) {
							shengshiqu = jsonObject3.get("province").toString()+"-";
							if (jsonObject3 != null && jsonObject3.get("city") != null&&!"[]".equals(jsonObject3.get("city").toString())) {
								shengshiqu += jsonObject3.get("city").toString()+"-";
								if (jsonObject3 != null && jsonObject3.get("district") != null&&!"[]".equals(jsonObject3.get("district").toString())) {
									shengshiqu += jsonObject3.get("district").toString();
									System.out.println("手推[普通订单]经纬度取省市区:"+shengshiqu);
								}else{
									shengshiqu = null;
								}
							}else{
								shengshiqu += jsonObject3.get("province").toString()+"-";
								if (jsonObject3 != null && jsonObject3.get("district") != null&&!"[]".equals(jsonObject3.get("district").toString())) {
									shengshiqu += jsonObject3.get("district").toString();
									System.out.println("手推[普通订单]经纬度取省市区:"+shengshiqu);
								}else{
									shengshiqu = null;
								}
							}
						}else{
							shengshiqu = null;
						}
					}
				}
			}
		}
		return shengshiqu;
		
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		getAreaShengShiQu(116.645538D, 39.885143D);
		getAreaShengShiQu(116.469263D,40.013658D);
	}	
	
}
