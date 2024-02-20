/*
 * Created on Mar 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;


/**
 * Listener class for benefit.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitListener extends WPDBackingBean implements javax.faces.event.ActionListener{
	 
	
	public void processAction(ActionEvent event){
		 UIComponent component = (UIComponent) event.getSource();
		 String buttonId = null;
		 List levelIds = null;
		 if(null != this.getSession().getAttribute("LevelIds")){
		 	levelIds = (List)this.getSession().getAttribute("LevelIds");
		 }
		 else
		 	levelIds = new ArrayList();
		 List lineIds = null;
		 if(null != this.getSession().getAttribute("LineIds")){
		 	lineIds = (List)this.getSession().getAttribute("LineIds");
		 }
		 else
		 	lineIds = new ArrayList();
		 String identifier = null;
		 String levelId = null;
		 String lineId = null;
	        if (component != null) {
	            HtmlCommandButton button = (HtmlCommandButton)component;
	            String id = button.getId();
	            StringTokenizer tokenizer = new StringTokenizer(id,"A");
	            if(tokenizer.hasMoreTokens()){
	        		buttonId = tokenizer.nextToken();
	        		identifier = tokenizer.nextToken();
	        		if("Level".equals(identifier)){
	        			levelId = tokenizer.nextToken().toString();
	        			List newLevelIdsToAdd = new ArrayList();
	        			List newLevelIdsToRemove = new ArrayList();
	        			if(null != levelIds && !levelIds.isEmpty()){
	        				for(int i = 0; i < levelIds.size();i++){
	        					if(null != levelIds.get(i)){
	        						if(!levelIds.get(i).equals(levelId))
	        							newLevelIdsToAdd.add(levelId);
	        						else
	        							newLevelIdsToRemove.add(levelId);
	        					}
	        				}
	        				if(null != newLevelIdsToAdd && !newLevelIdsToAdd.isEmpty())
	        					levelIds.addAll(newLevelIdsToAdd);	  
	        				if(null != newLevelIdsToRemove && !newLevelIdsToRemove.isEmpty())
	        					levelIds.removeAll(newLevelIdsToRemove);	 
	        			}
	        			else{
	        				levelIds.add(levelId);
	        			}
	        		}
	        		else if("Line".equals(identifier)){
	        			levelId = tokenizer.nextToken();
	        			lineId = tokenizer.nextToken();
	        			List newLineIdsToAdd = new ArrayList();
	        			List newLineIdsToRemove = new ArrayList();
	        			if(null != lineIds && !lineIds.isEmpty()){
	        				for(int i = 0; i < lineIds.size();i++){
	        					if(null != lineIds.get(i)){
	        						if(!lineIds.get(i).equals(lineId))
	        							newLineIdsToAdd.add(lineId);
	        						else
	        							newLineIdsToRemove.add(lineId);
	        					}
	        				}
	        				if(null != newLineIdsToAdd && !newLineIdsToAdd.isEmpty())
	        					lineIds.addAll(newLineIdsToAdd);	  
	        				if(null != newLineIdsToRemove && !newLineIdsToRemove.isEmpty())
	        					lineIds.removeAll(newLineIdsToRemove);	  
	        			}
	        			else
	        				lineIds.add(lineId);
	        		}
	            }
	            
	        }
	    this.getSession().setAttribute("LevelIds",levelIds);
	    this.getSession().setAttribute("LineIds",lineIds);
	}
}
