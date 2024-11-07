package com.example.pratica3324;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Squadra sq=new Squadra();
    private int i=0; //Temp

    @FXML
    private Label labelIn;
    @FXML
    private VBox insertBox;

    public void initialize(){

    }

    //get static
    public Squadra getSq() { return sq; }
    public Giocatore[] getSquadra() { return sq.getSquadra(); }
    public int getIndexInseriti() { return sq.getIndexInseriti(); }

    @FXML
    public void inserisciGioc() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == sq.getSquadraLength()) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE: Impossibile inserire un nuovo giocatore, squadra al completo (22/22)");
        } else {
            CheckBox c1=new CheckBox("<--");

            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("AGGIUNTA GIOCATORE");

            //Label e textField
            Label l1=new Label("Inserire il nome nome giocatore:");
            TextField t1=new TextField();
            Label l2=new Label("Inserire i gol  effettuati dal giocatore:");
            TextField t2=new TextField();

            //Li metto nella schermata
            insertBox.getChildren().add(l1);
            insertBox.getChildren().add(t1);
            insertBox.getChildren().add(l2);
            insertBox.getChildren().add(t2);

            //Inserimento capitano solo se capitano non è già presente
            if (sq.getIndexInseriti() == 0 || sq.controllaCapitani() == -1) {
                Label l3=new Label("Sara' il tuo prossimo capitano? ");
                insertBox.getChildren().add(l3);
                insertBox.getChildren().add(c1);
            }
            //Bottone finale
            Button b1=new Button("Inserisci");
            insertBox.getChildren().add(b1);
            b1.setOnMouseClicked( e -> {
                sq.setIndexInseriti(sq.aggGioc(t1.getText(), Integer.parseInt(t2.getText()), isCapitano(c1)));
                insertBox.getChildren().clear();
                labelIn.setText("INSERITO CORRETTAMENTE");
            });
        }
    }
    @FXML
    public boolean isCapitano(CheckBox c1){
        if (sq.getIndexInseriti() == 0 || sq.controllaCapitani() == -1){
            return c1.isSelected();
        }
        return false;

    }
    @FXML
    public void vediGioc() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("GIOCATORI DELLA SQUADRA:");
            Label l1=new Label(sq.stampa());
            insertBox.getChildren().add(l1);
        }
    }
    @FXML
    public void modificaGioc() {
        i=0;
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            //Ricerca giocatore

            CheckBox c1=new CheckBox("<--");

            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("MODIFICA:");

            //Label e textField
            Label l1=new Label("Inserire nome giocatore:");
            TextField t1=new TextField();
            Label l2=new Label("Inserire gol giocatore:");
            TextField t2=new TextField();

            //Li metto nella schermata
            insertBox.getChildren().add(l1);
            insertBox.getChildren().add(t1);
            insertBox.getChildren().add(l2);
            insertBox.getChildren().add(t2);

            //Inserimento valori
            if (sq.controllaCapitani() != -1) {
                Label l3=new Label("E' capitano?");
                insertBox.getChildren().add(l3);
                insertBox.getChildren().add(c1);
            }
            //Bottone finale
            Button b1=new Button("Ricerca");
            insertBox.getChildren().add(b1);
            b1.setOnMouseClicked( e -> { //All'azione
                i=sq.ricercaGioc(t1.getText(), Integer.parseInt(t2.getText()), isCapitano(c1));
                if (i==-1) {
                    insertBox.getChildren().clear();
                    labelIn.setStyle("-fx-text-fill: #FB0008");
                    labelIn.setText("GIOCATORE INESISTENTE");
                } else {
                    CheckBox c2=new CheckBox("<--");
                    labelIn.setStyle("-fx-text-fill: #0a0a0a");
                    labelIn.setText("MODIFICA DEL GIOCATORE RICHIESTO:");

                    insertBox.getChildren().clear();
                    //Label e textField
                    Label l11=new Label("Inserire il nuovo nome del giocatore:");
                    TextField t11=new TextField();
                    Label l21=new Label("Inserire il nuovo numero di gol del giocatore:");
                    TextField t21=new TextField();

                    //Li metto nella schermata
                    insertBox.getChildren().add(l11);
                    insertBox.getChildren().add(t11);
                    insertBox.getChildren().add(l21);
                    insertBox.getChildren().add(t21);

                    //Inserimento valori
                    if (sq.controllaCapitani() == -1 || sq.isCapitanoSingolo(sq.ricercaGioc(t1.getText(), Integer.parseInt(t2.getText()), isCapitano(c1)))) {
                        Label l3=new Label("E' capitano?");
                        insertBox.getChildren().add(l3);
                        insertBox.getChildren().add(c2);
                    }
                    //Bottone finale
                    Button b2=new Button("Conferma");
                    insertBox.getChildren().add(b2);
                    b2.setOnMouseClicked( b -> { //All'azione
                        sq.modificaGioc(i, t11.getText(), Integer.parseInt(t21.getText()), isCapitano(c2));
                        insertBox.getChildren().clear();
                        labelIn.setText("MODIFICA DEL GIOCATORE EFFETTUATA");
                    });
                }
            });
        }
    }
    @FXML
    public void cancellaGiocatore() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            //Ricerca giocatore
            CheckBox c1=new CheckBox("<--");
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CANCELLAZIONE:");

            insertBox.getChildren().clear();
            //Label e textField
            Label l1=new Label("Inserire il nome del giocatore:");
            TextField t1=new TextField();
            Label l2=new Label("Inserire i gol del giocatore:");
            TextField t2=new TextField();

            //Li metto nella schermata
            insertBox.getChildren().add(l1);
            insertBox.getChildren().add(t1);
            insertBox.getChildren().add(l2);
            insertBox.getChildren().add(t2);

            //Inserimento valori
            if (sq.controllaCapitani() != -1) {
                Label l3=new Label("E' capitano?");
                insertBox.getChildren().add(l3);
                insertBox.getChildren().add(c1);
            }
            //Bottone finale
            Button b1=new Button("Cerca");
            insertBox.getChildren().add(b1);
            b1.setOnMouseClicked( e -> { //All'azione
                i= sq.ricercaGioc(t1.getText(), Integer.parseInt(t2.getText()), isCapitano(c1));
                insertBox.getChildren().clear();
                if (i==-1) {
                    insertBox.getChildren().clear();
                    labelIn.setStyle("-fx-text-fill: #FB0008");
                    labelIn.setText("GIOCATORE INESISTENTE");
                } else {
                    labelIn.setStyle("-fx-text-fill: #0a0a0a");
                    labelIn.setText("Cancellazione:");
                    sq.cancellaGioc(i);
                    sq.setIndexInseriti(sq.getIndexInseriti()-1);
                    //Label e textField
                    Label l11=new Label("CANCELLATO CORRETTAMENTE");

                    //Li metto nella schermata
                    insertBox.getChildren().add(l11);
                }
            });
        }
    }
    @FXML
    public void vediGioc5Gol() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("GIOCATORI CON PIU' DI 5 GOL:");
            Label l1=new Label(sq.stampa5Gol());
            insertBox.getChildren().add(l1);
        }
    }
    @FXML
    public void vediCapitano() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CONTROLLO CAPITANI:");
            Label l1=new Label();
            insertBox.getChildren().add(l1);
            i= sq.controllaCapitani();
            if (i==-1)
                l1.setText("Non ci sono capitani");
            else
                l1.setText(sq.toStringSingolo(i));
        }
    }
    @FXML
    public void capitanoCasuale() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CAPITANO CASUALE");
            Label l1=new Label();
            insertBox.getChildren().add(l1);
            Label l2=new Label();
            insertBox.getChildren().add(l2);
            i=sq.capitaniRandom();
            if (i!=-1)
                l1.setText("Ci sono già capitani");
            else
                l1.setText("Capitano nuovo:");
            l2.setText(sq.toStringSingolo(sq.controllaCapitani()));
        }
    }
    @FXML
    private void goToVediGiocatori(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("giocatori-view.fxml"));//Ricarica
        Scene scene = new Scene(fxmlLoader.load(), 955.0, 440);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }
}
