package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.TransactionRepository;
import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Student;
import com.example.gestionbibliotheque.entities.Transaction;
import com.example.gestionbibliotheque.service.BookService;
import com.example.gestionbibliotheque.service.TransactionService;
import com.example.gestionbibliotheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private BookService bookService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository theTransactionRepository, BookService theBookService, UserService theUserService) {
        transactionRepository = theTransactionRepository;
        bookService = theBookService;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    // DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
    @Override
    public Transaction findOne(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent())
            return transaction.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
    }

    @Override
    public List<Transaction> findBorrowedBooksByUid(Long uid) {
        return transactionRepository.findByBorrower_UidAndReturnedFalse(uid);

    }

    @Override
    public List<Transaction> findTransactionsByReturnDate(LocalDate returnDate) {
        return transactionRepository.findTransactionsByReturnDate(returnDate);
    }

    @Override
    public List<Transaction> findByBorrower(Long borrowerId) {
        Student borrower = new Student();
        borrower.setUid(borrowerId);
        List<Transaction> transactions = transactionRepository.findByBorrower(borrower);
        if (!transactions.isEmpty())
            return transactions;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
    }


    @Override
    public void issueBook(Transaction transaction) {
        if (transaction.getBook().isAvailable()) {
            transactionRepository.save(transaction);
            bookService.checkAndUpdateBookAvailability(transaction.getBook().getIsbn());
        }
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The book is not available at the moment");
    }

    @Override
    public void returnBook(Transaction transaction) {
        if (!transaction.isReturned()) {
            transaction.setReturned(true);
            transaction.setReturnDate(new Date());
            transactionRepository.save(transaction);
            bookService.checkAndUpdateBookAvailability(transaction.getBook().getIsbn());
        }
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This book is not currently in possession of this student");
    }

    @Override
    public Transaction findTransactionToReturnBook(Transaction transactionParam) {
        return null;
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (transactionRepository.findById(id).get()==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
        transactionRepository.deleteById(id);
        bookService.checkAndUpdateBookAvailability(transactionRepository.findById(id).get().getBook().getIsbn());
    }
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
    public void updateTransaction(Transaction transaction) {
        // Update the transaction in the database
        transactionRepository.save(transaction);

        // Update the Book entity's status
        if (transaction.isReturned()) {
            Book book = transaction.getBook();
            book.setReadyForPickUp(false);
            book.setReservedByUser(null);
            book.setAvailable(true);// Assuming reservedBy is a User object, set it to null
            bookService.updateBook(book);
        }
    }
    @Override
    public List<Transaction> findOverdueTransactions() {
        return transactionRepository.findOverdueTransactions();
    }
    @Override
    public int getUnreturnedTransactionCount() {
        return transactionRepository.countUnreturnedTransactions();
    }
}