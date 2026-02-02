package insurance;

public class InsuranceClaim {

    private String claimId;
    private double amount;
    private String claimStatus;

    public InsuranceClaim(String id, double claimAmount) {
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending";
    }

    public boolean processClaim(String statusUpdate) {
        if ("Pending".equals(claimStatus) && statusUpdate != null) {
            claimStatus = statusUpdate;
            return true;
        }
        return false;
    }

    public double calculatePayout() {
        if ("Approved".equals(claimStatus)) {
            return amount * 0.85;
        }
        return 0;
    }

    public void updateClaimAmount(double newAmount) {
        if (newAmount > 0) {
            amount = newAmount;
        }
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public double getAmount() {
        return amount;
    }
}
