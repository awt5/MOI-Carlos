/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.exceptions;

import org.jalasoft.moi.controller.services.ProcessService;
import org.jalasoft.moi.model.core.Executer;
import org.junit.Test;

public class InputParametersExceptionTest {

    @Test(expected = InputParametersException.class)
    public void inputParametersExceptionNullTest() throws InputParametersException, ResultException {
        Executer executer = new Executer(new ProcessService());
        executer.processAnswer(null);
    }
}
