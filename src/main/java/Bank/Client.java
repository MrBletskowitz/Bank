package Bank;

public class Client {
    private ClientAim aim;
    private float cash;
    private boolean fast;

    public Client(ClientAim aim, float cash, boolean fast) {
        this.aim = aim;
        this.cash = cash;
        this.fast = fast;
    }

    public ClientAim getAim() {
        return aim;
    }

    public void setAim(ClientAim aim) {
        this.aim = aim;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
    }
}
