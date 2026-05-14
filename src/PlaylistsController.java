import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class PlaylistsController {
    @FXML private ListView<Playlist> playlistList;
    @FXML private ListView<Musique> musiqueList;
    @FXML private ListView<Musique> contenuPlaylistList;
    @FXML private TextField nomPlaylistField;
    @FXML private Label profilLabel;

    @FXML
    public void initialize() {
        profilLabel.setText("Profil : " + MusicData.profilActuel.getNom());
        playlistList.getItems().setAll(MusicData.profilActuel.getPlaylists());
        musiqueList.getItems().setAll(MusicData.musiques);

        playlistList.getSelectionModel().selectedItemProperty().addListener((obs, oldP, newP) -> {
            afficherContenu(newP);
        });
    }

    private void afficherContenu(Playlist playlist) {
        contenuPlaylistList.getItems().clear();
        if (playlist != null) {
            contenuPlaylistList.getItems().setAll(playlist.getMusiques());
        }
    }

    @FXML
    public void creerPlaylist() {
        String nom = nomPlaylistField.getText();
        if (nom != null && !nom.trim().isEmpty()) {
            MusicData.profilActuel.ajouterPlaylist(nom);
            playlistList.getItems().setAll(MusicData.profilActuel.getPlaylists());
            nomPlaylistField.clear();
        }
    }

    @FXML
    public void ajouterMusiquePlaylist() {
        Playlist playlist = playlistList.getSelectionModel().getSelectedItem();
        Musique musique = musiqueList.getSelectionModel().getSelectedItem();

        if (playlist != null && musique != null) {
            playlist.ajouterMusique(musique);
            afficherContenu(playlist);
        }
    }

    @FXML
    public void retirerMusiquePlaylist() {
        Playlist playlist = playlistList.getSelectionModel().getSelectedItem();
        Musique musique = contenuPlaylistList.getSelectionModel().getSelectedItem();

        if (playlist != null && musique != null) {
            playlist.retirerMusique(musique);
            afficherContenu(playlist);
        }
    }

    @FXML public void ouvrirProfils(ActionEvent event) { Navigation.changerPage(event, "profils.fxml"); }
    @FXML public void ouvrirAccueil(ActionEvent event) { Navigation.changerPage(event, "accueil.fxml"); }
    @FXML public void ouvrirBibliotheque(ActionEvent event) { Navigation.changerPage(event, "bibliotheque.fxml"); }
    @FXML public void ouvrirFavoris(ActionEvent event) { Navigation.changerPage(event, "favoris.fxml"); }
    @FXML public void ouvrirPlaylists(ActionEvent event) { Navigation.changerPage(event, "playlists.fxml"); }
    @FXML public void ouvrirStatistiques(ActionEvent event) { Navigation.changerPage(event, "statistiques.fxml"); }
    @FXML public void ouvrirAPropos(ActionEvent event) { Navigation.changerPage(event, "apropos.fxml"); }

}
