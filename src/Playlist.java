import java.util.ArrayList;

public class Playlist {
    private String nom;
    private ArrayList<Musique> musiques;

    public Playlist(String nom) {
        this.nom = nom;
        this.musiques = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Musique> getMusiques() {
        return musiques;
    }

    public void ajouterMusique(Musique musique) {
        if (musique != null && !musiques.contains(musique)) {
            musiques.add(musique);
        }
    }

    public void retirerMusique(Musique musique) {
        musiques.remove(musique);
    }

    @Override
    public String toString() {
        return nom + " (" + musiques.size() + " titres)";
    }
}
