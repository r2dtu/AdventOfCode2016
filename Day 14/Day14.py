# I'm getting to really like Python a lot! :D

import hashlib

num_keys = 70
input_string = 'abc'
counter = 0
counter2 = 0
current_key = 0 # 0 is the first key
while current_key < num_keys:
	found = False
	comb = input_string + str(counter)
	hash = hashlib.md5(comb.encode('utf-8')).hexdigest()
	
	# Implement Part 2: key stretching
	for i in range(0, 2016):
		hash = hashlib.md5(hash.encode('utf-8')).hexdigest()
	
	for char in hash:
		if char + char + char in hash:
			counter2 = counter
			while counter2 < counter + 1000 + 1:
				counter2 += 1
				comb = input_string + str(counter2)
				hash2 = hashlib.md5(comb.encode('utf-8')).hexdigest()
				
				# Implement Part 2: key stretching
				for j in range(0, 2016):
					hash2 = hashlib.md5(hash2.encode('utf-8')).hexdigest()
		
				if char + char + char + char + char in hash2:
					print("Key #" + str(current_key) + ": " + str(counter) + ", " + str(counter2))
					current_key += 1
					found = True
					break
		if found == True:
			break

	counter += 1
