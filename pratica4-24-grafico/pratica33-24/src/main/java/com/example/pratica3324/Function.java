package com.example.pratica3324;

public class Function {

    public static int aggGioc(Giocatore[] squadra, int indexInseriti, String nome, int gol, boolean capitano) {
        squadra[indexInseriti] = new Giocatore(nome, capitano, gol);
        return indexInseriti + 1;
    }

    public static String stampa(Giocatore[] squadra, int indexInseriti){
        String s="Squadra: \n";
        for (int i=0;i<indexInseriti;i++){
            s=s+"\nNome: "+squadra[i].getNome()+"\t Capitano:"+squadra[i].isCapitano()+"\t Gol:"+squadra[i].getGoal();
        }
        s=s+"\n----------";
        return s;
    }

    public static int ricercaGioc(Giocatore[] squadra, int indexInseriti, String nome, int gol, boolean capitano){
        for (int i=0;i<indexInseriti;i++){
            if ( squadra[i].getNome().equalsIgnoreCase(nome) && squadra[i].getGoal()==gol && squadra[i].isCapitano()==capitano )
                return i;
        }
        return -1;
    }

    public static void modificaGioc(Giocatore[] squadra, int indice, String nome, int gol, boolean capitano){
        squadra[indice].setNome(nome);
        squadra[indice].setGoal(gol);
        squadra[indice].setCapitano(capitano);
    }

    public static void cancellaGioc(Giocatore[] squadra, int indice, int indexInseriti){
        for (int l=indice;l<indexInseriti-1;l++){
            squadra[l]=squadra[l+1];
        }
    }

    public static String stampa5Gol(Giocatore[] squadra, int indexInseriti){
        String s="Giocatori con più di 5 gol: \n";
        for (int i=0;i<indexInseriti;i++){
            if (squadra[i].getGoal()>=5)
                s=s+"Nome: "+squadra[i].getNome()+"\t Capitano:"+squadra[i].isCapitano()+"\t Gol:"+squadra[i].getGoal();
        }
        return s;
    }

    public static int controllaCapitani(Giocatore[] squadra, int indexInseriti) {
        for (int i = 0; i < indexInseriti; i++) {
            if (squadra[i].isCapitano())
                return i;
        }
        return -1;
    }

    public static int capitaniRandom(Giocatore[] squadra, int indexInseriti) {
        int i=controllaCapitani(squadra,indexInseriti);
        if (i==-1) {
            i=(int) (Math.random() * indexInseriti);
            squadra[i].setCapitano(true);
            return -1; //Inserito
        }
        else
            return i; //Non inserito e do posizione attuale
    }

}
