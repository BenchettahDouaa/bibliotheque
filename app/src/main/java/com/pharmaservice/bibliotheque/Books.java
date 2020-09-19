package com.pharmaservice.bibliotheque;

public class Books {
    int id;
    String Titre,Auteur,MotCles;


    public Books(){

    }

    public Books(int id, String Titre, String Auteur, String MotCles){
        this.id=id;
        this.Titre=Titre;
        this.Auteur=Auteur;
        this.MotCles=MotCles;

    }

    public int getId() {
        return id;
    }

    public String getMotCles() {
        return MotCles;
    }

    public String getAuteur() {
        return Auteur;
    }

    public String getTitre() {
        return Titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotCles(String motCles) {
        MotCles = motCles;
    }

    public void setAuteur(String auteur) {
        Auteur = auteur;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }
}
