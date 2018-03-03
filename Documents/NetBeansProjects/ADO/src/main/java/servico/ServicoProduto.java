
package servico;

import db.conexao.DaoProduto;
import java.util.List;

import exception.data.DataSourceException;
import objetos.objetoProduto.ObjetoProduto;


public class ServicoProduto {
    
     public static void cadastrarProduto(ObjetoProduto produto)
            throws  DataSourceException {
        
        //Realiza validações no quarto

        try {
            //Realiza a chamada de inserção na fonte de dados
            DaoProduto.inserir(produto);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Atualiza um quarto na fonte de dados
    public static void atualizarProduto(ObjetoProduto produto)
            throws  DataSourceException {
        
        //Realiza validações no quarto

        try {
            //Realiza a chamada de atualização na fonte de dados
            DaoProduto.atualiza(produto);
            
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Realiza a pesquisa de um quarto por número na fonte de dados
    public static List<ObjetoProduto> procurarProduto(String Nome)
            throws  DataSourceException {
        try {
            //Verifica se um parâmetro de pesquisa não foi informado.
            //Caso afirmativo, realiza uma listagem simples do mock.
            //Caso contrário, realiza uma pesquisa com o parâmetro
            if (Nome == null || "".equals(Nome)) {
                return DaoProduto.listar();
            } else {
                return DaoProduto.procura(Nome);
            }
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Obtem o Produto com ID informado do mock
    public static ObjetoProduto obterProduto(int id)
            throws  DataSourceException {
        try {
            //Retorna o Produto obtido com o DAO
            return DaoProduto.obter(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Exclui o quarto com ID informado do mock
    public static void excluirProduto(int id)
            throws  DataSourceException {
        try {
            //Solicita ao DAO a exclusão do quarto informado
            DaoProduto.excluir(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}


    

