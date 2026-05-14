import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class BibliothequeController {
    @FXML private ListView<Musique> bibliothequeList;
    @FXML private TextField rechercheField;
    @FXML private Label profilLabel;

    @FXML
    public void initialize() {
        profilLabel.setText("Profil : " + MusicData.profilActuel.getNom());
        bibliothequeList.getItems().setAll(MusicData.musiques);
        rechercheField.textProperty().addListener((obs, oldText, newText) -> rechercher(newText));
    }

    private void rechercher(String mot) {
        bibliothequeList.getItems().clear();
        for (Musique musique : MusicData.musiques) {
            if (musique.afficherInfo().toLowerCase().contains(mot.toLowerCase())) {
                bibliothequeList.getItems().add(musique);
            }
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
