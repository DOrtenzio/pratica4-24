package com.example.pratica3324;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GiocatoriController {
    @FXML
    private Pane rootPane;
    @FXML
    private ComboBox s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
    @FXML
    private ComboBox [] squadraTitolareSchermo;
    @FXML
    private ComboBox p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11;
    @FXML
    private ComboBox [] panchinariSchermo;

    private static Giocatore[] predisposizioneSelezionata = new Giocatore[22];
    private Giocatore [] squadra;
    private int indexInseriti;
    private static boolean isDoingARefactoring=false;

    @FXML
    public void initialize() {
        // Inizializzo array vari e variabili di servizio
        squadraTitolareSchermo = setArray();
        panchinariSchermo = setArrayP();
        squadra = HelloController.getSquadra();
        indexInseriti = HelloController.getIndexInseriti();

        // Popolo combobox con tutti i giocatori
        popolaComboBoxConTuttiIGiocatori();

        // Carica la predisposizione salvata o imposta la configurazione iniziale
        if (!isDoingARefactoring) {
            inserimentoTitolari(squadraTitolareSchermo, squadra);
            inserimentoPanchinari(panchinariSchermo, squadra);
            isDoingARefactoring = true;
        } else {
            // Carica la configurazione salvata in predisposizioneSelezionata
            inserimentoPosizionato(predisposizioneSelezionata);
        }
    }


    private ComboBox [] setArray(){
        ComboBox [] squadraTitolare=new ComboBox[11];
        for (int i=0;i<10;i++){
            squadraTitolare[i]=new ComboBox();
        }
        squadraTitolare[0]=s1;
        squadraTitolare[1]=s2;
        squadraTitolare[2]=s3;
        squadraTitolare[3]=s4;
        squadraTitolare[4]=s5;
        squadraTitolare[5]=s6;
        squadraTitolare[6]=s7;
        squadraTitolare[7]=s8;
        squadraTitolare[8]=s9;
        squadraTitolare[9]=s10;
        squadraTitolare[10]=s11;
        return squadraTitolare;
    }
    private ComboBox [] setArrayP(){
        ComboBox [] panchinari=new ComboBox[11];
        for (int i=0;i<10;i++){
            panchinari[i]=new ComboBox();
        }
        panchinari[0]=p1;
        panchinari[1]=p2;
        panchinari[2]=p3;
        panchinari[3]=p4;
        panchinari[4]=p5;
        panchinari[5]=p6;
        panchinari[6]=p7;
        panchinari[7]=p8;
        panchinari[8]=p9;
        panchinari[9]=p10;
        panchinari[10]=p11;
        return panchinari;
    }

    @FXML
    public void puliziaCombo(ComboBox [] comboInEsame){
        for (int i = 0; i < comboInEsame.length; i++) {
            comboInEsame[i].getItems().clear();
        }
    }
    @FXML
    public void inserimentoTitolari(ComboBox [] titolari, Giocatore [] squadra) {
        if (this.indexInseriti != 0) {
            int giocPresenti = Math.min(this.indexInseriti, 11); // Limito a massimo 11 giocatori come in una squadra reale
            for (int i = 0; i < giocPresenti; i++) {
                titolari[i].getSelectionModel().select(squadra[i].getNome());
            }
        }
    }

    @FXML
    public void inserimentoPanchinari(ComboBox [] panchinari, Giocatore [] squadra) {
        if (this.indexInseriti > 11) {
            int numPanchinari = this.indexInseriti - 11; // Giocatori extra da inserire in panchina
            for (int i = 0; i < numPanchinari; i++) {
                panchinari[i].getSelectionModel().select(squadra[i+11].getNome());
            }
        }
    }
    @FXML
    public void inserimentoPosizionato(Giocatore [] predisposizioneSelezionata) {
        for (int i = 0; i < squadraTitolareSchermo.length; i++) {
            if (predisposizioneSelezionata[i]!=null)
                squadraTitolareSchermo[i].getSelectionModel().select(predisposizioneSelezionata[i].getNome());
        }
        for (int i = 0; i < panchinariSchermo.length; i++) {
            if (predisposizioneSelezionata[i+11]!=null)
                panchinariSchermo[i].getSelectionModel().select(predisposizioneSelezionata[i+11].getNome());
        }
    }

    @FXML
    public void popolaComboBoxConTuttiIGiocatori() {
        puliziaCombo(squadraTitolareSchermo);
        puliziaCombo(panchinariSchermo);

        for (int i=0;i<indexInseriti;i++) {
            String nomeGiocatore = squadra[i].getNome();
            for (ComboBox comboBox : squadraTitolareSchermo) {
                if (!comboBox.getItems().contains(nomeGiocatore)) {
                    comboBox.getItems().add(nomeGiocatore);
                }
            }
            for (ComboBox comboBox : panchinariSchermo) {
                if (!comboBox.getItems().contains(nomeGiocatore)) {
                    comboBox.getItems().add(nomeGiocatore);
                }
            }
        }
        for (ComboBox comboBox : squadraTitolareSchermo) {
            comboBox.getItems().add(" ");
        }
        for (ComboBox comboBox : panchinariSchermo) {
            comboBox.getItems().add(" ");
        }
    }

    @FXML
    public void salvaPredisposizione() {
        // Salva la formazione selezionata nei titolari
        for (int i = 0; i < squadraTitolareSchermo.length; i++) {
            String nomeSelezionato = (String) squadraTitolareSchermo[i].getValue();
            predisposizioneSelezionata[i] = trovaGiocatorePerNome(nomeSelezionato);
        }

        // Salva la formazione selezionata nei panchinari
        for (int i = 0; i < panchinariSchermo.length; i++) {
            String nomeSelezionato = (String) panchinariSchermo[i].getValue();
            predisposizioneSelezionata[i + 11] = trovaGiocatorePerNome(nomeSelezionato);
        }

        // Conferma il salvataggio e mostra il messaggio di conferma
        isDoingARefactoring = true;  // Imposta il flag per ricaricare la formazione salvata
        mostraMessaggioConferma();
    }

    private Giocatore trovaGiocatorePerNome(String nome) {
        for (int i=0;i<indexInseriti;i++) {
            if (squadra[i].getNome().equals(nome)) {
                return squadra[i];
            }
        }
        return null;
    }

    public void mostraMessaggioConferma() {
        Label messaggioConferma = new Label("Effettuato con successo");
        messaggioConferma.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");
        messaggioConferma.setTextFill(Color.WHITE);
        messaggioConferma.setOpacity(0.9);
        messaggioConferma.setLayoutX(19);
        messaggioConferma.setLayoutY(370);

        rootPane.getChildren().add(messaggioConferma);

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));
        pausa.setOnFinished(event -> rootPane.getChildren().remove(messaggioConferma));
        pausa.play();
    }

    @FXML
    private void backHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view2.fxml"));//Torno al menu
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }
    @FXML
    private void reload(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("giocatori-view.fxml"));//Ricarica
        Scene scene = new Scene(fxmlLoader.load(), 955.0, 440);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }
}
