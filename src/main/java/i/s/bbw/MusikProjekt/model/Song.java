package i.s.bbw.MusikProjekt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dauer", nullable = false)
    private int dauer;

    @ManyToOne
    @JoinColumn(name = "albumidfs")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genresidfs")
    private Genres genres;

}