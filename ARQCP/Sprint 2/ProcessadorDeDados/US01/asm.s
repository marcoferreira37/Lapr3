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
	je end              # if the char pointed by input is 0, jump to the end

	jmp loop			#jump inconditionally to loop

counter:
	movb (%rdi), %cl	#moves the value in rdi (char pointed by input) to cl
	cmp $0, %cl			#compares if the value pointed by the input is 0
	je end				#if yes, jump to end

	cmp $35, %cl		#compare if the value pointed by the input with '#' in ASCII code
	je input_inc		#if equal, jump to input_inc
	jmp next			#jump inconditionally to next

input_inc:
	incq %rdi			#pointer *input points to the next char

loop:
	movb (%rsi), %r9b	#move the char pointed by *token to r9b
	cmp $0, %r9b       	#compares 0 with the char pointed by *token, to see if it's a null terminator
	je match 			#if yes then jump to match

	movb (%rdi), %r10b  #move the char pointed by *input to r10b
	cmp $0, %r10b   	#compare it to 0
	je end				#if yes, jump to end

	cmp %r10b, %r9b   	#compare the char pointed by *input and pointed by *token
	jne no_match   		#if they are not equal to eachother, jump to no_match


	incq %rsi 			#increments rsi for *token to point to the next char
	incq %rdi 			#increments rdi for *input to point to the next char
	jmp loop			#jump inconditionally to loop

match:
	jmp get_value		#jumps inconditionally to get_value


no_match:
	movq $0, %rax 		#move 0 to rax
	movq %r8, %rsi 		#moves the value from r8 to rsi for the pointer *token to point to the element in r8
	jmp next 			#jump inconditionally to next

next:
	incq %rdi       	#increment rdi for *input to point to the next char
	jmp counter     	#jump inconditionally to counter

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

	sub $'0', %cl		#converting the char pointed by *input from ascii code in the correspondent integer value

	imulq $10, %rax 	#multiplies the result by 10
	add %rcx, %rax 		#sum the integer value pointed by the *input to rax

	jmp input_inc_loop	#jump inconditionally to input_inc_loop

input_inc_loop:
	incq %rdi			#increment rdi for *input point to the next char
	jmp extract_number 	#jump inconditionally to extract_number

update_output:
	movq %r8, %rsi 		#moves the value of the char pointer by the token into rsi again

	popq %rdi 			#restores the initial value of *input
	movl  %eax,(%rdx)	#moves the result extracted to *output


end:
	#epilogue
	movq %rbp, %rsp		#restores the stack pointer
	popq %rbp			#restores the  base pointer
	ret					#return




