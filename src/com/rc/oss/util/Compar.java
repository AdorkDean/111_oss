package com.rc.oss.util;

import java.util.Comparator;

import com.rc.oss.vo.TAdmModules;

public class Compar implements Comparator<TAdmModules> {
	@Override
	public int compare(TAdmModules arg0, TAdmModules arg1) {
		// TODO Auto-generated method stub
		if(arg0.getDispnum()>arg1.getDispnum()){
			return 1;
		}else if(arg0.getDispnum()<arg1.getDispnum()){
			return -1;
		}else{
			return 0;
		}
	}

}
