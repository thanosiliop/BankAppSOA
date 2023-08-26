package service;

import dao.IBankAppSOADAO;
import dto.AccountDTO;
import dto.UserDTO;
import model.Account;
import model.User;
import service.exceptions.*;

import java.util.List;
import java.util.Map;

public class BankAppSOAServiceImpl implements IBankAppSOAService {
    private final IBankAppSOADAO dao;

    public BankAppSOAServiceImpl(IBankAppSOADAO dao) { this.dao = dao; }


    @Override
    public Account insertAccount(AccountDTO accountDTO) throws IbanNotValidException,
                            IdNotValidException, SsnAlreadyExistsException {
        Account account;
        try {
            account = new Account();
            mapAccount(account, accountDTO);

            if (dao.ibanExists(accountDTO.getIban())) {
                throw new IbanNotValidException(account.getIban());
            }
            if (dao.userIdExists(accountDTO.getUserDTO().getId())) {
                throw new IdNotValidException(account.getHolder().getId());
            }
            if (dao.ssnAlreadyExists(accountDTO.getUserDTO().getSsn())) {
                throw new SsnAlreadyExistsException((account.getHolder().getSsn()));
            }

            account = dao.insert(account);
        } catch (IbanNotValidException | IdNotValidException | SsnAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }
        return account;
    }

    @Override
    public Account depositUpdate(AccountDTO accountDTO, double amount) throws NegativeAmountException, SsnNotValidException, IdNotValidException {
        Account account;
        try {
            account = new Account();
            mapAccount(account, accountDTO);

            if (amount < 0) {
                throw new NegativeAmountException(amount);
            }
            if (!dao.userIdExists(account.getId())) {
                throw new IdNotValidException(account.getId());
            }
            if (!dao.isSsnValid(account.getHolder().getSsn())) {
                throw new SsnNotValidException(account.getHolder().getSsn());
            }

            account = dao.depositUpdate(account, amount);
        } catch (NegativeAmountException | IdNotValidException e) {
            e.printStackTrace();
            throw e;
        }

        return account;
    }

    @Override
    public Account withdrawUpdate(AccountDTO accountDTO, double amount) throws InsufficientBalanceException, NegativeAmountException, SsnNotValidException, IdNotValidException {
        Account account;
        try {
            account = new Account();
            mapAccount(account, accountDTO);

            if (amount < 0) {
                throw new NegativeAmountException(amount);
            }
            if (amount > account.getBalance()) {
                throw new InsufficientBalanceException();
            }
            if (!dao.userIdExists(account.getId())) {
                throw new IdNotValidException(account.getId());
            }
            if (!dao.isSsnValid(account.getHolder().getSsn())) {
                throw new SsnNotValidException(account.getHolder().getSsn());
            }

            account = dao.withdrawUpdate(account, amount);

        } catch (NegativeAmountException | InsufficientBalanceException | IdNotValidException | SsnNotValidException e) {
            e.printStackTrace();
            throw e;
        }

        return account;
    }

    @Override
    public void deleteAccount(String iban) throws IbanNotValidException {
        Account account;
        try {
            account = new Account();
            account = dao.get(iban);
            if (!dao.ibanExists(iban)) {
                throw new IbanNotValidException(iban);
            }

            dao.delete(iban);
        } catch (IbanNotValidException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteAccount(long id) throws IdNotValidException {
        Account account;
        try {
            account = new Account();
            account = dao.get(id);
            if (!dao.userIdExists(id)) {
                throw new IdNotValidException(id);
            }

            dao.delete(id);
        } catch (IdNotValidException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Account getAccount(String iban) throws IbanNotValidException {
        Account account;
        try {
            account = dao.get(iban);
            if (account == null) {
                throw new IbanNotValidException(iban);
            }

            return account;
        } catch (IbanNotValidException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Account getAccount(long id) throws IdNotValidException {
        Account account;
        try {
            account = dao.get(id);
            if (account == null) {
                throw new IdNotValidException(id);
            }

            return account;
        } catch (IdNotValidException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Map.Entry<Long, Account>> getAll() {
        return dao.getAll();
    }

    private void mapAccount(Account account, AccountDTO accountDTO) {
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        account.setIban(accountDTO.getIban());
        User holder = new User();
        mapUser(holder, accountDTO.getUserDTO());
        account.setHolder(holder);
    }

    private void mapUser(User user, UserDTO userDTO) {
        user.setId(userDTO.getId());
        user.setSsn(userDTO.getSsn());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
    }
}
