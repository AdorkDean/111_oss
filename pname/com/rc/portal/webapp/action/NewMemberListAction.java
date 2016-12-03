package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CLocationCityManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.vo.CLocationCity;
import com.rc.portal.vo.CLocationCityExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberHobby;
import com.rc.portal.webapp.util.PageResult;

public class NewMemberListAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private List<Map> memberList=new ArrayList<Map>();
	private Integer count=0;
	private Integer status=0;
	private String message="";
	private TMember tmember;
	private TMemberBaseMessageExt base;
	private TMemberHobby hobby;
	private CLocationCityManager clocationcitymanager;
	private TMemberManager tmembermanager;
	private String province;
	private String city;
	private String county;
	private String[] shoppingPreference;
	private String[] childAge;
	private String[] liveStatus;
	public String getNewMemberList() throws Exception{
		Map map=new HashMap();
		boolean flag=false;
		if(model.getMobile()!=null&&!"".equals(model.getMobile().trim())){
			map.put("mobile", model.getMobile().trim());
			if(!"".equals(message)){
				message+="+"+model.getMobile().trim();
			}else{
				message+=model.getMobile().trim();
			}
			flag=true;
		}
		
		if(model.getEmail()!=null&&!"".equals(model.getEmail().trim())){
			map.put("email", model.getEmail().trim());
			if(!"".equals(message)){
				message+="+"+model.getEmail().trim();
			}else{
				message+=model.getEmail().trim();
			}
			flag=true;
		}
		
		if(model.getMemberid()!=null&&!"".equals(model.getMemberid().trim())){
			map.put("memberid", model.getMemberid().trim());
			if(!"".equals(message)){
				message+="+"+model.getMemberid().trim();
			}else{
				message+=model.getMemberid().trim();
			}
			flag=true;
		}
		if(model.getOrderno()!=null&&!"".equals(model.getOrderno().trim())){
			map.put("orderno", model.getOrderno().trim());
			if(!"".equals(message)){
				message+="+"+model.getOrderno().trim();
			}else{
				message+=model.getOrderno().trim();
			}
			flag=true;
		}
		if(flag){
			memberList = opensqlmanage.selectForListByMap_drug(map, "t_member.selectNewMemberList");
			if(memberList!=null&&memberList.size()>0){
				for(Map m:memberList){
					List<Map> liveList=new ArrayList<Map>();
					if( m.get("live_status")!=null){
						String liveStatus = m.get("live_status").toString();
						if(liveStatus.contains(",")){
							String[] ss = liveStatus.split(",");
							for(String s:ss){
								if(!"".equals(s.trim())){
									Map mm=new HashMap();
								mm.put("live", s.trim());
								liveList.add(mm);
								}
							}
						}else{
							Map mm=new HashMap();
							mm.put("live", liveStatus);
							liveList.add(mm);
						}
						
					}
					m.put("liveStatus", liveList);
					
					
					
					
					List<Map> childList=new ArrayList<Map>();
					if( m.get("child_age")!=null){
						String childAge = m.get("child_age").toString();
						if(childAge.contains(",")){
							String[] ss = childAge.split(",");
							for(String s:ss){
								Map m1=new HashMap();
								if(!"".equals(s.trim())){
								m1.put("child", s.trim());
								childList.add(m1);
								}
							}
						}else{
							Map m1=new HashMap();
							m1.put("child", childAge);
							childList.add(m1);
						}
						
					}
					
					List<Map> shoppingList=new ArrayList<Map>();
					if( m.get("shopping_preference")!=null){
						String shop = m.get("shopping_preference").toString();
						if(shop.contains(",")){
							String[] ss = shop.split(",");
							for(String s:ss){
								Map m2=new HashMap();
								if(!"".equals(s.trim())){
									m2.put("shop", s.trim());
									shoppingList.add(m2);
								}
							}
						}else{
							Map m2=new HashMap();
							m2.put("shop", shop);
							shoppingList.add(m2);
						}
						
					}
					m.put("liveStatus", liveList);
					m.put("childAge", childList);
					m.put("shop", shoppingList);
					
					Long areaId=(Long) m.get("area_id");
					if(areaId!=null&&areaId!=0){
						CLocationCity cLocationCity = clocationcitymanager.selectByPrimaryKey(areaId.intValue());
						if(cLocationCity!=null){
							int cityId = cLocationCity.getParentid();
							cLocationCity = clocationcitymanager.selectByPrimaryKey(cityId);
							int provinceId = cLocationCity.getParentid();
							m.put("province", provinceId);
							m.put("city", cityId);//市
							m.put("county", areaId);//区
						}
						
					}
				}
			}
			count = (Integer) opensqlmanage.selectForObjectByMap_drug(map, "t_member.selectNewMemberCount");
		}
		return "getNewMemberList";
	}
	
	
	public void saveMember() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(	province != null && province.trim().length() > 0
				&& city != null && city.trim().length() > 0
				&& county != null && county.trim().length() > 0
				&&!"0".equals(province) && !"0".equals(city) && !"0".equals(county)){
			String provinceStr = province.split("_")[0];
			String cityStr = city.split("_")[0];
			String countyStr = county.split("_")[0];
			
			long areaId = Integer.valueOf(city.split("_")[1]);
			long countyId = Integer.valueOf(county.split("_")[1]);
			base.setArea(provinceStr+"-"+cityStr+"-"+countyStr);
			base.setAreaId(countyId);
		}
		
		if(base != null&&base.getMemberId()!=null){
			if(liveStatus!=null&&liveStatus.length>0){
				String ss=",";
				for(String s:liveStatus){
					ss+=s+",";
				}
				System.out.println("居住状况============================================="+ss);
				base.setLiveStatus(ss);
			}
			if(childAge!=null&&childAge.length>0){
				String ss=",";
				for(String s:childAge){
					ss+=s+",";
				}
				System.out.println("孩子年龄============================================="+ss);
				base.setChildAge(ss);
			}
			//对于checkbox的特殊处理
			if(base.getLiveStatus() != null && base.getLiveStatus().trim().length() > 0){
				base.setLiveStatus(","+base.getLiveStatus().replace(" ", "")+",");
			}
			if(base.getChildAge() != null && base.getChildAge().trim().length() > 0){
				base.setChildAge(","+base.getChildAge().replace(" ", "")+",");
			}
			//对于input的trim处理
			if(base.getNickName() != null){
				base.setNickName(base.getNickName().trim());
			}
			if(base.getRealName() != null){
				base.setRealName(base.getRealName().trim());
			}
			if(base.getAddress() != null){
				base.setAddress(base.getAddress().trim());
			}
			if(base.getCompanyName() != null){
				base.setCompanyName(base.getCompanyName().trim());
			}
			if(base.getPosition() != null){
				base.setPosition(base.getPosition().trim());
			}
			if(base.getGraduationSchool() != null){
				base.setGraduationSchool(base.getGraduationSchool().trim());
			}
		}
		if(hobby!=null&&hobby.getMemberId()!=null){
			if(shoppingPreference!=null&&shoppingPreference.length>0){
				String ss=",";
				for(String s:shoppingPreference){
					ss+=s+",";
				}
				System.out.println("购物偏好============================================="+ss);
				hobby.setShoppingPreference(ss);
			}
		}
		flag = tmembermanager.saveMember(tmember,base,hobby);
		write.write(flag.toString());
		write.close();
	}
	
	/**
	 * 启用禁用用户
	 * @throws Exception 
	 * @throws Exception
	 */
	public void isuse() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getMemberid()!=null&&!"".equals(model.getMemberid().trim())){
			TMember member = tmembermanager.selectByPrimaryKey(new Long(model.getMemberid().trim()));
			if(member.getStatus()==0){
				member.setStatus(1);
			}else{
				member.setStatus(0);
			}
			flag=tmembermanager.updateByPrimaryKeySelective(member);
		}
		write.write(flag.toString());
		write.close();
	}
	/**
	 * 根据城市查询 区域
	 * @throws Exception
	 */
	public void ajaxGetFirstAreas() throws Exception{
		CLocationCityExample cLocationCityExample = new CLocationCityExample();
		cLocationCityExample.createCriteria().andParentidEqualTo(-1);
	    List<CLocationCity> cityAreaList =this.clocationcitymanager.selectByExample(cLocationCityExample);
	    if(cityAreaList!=null&&cityAreaList.size()>0){
//	    	cLocationCityExample = new CLocationCityExample();
//			cLocationCityExample.createCriteria().andParentidEqualTo(cityAreaList.get(0).getId());
//			cityAreaList = clocationcitymanager.selectByExample(cLocationCityExample);
			this.writeObjectToResponse(cityAreaList,ContentType.application_json);
	    }
	}
	
	/**
	 * 根据城市查询 区域
	 * @throws Exception
	 */
	public void ajaxGetTwoAreaByAreaId() throws Exception{
		String areaId = this.getRequest().getParameter("areaId");
		if(StringUtils.hasText(areaId)){
		    CLocationCityExample cLocationCityExample = new CLocationCityExample();
			cLocationCityExample.createCriteria().andParentidEqualTo(Integer.valueOf(areaId));
			List<CLocationCity> cityAreaList = clocationcitymanager.selectByExample(cLocationCityExample);
			this.writeObjectToResponse(cityAreaList,ContentType.application_json);
		}
	}
	
	
	public class Condition {
		
		private String mobile;
		private String email;
		private String memberid;
		private String orderno;
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMemberid() {
			return memberid;
		}
		public void setMemberid(String memberid) {
			this.memberid = memberid;
		}
		public String getOrderno() {
			return orderno;
		}
		public void setOrderno(String orderno) {
			this.orderno = orderno;
		}
		
		
		
		
	}
	
	
	
	@Override
	public Object getModel() {
		return this.model;
	}

	@Override
	public void setModel(Object o) {
		this.model = (Condition) o;
	}

	public PageWraper getPw() {
		return pw;
	}

	public void setPw(PageWraper pw) {
		this.pw = pw;
	}

	public PageResult getRs() {
		return rs;
	}

	public void setRs(PageResult rs) {
		this.rs = rs;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setMemberList(List<Map> memberList) {
		this.memberList = memberList;
	}


	public List<Map> getMemberList() {
		return memberList;
	}


	public TMember getTmember() {
		return tmember;
	}


	public void setTmember(TMember tmember) {
		this.tmember = tmember;
	}


	public TMemberBaseMessageExt getBase() {
		return base;
	}


	public void setBase(TMemberBaseMessageExt base) {
		this.base = base;
	}


	public TMemberHobby getHobby() {
		return hobby;
	}


	public void setHobby(TMemberHobby hobby) {
		this.hobby = hobby;
	}


	public CLocationCityManager getClocationcitymanager() {
		return clocationcitymanager;
	}


	public void setClocationcitymanager(CLocationCityManager clocationcitymanager) {
		this.clocationcitymanager = clocationcitymanager;
	}


	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}


	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String[] getShoppingPreference() {
		return shoppingPreference;
	}


	public void setShoppingPreference(String[] shoppingPreference) {
		this.shoppingPreference = shoppingPreference;
	}


	public String[] getChildAge() {
		return childAge;
	}


	public void setChildAge(String[] childAge) {
		this.childAge = childAge;
	}


	public String[] getLiveStatus() {
		return liveStatus;
	}


	public void setLiveStatus(String[] liveStatus) {
		this.liveStatus = liveStatus;
	}






	
}
