# distributed_file_system
Sistema de arquivos distribuído construído para a disciplina de Programação Distribuída.

## Compilação
Para compilar o projeto, basta executar um script usando o seguinte comando:
```
sh compile.sh
```
## Execução
Rode o servidor com o comando:
```
java src.Server
```

Execute o cliente com o comando:
```
java src.Client
```

No cliente, os seguintes comandos podem ser executados para operar sobre os arquivos:
| Comando | Função |
|--|--|
| `ls <path>` | listar conteúdo de um diretório |
| `mkfile <path>` | criar arquivo |
| `mkdir <path>` | criar diretório |
| `rmfile <path>` | remover arquivo |
| `rmdir <path>` | remover diretório |
| `cp <source> <destination>` | copiar arquivo |
| `open <path>` | abrir arquivo |
| `write <path>` | escrever arquivo |
