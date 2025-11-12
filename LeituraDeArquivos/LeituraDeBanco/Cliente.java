package LeituraDeBanco;

public class Cliente implements Comparable<Cliente>{

    // #region Atributos
    private String nome;
    private int idade;
    private String cpf;
    private String rg;
    private String data_nascimento;
    private String sexo;
    private String signo;
    private String mae;
    private String pai;
    private String email;
    private String senha;
    private String cep;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private long telefone;
    private long celular;
    private float altura;
    private float peso;
    private String tipo_sangue;
    private String cor;
    // #endregion

    // #region Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipo_sangue() {
        return tipo_sangue;
    }

    public void setTipo_sangue(String tipo_sangue) {
        this.tipo_sangue = tipo_sangue;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    // #endregion

    public void registrarCliente(String linha) {
        String palavra[] = linha.split(",");

        //System.out.println(palavra);
    
        setNome(palavra[0]);
        setIdade(Integer.parseInt(palavra[1]));
        setCpf(palavra[2]);
        setRg(palavra[3]);
        setData_nascimento(palavra[4]);
        setSexo(palavra[5]);
        setSigno(palavra[6]);
        setMae(palavra[7]);
        setPai(palavra[8]);
        setEmail(palavra[9]);
        setSenha(palavra[10]);
        setCep(palavra[11]);
        setEndereco(palavra[12]);
        setNumero(Integer.parseInt(palavra[13]));
        setBairro(palavra[14]);
        setCidade(palavra[15]);
        setEstado(palavra[16]);
        palavra[17] = palavra[17].replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
        setTelefone(Long.parseLong(palavra[17]));
        palavra[18] = palavra[18].replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
        setCelular(Long.parseLong(palavra[18]));
        setAltura(Float.parseFloat(palavra[19]));
        setPeso(Float.parseFloat(palavra[20]));
        setTipo_sangue(palavra[21]);
        setCor(palavra[22]);
    }

    
    @Override
    public int compareTo(Cliente outraConta) {
        
        // if (this.idade > outraConta.idade) {
        //     return 1;
        // }
        // if (this.idade < outraConta.idade) {
        //     return -1;
        // }
       
       // ordenação por nome (usando a implementação do compareTo dos Strings)
     if(this.nome.compareTo(outraConta.nome) > 0)
           return 1;
       if(this.nome.compareTo(outraConta.nome) < 0)
           return -1;
       return 0;

    }
}
