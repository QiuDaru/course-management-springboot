package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderBookId implements Serializable {
    private String readerId;
    private String bookId;

    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if( obj == null||obj.getClass() != this.getClass()){return false;}

        return ((ReaderBookId) obj).bookId.equals(this.bookId)&&((ReaderBookId) obj).readerId.equals(this.readerId);
    }
    public int hashCode() {
        return Objects.hash(readerId, bookId);
    }
}