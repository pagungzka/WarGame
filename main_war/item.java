package main_war;

public class item {

    private int idItem;
    private String tipoitem;
    private boolean equipado;
    private boolean usado;
    private Efeito efeito;
    private String descricao;

    public item() {
    }

    public item(int idItem, String tipoitem, boolean equipado, Efeito efeito, String descricao) {
        this.idItem = idItem;
        this.tipoitem = tipoitem;
        this.equipado = equipado;
        this.efeito = efeito;
        this.usado = false;
        this.descricao = descricao;
    }

    public void imprimirDados() {
        String texto = "id item: " + idItem
                + "\ntipo do item: " + tipoitem
                + "\nequipado: " + equipado
                + "\nusado: " + usado
                + "\ndescricao: " + descricao;
        InOut.MsgDeInformacao("Dados do Item", texto);
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(String tipoitem) {
        this.tipoitem = tipoitem;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public Efeito getEfeito() {
        return efeito;
    }

    @Override
    public String toString() {
        return "item{" + "idItem=" + idItem + ", tipoitem=" + tipoitem + ", equipado=" + equipado
                + ", usado=" + usado + ", efeito=" + efeito + ", descricao=" + descricao + '}';
    }
}
