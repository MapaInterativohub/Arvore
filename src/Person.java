// A classe Person representa uma pessoa com nome e idade.
// Ela implementa Comparable para permitir ordenação em árvores binárias.
public class Person implements Comparable<Person> {
    
    // Atributo que guarda o nome da pessoa
    private String name;

    // Atributo que guarda a idade da pessoa
    private int age;

    // Construtor: usado para criar uma nova pessoa com nome e idade
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    /*
        O método compareTo define como duas pessoas serão comparadas.
        Aqui, estamos ordenando APENAS pelo nome, ignorando letras maiúsculas/minúsculas.
        Isso é fundamental para que a árvore binária saiba onde inserir cada pessoa.
    */
    @Override
    public int compareTo(Person other){
        return this.name.compareToIgnoreCase(other.name);
    }

    /*
        toString é chamado quando queremos imprimir o objeto.
        Exemplo de saída: Maria(30)
    */
    @Override
    public String toString(){
        return name + "(" + age + ")";
    }

    /*
        Esses dois métodos abaixo (equals e hashCode) são essenciais para que a busca
        na árvore funcione corretamente. Sem eles, o contains() nunca encontra
        um objeto Person criado separadamente, mesmo que tenha o mesmo nome.

        Aqui, consideramos que duas pessoas são iguais se tiverem o mesmo NOME.
        Isso faz sentido porque a árvore está ordenada por nome.
    */

    @Override
    public boolean equals(Object o){

        // Se for o mesmo objeto na memória
        if(this == o) return true;

        // Se o outro objeto for nulo ou de classe diferente, não são iguais
        if(o == null || getClass() != o.getClass()) return false;

        // Fazemos um cast seguro para Person
        Person p = (Person) o;

        // Compara apenas o nome (ignorando maiúsculas/minúsculas)
        return this.name.equalsIgnoreCase(p.name);
    }

    @Override
    public int hashCode(){
        // Converte o nome para minúsculas antes de gerar o hash para manter consistência
        return name.toLowerCase().hashCode();
    }
}
