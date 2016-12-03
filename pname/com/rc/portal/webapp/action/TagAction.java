package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TTagManager;
import com.rc.portal.vo.TTag;
import com.rc.portal.webapp.util.PageResult;
/**
 * 会员，订单，商品  标签
 * @author 刘天灵
 *
 */
public class TagAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private OpenSqlManage opensqlmanage;
	
	private TTagManager ttagmanager ;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult();
	
	private TTag tag = new TTag();
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {
		
	}
	
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TTagManager getTtagmanager() {
		return ttagmanager;
	}

	public void setTtagmanager(TTagManager ttagmanager) {
		this.ttagmanager = ttagmanager;
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

	public TTag getTag() {
		return tag;
	}

	public void setTag(TTag tag) {
		this.tag = tag;
	}

	/**
	 * 列表
	 */
	public String list(){
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		if(StringUtils.isNotEmpty(this.getRequest().getParameter("type"))){
			param.put("type", this.getRequest().getParameter("type"));
		}
		
		System.out.println("---------------type="+ this.getRequest().getParameter("type"));
		if(tag != null)
		{
			if(!com.rc.commons.util.StringUtil.isEmpty(tag.getName()))
			{
				param.put("name", "%"+tag.getName()+"%");
			}	
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(param, "t_tag.ibatorgenerated_countByExample", "t_tag.selectByExampleByPage", rs.getP_curPage(), 20);
		return "list";
	}
	
	/**
	 * 添加
	 */
	public String add(){
		return "add";
	}
	
	
	/**
	 * 保存
	 */
	public String save()throws Exception{

		String [] fileNames = {"name","type"};
		if(isValid(tag,fileNames)){
			this.tag.setCreateDate(new Date());			
			this.tag.setModifyDate(new Date());
			this.ttagmanager.insertSelective(tag);
			this.addFlashMessage(true);
		}
		return "redirect";
	}

	
	/**
	 * 编辑 
	 * @throws SQLException 
	 */
	public String edit() throws SQLException{
		
		tag = this.ttagmanager.selectByPrimaryKey(tag.getId());
		
		return "edit";
	}
	
	/**
	 * 更新 
	 */
	public String update()throws Exception{
		String [] fileNames = {"id","name","type"};
		if(isValid(tag,fileNames)){
			this.tag.setModifyDate(new Date());
			this.ttagmanager.updateByPrimaryKeySelective(tag);
			this.addFlashMessage(true);
		}
		return "redirect";
	}
	
	/**
	 * 刪除
	 * @throws Exception
	 */
	public void delete () throws Exception{
		String [] fileNames = {"id"};
		if(isValid(tag,fileNames)){
			
			this.ttagmanager.deleteByPrimaryKey(tag.getId());
			this.writeObjectToResponse(true, ContentType.application_json);
			//TODO 对应类型的标记数据要去掉关联
			
		}else{
			this.writeObjectToResponse(false, ContentType.application_json);
		}
	}
}
