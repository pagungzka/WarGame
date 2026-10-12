package main_war;

public class oraculo {
    private String nome;
    private int numSecreto;
    private guerreiro warrior;
    private boolean deuVidaExtra = false;

    public oraculo(guerreiro warrior) {
        this.warrior = warrior;
        numSecreto = 1 + (int) (Math.random() * 100);
    }

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public String prologoIntroducao() {
        return "Ouça, mortal! Do alto do Monte Olimpo, entre relâmpagos de Zeus "
                + "e o mar revolto de Poseidon, eu falo por vontade divina.\n\n"
                + "Sou " + this.nome + ", Oráculo dos Doze, voz dos deuses entre os homens.\n"
                + "Diante de mim se ajoelha " + warrior.getNome()
                + ", escolhido pelo próprio Olimpo para provar seu valor.\n\n"
                + "Três provas separam-te da glória eterna. Terás coragem para "
                + "incumbir-se destes desafios?\n\n"
                + "Que os deuses tenham piedade... a Jornada do Olimpo começa agora!";
    }

    // Mais de 5 palavras = o oraculo se compadece e concede UMA vida extra
    public boolean decidirVidaExtra(String frase) {
        if (frase == null || frase.trim().isEmpty()) {
            return false;
        }
        boolean aceitou = frase.trim().split("\\s+").length > 5;
        if (aceitou) {
            deuVidaExtra = true;
        }
        return aceitou;
    }

    public String RelatorioFimGame() {
        return "Guerreiro: " + warrior.getNome()
                + "\nVidas restantes: " + warrior.getQntVidas()
                + "\nAjudas escolhidas: " + warrior.getMyBolsa().contarEquipados()
                + "\nVida extra concedida: " + (deuVidaExtra ? "sim" : "não");
    }

    public void prologoPerdedor() {
        InOut.MsgSemIcone(this.nome, "O trovão de Zeus silencia... as Moiras cortam o último fio"
                + " com suas tesouras de bronze.\n" + warrior.getNome() + ", lutaste com a bravura de"
                + " um mortal comum, mas os desígnios do Olimpo eram outros...");
        InOut.MsgSemIcone(this.nome, "Eu, " + this.nome + ", levarei tua história ao Hades com honra,"
                + " onde descansarás entre os guerreiros esquecidos.\n"
                + "Talvez, num outro ciclo, os deuses te concedam nova chance.");
        InOut.MsgDeErro("Fim", "Fim de jogo!");
    }

    public void prologoVencedor() {
        InOut.MsgComIcone(this.nome, "Os céus se abrem! Zeus ergue seu raio em saudação,"
                + " e um coro de louvores ecoa pelo Olimpo!\n" + warrior.getNome()
                + ", tu passaste por todas as provações com absoluto êxito.", "/recursos/oraculo.png");
        InOut.MsgDeInformacao(this.nome, "Restam " + warrior.getQntVidas() + " vidas em teu corpo mortal,"
                + " marca de um herói verdadeiro.");
        InOut.MsgDeInformacao(this.nome, "Eu, " + this.nome + ", por vontade dos Doze, te elevo ao posto de"
                + " Semideus, digno de sentar-se ao banquete do Olimpo.\n"
                + "Que teu nome seja gravado entre as constelações!");
    }
    
    private boolean temAjudaDisponivel(guerreiro warrior, Efeito efeito) {
    for (item i : warrior.getMyBolsa().getItens()) {
        if (i.isEquipado() && !i.isUsado() && i.getEfeito() == efeito) {
            return true;
        }
    }
    return false;
}
    
