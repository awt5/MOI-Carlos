/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package org.jalasoft.moi.model.cplusplus;

import org.jalasoft.moi.model.core.ICommandBuilder;

import java.nio.file.Path;

/**
 * This class builds a command for C++ execution.
 *
 * @author Carlos Camacho
 *         Diego Perez
 * @version 1.1
 */
public class CppCommandBuilder implements ICommandBuilder {

    private static final String ALL_FILES = "/*.cpp";
    private static final String CPP_COMPILE_COMMAND = "c++ ";

    /**
     * Build the command for C++.
     *
     * @param path contains the location of the directory or file
     * @return The commands to compile the code.
     */
    @Override
    public String buildCommand(Path path) {
        String outputFilePath = path.toString().replace(".cpp", ".exe");
        String compileCommands = CPP_COMPILE_COMMAND +
                path.getParent().toString().concat(ALL_FILES) +
                " -o " + outputFilePath + " && " + outputFilePath;
        return (compileCommands);
    }
}
