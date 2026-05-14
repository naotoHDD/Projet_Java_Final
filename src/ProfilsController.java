import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class ProfilsController {
    @FXML private ListView<Profil> profilsList;
    @FXML private Label profilActuelLabel;

    @FXML
    public void initialize() {
        profilsList.getItems().setAll(MusicData.profils);
        profilActuelLabel.setText("Profil actuel : " + MusicData.profilActuel.getNom());
    }

    @FXML
    public void choisirProfil() {
        Profil profil = profilsList.getSelectionModel().getSelectedItem();
        if (profil != null) {
            MusicData.profilActuel = profil;
            profilActuelLabel.setText("Profil actuel : " + profil.getNom());
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
