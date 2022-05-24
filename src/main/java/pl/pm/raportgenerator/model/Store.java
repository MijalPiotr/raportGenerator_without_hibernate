package pl.pm.raportgenerator.model;

public class Store {
    private int id;
    private String operationType;
    private int amount;

    public Store(String operationType, int amount) {
        this.operationType = operationType;
        this.amount = amount;
    }
    public Store() {}

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Store{" +
                "operationType='" + operationType + '\'' +
                ", amount=" + amount +
                '}';
    }
}

