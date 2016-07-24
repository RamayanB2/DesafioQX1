/** 
* Problema Programa��o Curta - Quantum
* @autor Danielle Caled 
*
*/

import br.com.quantumfinance.selecaoEstagio.dto.Acao;
import br.com.quantumfinance.selecaoEstagio.dto.Cotacao;
import br.com.quantumfinance.selecaoEstagio.leitor.LeitorDeArquivo;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * Esta classe implementa as solu��es das quest�es de 1 a 5, cada uma delas em um m�todo distinto.
 * Os resultados das quest�es s�o retornados em linha de comando.
 */

public class Questoes 
{

	/** M�todo para a a execu��o das quest�es de 1 a 5.
	 * @return void 
	 */
         static ArrayList<Acao> acoes;
         static DateFormat format;

	public static void main(String[] args) 
	{
		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<String> cotacoes = leitor.lerArquivo();                
	       
                format = new SimpleDateFormat("yyyy-MM-dd");
                acoes = new ArrayList();
                loadAcoes(cotacoes);                
                
		calculaMenorFechamentoPorAcao(acoes);
		calculaMaiorRetornoPorAcao(acoes);
		calculaVolumeMedioPorAcao(acoes);
	}
        
        private static void loadAcoes(List<String> cotacoes){
            try {
                String ultima_acao = "";
                Acao acao = new Acao("");
                for(String s: cotacoes){
                    String[] info_linha = s.split(",");
                    Cotacao cot;
                        cot = new Cotacao(info_linha[0],
                            format.parse(info_linha[1]),
                            Float.parseFloat(info_linha[2]),
                            Long.parseLong(info_linha[3]));
                    if(!ultima_acao.equalsIgnoreCase(info_linha[0])){ 
                        /*Se mudou o nome da a��o recebido, cria uma nova*/
                        if(!acao.getNome().equalsIgnoreCase(""))acoes.add(acao);
                        acao = new Acao(info_linha[0]);
                    }
                   acao.adicionaCotacao(cot);
                   ultima_acao =info_linha[0];
                   /*Registra a �ltima a��a que foi passada.J� que os dados s�o guardados em blocos
                   nos quais o nome da a��o muda de uma vez,� poss�vel guardar o nome da ultima a��o
                   numa vari�vel para diminuir o tempo computacional de fazer um for para procurar se
                   a a��o � do mesmo nome ou n�o de uma j� existente
                   */
                }
                acoes.add(acao);
            } catch (ParseException ex) {
                Logger.getLogger(Questoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

	private static void calculaMenorFechamentoPorAcao(List<Acao> acoes){ 
            System.out.println("============ MENOR FECHAMENTO POR A��O ================");
            for(Acao a:acoes){
                List<Cotacao> cotacoes = a.getCotacoes();
                float min_val = 99999;
                float fech;
                Date date = new Date();
                for(Cotacao cot:cotacoes){
                    fech = cot.getFechamento();
                    if(fech<min_val){
                        min_val=fech;
                        date= cot.getData();
                    }
                }
                System.out.println("A��o: "+a.getNome()+" Dia: "+date.toString()+" Fechamento: "+ min_val);
            }
        }

	private static void calculaMaiorRetornoPorAcao(List<Acao> acoes){
            System.out.println("=============== MAIOR RETORNO POR A��O ===================");
            for(Acao a:acoes){
                List<Cotacao> cotacoes = a.getCotacoes();
                float penult_fech = 0;
                boolean isFirst = true;
                float maior_retorno = 0;
                Date date = new Date();
                for(Cotacao cot:cotacoes){
                    if(!isFirst){
                        float ult_fech = cot.getFechamento();
                        float retorno_cot = (ult_fech/penult_fech)-1;
                        cot.setRetorno(retorno_cot);
                        if(retorno_cot>maior_retorno){
                            maior_retorno=retorno_cot;
                            date= cot.getData();
                        }
                        penult_fech = ult_fech;
                    }else{
                        float ult_fech = cot.getFechamento();
                        penult_fech = ult_fech;
                        cot.setRetorno(0);                        
                    }
                    isFirst = false;
                }     
                System.out.println("A��o: "+a.getNome()+" Dia: "+date.toString()+" Maior retorno: "+ maior_retorno);
            }
        }

	private static void calculaVolumeMedioPorAcao(List<Acao> acoes){
            System.out.println("================ VOLUME MEDIO POR A��O ==============");
            for(Acao a:acoes){
                long vol_med;
                long vol_total = 0 ;
                int count = 0;
                List<Cotacao> cotacoes = a.getCotacoes();
                for(Cotacao cot:cotacoes){                    
                    if(cot.getVolume()!=0){
                        vol_total += cot.getVolume();
                        count++;
                        /*desconsidera linhas  vol = 0*/
                    }
                }
                vol_med = Math.round(vol_total/count);
                System.out.println("A��o: "+a.getNome()+" Volume m�dio: "+ vol_med);
                
            }
        }
}
