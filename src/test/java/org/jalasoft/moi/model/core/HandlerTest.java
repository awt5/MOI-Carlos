/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.core;
import org.jalasoft.moi.model.core.parameters.Parameters;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Result;
import org.jalasoft.moi.model.exceptions.CommandBuildException;
import org.jalasoft.moi.model.exceptions.ParametersException;
import org.jalasoft.moi.model.exceptions.ProcessIDException;
import org.jalasoft.moi.model.exceptions.ResultException;
import org.jalasoft.moi.model.interaction.ProcessCacheTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;



public class HandlerTest {

    private static ProcessCacheTest processCache;

    @BeforeAll
    static void initAll() {
        processCache = new ProcessCacheTest();
    }

    @Test
    public void givenTestParamAndHandlerWhenExecuteParamThenReceiveTheExpectedOutput() throws ResultException, CommandBuildException, ParametersException, ProcessIDException {
        //given
        String expectedResult;
        Parameters testParam = new Params();
        testParam.setFilesPath(Paths.get("./temp/java/"));
        testParam.setLanguage(Language.JAVA);
        Handler runJavaHandler = new Handler(processCache);
        //when
        Result result = runJavaHandler.runProgram(testParam);
        String currentResult = result.getValue();
        expectedResult ="#QuedateEnCasa";
        //then
        assertTrue(currentResult.contains(expectedResult));
    }

    @Test
    public void whenHandlerReceiveParamsBuildCommandAndExecuteThenRun() throws ResultException, CommandBuildException, ParametersException, ProcessIDException {
        //given
        String expectedResult = "#QuedateEnCasa";
        Parameters params = new Params();
        params.setLanguage(Language.PYTHON_32);
        params.setFilesPath(Paths.get("./temp/python/"));
        //when
        Handler csharpHandler = new Handler(processCache);
        Result currentResult = csharpHandler.runProgram(params);
        //then
        assertTrue(currentResult.getValue().contains(expectedResult));
    }

    @Test
    public void cppHandlerTestEmptyPath() {
        //given
        Handler cppHandler = new Handler(processCache);
        Exception exception = assertThrows(ParametersException.class, () -> {
            cppHandler.runProgram(getCppParams(""));
        });
        //when
        String expected = "Invalid or Null parameters gere generated.";
        //then
        assertEquals(expected, exception.getMessage());
    }

    private Params getCppParams(String paramTest) {
        Params params = new Params();
        params.setFilesPath(Paths.get(paramTest));
        params.setLanguage(Language.CPP);
        return params;
    }
}
