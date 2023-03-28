package validators;

import com.my_money.models.Command;
import com.my_money.models.MutualFunds;
import com.my_money.validators.validators.CommandValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandValidatorTest {

    @Test
    void shouldThrowExceptionIfInputParametersAreInsufficientForAllocateCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[2];
        input[0] = "ALLOCATE";
        input[1] = "2000";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(Command.ALLOCATE, input, null));

        assertEquals("ALLOCATE command requires 3 inputs", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfInputParametersAreInsufficientForSipCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[2];
        input[0] = "SIP";
        input[1] = "2000";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(Command.SIP, input, null));

        assertEquals("SIP command requires 3 inputs", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfInputParametersAreInsufficientForChangeCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[2];
        input[0] = "CHANGE";
        input[1] = "2000";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(Command.CHANGE, input, null));

        assertEquals("CHANGE command requires 4 inputs", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfInputParametersAreInsufficientForBALANCECommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[1];
        input[0] = "BALANCE";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(Command.BALANCE, input, null));

        assertEquals("BALANCE command requires 1 inputs", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfInputParametersAreInsufficientForRebalanceCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[2];
        input[0] = "REBALANCE";
        input[1] = "2000";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(Command.REBALANCE, input, null));

        assertEquals("REBALANCE command requires 0 inputs", exception.getMessage());
    }

    @Test
    void shouldNotThrowExceptionIfPortfolioIsNotDefinedForAllocateCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[4];
        input[0] = "ALLOCATE";
        input[1] = "2000";
        input[2] = "2000";
        input[3] = "2000";

        assertDoesNotThrow(() -> validator.validate(Command.ALLOCATE, input, null));
    }
    @Test
    void shouldThrowExceptionIfPortfolioIsNotDefinedForSipCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[4];
        input[0] = "SIP";
        input[1] = "2000";
        input[2] = "2000";
        input[3] = "2000";

        Exception exception = assertThrows(IllegalStateException.class, () -> validator.validate(Command.SIP, input, null));

        assertEquals("Portfolio is not defined", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfPortfolioIsNotDefinedForChangeCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[5];
        input[0] = "CHANGE";
        input[1] = "2000";
        input[2] = "2000";
        input[3] = "2000";
        input[4] = "APRIL";

        Exception exception = assertThrows(IllegalStateException.class, () -> validator.validate(Command.CHANGE, input, null));

        assertEquals("Portfolio is not defined", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfPortfolioIsNotDefinedForBALANCECommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[2];
        input[0] = "BALANCE";
        input[1] = "MARCH";

        Exception exception = assertThrows(IllegalStateException.class, () -> validator.validate(Command.BALANCE, input, null));

        assertEquals("Portfolio is not defined", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfPortfolioIsNotDefinedForRebalanceCommand() {
        CommandValidator validator = new CommandValidator(new MutualFunds());
        String[] input = new String[1];
        input[0] = "REBALANCE";

        Exception exception = assertThrows(IllegalStateException.class, () -> validator.validate(Command.REBALANCE, input, null));

        assertEquals("Portfolio is not defined", exception.getMessage());
    }
}