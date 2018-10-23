package view;

import ood.hw3.Bill;
import ood.hw3.Wallet;

import java.util.ArrayList;

public class WalletView extends MyListView{
    public WalletView(Wallet wallet){
        this();
        showWallet(wallet);
    }

    public WalletView(){
        super("Wallet");
    }

    public void showWallet(Wallet wallet){
        ArrayList<String> bills = new ArrayList<>();

        for (Bill bill : wallet){
            bills.add(bill.toString());
        }

        setItems(bills);

    }
}
