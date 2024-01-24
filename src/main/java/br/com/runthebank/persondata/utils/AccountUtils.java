package br.com.runthebank.persondata.utils;
public class AccountUtils {

    public static int generateRandomBranchCode() {
        return (int) (Math.random() * 10000);
    }

    public static int generateAccountCode() {
        return (int) (Math.random() * 1000000);
    }

    public static int generateBankCode() {
        return (int) (Math.random() * 10000);
    }
}
