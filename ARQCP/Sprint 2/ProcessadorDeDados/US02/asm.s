.section .text
.global enqueue_value             # Declaração global da função enqueue_value

enqueue_value:
    # Prologue
    pushq %rbp                     # Salva o ponteiro de base da pilha
    movq %rsp, %rbp                # Atualiza o ponteiro de base da pilha

    cmp $0, %esi                   # Compara o comprimento com 0
    je end                         # Se for igual, salta para o final (end)

    movl (%rcx), %r9d              # Carrega o índice de escrita (write)
    imull $4, %r9d                 # Multiplica por 4 para obter o deslocamento
    movslq %r9d, %r9               # Estende o sinal de r9d para r9
    addq %r9, %rdi                 # Adiciona o deslocamento para o array
    movl %r8d, (%rdi)              # Armazena o valor (r8d) em array[write]

    addl $1, (%rcx)                # Incrementa *write

    pushq %rdx                     # Salva *read

    movl (%rcx), %eax              # Carrega *write em eax
    cltd                           # Estende o sinal de eax para edx
    idivl %esi                     # Divide por comprimento
    movl %edx, (%rcx)              # Armazena o resto em *write

    popq %rdx                      # Restaura *read

    movl (%rdx), %r9d              # Carrega *read em r9d
    cmp (%rcx), %r9d               # Compara *read com *write
    je bufferIsEmpty               # Se forem iguais, salta para bufferIsEmpty

    jmp end                        # Caso contrário, salta para o final (end)

bufferIsEmpty:
    addl $1, (%rdx)                # Incrementa *read

    pushq %rdx                     # Salva *read

    movl (%rdx), %r9d              # Carrega *read em r9d
    cltd                           # Estende o sinal de eax para edx
    idivl %esi                     # Divide por comprimento
    movl %edx, %r9d                # Armazena o resto em r9d

    popq %rdx                      # Restaura *read

    movl %r9d, (%rdx)              # Move o resto de eax para *read

end:
    # Epilogue
    movq %rbp, %rsp                # Restaura o ponteiro de pilha
    popq %rbp                      # Desempilha o ponteiro de base da pilha
    ret                             # Retorna da função
