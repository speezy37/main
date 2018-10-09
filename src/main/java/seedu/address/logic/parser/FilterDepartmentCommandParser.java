package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterDepartmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterDepartmentCommandParser implements Parser<FilterDepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterDepartmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterDepartmentCommand.MESSAGE_USAGE));
        }

        String[] departmentKeywords = trimmedArgs.split("\\s+");

        return new FilterDepartmentCommand(new DepartmentContainsKeywordsPredicate(Arrays.asList(departmentKeywords)));
    }

}
