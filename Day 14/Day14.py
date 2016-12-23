# I'm getting to really like Python a lot! :D
import hashlib

def getHash(hash, times):
	# Implements Parts 1 & 2 (key stretching)
	for i in range(times):
		hash = hashlib.md5(hash.encode('utf-8')).hexdigest()
	return hash

def getNext1000Hashes(salt, index, part1):
	hashes = []
	for i in range(1000):
		hashes.append(getHash(salt + str(index),  1 if part1 else 2017))
		index += 1
	return hashes

def find3(hash):
	for char in hash:
		if char * 3 in hash:
			return char
	return ''

def find5(hash, char):
	if char * 5 in hash:
		return char
	return ''

def getKeyIndex(input_string, num_keys, part1):
	counter = 0
	current_key = 0 # 0 is the first key
	while current_key < num_keys:
		hash = getHash(input_string + str(counter), 1 if part1 else 2017)
		char = find3(hash)
		counter += 1
		if char != '':
			hash2 = getNext1000Hashes(input_string, counter, part1)
			for nHash in hash2:
				if find5(nHash, char):
					print("Key #" + str(current_key) + ": " + str(counter - 1))
					current_key += 1
					break
	return counter - 1

def getKeyIndex1(input_string, num_keys):
	return getKeyIndex(input_string, num_keys, True)

def getKeyIndex2(input_string, num_keys):
	return getKeyIndex(input_string, num_keys, False)

input_string = 'abc'
num_keys = 64
print("Part 1:", getKeyIndex1(input_string, num_keys + 1))
print("Part 2:", getKeyIndex2(input_string, num_keys + 1))