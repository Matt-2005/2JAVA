package com.iStore.model;

import java.math.BigDecimal;

/**
 * Classe représentant un article (Item) dans l'application iStore.
 * Un article est défini par un identifiant, un nom, un prix et un stock disponible.
 */
public class Item {
    private int Id;
    private String Name;
    private BigDecimal Price;
    private int Stock;

    /**
     * Constructeur pour créer un nouvel article.
     *
     * @param Id    L'identifiant unique de l'article.
     * @param Name  Le nom de l'article.
     * @param Price Le prix de l'article sous forme de {@link BigDecimal}.
     * @param Stock Le stock disponible de l'article.
     */
    public Item(int Id, String Name, BigDecimal Price, int Stock) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Stock = Stock;
    }

    /**
     * Obtient l'identifiant de l'article.
     *
     * @return L'identifiant de l'article.
     */
    public int getId() {
        return Id;
    }
    
    /**
     * Définit l'identifiant de l'article.
     *
     * @param Id Le nouvel identifiant de l'article.
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtient le nom de l'article.
     *
     * @return Le nom de l'article.
     */
    public String getName() {
        return Name;
    }

    /**
     * Définit le nom de l'article.
     *
     * @param Name Le nouveau nom de l'article.
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Obtient le prix de l'article.
     *
     * @return Le prix de l'article sous forme de {@link BigDecimal}.
     */
    public BigDecimal getPrice() {
        return Price;
    }

    /**
     * Définit le prix de l'article.
     *
     * @param Price Le nouveau prix de l'article sous forme de {@link BigDecimal}.
     */
    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    /**
     * Obtient la quantité en stock de l'article.
     *
     * @return La quantité en stock de l'article.
     */
    public int getStock() {
        return Stock;
    }

    /**
     * Définit la quantité en stock de l'article.
     *
     * @param Stock La nouvelle quantité en stock de l'article.
     */
    public void setStock(int Stock) {
        this.Stock = Stock;
    }
}
