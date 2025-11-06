package adawardrobe.service;

import adawardrobe.model.Transaction;
import adawardrobe.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(Long.parseLong(id));
    }

    public Optional<Transaction> updateTransaction(String id, Transaction transactionDetails) {
        Optional<Transaction> transaction = transactionRepository.findById(Long.parseLong(id));

        if (transaction.isPresent()) {
            Transaction existingTransaction = transaction.get();
            existingTransaction.setSoldPrice(transactionDetails.getSoldPrice());

            Transaction updatedTransaction = transactionRepository.save(existingTransaction);
            return Optional.of(updatedTransaction);
        }
        return Optional.empty();
    }

    public boolean deleteTransaction(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(Long.parseLong(id));

        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
            return true;
        }
        return false;
    }
}
