package com.rjrudin.marklogic.appdeployer.command.databases;

import com.rjrudin.marklogic.appdeployer.command.CommandContext;
import com.rjrudin.marklogic.appdeployer.command.SortOrderConstants;

public class DeployTriggersDatabaseCommand extends DeployDatabaseCommand {

    public final static String DATABASE_FILENAME = "triggers-database.json";

    public DeployTriggersDatabaseCommand() {
        setExecuteSortOrder(SortOrderConstants.DEPLOY_TRIGGERS_DATABASE);
        setUndoSortOrder(SortOrderConstants.DELETE_TRIGGERS_DATABASE);
        setDatabaseFilename(DATABASE_FILENAME);
        setCreateForestsOnEachHost(false);
    }

    @Override
    public void execute(CommandContext context) {
        if (context.getAppConfig().isCreateTriggersDatabase()) {
            setCreateDatabaseWithoutFile(true);
        }
        super.execute(context);
    }

    @Override
    public void undo(CommandContext context) {
        if (context.getAppConfig().isCreateTriggersDatabase()) {
            setCreateDatabaseWithoutFile(true);
        }
        super.undo(context);
    }

    protected String buildDefaultDatabasePayload(CommandContext context) {
        return format("{\"database-name\": \"%s\"}", context.getAppConfig().getTriggersDatabaseName());
    }

}
