public class Musique extends Media {
    private String artiste;
    private String humeur;
    private boolean favori;
    private int nombreEcoutes;

    public Musique(String titre, String chemin, String artiste, String humeur) {
        super(titre, chemin);
        this.artiste = artiste;
        this.humeur = humeur;
        this.favori = false;
        this.nombreEcoutes = 0;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getHumeur() {
        return humeur;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public int getNombreEcoutes() {
        return nombreEcoutes;
    }

    public void incrementerEcoutes() {
        nombreEcoutes++;
    }

    @Override
    public String afficherInfo() {
        return titre + " - " + artiste + " [" + humeur + "]";
    }

    @Override
    public String toString() {
        return afficherInfo();
    }
}
