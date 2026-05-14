import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import java.io.File;
import java.util.Random;

public class AccueilController {
    @FXML private ListView<Musique> musiqueList;
    @FXML private Slider progressSlider;
    @FXML private Slider volumeSlider;
    @FXML private Label tempsLabel;
    @FXML private Label titreLectureLabel;
    @FXML private Label profilLabel;
    @FXML private HBox equalizerBox;

    private MediaPlayer mediaPlayer;
    private Timeline equalizerTimeline;
    private Random random = new Random();

    @FXML
    public void initialize() {
        musiqueList.getItems().setAll(MusicData.musiques);
        volumeSlider.setValue(50);
        profilLabel.setText("Profil : " + MusicData.profilActuel.getNom());
        creerEqualizer();

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (mediaPlayer != null) mediaPlayer.setVolume(newVal.doubleValue() / 100);
        });
    }

    private void creerEqualizer() {
        equalizerBox.getChildren().clear();
        for (int i = 0; i < 18; i++) {
            Region bar = new Region();
            bar.setPrefWidth(8);
            bar.setPrefHeight(20 + random.nextInt(45));
            bar.setStyle("-fx-background-color: #ff2b2b; -fx-background-radius: 6;");
            equalizerBox.getChildren().add(bar);
        }

        equalizerTimeline = new Timeline(new KeyFrame(Duration.millis(180), e -> {
            for (javafx.scene.Node n : equalizerBox.getChildren()) {
                Region bar = (Region) n;
                bar.setPrefHeight(15 + random.nextInt(60));
            }
        }));
        equalizerTimeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    public void ajouterMusique() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une musique");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers audio", "*.mp3", "*.wav")
        );

        File fichier = fileChooser.showOpenDialog(new Stage());

        if (fichier != null) {
            Musique musique = new Musique(fichier.getName(), fichier.getAbsolutePath(), "Artiste inconnu", "Chill");
            MusicData.musiques.add(musique);
            musiqueList.getItems().add(musique);
        }
    }

    @FXML
    public void jouerMusique() {
        Musique musique = musiqueList.getSelectionModel().getSelectedItem();

        if (musique == null) {
            afficherInfo("Sélectionne une musique avant de cliquer sur Play.");
            return;
        }

        try {
            if (mediaPlayer != null) mediaPlayer.stop();

            Media media = new Media(new File(musique.getChemin()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            mediaPlayer.play();

            musique.incrementerEcoutes();
            MusicData.profilActuel.ajouterHistorique(musique);
            titreLectureLabel.setText("Lecture : " + musique.getTitre());
            if (equalizerTimeline != null) equalizerTimeline.play();

            mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                Duration total = mediaPlayer.getTotalDuration();
                if (total != null && total.toSeconds() > 0) {
                    progressSlider.setValue(newTime.toSeconds() / total.toSeconds() * 100);
                    tempsLabel.setText(formatTime(newTime) + " / " + formatTime(total));
                }
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                if (equalizerTimeline != null) equalizerTimeline.pause();
            });

        } catch (Exception e) {
            afficherInfo("Impossible de lire cette musique.");
        }
    }

    @FXML
    public void randomMusique() {
        if (MusicData.musiques.isEmpty()) {
            afficherInfo("Aucune musique disponible.");
            return;
        }
        int index = random.nextInt(MusicData.musiques.size());
        Musique musique = MusicData.musiques.get(index);
        musiqueList.getSelectionModel().select(musique);
        musiqueList.scrollTo(musique);
        jouerMusique();
    }

    @FXML
    public void pauseMusique() {
        if (mediaPlayer != null) mediaPlayer.pause();
        if (equalizerTimeline != null) equalizerTimeline.pause();
    }

    @FXML
    public void stopMusique() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            progressSlider.setValue(0);
            tempsLabel.setText("00:00 / 00:00");
            titreLectureLabel.setText("Aucune musique en lecture");
        }
        if (equalizerTimeline != null) equalizerTimeline.pause();
    }

    @FXML
    public void supprimerMusique() {
        Musique musique = musiqueList.getSelectionModel().getSelectedItem();
        if (musique != null) {
            MusicData.musiques.remove(musique);
            musiqueList.getItems().remove(musique);
        }
    }

    @FXML
    public void ajouterFavori() {
        Musique musique = musiqueList.getSelectionModel().getSelectedItem();
        if (musique != null) {
            musique.setFavori(true);
            afficherInfo("Musique ajoutée aux favoris.");
        }
    }

    private void afficherInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TuneMood");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @FXML public void ouvrirProfils(ActionEvent event) { Navigation.changerPage(event, "profils.fxml"); }
    @FXML public void ouvrirAccueil(ActionEvent event) { Navigation.changerPage(event, "accueil.fxml"); }
    @FXML public void ouvrirBibliotheque(ActionEvent event) { Navigation.changerPage(event, "bibliotheque.fxml"); }
    @FXML public void ouvrirFavoris(ActionEvent event) { Navigation.changerPage(event, "favoris.fxml"); }
    @FXML public void ouvrirPlaylists(ActionEvent event) { Navigation.changerPage(event, "playlists.fxml"); }
    @FXML public void ouvrirStatistiques(ActionEvent event) { Navigation.changerPage(event, "statistiques.fxml"); }
    @FXML public void ouvrirAPropos(ActionEvent event) { Navigation.changerPage(event, "apropos.fxml"); }

}
