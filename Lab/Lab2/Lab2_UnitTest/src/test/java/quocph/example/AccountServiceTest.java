package quocph.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import quocph.example.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    @ParameterizedTest(name = "Test {index}: {0}, {1}, {2} => {3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Register account using CSV data")
    void testRegisterAccount(String username,
                             String password,
                             String email,
                             boolean expected) {

        boolean actual = service.registerAccount(username, password, email);
        assertEquals(expected, actual);
    }
}
