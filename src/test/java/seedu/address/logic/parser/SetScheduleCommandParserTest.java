package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import org.junit.Test;

import seedu.address.logic.commands.SetScheduleCommand;

public class SetScheduleCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TIME_END + " "
            + PREFIX_TIME_START + " " + PREFIX_VENUE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetScheduleCommand.MESSAGE_USAGE);

    private SetScheduleCommandParser parser = new SetScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {

    }

    @Test
    public void parse_invalidPreamble_failure() {

    }

    @Test
    public void parse_invalidValue_failure() {

    }
}
