import hashlib

def getHash(hash, times):
	# Implements Parts 1 & 2 (key stretching)
	for i in range(0, times):
		hash = hashlib.md5(hash.encode('utf-8')).hexdigest()
	return hash

input_string = 'abc'
counter = 39
hash = getHash(input_string + str(counter), 1)
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

