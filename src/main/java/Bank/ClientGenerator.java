package Bank;

import java.util.Random;

public class ClientGenerator extends Thread {
    private Random random;
    private Client myClient;
    public Money cash;
    public Operationist[] operationists;

    public ClientGenerator(Operationist[] operationists, float cash){
        this.cash = new Money(cash);
        random = new Random();
        this.operationists = operationists;
    }

    public Client generateClient(){
        ClientAim aim;
        if(random.nextLong() > 0){
            aim = ClientAim.DEPOSIT;
        } else{
           aim = ClientAim.TAKE;
        }
        float cash = random.nextFloat()  + random.nextInt(100);
        if(cash < 0.0f){
            cash *= -1.0f;
        }
        boolean fast = random.nextBoolean();
        Client client = new Client(aim, cash, fast);
        return client;
    }

    public Client getClient() {
        return myClient;
    }

    private Operationist checkOps(){
        int minQ = 0;
        int minOp = 0;
        for(int i = 0; i < operationists.length; i++){
            if(operationists[i].getQuery() <= minQ){
                minOp = i;
                minQ = operationists[i].getQuery();
            }
        }
        return operationists[minOp];
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < operationists.length; i++){
            operationists[i].setGenerator(this);
            operationists[i].addClient(generateClient());
            operationists[i].start();
        }
        while (true){
            myClient = generateClient();
            checkOps().addClient(myClient);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
