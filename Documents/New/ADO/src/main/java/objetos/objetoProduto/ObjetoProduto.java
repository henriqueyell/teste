
package objetos.objetoProduto;

public class ObjetoProduto {
    
      private int iCodigo;
    private String sNome;
    private String sTipoProd;
    private String sQuantidade;
    private String sValorVenda;
    private String sDesc;
    private boolean bStatus;
    private String sValorCompra;

    public String getsValorCompra() {
        return sValorCompra;
    }

    public void setsValorCompra(String sValorCompra) {
        this.sValorCompra = sValorCompra;
    }

    public boolean isbStatus() {
        return bStatus;
    }

    public void setbStatus(boolean bStatus) {
        this.bStatus = bStatus;
    }
    

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public int getiCodigo() {
        return iCodigo;
    }

    public void setiCodigo(int iCodigo) {
        this.iCodigo = iCodigo;
    }

    public String getsNome() {
        return sNome;
    }

    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public String getsTipoProd() {
        return sTipoProd;
    }

    public void setsTipoProd(String sTipoProd) {
        this.sTipoProd = sTipoProd;
    }

    public String getsQuantidade() {
        return sQuantidade;
    }

    public void setsQuantidade(String iQuantidade) {
        this.sQuantidade = iQuantidade;
    }

    public String getsValor() {
        return sValorVenda;
    }

    public void setsValor(String fValor) {
        this.sValorVenda = fValor;
    }
    
}
