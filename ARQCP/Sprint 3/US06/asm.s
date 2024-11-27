.section .data

.section .text
	.global extract_token
	#parameters
	#%rdi- *input
	#%rsi- *token
	#%rdx- *output
extract_token:
	#prologue
	pushq %rbp			#saves the base pointer
	movq %rsp, %rbp     #sets the base pointer to the stack pointer

	movq %rsi, %r8 		#moves the value in rsi (char pointed by token) in r8
	movb (%rdi), %cl    #moves the value in rdi (char pointed by input) in cl
	cmp $0, %cl     	#compares if the char pointed by input is 0
	je not_extracted    # if the char pointed by input is 0, jump to not_extracted

	jmp loop			#jump unconditionally to loop

counter:
	movb (%rdi), %cl	#moves the value in rdi (char pointed by input) to cl
	cmp $0, %cl			#compares if the value pointed by the input is 0
	je not_extracted	#if yes, jump to not_extracted

	cmp $35, %cl		#compare if the value pointed by the input with '#' in ASCII code
	je input_inc		#if equal, jump to input_inc
	jmp next			#jump unconditionally to next

input_inc:
	incq %rdi			#pointer *input points to the next char

loop:
	movb (%rsi), %r9b	#move the char pointed by *token to r9b
	cmp $0, %r9b       	#compares 0 with the char pointed by *token, to see if it's a null terminator
	je match 			#if yes then jump to match

	movb (%rdi), %r10b  #move the char pointed by *input to r10b
	cmp $0, %r10b   	#compare it to 0
	je not_extracted	#if yes, jump to not_extracted

	cmp %r10b, %r9b   	#compare the char pointed by *input and pointed by *token
	jne no_match   		#if they are not equal to each other, jump to no_match

	incq %rsi 			#increments rsi for *token to point to the next char
	incq %rdi 			#increments rdi for *input to point to the next char
	jmp loop			#jump unconditionally to loop

match:
	jmp get_value		#jumps unconditionally to get_value

no_match:
	movq $0, %rax 		#move 0 to rax
	jmp return_value	#jump to return_value

next:
	incq %rdi       	#increment rdi for *input to point to the next char
	jmp counter     	#jump unconditionally to counter

get_value:
	movq $0, %rax 		# place 0 in rax
	movq $0, %rcx		# place 0 in rcx

extract_number:
	movzbq (%rdi), %rcx #moves the char pointed by *input to rcx

	cmp $0, %cl 		#compare if the char pointed by *input is a null terminator
	je update_output 	#if it is, jump to update_output

	cmp $35, %cl 		#compare if the char pointed by *input is equal to '#'
	je update_output 	#if it is, jump to update_output

	cmp $46, %cl		#compare if the char pointed by *input is equal to '.'
	je input_inc_loop	#if it is, jump to input_inc_loop

	sub $'0', %cl		#converting the char pointed by *input from ASCII code to the correspondent integer value

	imulq $10, %rax 	#multiplies the result by 10
	add %rcx, %rax 		#sum the integer value pointed by the *input to rax

	jmp input_inc_loop	#jump unconditionally to input_inc_loop

input_inc_loop:
	incq %rdi			#increment rdi for *input point to the next char
	jmp extract_number 	#jump unconditionally to extract_number

update_output:
	movl %eax, (%rdx)	#moves the result extracted to *output
	movq $1, %rax		# set return value to 1 (success)
	jmp return_value	#jump to return_value

not_extracted:
	movq $0, %rax		# set return value to 0 (failure)

return_value:
	#epilogue
	movq %rbp, %rsp		#restores the stack pointer
	popq %rbp			#restores the base pointer
	ret					#return
