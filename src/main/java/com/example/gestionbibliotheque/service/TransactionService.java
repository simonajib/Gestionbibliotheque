package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    public List<Transaction> findAll();
    void saveTransaction(Transaction transaction);

    public Transaction findOne(Long id);

    public void delete(Long id);

    List<Transaction> findByBorrower(Long borrowerId);

    void issueBook(Transaction transaction);

    void returnBook(Transaction transaction);

    Transaction findTransactionToReturnBook(Transaction transactionParam);

    List<Transaction> getAllTransactions();

    void updateTransaction(Transaction transaction);

    List<Transaction> findBorrowedBooksByUid(Long uid);

    List<Transaction> findTransactionsByReturnDate(LocalDate returnDateTwoDaysAway);

    List<Transaction> findOverdueTransactions();

    int getUnreturnedTransactionCount();
}