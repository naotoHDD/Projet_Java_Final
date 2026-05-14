import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class StatistiquesController {
    @FXML private Label profilLabel;
    @FXML private Label totalMusiquesLabel;
    @FXML private Label totalFavorisLabel;
    @FXML private Label totalPlaylistsLabel;
    @FXML private Label totalEcoutesLabel;
    @FXML private ListView<String> topEcoutesList;
    @FXML private ListView<Musique> historiqueList;

    @FXML
    public void initialize() {
        profilLabel.setText("Profil : " + MusicData.profilActuel.getNom());

        int totalFavoris = 0;
        int totalEcoutes = 0;

        for (Musique m : MusicData.musiques) {
            if (m.isFavori()) totalFavoris++;
            totalEcoutes += m.getNombreEcoutes();
        }

        totalMusiquesLabel.setText(String.valueOf(MusicData.musiques.size()));
        totalFavorisLabel.setText(String.valueOf(totalFavoris));
        totalPlaylistsLabel.setText(String.valueOf(MusicData.profilActuel.getPlaylists().size()));
        totalEcoutesLabel.setText(String.valueOf(totalEcoutes));

        topEcoutesList.getItems().clear();
        for (Musique m : MusicData.musiques) {
            if (m.getNombreEcoutes() > 0) {
                topEcoutesList.getItems().add(m.getTitre() + " : " + m.getNombreEcoutes() + " écoute(s)");
            }
        }

        historiqueList.getItems().setAll(MusicData.profilActuel.getHistorique());
    }

    @FXML public void ouvrirProfils(ActionEvent event) { Navigation.changerPage(event, "profils.fxml"); }
    @FXML public void ouvrirAccueil(ActionEvent event) { Navigation.changerPage(event, "accueil.fxml"); }
    @FXML public void ouvrirBibliotheque(ActionEvent event) { Navigation.changerPage(event, "bibliotheque.fxml"); }
    @FXML public void ouvrirFavoris(ActionEvent event) { Navigation.changerPage(event, "favoris.fxml"); }
    @FXML public void ouvrirPlaylists(ActionEvent event) { Navigation.changerPage(event, "playlists.fxml"); }
    @FXML public void ouvrirStatistiques(ActionEvent event) { Navigation.changerPage(event, "statistiques.fxml"); }
    @FXML public void ouvrirAPropos(ActionEvent event) { Navigation.changerPage(event, "apropos.fxml"); }

}
