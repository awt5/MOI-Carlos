/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.python;

import org.jalasoft.moi.model.core.ICommandBuilder;
import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Parameters;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.utils.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PythonCommandBuilderTest {

    @Test
    public void builderCommandCompileTest() {
        //given
        Parameters params = new Params();
        params.setFilesPath(Paths.get("/home/carlos/test"));
        params.setLanguage(Language.PYTHON_32);
        ICommandBuilder pythonCommandBuilder = params.getLanguage().getCommandBuilder();
        String commandResult = pythonCommandBuilder.buildCommand(params.getFilesPath());
        //when
        String expectedCommand = "python3 -m compileall /home/carlos/test && python3 /home/carlos/test/MainClass.py";
        //then
        assertEquals(expectedCommand, commandResult);
    }
}
