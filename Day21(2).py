# When it wasn't late at night...
from itertools import *

baseds = []

# right: num = (-), left: num = (+)
def rotate(str, num, scrambled):
	return str[num:] + str[:num] if not scrambled else str[-num:] + str[:-num]

# scrambled - is input scrambled? we must unscramble
def getScrambled(passcode, content, scrambled):
	# Convert to list - much easier to swap, etc. than with string
	passcode = list(passcode)
	if scrambled == True:
		content.reverse()

	for instr in content:
		strs = instr.split()
		pos = [int(num) for num in strs if num.isdigit()]
		
		if 'rotate' in instr:
			# "rotated based on position of letter x"
			if 'based' in instr:
				x = strs[-1]
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
				# "rotate left/right x step(s)"
				x = pos[0]
				x = x % len(passcode)
				if 'right' in instr:
					passcode = rotate(passcode, -x, scrambled)
				else:
					passcode = rotate(passcode, x, scrambled)
		
		elif 'swap' in instr:
			if 'position' in instr:
				# "swap position x with position y"
				x, y = pos
				passcode[x], passcode[y] = passcode[y], passcode[x]
			else:
				# "swap letter x with letter y"
				x, y = strs[2], strs[-1]				
				index1 = passcode.index(x)
				index2 = passcode.index(y)
				passcode[index1], passcode[index2] = passcode[index2], passcode[index1]

		elif 'move' in instr:
			# move position x to position y (remove from x, insert so it ends up at y)
			x, y = pos
			if scrambled:
				y, x = x, y
			temp = passcode.pop(x)
			passcode = passcode[:y] + [temp] + passcode[y:]

		elif 'reverse' in instr:
			# "reverse positions x through y" (including x and y)
			x, y = pos
			passcode[x:y+1] = passcode[x:y+1][::-1]

	return ''.join(passcode)

if __name__ == '__main__':
	
	passcode = 'abcdefgh'
	fname = 'Day21_input.txt'
	with open(fname) as f:
		content = f.readlines()

	print("Scrambled Password of " + passcode + " is " + getScrambled(passcode, content, False))

	# The following only works if you have the sequence of "rotate based on letters" after scrambling it
	"""
	passcode = getScrambled(passcode, content, False)
	#passcode = 'fbgdceah'
	print("Un-scrambled Password of " + passcode + " is " + getScrambled(passcode, content, True))
	"""
	
	# Brute force - the rotate based on letter position throws off the unscrambling =/
	scrambledCode = 'fbgdceah'
	for perm in permutations(scrambledCode):
		if getScrambled(perm, content, False) == scrambledCode:
			print("Un-scrambled Password of " + scrambledCode + " is " + ''.join(perm))