# Calculadora de Distâncias

## Para Rodar é necessário:
- Java 17;
- Ide de sua preferência
- Configuracão de planilhas:
  - Dentro da pasta resources/planilhas
    - championship: é uma tabela com todos os jogos do campeonato com formato mandante x visitante
      - coluna A representa o mandante e a coluna B o visitante
      - não deve conter separador de jogo ou rodada
      - não deve conter cabeçalhos
      - a aba da planilha deve ser nomeada com o ano da tabela desejada
    - roadmap: representando a distância entre as localizações das equipes
      - os nomes das colunas devem estar de acordo com as localidades apresentada na planilha de teams
      - a aba da planilha deve ser nomeada com o ano da tabela desejada
      - teams: lista das equipes e suas localidades
        - deve conter os 20 clubes do campeonato brasileiro do ano desejado 
        - cada time deve estar associado a uma localidade a escolha (cidade, centro de treinamento, estádio, etc)
        - a aba da planilha deve ser nomeada com o ano da tabela desejada

## Endpoint
#### Retorna todos os itens

```http
  POST /calculate
```

| Parâmetro   | Tipo       |Formato   | Descrição                               |
| :---------- | :--------- | :--------- |:----------------------------------------|
| `year` | `Body` | `string` | **Obrigatório**. Ano da tabela desejada |



  
