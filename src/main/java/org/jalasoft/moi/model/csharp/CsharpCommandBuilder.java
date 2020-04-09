/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package org.jalasoft.moi.model.csharp;

import org.jalasoft.moi.model.core.ICommandBuilder;

import java.nio.file.Path;

/**
 * Builds a command for c# files compilation and execution
 * using the path provided by Params object.
 *
 * @author Carlos Meneses
 *         Mauricio Oroza
 *         Lucero Quiroga Perez
 * @version 1.2
 */
public class CsharpCommandBuilder implements ICommandBuilder {

    private static final String COMPILER_PATH = "C:/Windows/Microsoft.NET/Framework64/v4.0.30319/csc.exe ";
    private static final String OPTIMIZE_OUT = "-optimize -out:";
    private static final String OUTPUT_EXE = "Output.exe ";
    private static final String RUN_OUTPUT = "Output";

    /**
     * Builds a string with the command needed for compilation and execution of multiple files.
     *
     * @param completePath contains the location of the directory of the files
     * @return String of the command builded with the path received
     */
    @Override
    public String buildCommand(Path completePath) {
        return COMPILER_PATH + OPTIMIZE_OUT + completePath + "/" + OUTPUT_EXE + completePath + "/*.cs" + " && " + completePath + "/" + RUN_OUTPUT;
    }
}
