package com.rc.portal.webapp.action;

import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCutWordManager;
import com.rc.portal.vo.TCutWord;
import com.rc.portal.webapp.util.PageResult;
/**
 * 分词
 * @author liutianling
 *
 */
public class CutWordAction extends BaseAction{

	private static final long serialVersionUID = 2545343541L;
	
	private OpenSqlManage opensqlmanage;
	
	private TCutWordManager tcutwordmanager;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private TCutWord cutWord = new TCutWord();

	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TCutWordManager getTcutwordmanager() {
		return tcutwordmanager;
	}

	public void setTcutwordmanager(TCutWordManager tcutwordmanager) {
		this.tcutwordmanager = tcutwordmanager;
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

	public TCutWord getCutWord() {
		return cutWord;
	}

	public void setCutWord(TCutWord cutWord) {
		this.cutWord = cutWord;
	}
	
	public String list(){
		Map<String,Object> param = new HashMap<String,Object>();
		if(cutWord != null){
			if(!StringUtil.isEmpty(cutWord.getCutword()))
				param.put("cutword", "%"+cutWord.getCutword()+"%");
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(param, "t_cut_word.selectPageCount", "t_cut_word.selectPage", rs.getP_curPage(), rs.getP_pageSize());
		
		return "list";
	}
	
	
	public String  add(){
		return "add";
	}
	
	public String  save() throws Exception{
		String [] fileNames = {"cutword"};
		if(isValid(cutWord,fileNames)){
			this.tcutwordmanager.insertSelective(cutWord);
			this.addFlashMessage(true);
		}
		return "redirect";
	}
	
	public String edit() throws Exception{
		cutWord = this.tcutwordmanager.selectByPrimaryKey(cutWord.getId());
		return "edit";
	}
	
	public String update () throws Exception{
		String [] fileNames = {"id","cutword"};
		if(isValid(cutWord,fileNames)){
			this.tcutwordmanager.updateByPrimaryKeySelective(cutWord);
			this.addFlashMessage(true);
		}
		return "redirect";
	}
	
	public void delete () throws Exception{
		String [] fileNames = {"id"};
		if(isValid(cutWord,fileNames)){
			
			this.tcutwordmanager.deleteByPrimaryKey(cutWord.getId());
			this.writeObjectToResponse(true, ContentType.application_json);
		}else{
			this.writeObjectToResponse(false, ContentType.application_json);
		}
	}
}
