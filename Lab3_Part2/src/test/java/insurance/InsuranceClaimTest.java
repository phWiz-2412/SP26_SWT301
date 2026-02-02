package insurance;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InsuranceClaimTest {

    @Test
    void testProcessClaimSuccess() {
        InsuranceClaim claim = new InsuranceClaim("C001", 1000);
        boolean result = claim.processClaim("Approved");
        assertTrue(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    void testProcessClaimFailWhenNotPending() {
        InsuranceClaim claim = new InsuranceClaim("C002", 1000);
        claim.processClaim("Rejected");
        boolean result = claim.processClaim("Approved");
        assertFalse(result);
    }

    @Test
    void testCalculatePayoutApproved() {
        InsuranceClaim claim = new InsuranceClaim("C003", 1000);
        claim.processClaim("Approved");
        assertEquals(850, claim.calculatePayout());
    }

    @Test
    void testCalculatePayoutNotApproved() {
        InsuranceClaim claim = new InsuranceClaim("C004", 1000);
        assertEquals(0, claim.calculatePayout());
    }

    @Test
    void testUpdateClaimAmountValid() {
        InsuranceClaim claim = new InsuranceClaim("C005", 1000);
        claim.updateClaimAmount(2000);
        assertEquals(2000, claim.getAmount());
    }

    @Test
    void testUpdateClaimAmountInvalid() {
        InsuranceClaim claim = new InsuranceClaim("C006", 1000);
        claim.updateClaimAmount(-500);
        assertEquals(1000, claim.getAmount());
    }
}