    public void perderVida(guerreiro warrior) {
    item escudo = warrior.getMyBolsa().usarAjuda(Efeito.BLOQUEIA_DANO);

    if (escudo != null) {
        InOut.MsgDeInformacao("Escudo de Zeus",
                "O escudo bloqueou a perda de uma vida!");
    } else {
        warrior.perderVidas();
    }
}
    
    
    public boolean loadLevel01(guerreiro warrior) {

    int tentativas = 1;

    if (temAjudaDisponivel(warrior, Efeito.FAIXA_NUMERO)) {
        String escolha = InOut.leString(
                "Deseja usar o Arco de Artemis para receber uma dica? (S/N)");

        if (escolha.equalsIgnoreCase("S")) {
            item arco = warrior.getMyBolsa().usarAjuda(Efeito.FAIXA_NUMERO);

            if (arco != null) {
                int inicio = ((numSecreto - 1) / 25) * 25 + 1;
                int fim = inicio + 24;

                InOut.MsgDeInformacao("Arco de Artemis",
                        "O numero secreto esta entre " + inicio + " e " + fim + ".");
            }
        }
    }

    if (temAjudaDisponivel(warrior, Efeito.TENTATIVAS_EXTRAS)) {
        String escolha = InOut.leString(
                "Deseja usar as Sandalias de Hermes para ganhar 2 tentativas extras? (S/N)");

        if (escolha.equalsIgnoreCase("S")) {
            item sandalias = warrior.getMyBolsa().usarAjuda(Efeito.TENTATIVAS_EXTRAS);

            if (sandalias != null) {
                tentativas += 2;
                InOut.MsgDeInformacao("Sandalias de Hermes",
                        "Voce ganhou duas tentativas extras!");
            }
        }
    }

    for (int i = 1; i <= tentativas; i++) {
        int palpite = InOut.leInt(
                "Tentativa " + i + " de " + tentativas + ". Diga o seu palpite:");

        if (palpite == numSecreto) {
            InOut.MsgDeInformacao("Parabens",
                    "Tal desafio nao foi nada perante a tua astucia!");
            return true;
        } else {
            if (palpite > numSecreto) {
                InOut.MsgDeInformacao("Incorreto",
                        "O algarismo de Apollo e menor que sua tola aposta."
                        + "\nVidas restantes: " + warrior.getQntVidas());
            } else {
                InOut.MsgDeInformacao("Incorreto",
                        "O algarismo de Apollo e maior que sua tola aposta."
                        + "\nVidas restantes: " + warrior.getQntVidas());
            }
        }
    }

    return false;
}
    
    public boolean loadLevel02(guerreiro warrior) {
    InOut.MsgDeInformacao("LEVEL 2:",
            "Hora da charada de ATENAS!\nResponda as charadas corretamente");

    boolean acertouResposta01 = false;
    boolean acertouResposta02 = false;
    boolean acertouResposta03 = false;

    String resposta01;
    String resposta02;
    String resposta03;

    while (!acertouResposta01) {
        resposta01 = InOut.leString(
                "Charada 1 de 3:\nE mais leve que uma pluma,\nmas nem o homem mais forte do universo "
                + "e capaz de segura-la por mais de 5 minutos,\no que e?\n"
                + "Digite AJUDA para uma dica ou PULAR para pular.\n"
                + "Numero de vidas: " + warrior.getQntVidas());

        if (resposta01.equalsIgnoreCase("AJUDA")) {
            item lanca = warrior.getMyBolsa().usarAjuda(Efeito.DICA_CHARADA);

            if (lanca != null) {
                InOut.MsgDeInformacao("Lanca de Atena",
                        "Dica: pense em algo que fazemos continuamente, sem segurar fisicamente.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui uma Lanca de Atena disponivel.");
            }

        } else if (resposta01.equalsIgnoreCase("PULAR")) {
            item elmo = warrior.getMyBolsa().usarAjuda(Efeito.PULAR_CHARADA);

            if (elmo != null) {
                acertouResposta01 = true;
                InOut.MsgDeInformacao("Elmo de Hades",
                        "Voce pulou esta charada sem perder vida.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui um Elmo de Hades disponivel.");
            }

        } else if (resposta01.equalsIgnoreCase("Respiracao")) {
            acertouResposta01 = true;
            InOut.MsgDeInformacao("Acertou", "Resposta correta");

        } else {
            perderVida(warrior);
            InOut.MsgDeInformacao("Errou",
                    "Atenas esta decepcionada!\nQuantidade de vidas restante: "
                    + warrior.getQntVidas());

            if (warrior.getQntVidas() <= 0) {
                return false;
            }
        }
    }

    while (!acertouResposta02) {
        resposta02 = InOut.leString(
                "Charada 2 de 3:\nO que e que tem cidades, mas nao tem casas,\n"
                + "tem montanhas, mas nao tem arvores,\ntem agua, mas nao tem peixe?\n"
                + "Digite AJUDA para uma dica ou PULAR para pular.\n"
                + "Numero de vidas: " + warrior.getQntVidas());

        if (resposta02.equalsIgnoreCase("AJUDA")) {
            item lanca = warrior.getMyBolsa().usarAjuda(Efeito.DICA_CHARADA);

            if (lanca != null) {
                InOut.MsgDeInformacao("Lanca de Atena",
                        "Dica: pense em uma representacao de um territorio.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui uma Lanca de Atena disponivel.");
            }

        } else if (resposta02.equalsIgnoreCase("PULAR")) {
            item elmo = warrior.getMyBolsa().usarAjuda(Efeito.PULAR_CHARADA);

            if (elmo != null) {
                acertouResposta02 = true;
                InOut.MsgDeInformacao("Elmo de Hades",
                        "Voce pulou esta charada sem perder vida.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui um Elmo de Hades disponivel.");
            }

        } else if (resposta02.equalsIgnoreCase("mapa")) {
            acertouResposta02 = true;
            InOut.MsgDeInformacao("Acertou", "Resposta correta");

        } else {
            perderVida(warrior);
            InOut.MsgDeInformacao("Errou",
                    "Atenas esta decepcionada!\nQuantidade de vidas restante: "
                    + warrior.getQntVidas());

            if (warrior.getQntVidas() <= 0) {
                return false;
            }
        }
    }

    while (!acertouResposta03) {
        resposta03 = InOut.leString(
                "Charada 3 de 3:\nQual o nome da fruta que tem sete letras, "
                + "mas tirando cinco fica onze?\n"
                + "Digite AJUDA para uma dica ou PULAR para pular.\n"
                + "Numero de vidas: " + warrior.getQntVidas());

        if (resposta03.equalsIgnoreCase("AJUDA")) {
            item lanca = warrior.getMyBolsa().usarAjuda(Efeito.DICA_CHARADA);

            if (lanca != null) {
                InOut.MsgDeInformacao("Lanca de Atena",
                        "Dica: observe as letras finais de 'abacaxi', que podem ser lidas como numero romano.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui uma Lanca de Atena disponivel.");
            }

        } else if (resposta03.equalsIgnoreCase("PULAR")) {
            item elmo = warrior.getMyBolsa().usarAjuda(Efeito.PULAR_CHARADA);

            if (elmo != null) {
                acertouResposta03 = true;
                InOut.MsgDeInformacao("Elmo de Hades",
                        "Voce pulou esta charada sem perder vida.");
            } else {
                InOut.MsgDeErro("Ajuda indisponivel",
                        "Voce nao possui um Elmo de Hades disponivel.");
            }

        } else if (resposta03.equalsIgnoreCase("abacaXI")) {
            acertouResposta03 = true;
            InOut.MsgDeInformacao("Magnifico",
                    "Tal sabedoria e fora da compreensao!");

        } else {
            perderVida(warrior);
            InOut.MsgDeInformacao("Errou",
                    "Atenas esta decepcionada!\nQuantidade de vidas restante: "
                    + warrior.getQntVidas());

            if (warrior.getQntVidas() <= 0) {
                return false;
            }
        }
    }

    return true;
}

