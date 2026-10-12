package main_war;

public class Main_war {

    public static void main(String[] args) {

        guerreiro meuGuerreiro = new guerreiro();
        oraculo meuOraculo = new oraculo(meuGuerreiro);

        // nomes
        meuOraculo.definirNome(InOut.leString("Digite o nome do oráculo: "));
        meuGuerreiro.definirNome(InOut.leString("Digite o nome do guerreiro: "));
        InOut.MsgDeInformacao("Sucesso", "O nome do oráculo é " + meuOraculo.getNome());
        InOut.MsgDeInformacao("Sucesso", "O nome do guerreiro é " + meuGuerreiro.getNome());

        // vidas e ajudas (a bolsa pertence ao guerreiro)
        meuGuerreiro.sortearVidas();
        meuGuerreiro.getMyBolsa().escolherAjudas();
        for (item i : meuGuerreiro.getMyBolsa().getItens()) {
            if (i.isEquipado()) {
                i.imprimirDados();
            }
        }

        // prologo (so uma vez)
        InOut.MsgComIcone(meuOraculo.getNome(), meuOraculo.prologoIntroducao(), "/recursos/oraculo.png");
        audio.tocarSom();

        int fase = 1;
        boolean pediuVidaExtra = false;

        while (fase <= 3) {
            boolean passou;

            if (fase == 1) {
                InOut.MsgDeInformacao("LEVEL 1", "O primeiro nível, A Rifa de Apollo. "
                        + "Faça um palpite entre 1 e 100. Que a sorte esteja contigo.");
                passou = meuOraculo.loadLevel01(meuGuerreiro);
                if (!passou) {
                    meuOraculo.perderVida(meuGuerreiro);
                }
            } else if (fase == 2) {
                passou = meuOraculo.loadLevel02(meuGuerreiro);
            } else {
                InOut.MsgDeInformacao(meuOraculo.getNome(), "Hora do desafio final");
                passou = meuOraculo.loadLevel03(meuGuerreiro);
            }

            if (passou) {
                fase++;
            } else if (meuGuerreiro.getQntVidas() <= 0) {
                // acabaram as vidas: so pode pedir misericordia uma vez
                if (pediuVidaExtra) {
                    break;
                }
                pediuVidaExtra = true;
                if (meuOraculo.decidirVidaExtra(meuGuerreiro.vidaExtra())) {
                    meuGuerreiro.setQntVidas(1);
                    InOut.MsgDeInformacao("Misericórdia", "Você ganhou UMA vida extra!");
                } else {
                    break;
                }
            }
        }

        if (fase > 3) {
            meuOraculo.prologoVencedor();
        } else {
            audio.pararSom();
            audio.gameOver();
            meuOraculo.prologoPerdedor();
        }

        InOut.MsgDeInformacao("Relatório final", meuOraculo.RelatorioFimGame());
    }
}
