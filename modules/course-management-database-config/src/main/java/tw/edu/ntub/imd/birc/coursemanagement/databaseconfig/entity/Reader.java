package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reader")
public class Reader {
    @Id
    private String id;
    private String name;
    private String email;
}
