import java.util.ArrayList;

public class Profil {
    private String nom;
    private ArrayList<Playlist> playlists;
    private ArrayList<Musique> historique;

    public Profil(String nom) {
        this.nom = nom;
        this.playlists = new ArrayList<>();
        this.historique = new ArrayList<>();
        this.playlists.add(new Playlist("Chill"));
        this.playlists.add(new Playlist("Sport"));
        this.playlists.add(new Playlist("Focus"));
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Musique> getHistorique() {
        return historique;
    }

    public void ajouterHistorique(Musique musique) {
        if (musique != null) {
            historique.add(0, musique);
            if (historique.size() > 20) {
                historique.remove(historique.size() - 1);
            }
        }
    }

    public void ajouterPlaylist(String nom) {
        if (nom != null && !nom.trim().isEmpty()) {
            playlists.add(new Playlist(nom.trim()));
        }
    }

    @Override
    public String toString() {
        return nom;
    }
}