    public boolean loadLevel03(guerreiro warrior) {
    String[] jokenpo = {"Pedra", "Papel", "Tesoura"};

    InOut.MsgDeInformacao("LEVEL 3",
            "Minigame final: Pedra, Papel ou Tesoura contra o Oraculo!"
            + " Venca ao menos 2 das 3 rodadas.");

    int vitoriasGuerreiro = 0;

    for (int rodada = 1; rodada <= 3; rodada++) {
        if (warrior.getQntVidas() <= 0) {
            return false;
        }

        String respostaGuerreiro = InOut.leString(
                "Rodada " + rodada + " - Escolha: PEDRA, PAPEL ou TESOURA"
                + " (Vidas: " + warrior.getQntVidas() + ")");

        int escolhaOraculo = (int) (Math.random() * 3);

        int validarRespostaGuerreiro = -14;

        for (int k = 0; k < jokenpo.length; k++) {
            if (jokenpo[k].equalsIgnoreCase(respostaGuerreiro)) {
                validarRespostaGuerreiro = k;
            }
        }

        if (validarRespostaGuerreiro == -14) {
            InOut.MsgDeErro("Invalido",
                    "Escolha invalida! O Oraculo vence essa rodada por padrao.");

            perderVida(warrior);
            continue;
        }

        InOut.MsgSemIcone("Resultado da rodada",
                "Voce: " + respostaGuerreiro
                + " | Oraculo: " + jokenpo[escolhaOraculo]);

        if (validarRespostaGuerreiro == escolhaOraculo) {
            item espada = warrior.getMyBolsa()
                    .usarAjuda(Efeito.EMPATE_VIRA_VITORIA);

            if (espada != null) {
                vitoriasGuerreiro++;

                InOut.MsgDeInformacao("Espada de Ares",
                        "A espada transformou o empate em uma vitoria!");
            } else {
                InOut.MsgSemIcone("Empate",
                        "Rodada empatada! Ninguem perde vida.");
            }

        } else if ((validarRespostaGuerreiro == 0 && escolhaOraculo == 2)
                || (validarRespostaGuerreiro == 1 && escolhaOraculo == 0)
                || (validarRespostaGuerreiro == 2 && escolhaOraculo == 1)) {

            vitoriasGuerreiro++;

            InOut.MsgDeInformacao("Voce venceu!",
                    "Voce venceu essa rodada!");

        } else {
            perderVida(warrior);

            InOut.MsgDeAviso("Oraculo venceu",
                    "O Oraculo venceu essa rodada! Voce perdeu uma vida.");
        }
    }

    return vitoriasGuerreiro >= 2 && warrior.getQntVidas() > 0;
}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public guerreiro getWarrior() {
        return warrior;
    }
}
