package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TSysParameter;
import com.rc.portal.vo.TSysParameterExample;
import com.rc.portal.webapp.util.PageResult;

/**
 * 配置文件管理
 *
 */
public class SystemConfigAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private TSysParameter sysp;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TSysParameterManager tsysparametermanager;
	private String syskey;
	/**
	 * 查询key是否唯一
	 */
	public void syscGetKey() throws Exception{
		String keys = getRequest().getParameter("keys");
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(keys)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			TSysParameterExample example = new TSysParameterExample();
			example.createCriteria().andSysKeyEqualTo(keys);
			List list = tsysparametermanager.selectByExample(example);
			if(0<list.size()){
				if(StringUtils.hasText(id)){
					TSysParameter obj = (TSysParameter) list.get(0);
					long ids = obj.getId();
					if(Long.parseLong(id)==ids){
						out.print(0);
					}else {
						out.print(1);
					}
				}else {
					out.print(1);
				}
			}else {
				out.print(0);
			}
			out.close();
		}
	}
	/**
	 * 修改
	 */
	public String syscUpdate() throws Exception{
		tsysparametermanager.updateByPrimaryKeySelective(sysp);
		return "sysc_add";
	}
	/**
	 * 删除
	 */
	public void syscDelete() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			int n = tsysparametermanager.deleteByPrimaryKey(Long.parseLong(id));
			out.print(n);
			out.close();
		}
	}
	/**
	 * 视图跳转    0 查看  1 修改
	 */
	public String syscGetView() throws Exception{
		String flag = getRequest().getParameter("flag");  // 0 查看  1 修改
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			sysp = tsysparametermanager.selectByPrimaryKey(Long.parseLong(id));
		}
		this.getRequest().setAttribute("flag",flag);
		return "get_view";
	}
	
	/**
	 * 运营使用
	 * 视图跳转    0 查看  1 修改
	 */
	public String syscCouponGetView() throws Exception{
		String flag = getRequest().getParameter("flag");  // 0 查看  1 修改
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			sysp = tsysparametermanager.selectByPrimaryKey(Long.parseLong(id));
		}
		this.getRequest().setAttribute("flag",flag);
		return "get_view_coupon";
	}
	
	/**
	 * 运营使用
	 * 修改
	 */
	public String syscUpdateCoupon() throws Exception{
		tsysparametermanager.updateByPrimaryKeySelective(sysp);
		return "sysc_coupon";
	}
	
	/**
	 * 添加页面跳转
	 */
	public String syscAddPage() throws Exception{
		return "sysc_add_page";
	}
	/**
	 * 添加
	 */
	
	public String syscAdd() throws Exception{
		tsysparametermanager.insert(sysp);
		return "sysc_add";
	}
	/**
	 * 分页查询
	 */
	public String syscList() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		if(StringUtils.hasText(syskey)){
			map.put("syskey", syskey);
		}
		rs.setP_pageSize(10);
	    pw = opensqlmanage.selectForPageByMap_drug(map, "t_sys_parameter.query_record_count", "t_sys_parameter.query_record", rs.getP_curPage(), rs.getP_pageSize());
		return "sysc_list";
	}
	
	/**
	 * 系统管理优惠劵发放
	 * @return
	 * @throws Exception
	 */
	public String sysCoupon() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		String sysCoupon = tsysparametermanager.getKeys("couponKeys_id");
		if(null != sysCoupon){
			map.put("sysCoupon", sysCoupon);
			pw = opensqlmanage.selectForPageByMap_drug(map, "t_sys_parameter.get_coupon_sys_count", "t_sys_parameter.get_coupon_sys", rs.getP_curPage(), rs.getP_pageSize());
		}
	    return "sysc_coupon_list";
	}
	
	
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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
	public String getSyskey() {
		return syskey;
	}
	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}
	public TSysParameter getSysp() {
		return sysp;
	}
	public void setSysp(TSysParameter sysp) {
		this.sysp = sysp;
	}
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
	@Override
	public Object getModel() {
		return null;
	}
	@Override
	public void setModel(Object o) {
	}
}
