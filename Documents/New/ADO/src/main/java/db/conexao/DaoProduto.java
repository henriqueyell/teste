
package db.conexao;

import db.conexao.utils.ConnectionUtils;
import objetos.objetoProduto.ObjetoProduto;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DaoProduto {
    
    
    
public static void inserir(ObjetoProduto produto) throws SQLException, Exception{
    
        // construindo a string de inserção no BD na tabela produto
        String sql = "INSERT INTO Produto (Nome, TipoProd, Quantidade, "
                + " Valor, Descricao, Status)" 
                + " VALUES (?,?, ?, ?, ?, ?)";
    
        
        // Conexao para abertura e fechamento do BD
        Connection connection = null;
        
        
        PreparedStatement psinsereprod = null;
         
        try{
            // abrindo conexão
            connection = ConnectionUtils.getConnection();
            
            // criar um preparedStatement para execução de instruções SQL
            psinsereprod = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            // configura os parametros do preparedStatement
  
            psinsereprod.setString(1, produto.getsNome());
            psinsereprod.setString(2, produto.getsTipoProd());
            psinsereprod.setString(3, produto.getsQuantidade());
            psinsereprod.setString(4, produto.getsValor());
            psinsereprod.setString(5, produto.getsDesc());
            psinsereprod.setBoolean(6, true);
            
            psinsereprod.execute();
           
            psinsereprod.getGeneratedKeys().next();
              
        }
        finally{
            
            // Se o preparedStatement ainda estiver aberto, realiza fechamento
            if (psinsereprod != null && !psinsereprod.isClosed()){
                psinsereprod.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
        }
                
        
}

public static void atualiza(ObjetoProduto produto) throws SQLException,Exception{
    
    // construindo a strin de inserção no BD na tabela produto
        String sql = "UPDATE Produto SET Nome=?, TipoProd=?, Quantidade=?, "
                + "Status =?, Valor=?,"
                + "Descricao=? WHERE IdProduto =?";
        
        // Conexao para abertura e fechamento do BD
        Connection connection = null;
        
        
        PreparedStatement psupdateprod = null;
        
        
        try{ 
            // abrindo conexão
            connection = ConnectionUtils.getConnection();
            
            // Criar um preparedStatement para executar instruções SQL
            psupdateprod = connection.prepareStatement(sql);
            
            // configura os parametros do preparedStatement       
            psupdateprod.setString(1, produto.getsNome());
            psupdateprod.setString(2, produto.getsTipoProd());
            psupdateprod.setString(3, produto.getsQuantidade());
            psupdateprod.setBoolean(4, true);
            psupdateprod.setString(5, produto.getsValor());
            psupdateprod.setString(6, produto.getsDesc());
            psupdateprod.setInt(7, produto.getiCodigo());
            
            psupdateprod.execute();  
            
            
        }
        catch (Exception e) {
            // imprimir erro tecnico no consile
            e.printStackTrace();
        
        }    
        finally{
            //Se o preparedStatement ainda estiver aberto, realiza seu fechamento
            if (psupdateprod != null && !psupdateprod.isClosed()) {
                psupdateprod.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
            
        }
        
    }

public static void excluir(int id) throws SQLException, Exception{
    
        String sql = "UPDATE Produto SET Status = ? WHERE IdProduto =?";
        
        //Conexão para abertura e fechamento
        Connection connection = null;
        
        //Statement para obtenção e execução de comandos SQL.
        PreparedStatement psdelete = null;
    
        try{
            
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execução de instruções SQL
            psdelete = connection.prepareStatement(sql);
            
            //Configura os parâmetros do "psdelete"
            psdelete.setBoolean(1, false);
            psdelete.setInt(2, id);
            
            
            //Executa o comando no banco de dados
            psdelete.execute();
            
        }
        finally{
            
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (psdelete != null && !psdelete.isClosed()) {
                psdelete.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
}

public static List<ObjetoProduto> listar() throws SQLException,Exception{
    
    String sql = "SELECT * FROM Produto WHERE Status=?" ;
    
    List<ObjetoProduto> listaProd = null;
    
    // Conexao para abertura e fechamento do BD
    Connection connection = null;
      
    //Statement para obtenção de comandos SQL e fechamentos de conexão
    PreparedStatement psselectprod = null;
    
    //Armazenará os resultados do banco de dados
    ResultSet rsprod = null;
    
    try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();

            //Cria um statement para execução de instruções SQL
            psselectprod = connection.prepareStatement(sql);
            
            psselectprod.setBoolean(1, true);
            
            //Executa a consulta SQL no banco de dados
            rsprod = psselectprod.executeQuery();
            
            //Itera por cada item do resultado
            while (rsprod.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaProd == null) {
                    listaProd = new ArrayList<>();
                }
                
                ObjetoProduto produto = new ObjetoProduto();
                
                produto.setiCodigo(rsprod.getInt("IDPRODUTO"));
                produto.setsNome(rsprod.getString("Nome"));
                produto.setsTipoProd(rsprod.getString("TipoProd"));
                produto.setsQuantidade(rsprod.getString("Quantidade"));
                produto.setsValor(rsprod.getString("Valor"));
                produto.setsDesc(rsprod.getString("Descricao"));
 
                //Adiciona a instância na lista
                listaProd.add(produto);
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (rsprod != null && !rsprod.isClosed()) {
                rsprod.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (psselectprod != null && !psselectprod.isClosed()) {
                psselectprod.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaProd;
    }
  
public static List<ObjetoProduto> procura(String nome) throws SQLException, Exception{
    
    String sql= "SELECT * FROM Produto WHERE UPPER(nome) LIKE UPPER(?) AND Status = ?";
    
    List<ObjetoProduto> listaProd =null;
    
    //Conexão para abertura e fechamento
    Connection connection = null;
        
    //Statement para obtenção através da conexão, execução comandos SQL e fechamentos
    PreparedStatement psprocuraprod = null;
        
    //Armazenará os resultados do banco de dados
    ResultSet rsprocuraprod = null;
    
    try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();

            //Cria um statement para execução de instruções SQL
            psprocuraprod = connection.prepareStatement(sql);
           
            psprocuraprod.setString(1, "%" +nome+ "%");
            psprocuraprod.setBoolean(2, true);
            
            //Executa a consulta SQL no banco de dados
            rsprocuraprod = psprocuraprod.executeQuery();
            
            //Itera por cada item do resultado
            while (rsprocuraprod.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaProd == null) {
                    listaProd = new ArrayList<>();
                }
                
                ObjetoProduto produto = new ObjetoProduto();
                produto.setiCodigo(rsprocuraprod.getInt("IDPRODUTO"));
                produto.setsNome(rsprocuraprod.getString("Nome"));
                produto.setsTipoProd(rsprocuraprod.getString("TipoProd"));
                produto.setsQuantidade(rsprocuraprod.getString("Quantidade"));
                produto.setsValor(rsprocuraprod.getString("Valor"));
                produto.setsDesc(rsprocuraprod.getString("Descricao"));
 
                //Adiciona a instância na lista
                listaProd.add(produto);
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (rsprocuraprod != null && !rsprocuraprod.isClosed()) {
                rsprocuraprod.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (psprocuraprod != null && !psprocuraprod.isClosed()) {
                psprocuraprod.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    
    return listaProd;
}

public static ObjetoProduto obter(int id) throws SQLException, Exception{
    
    
    String sql = "SELECT * FROM produto WHERE IDProduto=? AND STATUS=?";
    
    //Conexão para abertura e fechamento
    Connection connection = null;

    //Statement para obtenção através da conexão, execução de
    //comandos SQL e fechamentos
    PreparedStatement psobterprod = null;

    //Armazenará os resultados do banco de dados
    ResultSet rsobterprod = null;
    
    try{
        
        //Abre uma conexão com o banco de dados
        connection = ConnectionUtils.getConnection();

        //Cria um statement para execução de instruções SQL
        psobterprod = connection.prepareStatement(sql);

        //Configura os parâmetros do "PreparedStatement"
        psobterprod.setInt(1, id);
        psobterprod.setBoolean(2, true);

        //Executa a consulta SQL no banco de dados
        rsobterprod = psobterprod.executeQuery();
        if(rsobterprod.next()){
           
            ObjetoProduto produto = new ObjetoProduto();
            produto.setiCodigo(rsobterprod.getInt("IDPRODUTO"));
            produto.setsNome(rsobterprod.getString("Nome"));
            produto.setsTipoProd(rsobterprod.getString("TipoProd"));
            produto.setsQuantidade(rsobterprod.getString("Quantidade"));
            produto.setsValor(rsobterprod.getString("Valor"));
            produto.setsDesc(rsobterprod.getString("Descricao"));
                
            return produto;
        }
        
        
    }
    finally{
        
        //Se o result ainda estiver aberto, realiza seu fechamento
            if (rsobterprod != null && !rsobterprod.isClosed()) {
                rsobterprod.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (psobterprod != null && !psobterprod.isClosed()) {
                psobterprod.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
    }
        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
    return null;
}

    
}
