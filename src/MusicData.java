import java.util.ArrayList;

public class MusicData {
    public static ArrayList<Musique> musiques = new ArrayList<>();
    public static ArrayList<Profil> profils = new ArrayList<>();
    public static Profil profilActuel;

    static {
        profils.add(new Profil("Mohamed"));
        profils.add(new Profil("Aziz"));
        profils.add(new Profil("Ayoub"));
        profils.add(new Profil("Chirine"));
        profilActuel = profils.get(0);
    }
}
