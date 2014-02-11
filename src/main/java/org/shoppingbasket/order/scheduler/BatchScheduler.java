/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.order.scheduler;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.shoppingbasket.annotation.Logging;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@Startup
public class BatchScheduler {

    @Inject
    @Logging
    Logger logger;

    public void init() {

    }

    @Schedule(hour = "*", minute = "*/1", second = "59", info = "Every 7 second timer")
    public void batchProcess() {
        logger.log(Level.INFO, "Batch Processing started @".concat(new Date().toString()));
        JobOperator jo = BatchRuntime.getJobOperator();
        jo.start("processingJob", new Properties());
        logger.log(Level.INFO, "Batch Processing Completed @ ".concat(new Date().toString()));
    }

}
