package Bank;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Operationist extends Thread{
    private Queue<Client> clients;
    private ClientGenerator generator;
    public int num;
    public Operationist(int num){
        this.clients = new ArrayDeque<>();
        this.num = num;
    }

    public void setGenerator(ClientGenerator generator){
        this.generator = generator;
    }

    synchronized public void addClient(Client client){
        clients.add(client);
        System.out.println(client.getCash() + " was added");
        notify();
    }

    public int getQuery(){
        return clients.size();
    }

    @Override
    synchronized public void run() {
        while (true) {
            if(clients.size() >= 1){
                    try {
                        Client client = clients.peek();
                        if (client.getAim() == ClientAim.DEPOSIT) {
                            generator.cash.addCash(client.getCash());
                            if(client.isFast()){
                                sleep(400);
                            }
                            else {
                                sleep(1000);
                            }
                            clients.remove();
                            client = null;
                            System.out.println("Operationist " + num + " deposited cash, query = " + clients.size());
                            } else {

                            if(client.getCash() > generator.cash.getCash()){
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if(client.getCash() > generator.cash.getCash()){
                                    clients.remove();
                                    client = null;
                                    System.out.println("Operation is unavailable because bank have no enough money");
                                }
                            }
                            else{
                                generator.cash.takeCash(client.getCash());
                            }
                            if(client.isFast()){
                                sleep(400);
                            }
                            else {
                                sleep(1000);
                            }
                            clients.remove();
                            client = null;
                            System.out.println("Operationist " + num + " tooked cash, query = " + clients.size());
                            }
                } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(generator.cash.getCash() + "------------------------------------------------------------------------------");
        }
    }
}
