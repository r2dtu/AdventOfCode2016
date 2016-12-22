input_file = 'Day22_input.txt'

with open(input_file) as f:
	nodes = f.readlines()

nodes.pop(0)
nodes.pop(0)

numPairs = 0
for node in nodes:

	# node pos | size | used | avail | use%
	sp = node.split()
	print(int(sp[3][:-1]))
	if sp[2] != '0T':
		for nextNode in nodes:
			if nextNode == node:
				continue
			spN = nextNode.split()
			if int(sp[2][:-1]) <= int(spN[3][:-1]):
				numPairs += 1

print(numPairs)