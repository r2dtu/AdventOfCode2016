baseds = []

def rotate(str, num, scrambled):
	return str[num:] + str[:num] if not scrambled else str[-num:] + str[:-num]

# scrambled - is input scrambled? we must unscramble
def getScrambled(passcode, content, scrambled):

	if scrambled == True:
		content.reverse()

	for instr in content:
		if 'rotate' in instr:
			if 'based' in instr:

				# "rotated based on position of letter x"
				x = instr[-2]
				index = passcode.index(x)

				if not scrambled:
					# rotate once
					passcode = rotate(passcode, -1, scrambled)

					# rotate position times
					passcode = rotate(passcode, -index, scrambled)

					# rotate once if position >= 4
					if index >= 4:
						passcode = rotate(passcode, -1, scrambled)
					baseds.append(index)
					
				else:
				
					# rotate once if position >= 4
					index = baseds.pop()
					if index >= 4:
						passcode = rotate(passcode, -1, scrambled)

					# rotate position times
					passcode = rotate(passcode, -index, scrambled)

					# rotate once
					passcode = rotate(passcode, -1, scrambled)
			else:
				# "rotate left/right x step"
				x = 0
				if 'steps' in instr:
					x = int(instr[-8], 10)
				else:
					x = int(instr[-7], 10)

				x = x % len(passcode)
				if 'right' in instr:
					passcode = rotate(passcode, -x, scrambled)
				else:
					passcode = rotate(passcode, x, scrambled)
		
		elif 'swap' in instr:
			if 'position' in instr:
				# "swap position x with position y"
				x = int(instr[14], 10)
				y = int(instr[-2], 10)
				
				if x > y:
					temp = x
					x = y
					y = temp
				
				temp1 = passcode[x]
				temp2 = passcode[y]
				
				passcode = passcode[0:x] + temp2 + passcode[x+1:y] + temp1 + passcode[y+1:]
				
			else:
				# "swap letter x with letter y"
				x = instr[12]
				y = instr[-2]
				
				index1 = passcode.index(x)
				index2 = passcode.index(y)

				if index1 > index2:
					temp = index1
					index1 = index2
					index2 = temp

				temp1 = passcode[index1]
				temp2 = passcode[index2]
				
				passcode = passcode[0:index1] + temp2 + passcode[index1+1:index2] + temp1 + passcode[index2+1:]

		elif 'move' in instr:
			# move position x to position y (remove from x, insert so it ends up at y)
			x = int(instr[14], 10)
			y = int(instr[-2], 10)
			temp = passcode[x if not scrambled else y]
			if not scrambled:
				if x < y:
					passcode = passcode[0:x] + passcode[x+1:y+1] + temp + passcode[y+1:]
				else:
					passcode = passcode[0:y] + temp + passcode[y:x] + passcode[x+1:]
			else:
				if x < y:
					passcode = passcode[0:x] + temp + passcode[x:y] + passcode[y+1:]
				else:
					passcode = passcode[0:y] + passcode[y+1:x+1] + temp + passcode[x+1:]

		elif 'reverse' in instr:
			# reverse positions x through y (including x and y)
			x = int(instr[18], 10)
			y = int(instr[-2], 10)
			
			string = passcode[x:y+1]
			reverseStr = string[::-1]
			passcode = passcode[0:x] + reverseStr + passcode[y+1:]
		#print passcode
	return passcode

if __name__ == '__main__':
	
	passcode = 'abcdefgh'

	fname = 'C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day21_input.txt'
	with open(fname) as f:

		content = f.readlines()

	print "Scrambled Password of " + passcode + " is",
	print getScrambled(passcode, content, False)

	# Things that are different in reverse: rotations & move position
	#passcode = getScrambled(passcode, content, False)
	passcode = 'fbgdceah'
	print "Un-scrambled Password of " + passcode + " is",
	print getScrambled(passcode, content, True)
