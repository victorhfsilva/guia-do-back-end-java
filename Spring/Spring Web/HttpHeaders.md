# HttpHeaders

### **1. Criando e Modificando Cabeçalhos**

   - **Criando um objeto HttpHeaders:**
   
     ```java
     HttpHeaders headers = new HttpHeaders();
     ```

     Isso cria um novo objeto HttpHeaders que pode ser usado para armazenar informações sobre os cabeçalhos HTTP da solicitação ou resposta.

   - **Adicionando e Substituindo Valores:**
   
     ```java
     headers.add("NomeDoCabeçalho", "ValorDoCabeçalho");
     headers.set("NomeDoCabeçalho", "NovoValor");
     ```

     `add()` adiciona um valor ao cabeçalho especificado, enquanto `set()` substitui todos os valores existentes pelo novo valor.

   - **Removendo Cabeçalhos:**
   
     ```java
     headers.remove("NomeDoCabeçalho");
     ```

     Isso remove o cabeçalho especificado da lista de cabeçalhos.

### **2. Acessando Valores dos Cabeçalhos**

   - **Obtendo Valores de um Cabeçalho:**
   
     ```java
     List<String> values = headers.get("NomeDoCabeçalho");
     ```

     Isso retorna todos os valores associados ao cabeçalho especificado.

   - **Verificando a Existência de um Cabeçalho:**
   
     ```java
     boolean exists = headers.containsKey("NomeDoCabeçalho");
     ```

     Isso verifica se um cabeçalho específico está presente na lista de cabeçalhos.

   - **Obtendo Todos os Nomes de Cabeçalho:**
   
     ```java
     Set<String> headerNames = headers.keySet();
     ```

     Isso retorna um conjunto de todos os nomes de cabeçalho presentes na lista de cabeçalhos.

### **3. Definindo Informações Específicas do Cabeçalho**

   - **Definindo o Tipo de Mídia (Content-Type):**
   
     ```java
     headers.setContentType(MediaType.APPLICATION_JSON);
     ```

     Define o tipo de mídia da solicitação ou resposta para JSON.

   - **Definindo um Token de Autorização (Bearer Token):**
   
     ```java
     headers.set("Authorization", "Bearer SeuTokenDeAutorização");
     ```

     Define um token de autorização para autenticação no cabeçalho da solicitação.

   - **Definindo um Cabeçalho de Autenticação Básica (Basic Auth):**
   
     ```java
     String basicAuth = "usuário:senha";
     byte[] base64Creds = Base64.encode(basicAuth.getBytes());
     headers.set("Authorization", "Basic " + new String(base64Creds));
     ```

     Define um cabeçalho de autorização usando autenticação básica com base no nome de usuário e senha.

   - **Definindo Cookies:**
   
     ```java
     headers.add("Cookie", "cookie1=valor1; cookie2=valor2");
     ```

     Define os cookies que devem ser incluídos na solicitação.

### **4. Obtendo Informações Específicas**

   - **Obtendo o Tipo de Mídia (Content-Type) do Cabeçalho:**
   
     ```java
     MediaType mediaType = headers.getContentType();
     ```

     Retorna o tipo de mídia especificado no cabeçalho.

   - **Obtendo o Token de Autorização do Cabeçalho:**
   
     ```java
     String authorization = headers.getFirst("Authorization");
     ```

     Retorna o token de autorização especificado no cabeçalho.

