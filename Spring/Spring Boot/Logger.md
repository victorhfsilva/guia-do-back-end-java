
# **Logger:**

### **Obtenção de uma Instância de Logger:**
   
   - Crie uma instância de logger na classe onde você deseja realizar o log.

   ```java
   private static final Logger logger = LoggerFactory.getLogger(SuaClasse.class);
   ```

### **Tipos de Logs:**
   - Use os diferentes métodos de log para registrar informações, como `info`, `debug`, `warn` e `error`.

   ```java
   logger.info("Isso é uma mensagem de informação.");
   logger.debug("Isso é uma mensagem de depuração.");
   logger.warn("Isso é uma mensagem de aviso.");
   logger.error("Isso é uma mensagem de erro.");
   ```

**Dicas:**

- O Logger é útil para registrar informações e depuração na aplicação.
- O Logger também pode ser usado em outros componentes, serviços e controladores para registrar eventos na aplicação.