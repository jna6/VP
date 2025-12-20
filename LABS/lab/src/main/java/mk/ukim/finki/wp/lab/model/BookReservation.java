package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
@Entity
public class BookReservation {
    @Id
    @GeneratedValue
    private Long id;
    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private int numberOfCopies;

    public BookReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }

    public BookReservation() {

    }
}
