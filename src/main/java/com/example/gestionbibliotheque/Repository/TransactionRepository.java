package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.BookItem;
import com.example.gestionbibliotheque.entities.Student;
import com.example.gestionbibliotheque.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBorrower(Student borrower);
    List<Transaction> findByBook(Book book);
    Transaction findByBookItemAndBorrowerAndReturned(BookItem bookItem, Student borrower, boolean returned);

    List<Transaction> findByBorrower_UidAndReturnedFalse(Long uid);

    List<Transaction> findTransactionsByReturnDate(LocalDate returnDate);

    List<Transaction> findByReturnedFalse();

    @Query("SELECT t FROM Transaction t WHERE t.returnDate < CURRENT_DATE AND t.returned = false")
    List<Transaction> findOverdueTransactions();

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.returned = false")
    int countUnreturnedTransactions();
}