import hashlib

def findPass(input_string, part2):
	password = [] if not part2 else ['#','#','#','#','#','#','#','#']
	counter = 0
	numFilled = 0
	while numFilled < 8:
		comb = input_string + str(counter)
		hash = hashlib.md5(comb.encode('utf-8')).hexdigest()
		if hash.startswith('00000'):
			print(hash)
			if not part2:
				password.append(hash[5])
				numFilled += 1
			else:
				if hash[5].isdigit() and int(hash[5]) < 8 and password[int(hash[5])] == '#':
					password[int(hash[5])] = hash[6]
					numFilled += 1
		counter += 1

	return ''.join(password)

input_string = 'ojvtpuvg'
print(findPass(input_string, False))
print(findPass(input_string, True))