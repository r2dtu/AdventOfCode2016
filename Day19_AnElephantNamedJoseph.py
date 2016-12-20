# Part 1 - algorithm credits to Numberphile
import math

n = 3004953
x = math.floor(math.log(n, 2))
l = n - (2 ** x)
winner = (2 * l) + 1

print "Index of winner (Part 1): " + str(winner)

# Part 2: only works for small values of n
elves = []
curPos = 0

for i in range(1, n + 1):
	elves.append(i)

while len(elves) > 1:
	lost = ((curPos + len(elves)) / 2) % len(elves)
	elves.remove(elves[lost]) # inefficient remove, see Day19(2)
	curPos = (curPos + 1) % n

print "Index of winner (Part 2):",
print elves
