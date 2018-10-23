package ood.hw3;

public interface HotelGuestMoneyManagement {

    void addBillToWallet(Bill bill);

    void moveMoneyToSafe(String safeCode, Currency currency, double amount);

    void setSafeCode(String oldCode, String newCode);

    Wallet getWalletStatus();

    Safe getSafeStatus();

    void save();

    void undo();
}
