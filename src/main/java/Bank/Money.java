package Bank;

public class Money {
    private Float cash;

    public Money(float cash) {
        this.cash = cash;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public void addCash(float add){
        this.cash += add;
    }

    public void takeCash(float take) throws InterruptedException {
        synchronized (cash){
            while(this.cash < take){
                cash.wait();
            }
        }
        this.cash -= take;
    }
}
