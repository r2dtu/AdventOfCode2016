# Test input:
#input = '.^^.^.^^^^'

# Real input:
input = ".^^^^^.^^.^^^.^...^..^^.^.^..^^^^^^^^^^..^...^^.^..^^^^..^^^^...^.^.^^^^^^^^....^..^^^^^^.^^^.^^^.^^"

# Checks endpoints for safety (adding '.')
def isSafe2(tiles, char):
	if char == 'L':
		return isSafe3('.' + tiles)
	return isSafe3(tiles + '.')

# Checks if next row is safe, depending on current row pattern
# See Day 18 for criteria (or read code)
def isSafe3(tiles):
	if tiles[0] == '.' and tiles[1] == tiles[2] == '^':
		return False
	if tiles[2] == '.' and tiles[1] == tiles[0] == '^':
		return False
	if tiles[0] == '^' and tiles[0] != tiles[1] and tiles[0] != tiles[2]:
		return False
	if tiles[2] == '^' and tiles[2] != tiles[1] and tiles[2] != tiles[0]:
		return False
	return True

if __name__ == "__main__":
	
	print "Number of safe spaces: ",
	
	#numRows = 40
	numRows = 400000
	
	# Count number safe in current input before we go to next row
	numSafe = input.count('.')

	for j in range(numRows - 1):
		temp = input
		input = ''
		for i in range(len(temp)):

			# Left edge
			if i == 0:
				if isSafe2(temp[i:i+2], 'L'):
					numSafe += 1
					input += '.'
				else:
					input += '^'

			# Right edge
			elif i == len(temp) - 1:
				if isSafe2(temp[i-1:i+1], 'R'):
					numSafe += 1
					input += '.'
				else:
					input += '^'

			# Middle
			else:
				if isSafe3(temp[i-1:i+2]):
					numSafe += 1
					input += '.'
				else:
					input += '^'

		# Debug - print row
		#print input
		
	print numSafe