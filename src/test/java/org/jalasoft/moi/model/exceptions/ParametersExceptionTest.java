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
import org.jalasoft.moi.model.core.Handler;
import org.jalasoft.moi.model.core.ICacheProvider;
import org.jalasoft.moi.model.core.parameters.Params;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParametersExceptionTest {

    @Test
    public void throwsExceptionWhenCommandNullTest() {
        Params params = null;
        ICacheProvider cache = new ProcessService();
        Handler handler = new Handler(cache);
        Exception exception = assertThrows(ParametersException.class, () -> {
            handler.runProgram(params);
        });

        String expected = "Invalid or Null parameters gere generated.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

}
