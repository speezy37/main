package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TIME_END_DESC_DUSK;
import static seedu.address.logic.commands.CommandTestUtil.TIME_START_DESC_DAWN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.SetScheduleCommand;

public class SetScheduleCommandParserTest {

    private static final String EMPTY_SCHEDULE = " " + PREFIX_TIME_END + " "
            + PREFIX_TIME_START + " " + PREFIX_VENUE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetScheduleCommand.MESSAGE_USAGE);

    private SetScheduleCommandParser parser = new SetScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TIME_START_DESC_DAWN, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", SetScheduleCommand.MESSAGE_SCHEDULE_FAIL);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TIME_START_DESC_DAWN, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TIME_END_DESC_DUSK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

    }
}
