package org.jalasoft.moi.model.interaction;

import org.jalasoft.moi.model.core.Executer;
import org.jalasoft.moi.model.core.ICacheProvider;
import org.jalasoft.moi.model.core.ICommandBuilder;
import org.jalasoft.moi.model.core.parameters.InputParameters;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Result;
import org.jalasoft.moi.model.exceptions.CommandBuildException;
import org.jalasoft.moi.model.exceptions.InputParametersException;
import org.jalasoft.moi.model.exceptions.ProcessIDException;
import org.jalasoft.moi.model.exceptions.ResultException;

public class BuilderTest {

    private ICacheProvider processCache;

    public BuilderTest(org.jalasoft.moi.model.interaction.ProcessCacheTest processCache) {

        this.processCache = processCache;
    }

    public Result createExecution(Params params) throws ResultException, CommandBuildException, ProcessIDException {
        ICommandBuilder commandBuilder = params.getLanguage().getCommandBuilder();
        String command = commandBuilder.buildCommand(params.getFilesPath());
        System.out.println(command);
        Executer executer = new Executer(processCache);
        return executer.execute(command);
    }

    public Result buildResultWithInput(InputParameters input) throws InputParametersException, ResultException {
        Executer executer = new Executer(processCache);
        return executer.processAnswer(input);
    }
}
