/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.patterns.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.CSHPaymentType;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.patterns.PaymentStratergy;

/**
 *
 * @author Naveen_P08
 */
@Named
@CSHPaymentType
public class CSHPaymentStratergy implements PaymentStratergy {

    @Inject
    @Logging
    Logger logger;

    @Override
    public void doPayment() {
        logger.log(Level.INFO,"Cash Payment Done............");
    }

}
