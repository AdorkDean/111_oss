package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderGoodsRecommendManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TLeaderGoodsRecommend;
import com.rc.portal.vo.TLeaderGoodsRecommendExample;
import com.rc.portal.webapp.util.PageResult;
/**
 *  领秀推荐   注册 药房添加商品
 * @author user3
 *
 */
public class TLeaderGoodsRecommendAction extends BaseAction{
	private static final long serialVersionUID = 5889624095734689577L;
    /**
     * 商品海典编号 
     */
    private String goodno;
	/**
	 * 商品名称
	 */
    private String goodsName;
	/**
	 * 类型  1 注册  2 推荐   
	 */
	private int type ;
	
    private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private OpenSqlManage opensqlmanage;
	
	
	private TLeaderGoodsRecommendManager tleadergoodsrecommendmanager;
	
	//获取系统参数
    private TSysParameterManager tsysparametermanager;
	/**
	 * 注册领袖 赠送商品 list
	 * @return
	 * @throws SQLException 
	 */
	public String registerLeaderGoodsList() throws SQLException{
	    Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtils.hasText(goodno)){
			paramMap.put("goodsno",goodno.trim());
		}
		if(StringUtils.hasText(goodsName)){
			paramMap.put("goodsName",goodsName.trim());
		}
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		paramMap.put("brokerage",brokerage);
		paramMap.put("type", 1);
		
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_leader_goods_recommend.select_leader_goods_count", "t_leader_goods_recommend.select_leader_goods_list", rs.getP_curPage(), rs.getP_pageSize());
		return "register_leader_goods_list";
	}
	/**
	 * 领袖 推荐商品 list
	 * @return
	 * @throws SQLException 
	 */
	public String leaderRecommendGoodsList() throws SQLException{
	    Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtils.hasText(goodno)){
			paramMap.put("goodsno",goodno.trim());
		}
		if(StringUtils.hasText(goodsName)){
			paramMap.put("goodsName",goodsName.trim());
		}
		String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
		paramMap.put("brokerage",brokerage);
		paramMap.put("type", 2);
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_leader_goods_recommend.select_leader_goods_count", "t_leader_goods_recommend.select_leader_goods_list", rs.getP_curPage(), rs.getP_pageSize());
		return "leader_recommend_goods_list";
	}
	/**
	 * 删除领袖推荐注册商品
	 * @throws Exception
	 */
	public void deleteLeaderRecommendGoods(){
		String id = this.getRequest().getParameter("id");
		Map<String,String> resultMap = new HashMap<String,String>();
		String flag ="0";//删除失败
		try{
			if(StringUtils.hasText(id)){
				this.tleadergoodsrecommendmanager.deleteByPrimaryKey(Long.valueOf(id.trim()));
				flag="1";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	/**
	 * 跳转到 领秀注册 推荐商品
	 * @return
	 */
	public String addLeaderRecommendGoods(){
		return "add_leader_recommend_goods";
	}
	
	
	/**
	 * 添加 领秀注册 推荐商品
	 */
	public void saveLeaderRecommendGoods() {
		Map<String,String> resultMap = new HashMap<String,String>();
		String flag ="0";
        try{
        	String goodsid = this.getRequest().getParameter("goodsId");
        	if(StringUtils.hasText(goodsid)){
        		TLeaderGoodsRecommend goodsRecommend = new TLeaderGoodsRecommend();
        		goodsRecommend.setGoodsId(Long.valueOf(goodsid.trim()));
        		goodsRecommend.setCreateDt(new Date());
        		goodsRecommend.setType(type);
        		TLeaderGoodsRecommendExample example = new TLeaderGoodsRecommendExample();
        		example.createCriteria().andGoodsIdEqualTo(goodsRecommend.getGoodsId()).andTypeEqualTo(type);
        		List list =tleadergoodsrecommendmanager.selectByExample(example);
        		if(list != null&&list.size()>0){
        			flag="2";
        		}else{
        			this.tleadergoodsrecommendmanager.insertSelective(goodsRecommend);
            		flag="1";
        		}
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        resultMap.put("flag", flag);
        this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	/**
	 * 查询商品佣金比例
	 */
	public  void queryGoodsBrokerage(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag ="0";
		try{
			if(StringUtils.hasText(goodno)){
				String  brokerage= tsysparametermanager.getKeys("fanyong_default_bili");//返佣比例默认4.5%
				Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("goodsno", goodno.trim());
				paramMap.put("brokerage", brokerage);
				List<Map<String,Object>> resultList =this.opensqlmanage.selectForListByMap(paramMap, "t_leader_goods_recommend.select_t_goods_brokerage_bygoodsno");
				if(resultList!=null&&resultList.size()>0){
					resultMap.put("result", resultList.get(0));
					flag="1";
				}else{
					flag="2";
				}
			}else{
				flag="3";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {
		
	}

	public String getGoodno() {
		return goodno;
	}

	public void setGoodno(String goodno) {
		this.goodno = goodno;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	public TLeaderGoodsRecommendManager getTleadergoodsrecommendmanager() {
		return tleadergoodsrecommendmanager;
	}
	public void setTleadergoodsrecommendmanager(
			TLeaderGoodsRecommendManager tleadergoodsrecommendmanager) {
		this.tleadergoodsrecommendmanager = tleadergoodsrecommendmanager;
	}
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
    
	
	
}
