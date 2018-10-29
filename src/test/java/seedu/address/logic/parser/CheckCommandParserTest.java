package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Nric;
import seedu.address.model.person.password.Password;

public class CheckCommandParserTest {
    private CheckCommandParser parser = new CheckCommandParser();
    private final String inMode = "in";
    private final String outMode = "out";

    @Test
    public void parse_modeSpecified_success() {
        // in mode
        String userInput = " " + PREFIX_NRIC + VALID_NRIC_AMY + " " + PREFIX_PASSWORD
                + VALID_PASSWORD_AMY + " " + PREFIX_MODE + inMode;
        CheckCommand expectedCommand = new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_AMY), new Mode(inMode));
        assertParseSuccess(parser, userInput, expectedCommand);

        // out mode
        userInput = " " + PREFIX_NRIC + VALID_NRIC_AMY + " " + PREFIX_PASSWORD
                + VALID_PASSWORD_AMY + " " + PREFIX_MODE + outMode;
        expectedCommand = new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_AMY), new Mode(outMode));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, CheckCommand.COMMAND_WORD, expectedMessage);

        // no nric
        assertParseFailure(parser, CheckCommand.COMMAND_WORD + " "
            + PREFIX_PASSWORD + VALID_PASSWORD_AMY + " "
            + PREFIX_MODE + inMode, expectedMessage);

        // no password
        assertParseFailure(parser, CheckCommand.COMMAND_WORD + " "
            + PREFIX_NRIC + VALID_NRIC_AMY + " "
            + PREFIX_MODE + inMode, expectedMessage);
    }
}
