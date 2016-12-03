package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.portal.dao.TCategoryDAO;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;

public class TCategoryManagerImpl  implements TCategoryManager {
	

    private TCategoryDAO tcategorydao;

    public TCategoryManagerImpl() {
        super();
    }
    public void  setTcategorydao(TCategoryDAO tcategorydao){
        this.tcategorydao=tcategorydao;
    }
    public TCategoryDAO getTcategorydao(){
        return this.tcategorydao;
    }
    public     int countByExample(TCategoryExample example) throws SQLException{
        return tcategorydao. countByExample( example);
    }

    public     int deleteByExample(TCategoryExample example) throws SQLException{
        return tcategorydao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tcategorydao. deleteByPrimaryKey( id);
    }

    public     Long insert(TCategory record) throws SQLException{
        return tcategorydao. insert( record);
    }

    public     Long insertSelective(TCategory record) throws SQLException{
        return tcategorydao. insertSelective( record);
    }

    public     List selectByExample(TCategoryExample example) throws SQLException{
        return tcategorydao. selectByExample( example);
    }

    public     TCategory selectByPrimaryKey(Long id) throws SQLException{
        return tcategorydao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TCategory record, TCategoryExample example) throws SQLException{
        return tcategorydao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TCategory record, TCategoryExample example) throws SQLException{
        return tcategorydao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TCategory record) throws SQLException{
        return tcategorydao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TCategory record) throws SQLException{
        return tcategorydao. updateByPrimaryKey( record);
    }
    
	public int deleteCategory(Long id) throws Exception {
		Integer flag=0;
		if(id!=null&&id!=0){
			TCategoryExample tce=new TCategoryExample();
			tce.createCriteria().andParentIdEqualTo(id);
			List<TCategory> cateList = tcategorydao.selectByExample(tce);
			for(TCategory cate:cateList){
				tcategorydao.deleteByPrimaryKey(cate.getId());
			}
			flag=tcategorydao.deleteByPrimaryKey(id);
		}
		return flag;
	}
	//修改或者添加分类
	public int updateOrInsertCategory(TCategory category) throws Exception {
		Integer flag=0;
		boolean fl=true;
		if(category!=null){
			if(category.getCategoryLevel()==1){
				TCategoryExample tce=new TCategoryExample();
				tce.createCriteria().andCategoryNameEqualTo(category.getCategoryName().trim());
				List<TCategory> l = tcategorydao.selectByExample(tce);
				if(l!=null&&l.size()>0){
					if(category.getId()!=null){
					for(TCategory ca:l){
							if(ca.getId().intValue()!=category.getId().intValue()){
								fl=false;
								break;							
							}
						}
					}else{
						fl=false;
					}
				}
			}
		}
		if(fl){
			if(category!=null&&category.getId()!=null&&category.getId()!=0){
				flag= tcategorydao.updateByPrimaryKeySelective(category);
				/*			if(category.getCategoryLevel()!=4){
	TCategoryExample tce=new TCategoryExample();
	tce.createCriteria().andParentIdEqualTo(category.getId());
	List<TCategory> cateListTemp = tcategorydao.selectByExample(tce);
	for(TCategory ca:cateListTemp){
		ca.setAllParentId(allparentid+","+category.getId());
		tcategorydao.updateByPrimaryKeySelective(cate);
	}
}
				 */		}else{
					 TCategory cate = tcategorydao.selectByPrimaryKey(category.getParentId());//父类信息
					 String allparentid="";
					 if(cate!=null){
						 if(cate.getCategoryLevel()!=1){
							 allparentid=cate.getAllParentId()+cate.getId()+",";//本身类的树路径
						 }else{
							 allparentid=","+cate.getId().toString()+",";
						 }
						 category.setAllParentId(allparentid);
					 }
					 category.setCreateTime(new Date());
					 Long f = tcategorydao.insertSelective(category);
					 flag=f.intValue();
				 }
		}else{
			flag=-1;
		}
		return flag;
	}


}
