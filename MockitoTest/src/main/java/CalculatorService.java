public class CalculatorService {

    private CalculatorRepository repository;

    public CalculatorService(CalculatorRepository repository) {
        this.repository = repository;
    }

    public int add(int a) {
        return a + repository.getBaseValue();
    }
}
