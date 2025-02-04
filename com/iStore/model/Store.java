package com.iStore.model;

/**
 * Classe représentant un magasin (Store) dans l'application iStore.
 * Un magasin est défini par un identifiant unique et un nom.
 */
public class Store {
    private int Id;
    private String Name;

    /**
     * Constructeur pour créer un nouveau magasin.
     *
     * @param Id   L'identifiant unique du magasin.
     * @param Name Le nom du magasin.
     */
    public Store(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    /**
     * Obtient l'identifiant du magasin.
     *
     * @return L'identifiant du magasin.
     */
    public int getId() {
        return Id;
    }

    /**
     * Définit l'identifiant du magasin.
     *
     * @param Id Le nouvel identifiant du magasin.
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtient le nom du magasin.
     *
     * @return Le nom du magasin.
     */
    public String getName() {
        return Name;
    }

    /**
     * Définit le nom du magasin.
     *
     * @param Name Le nouveau nom du magasin.
     */
    public void setName(String Name) {
        this.Name = Name;
    }
}

