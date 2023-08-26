import dao.IBankAppSOADAO;
import dao.IBankAppSOADAOImpl;
import dto.AccountDTO;
import dto.UserDTO;
import model.Account;
import service.BankAppSOAServiceImpl;
import service.IBankAppSOAService;
import service.exceptions.*;

public class Main {
    public static void main(String[] args) {
        IBankAppSOADAO dao = new IBankAppSOADAOImpl();

        BankAppSOAServiceImpl service = new BankAppSOAServiceImpl(dao);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setFirstname("Thanos");
        userDTO.setLastname("Iliopoulos");
        userDTO.setSsn("12345");
        accountDTO.setUserDTO(userDTO);
        accountDTO.setBalance(10000);
        accountDTO.setIban("67890");

        try {
            Account account = service.insertAccount(accountDTO);
            System.out.println("Inserted account: " + account);
        } catch (IdNotValidException | IbanNotValidException | SsnAlreadyExistsException e) {
            System.out.println("Insertion unsuccessful.");
        }

        AccountDTO accountDTO1 = new AccountDTO(2, new UserDTO(2, "User", "One", "22345"), "67890", 8000);

        try {
            Account account1 = service.insertAccount(accountDTO);
            System.out.println("Inserted account: " + account1);
        } catch (IdNotValidException | IbanNotValidException | SsnAlreadyExistsException e) {
            System.out.println("Insertion unsuccessful.");
        }

        try {
            Account account = service.depositUpdate(accountDTO, 3000);
            System.out.println("Deposit is successful. Your new balance is " + account.getBalance());
        } catch (NegativeAmountException | SsnNotValidException | IdNotValidException e) {
            System.out.println("Deposit was unsuccessful");
        }

        AccountDTO accountDTO2 = new AccountDTO(4, new UserDTO(4, "User", "Two", "13579"), "97531", 5000);

        try {
            Account account = service.insertAccount(accountDTO2);
            System.out.println("Inserted account: " + account);
        } catch (IdNotValidException | IbanNotValidException | SsnAlreadyExistsException e) {
            System.out.println("Insertion unsuccessful.");
        }

        try {
            Account account = service.withdrawUpdate(accountDTO2, 4000);
            System.out.println("Withdrawal is successful. Your new balance is " + account.getBalance());
        } catch (InsufficientBalanceException | NegativeAmountException | SsnNotValidException | IdNotValidException e) {
            System.out.println("Withdrawal unsuccessful.");
        }


    }
}
