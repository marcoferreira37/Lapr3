.section .data

.section .text

	.global mediana
	.global sort_array

mediana:
	movq $0, %rax			#limpa rax
	cmp $0, %esi
	je end
	movl $0, %r9d			# Inicialize counter r9d in 0
	movl %esi, %eax
	movl $2, %ecx
	cdq
	idivl %ecx				# Now mediana indice (num/2) is safe in position eax

	pushq %r9
	pushq %rax
	pushq %rsi
	call sort_array
	popq %rsi
	popq %rax
	popq %r9

loop:
	cmpl %r9d, %eax
	je found

	addq $4, %rdi			# Move to the next element of the array
	incl %r9d				# Increment counter
	jmp loop

found:
	movl (%rdi), %eax		# Place mediana value in eax
	ret

end:
	ret






