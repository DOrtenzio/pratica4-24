package com.example.pratica3324;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GiocatoriController {
    @FXML
    private Label s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
    @FXML
    private Label [] squadraTitolareSchermo;
    @FXML
    private Label p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11;
    @FXML
    private Label [] panchinariSchermo;

    private Giocatore [] squadra;
    private int indexInseriti;

    @FXML
    public void initialize(){
        squadraTitolareSchermo=setArray();
        panchinariSchermo =setArrayP();
        squadra=HelloController.getSquadra();
        indexInseriti=HelloController.getIndexInseriti();

        inserimentoSquadra();
        inserimentoPanchinari();
    }

    private Label [] setArray(){
        Label [] squadraTitolare=new Label[11];
        for (int i=0;i<10;i++){
            squadraTitolare[i]=new Label();
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
    private Label [] setArrayP(){
        Label [] panchinari=new Label[11];
        for (int i=0;i<10;i++){
            panchinari[i]=new Label();
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
    public  void inserimentoSquadra(){
        if (this.indexInseriti!=0){
            //Delimito inserimento della squadra
            int giocPresenti=0;
            if (this.indexInseriti<=11)
                giocPresenti=this.indexInseriti;
            else if (this.indexInseriti>11)
                giocPresenti=11;

            for (int i=0;i<giocPresenti;i++){
                squadraTitolareSchermo[i].setText(squadra[i].getNome());
            }
        }
    }
    @FXML
    public void inserimentoPanchinari(){;
        if (this.indexInseriti!=0 && this.indexInseriti>11){
            for (int i=0;i<(this.indexInseriti-11);i++){
                panchinariSchermo[i].setText(squadra[i+11].getNome());
            }
        }
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
