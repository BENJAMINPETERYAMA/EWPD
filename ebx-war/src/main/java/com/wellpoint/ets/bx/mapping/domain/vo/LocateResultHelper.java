/*
 * Created on May 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.vo;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateResultHelper {
    
    private Mapping mapping;
    private Variable variable;
    
    /**
     * @return Returns the mapping.
     */
    public Mapping getMapping() {
        return mapping;
    }
    /**
     * @param mapping The mapping to set.
     */
    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }
    /**
     * @return Returns the variable.
     */
    public Variable getVariable() {
        return variable;
    }
    /**
     * @param variable The variable to set.
     */
    public void setVariable(Variable variable) {
        this.variable = variable;
    }
}
