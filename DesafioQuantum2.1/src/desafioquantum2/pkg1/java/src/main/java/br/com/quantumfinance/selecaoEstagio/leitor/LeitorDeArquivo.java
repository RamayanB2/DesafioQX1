package br.com.quantumfinance.selecaoEstagio.leitor;

import br.com.quantumfinance.selecaoEstagio.dto.Acao;
import br.com.quantumfinance.selecaoEstagio.dto.Cotacao;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeitorDeArquivo {

    public List<String> lerArquivo() {

        try (InputStream resourceAsStream = LeitorDeArquivo.class.getResourceAsStream("/acoes.csv")) {
            // Leitura do arquivo.
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
            // ignora a primeira linha
            br.readLine();
            List<String> cotacoes = new ArrayList<>();

            String linha;
            while ((linha = br.readLine()) != null) {
                cotacoes.add(linha);
            }
            return cotacoes;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro de IO.");
            e.printStackTrace();
        }
        throw new RuntimeException("Erro na leitura do arquivo, consulte o console para maiores detalhes.");
    }
}


	
	

