<prompt>
  <description>Gerar um arquivo README.md para documentar um projeto.</description>
  <objectives>
    <objective>Descrever o propósito e funcionalidades do projeto.</objective>
    <objective>Instruir como executar o projeto localmente.</objective>
    <objective>Listar oportunidades de melhorias futuras.</objective>
    <objective>Indicar onde estão localizados os prompts utilizados no projeto.</objective>
  </objectives>
  <sections>
    <section title="📘 Sobre o Projeto">
      <content>Descreva brevemente o que o projeto faz, qual problema resolve e quais tecnologias principais foram utilizadas.</content>
    </section>
    <section title="🚀 Como Executar o Projeto">
      <content>
        <step>Clone o repositório: <code>git clone &lt;URL_DO_REPOSITORIO&gt;</code></step>
        <step>Instale as dependências conforme necessário.</step>
        <step>Execute o backend conforme instruções específicas.</step>
        <step>Suba o banco de dados MongoDB com o seguinte comando Docker:</step>
        <code>docker run --name mongodb -d -p 27017:27017 mongo</code>
      </content>
    </section>
    <section title="💡 Oportunidades de Melhoria">
      <content>
        <item>Adicionar testes automatizados.</item>
        <item>Melhorar a cobertura de logs e tratamento de erros.</item>
        <item>Implementar autenticação e autorização.</item>
        <item>Documentar a API com Swagger ou outra ferramenta.</item>
      </content>
    </section>
    <section title="🧠 Prompts Utilizados">
      <content>Os prompts utilizados para gerar código, documentação e ideias estão localizados na pasta <code>/prompts</code> do projeto.</content>
    </section>
  </sections>
  <output>README.md</output>
</prompt>