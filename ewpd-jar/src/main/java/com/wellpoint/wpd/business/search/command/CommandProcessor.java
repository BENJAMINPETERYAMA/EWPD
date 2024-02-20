/*
 * CommandProcessor.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.command;

/**
 * Executes multiple Commands in parallel.  
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: CommandProcessor.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class CommandProcessor {
    
    public boolean execute(Command[] commands){
        if(isParallel()){
            int numberOfCommands = commands.length;
            Thread[] threads = new Thread[numberOfCommands - 1];
            for(int i=0;i<threads.length;i++){
                threads[i] = new Thread(commands[i]);
                threads[i].start();
            }
            commands[numberOfCommands - 1].run();
            for(int i=0;i<threads.length;i++){
                try{
                    threads[i].join();
                }catch(InterruptedException ie){
                    //nothing to do.
                }
            }
        }else{
            for(int i=0;i<commands.length;i++){
                commands[i].run();
            }
        }
        return true;
    }
    /**
     * Method to control if the commands will be executed in different threads.  
     * 
     * @return
     */
    protected boolean isParallel(){
        return true;
    }
}
