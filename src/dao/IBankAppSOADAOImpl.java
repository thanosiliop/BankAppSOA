package dao;

import model.Account;

import java.util.*;

public class IBankAppSOADAOImpl implements IBankAppSOADAO {
    // private static final ArrayList<Account> accounts = new ArrayList<>();

    private static final HashMap<Long, Account> accounts = new HashMap<>();

//    public Account insert(Account account) {
//        if (account == null) return null;
//        accounts.add(account);
//        return account;
//    }

    public Account insert(Account account) {
        if (account == null) return null;
        accounts.put(account.getId(), account);
        return account;
    }

//    public Account depositUpdate(Account account, double amount) {
//        if (account == null) return null;
//        int positionToUpdate = getIndexByIban(account.getIban());
//        account.setBalance(account.getBalance() + amount);
//        accounts.set(positionToUpdate, account);
//        return account;
//    }

    public Account depositUpdate(Account account, double amount) {
        if (account == null) return null;
        account.setBalance(account.getBalance() + amount);
        accounts.put(account.getId(), account);
        return account;
    }

//    public Account withdrawUpdate(Account account, double amount) {
//        if (account == null) return null;
//        int positionToUpdate = getIndexByIban(account.getIban());
//        account.setBalance(account.getBalance() - amount);
//        accounts.set(positionToUpdate, account);
//        return account;
//    }

    public Account withdrawUpdate(Account account, double amount) {
        if (account == null) return null;
        account.setBalance(account.getBalance() - amount);
        accounts.put(account.getId(), account);
        return account;
    }

//    public void delete(String iban) {
//        accounts.removeIf((account) -> account.getIban().equals(iban));
//    }

    public void delete(String iban) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getValue().getIban().equals(iban)) {
                it.remove();
            }
        }
    }

//    public void delete(long id) {
//        accounts.removeIf(account -> account.getId() == id);
//    }

    public void delete(long id) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getKey() == id) {
                it.remove();
            }
        }
    }

//    public Account get(long id) {
//        int pos = getIndexById(id);
//        if (pos == -1) return null;
//        return accounts.get(pos);
//    }

    public Account get(long id) {
        return accounts.get(id);
    }

//    public Account get(String iban) {
//        int pos = getIndexByIban(iban);
//        if (pos == -1) return null;
//        return accounts.get(pos);
//    }

    public Account get(String iban) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getValue().getIban().equals(iban)) return entry.getValue();
        }
        return null;
    }

//    public List<Account> getAll() {
//        return Collections.unmodifiableList(accounts);
//    }

    public ArrayList<Map.Entry<Long, Account>> getAll() {
        return new ArrayList<>(accounts.entrySet());
    }

//    public boolean ibanExists(String iban) {
//        return getIndexByIban(iban) != -1;
//    }

    public boolean ibanExists(String iban) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getValue().getIban().equals(iban)) return true;
        }
        return false;
    }

//    public boolean userIdExists(long id) {
//        return getIndexById(id) != -1;
//    }

    public boolean userIdExists(long id) {
        return accounts.containsKey(id);
    }

//    public boolean isSsnValid(String ssn) {
//        return ssn != null && getIndexBySsn(ssn) != -1;
//    }

    public boolean isSsnValid(String ssn) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getValue().getHolder().getSsn().equals(ssn)) return true;
        }
        return true;
    }

//    public boolean ssnAlreadyExists(String ssn) {
//        for (int i = 0; i < accounts.size(); i++) {
//            if (accounts.get(i).getHolder().getSsn().equals(ssn)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean ssnAlreadyExists(String ssn) {
        Iterator<Map.Entry<Long, Account>> it = accounts.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Long, Account> entry = it.next();
            if (entry.getValue().getHolder().getSsn().equals(ssn)) return true;
        }
        return false;
    }

    /**
     * Returns the position in the ArrayList Datasource
     * of the Account having the input <code>id</code>.
     * @param id the Account id to be searched for
     * @return the resulting position or -1 if it is not
     * found.
     */
    private int getIndexById(long id) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    /**
     * Returns the position in the ArrayList Datasource
     * of the Account having the input <code>iban</code>.
     * @param iban the Account iban to be searched for
     * @return the resulting position or -1 if it is not
     * found.
     */
    private int getIndexByIban(String iban) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getIban().equals(iban)) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    /**
     * Returns the position in the ArrayList Datasource
     * of the Account having the input <code>ssn</code>.
     * @param ssn the Account ssn to be searched for
     * @return the resulting position or -1 if it is not
     * found.
     */
    private int getIndexBySsn(String ssn) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getHolder().getSsn().equals(ssn)) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
