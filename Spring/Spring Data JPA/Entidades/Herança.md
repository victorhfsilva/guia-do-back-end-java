# Estratégias de Herança

`InheritanceType` é uma anotação usada em mapeamento de herança no Java Persistence API (JPA) para definir a estratégia de herança a ser usada ao persistir classes em um banco de dados. A anotação `InheritanceType` pode ser aplicada à classe pai de uma hierarquia de herança para especificar como as subclasses herdarão os campos e os mapeamentos de banco de dados. Existem três estratégias de herança principais disponíveis no JPA:

**1. SINGLE_TABLE (Tabela Única):**
- Todas as classes da hierarquia de herança são mapeadas para uma única tabela no banco de dados.
- Campos específicos de cada classe são identificados por uma coluna discriminadora (geralmente chamada `dtype`).
- Use quando as classes compartilham muitos campos em comum.
- Exemplo:
  ```java
  @Entity
  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  @DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
  public class BaseEntity {
      @Id
      @GeneratedValue
      private Long id;
  }

  @Entity
  public class SubclassA extends BaseEntity {
      //...
  }
  ```

**2. JOINED (Tabelas Separadas):**
- Cada classe da hierarquia de herança é mapeada para uma tabela separada no banco de dados.
- São criadas tabelas separadas para cada classe com seus campos específicos.
- Use quando as classes têm muitos campos exclusivos.
- Exemplo:
  ```java
  @Entity
  @Inheritance(strategy = InheritanceType.JOINED)
  public class BaseEntity {
      @Id
      @GeneratedValue
      private Long id;
  }

  @Entity
  public class SubclassA extends BaseEntity {
      //...
  }
  ```

**3. TABLE_PER_CLASS (Tabela para Cada Classe):**
- Cada classe da hierarquia de herança é mapeada para uma tabela separada no banco de dados, sem uma tabela comum.
- As tabelas não possuem informações de herança.
- Use quando as classes têm campos significativamente diferentes e você deseja evitar colunas nulas.
- Exemplo:
  ```java
  @Entity
  @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
  public class BaseEntity {
      @Id
      @GeneratedValue
      private Long id;
  }

  @Entity
  public class SubclassA extends BaseEntity {
      //...
  }
  ```

**3. MappedSuperClass:**

- Caso a classe base da hierarquia não seja uma entidade (`@Entity`) ela deve ser anotada com `@MappedSuperclass`.
- Todas as filhas de classes `@MappedSuperclass` contém todos os itens das classes superiores dentro da sua tabela.
- A diferença das classes `@MappedSuperclass` e das `@Entity` é que as `@MappedSuperclass` não permitem consultas polimorficas como consultar todos os itens das classes filhas ao consultar a classe mãe.

```java
@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

}
```

```java
@Entity
public class Person extends BaseEntity {

	@Column(name = "first_name")
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@NotBlank
	private String lastName;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
```
Essas estratégias determinam como as classes e as tabelas do banco de dados se relacionam em uma hierarquia de herança. A escolha da estratégia depende das necessidades específicas do seu aplicativo e da estrutura da hierarquia de herança. Certifique-se de selecionar a estratégia mais apropriada com base nas características das classes e dos dados a serem armazenados.