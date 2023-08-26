package dao;

import model.Account;

import java.util.List;
import java.util.Map;

public interface IBankAppSOADAO {
    /**
     * Inserts a new instance of {@link Account} to the Datasource
     *
     * @param account the model object with the account data to insert.
     * @return        the resulting {@link Account}
     */
    Account insert(Account account);

    /**
     * Makes a deposit and updates the {@link Account} to the Datasource
     *
     * @param account the model object with the account data.
     * @param amount  the amount to be deposited.
     * @return        the resulting {@link Account}
     */
    Account depositUpdate(Account account, double amount);

    /**
     * Makes a withdraw and updates the {@link Account} to the Datasource
     *
     * @param account the model object with the account data.
     * @param amount  the amount to be withdrawed.
     * @return        the resulting {@link Account}
     */
    Account withdrawUpdate(Account account, double amount);

    /**
     * Removes {@link Account} from the Datasource.
     * @param iban the iban of the account needed to be removed.
     */
    void delete(String iban);

    /**
     * Removes {@link Account} from the Datasource.
     * @param id the id of the account needed to be removed.
     */
    void delete(long id);

    /**
     * Returns a {@link Account} based on the input id.
     * @param id  the id to search for the account.
     * @return    the resulting account.
     */
    Account get(long id);

    /**
     * Returns a {@link Account} based on the input iban.
     * @param iban the iban to search for the account.
     * @return     the resulting account.
     */
    Account get(String iban);

    /**
     * Returns all the {@link Account} instances from the datasource.
     * @return the resulting {@link List<Account>}.
     */
//    List<Account> getAll();
    List<Map.Entry<Long, Account>> getAll();

    /**
     * Checks if an iban already exists in the Datasource
     * as part of {@link Account}
     * @param iban the iban to search for
     * @return     true if it exists, false otherwise.
     */
    boolean ibanExists(String iban);

    /**
     * Checks if an id already exists in the Datasource
     * as part of {@link Account}
     * @param id the id to search for
     * @return     true if it exists, false otherwise.
     */
    boolean userIdExists(long id);

    /**
     * Checks if an ssn already exists in the Datasource
     * as part of {@link Account}
     * @param ssn the ssn to be checked for validity
     * @return    true if the ssn does not exist thus it is valid,
     *            false otherwise.
     */
    boolean isSsnValid(String ssn);

    /**
     * Checks if an ssn already exists in the Datasource
     * as part of {@link Account}
     * @param ssn the ssn to search for
     * @return    true if the ssn exists, false otherwise.
     */
    boolean ssnAlreadyExists(String ssn);
}
