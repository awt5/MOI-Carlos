/*
 *   Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.core;

import org.jalasoft.moi.model.cplusplus.CppCommandBuilder;
import org.jalasoft.moi.model.csharp.CsharpCommandBuilder;
import org.jalasoft.moi.model.java.JavaCommandBuilder;
import org.jalasoft.moi.model.python.PythonCommandBuilder;
import org.jalasoft.moi.model.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

/**
 * Represent the supported languages and give access to the appropriate command builder.
 *
 * @author Lucero Quiroga Perez
 * @version 1.1
 */
public enum Language {

    PYTHON_32(new PythonCommandBuilder(Paths.get(Constant.ROOTPATH.getValue() + "/thirdparty/python/win" +
            "/python32/Portable_Python_3.2.5.1/App/python.exe")), ".py"),
    //PYTHON_32(new PythonCommandBuilder(), ".py"),
    CSHARP(new CsharpCommandBuilder(), ".cs"),
    JAVA(new JavaCommandBuilder(), ".java"),
    CPP(new CppCommandBuilder(), ".cpp");

    private static Logger LOGGER = LoggerFactory.getLogger(Language.class);
    private final ICommandBuilder commandBuilder;
    private final String fileExtention;

    /**
     * Set the command builder of a specific language and version.
     *
     * @param commandBuilder contains a ICommandBuilder
     */
    Language(ICommandBuilder commandBuilder, String fileExtention) {
        this.commandBuilder = commandBuilder;
        this.fileExtention = fileExtention;
    }

    /**
     * Contains the command builder specific to the language.
     *
     * @return ICommandBuilder to builds commands
     */
    public ICommandBuilder getCommandBuilder() {
        LOGGER.info("Use {}", this.commandBuilder.toString());
        return commandBuilder;
    }

    public String getFileExtention() {
        return fileExtention;
    }
}
