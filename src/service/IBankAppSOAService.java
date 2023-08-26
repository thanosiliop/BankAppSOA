package service;

import dto.AccountDTO;
import model.Account;
import service.exceptions.*;

import java.util.List;
import java.util.Map;

public interface IBankAppSOAService {
    /**
     * Creates a {@link Account} with the data carried by the {@link AccountDTO}.
     *
     * @param accountDTO  the DTO object that contains the account data
     * @return            the resulting Account
     * @throws IbanNotValidException      if the iban already exists in the Datasource
     * @throws IdNotValidException        if the id already exists in the Datasource
     * @throws SsnAlreadyExistsException  if the ssn already exists in the Datasource
     */
    Account insertAccount(AccountDTO accountDTO) throws IbanNotValidException,
            IdNotValidException, SsnAlreadyExistsException;

    /**
     * Updates the {@link Account} by making the deposit based on the {@link AccountDTO}
     * and the amount.
     *
     * @param accountDTO the DTO object that contains the account data.
     * @param amount     the amount of money to be deposited
     * @return           the resulting Account
     * @throws NegativeAmountException if the amount is negative.
     * @throws SsnNotValidException    if the ssn of the {@link AccountDTO} does not exist in the Datasource
     * @throws IdNotValidException     if the id of the {@link AccountDTO} does not exist in the Datasource
     */
    Account depositUpdate(AccountDTO accountDTO, double amount) throws NegativeAmountException, SsnNotValidException, IdNotValidException;

    /**
     * Updates the {@link Account} by making the withdraw based on the {@link AccountDTO}
     * and the amount.
     * @param accountDTO the DTO object that contains the account data.
     * @param amount     the amount of money to be deposited
     * @return           the resulting account
     * @throws InsufficientBalanceException if the amount is greater than the balance
     * @throws NegativeAmountException      if the amount is negative.
     * @throws SsnNotValidException         if the ssn of the {@link AccountDTO} does not exist in the Datasource
     * @throws IdNotValidException          if the id of the {@link AccountDTO} does not exist in the Datasource
     */
    Account withdrawUpdate(AccountDTO accountDTO, double amount) throws InsufficientBalanceException, NegativeAmountException, SsnNotValidException, IdNotValidException;

    /**
     * Removes the account with the specified iban from the Datasource
     * @param iban  the iban of the {@link Account} needed to be removed
     * @throws IbanNotValidException if the specified iban does not belong to an {@link Account}
     */
    void deleteAccount(String iban) throws IbanNotValidException;

    /**
     * Removes the account with the specified id from the Datasource
     * @param id  the id of the {@link Account} needed to be removed
     * @throws IdNotValidException if the specified id does not belong to an {@link Account}
     */
    void deleteAccount(long id) throws IdNotValidException;

    /**
     * Returns a {@link Account} based on the iban provided.
     * @param iban  the iban of the {@link Account} needed to be returned
     * @return      the resulting {@link Account}
     * @throws IbanNotValidException if the specified iban does not belong to an {@link Account}
     */
    Account getAccount(String iban) throws IbanNotValidException;

    /**
     * Returns a {@link Account} based on the iban provided.
     * @param id  the id of the {@link Account} needed to be returned
     * @return      the resulting {@link Account}
     * @throws IbanNotValidException if the specified id does not belong to an {@link Account}
     */
    Account getAccount(long id) throws IdNotValidException;

    /**
     * Returns all the {@link Account} instances of the Datasource
     * @return the resulting {@link List<Account>}
     */
    List<Map.Entry<Long, Account>> getAll();
}
