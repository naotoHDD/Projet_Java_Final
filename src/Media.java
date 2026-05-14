public abstract class Media {
    protected String titre;
    protected String chemin;

    public Media(String titre, String chemin) {
        this.titre = titre;
        this.chemin = chemin;
    }

    public String getTitre() {
        return titre;
    }

    public String getChemin() {
        return chemin;
    }

    public abstract String afficherInfo();
}
