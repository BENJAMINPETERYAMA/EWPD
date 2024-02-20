/*
 * CustomBoundary.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.dictionary;

import com.keyoti.rapidSpell.AdvancedTextBoundary;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomBoundary extends AdvancedTextBoundary {
    protected boolean isAtNonWhiteSpace(int position){
        
        if(position > 0 && position < theText.length() && 
        		(theText.charAt(position)=='#' ||theText.charAt(position)=='*' ||
        		 theText.charAt(position)=='~' ||theText.charAt(position)=='!' ||    
        		 theText.charAt(position)=='+' ||theText.charAt(position)=='$' ||
        		 theText.charAt(position)=='<' ||theText.charAt(position)=='>' ||				 
        		 theText.charAt(position)=='^' ||theText.charAt(position)=='%')
                )
            return true;//want special character to be included as part of a word
        else
            return super.isAtNonWhiteSpace(position);
    }
}
