package i.s.bbw.MusikProjekt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

//ivana
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "anzahlsongs", nullable = false)
    private int anzahlsongs;

    @ManyToOne
    @JoinColumn(name = "artistidfs")
    private Artist artist;

    @OneToMany(mappedBy="album")
    private List<Song> listOfSongs;
}