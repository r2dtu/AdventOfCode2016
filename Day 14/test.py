import hashlib

input_string = 'ihaygndm'
counter = 3098
comb = input_string + str(counter)
hash = hashlib.md5(comb.encode('utf-8')).hexdigest()
print(hash)
counter2 = counter

for char in hash:
	found = False
	if hash.find(char + char + char) > -1:
		counter2 = counter
		while counter2 < counter + 1000 + 1:
			counter2 += 1
			comb = input_string + str(counter2)
			hash2 = hashlib.md5(comb.encode('utf-8')).hexdigest()
			if hash2.find(char + char + char + char + char) > -1:
				print(str(counter) + ", " + str(counter2))
				found = True
				break
	if found == True:
		break

