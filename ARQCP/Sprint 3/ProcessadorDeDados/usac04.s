.section .data

.section .text
	.global sort_array

sort_array:
		movl $0, %r9d			#set r9d to 0

loop:
	cmpl $0, %esi				#compare esi to 0
	je end						#if esi =0 , jump to end

	decl %esi					#decrement esi (num) by 1
	movl %esi, %r9d				#move esi into r9d
	jmp loop2					#jump to loop2

loop2:
	cmpl $0, %r9d				#compare r9d to 0
	je end2						#if r9d is 0, jump to end2

	movl (%rdi), %ecx			#load the value from rdi to ecx
	movl %ecx, %r10d			#move ecx into r10d
	addq $4, %rdi				#add 4 to rdi
	movl (%rdi), %ecx			#load the value at rdi into ecx
	movl %ecx, %r11d			#move ecx into r11d
	cmpl %r10d, %r11d			#compare r10d to r11d
	jl change  					#if r11d is lower than r10d, jump to change

	decl %r9d					#decrement r9d by 1
	jmp loop2					#jump to loop2

change:
	subq $4, %rdi				#subtract 4 from rdi
	movl %r11d, (%rdi)			#move r11d into the value at rdi
	addq $4, %rdi				#add 4 to rdi
	movl %r10d, (%rdi)			#move r10d into the value at rdi
	decl %r9d					#decrement r9d by 1
	jmp loop2					#jump to loop2

end2:
	movl %esi, %r9d				#move esi into r9d
	jmp back					#jump to back

back:
	cmpl $0, %r9d				#compare if r9d is 0
	je loop						#if r9d is 0, jump to loop

	subq $4, %rdi				#subtract 4 from rdi
	decl %r9d					#decrement 1 from r9d
	jmp back					#jump to back

end:
	ret							#return


