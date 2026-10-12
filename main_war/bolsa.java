
package main_war;

import java.util.ArrayList;
import java.util.List;

public class bolsa {

    
    public static final int MAX_AJUDAS = 3;

    
    private List<item> itens;

   
    public bolsa() {
        this.itens = new ArrayList<>();
        criarItens();
    }

    private void criarItens() {
        itens.add(new item(1, "Espada de Bronze de Ares", false, Efeito.EMPATE_VIRA_VITORIA,"Fase 3 (Pedra/Papel/Tesoura): em UMA rodada, o empate conta como vitoria."));
        itens.add(new item(2, "Lanca de Atena", false, Efeito.DICA_CHARADA,"Fase 2 (Charadas): revela uma dica da charada atual."));
        itens.add(new item(3, "Arco de Artemis", false, Efeito.FAIXA_NUMERO,"Fase 1 (Numero secreto): mostra uma faixa onde o numero esta."));
        itens.add(new item(4, "Escudo Egide de Zeus", false, Efeito.BLOQUEIA_DANO,"Qualquer fase: bloqueia automaticamente a proxima perda de vida."));
        itens.add(new item(5, "Elmo de Hades", false, Efeito.PULAR_CHARADA,"Fase 2 (Charadas): pula uma charada sem perder vida."));
        itens.add(new item(6, "Sandalias Aladas de Hermes", false, Efeito.TENTATIVAS_EXTRAS,"Fase 1 (Numero secreto): ganha 2 tentativas extras."));
    }

    public void adicionarItem(item novoItem) {
        itens.add(novoItem);
    }

    public List<item> getItens() {
        return itens;
    }

   
    public int contarEquipados() {
        int total = 0;
        for (item i : itens) {
            if (i.isEquipado()) total++;
        }
        return total;
    }

 
    public int ajudasRestantes() {
        int total = 0;
        for (item i : itens) {
            if (i.isEquipado() && !i.isUsado()) total++;
        }
        return total;
    }

   
    public void escolherAjudas() {
        InOut.MsgDeInformacao("Ajudas dos deuses",
                "Escolha " + MAX_AJUDAS + " itens. Cada um so pode ser usado UMA vez em todo o jogo!");
        int limite = Math.min(MAX_AJUDAS, itens.size());
        while (contarEquipados() < limite) {
            equipar();
        }
    }

  
    public void equipar() {
        String lista = "";
        for (item i : itens) {
            if (!i.isEquipado()) {
                lista += i.getIdItem() + " - " + i.getTipoitem() + "\n" + i.getDescricao() + "\n\n";
            }
        }

        int id = InOut.leInt("Escolhidos: " + contarEquipados() + "/" + MAX_AJUDAS
                + "\nItens disponiveis:\n" + lista + "\nDigite o ID do item:");

        for (item i : itens) {
            if (i.getIdItem() == id && !i.isEquipado()) {
                i.setEquipado(true);
                InOut.MsgDeInformacao("Equipar", "Item " + i.getTipoitem() + " escolhido!");
                return;
            }
        }
        InOut.MsgDeErro("Erro", "ID invalido ou item ja escolhido.");
    }

    public void desequipar() {
        String lista = "";
        for (item i : itens) {
            if (i.isEquipado() && !i.isUsado()) {
                lista += i.getIdItem() + " - " + i.getTipoitem() + "\n";
            }
        }
        if (lista.equals("")) {
            InOut.MsgDeErro("Erro", "Nenhum item para desequipar.");
            return;
        }

        int id = InOut.leInt("Itens equipados:\n" + lista + "\nDigite o ID do item a desequipar:");

        for (item i : itens) {
            if (i.getIdItem() == id && i.isEquipado() && !i.isUsado()) {
                i.setEquipado(false);
                InOut.MsgDeInformacao("Desequipar", "Item " + i.getTipoitem() + " devolvido à bolsa!");
                return;
            }
        }
        InOut.MsgDeErro("Erro", "ID invalido ou item nao esta equipado.");
    }

    // Procura um item equipado, nao usado, com um dos efeitos aceitos.
    // Se achar, marca como usado e devolve. Se nao, devolve null.
    public item usarAjuda(Efeito... validos) {
        for (item i : itens) {
            if (i.isEquipado() && !i.isUsado()) {
                for (Efeito e : validos) {
                    if (i.getEfeito() == e) {
                        i.setUsado(true);
                        return i;
                    }
                }
            }
        }
        return null;
    }
}
