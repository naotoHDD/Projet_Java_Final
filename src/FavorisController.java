import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class FavorisController {
    @FXML private ListView<Musique> favorisList;
    @FXML private Label profilLabel;

    @FXML
    public void initialize() {
        profilLabel.setText("Profil : " + MusicData.profilActuel.getNom());
        favorisList.getItems().clear();
        for (Musique musique : MusicData.musiques) {
            if (musique.isFavori()) favorisList.getItems().add(musique);
        }
    }

    @FXML
    public void retirerFavori() {
        Musique musique = favorisList.getSelectionModel().getSelectedItem();
        if (musique != null) {
            musique.setFavori(false);
            favorisList.getItems().remove(musique);
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
