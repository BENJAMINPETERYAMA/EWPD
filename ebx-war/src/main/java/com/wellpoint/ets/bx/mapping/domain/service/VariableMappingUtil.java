/*
 * Created on Jun 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;

/**
 * @author u19278
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VariableMappingUtil {
	
	

	public static boolean isHippaCodeSlctdListEmpty(List list){
		HippaCodeValue hippaCodeValue = null;
		
		boolean isEmpty = false;
		if(null != list && !list.isEmpty()){
			if(list.size()>0){
				for(int i =0; i<list.size();i++){
					hippaCodeValue = (HippaCodeValue)list.get(i);
					if(hippaCodeValue.getValue() == null ||hippaCodeValue.getValue().equals("")){
						isEmpty = true;
						
					}else{
						isEmpty = false;
						return isEmpty;
					}
				}
			}
		}
		return isEmpty;
	}
}
