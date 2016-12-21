# Test inputs
#length = 20
#input = '10000'

# Actual inputs
# length1: 272
length = 35651584
input = '01111001100111011'

while len(input) < length:

	# Reverse input string
	reverseStr = input[::-1]
	
	# Replace all 1s w/ 0s and 0s w/ 1s
	inputB = ''.join('1' if x == '0' else '0' for x in reverseStr)
	
	# A + 0 + B
	input = input + '0' + inputB

# Calculate checksum (if checksum has even length)
checksum = ''
while len(checksum) % 2 == 0:

	# Reset
	checksum = ''
	
	# range(length)[0::2]
	for i in xrange(0, length if len(input) > length else len(input), 2):

		pair = input[i:i+2]
		if input[i] == input[i+1]:
			checksum += str(1)
		else:
			checksum += str(0)

	# We don't care about the original input anymore, so why not
	input = checksum

print checksum