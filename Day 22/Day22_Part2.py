# Basically just printing the grid - solvable that way (trace through output)

def addTile(used, minSize, column):
	if used > minSize:
		column.append('#')
	elif used == 0:
		column.append('_')
	else:
		column.append('.')

input_file = 'Day22_input.txt'

with open(input_file) as f:
	nodes = f.readlines()

nodes.pop(0)
nodes.pop(0)
pos = nodes[-1].split()[0].split('-')
maxX = int(pos[-2][pos[-2].index('x') + 1:])
maxY = int(pos[-1][pos[-1].index('y') + 1:])

# Find the minimum size of files:
minSize = 0
for node in nodes:
	sp = node.split()
	if sp[1] != '0T' and (int(sp[1][:-1]) < minSize or minSize == 0):
		minSize = int(sp[1][:-1])

# Let's add to the grid
grid = [[]]
for node in nodes:
	sp = node.split()
	used = int(sp[2][:-1])
	x = int(sp[0].split('-')[-2][sp[0].split('-')[-2].index('x') + 1:])
	y = int(sp[0].split('-')[-1][sp[0].split('-')[-1].index('y') + 1:])
	if x == maxX:
		if y == 0:
			grid[y].append('G')
		else:
			addTile(used, minSize, grid[y])
	else:
		addTile(used, minSize, grid[y])
		if len(grid) - 1 == y:
			grid.append([])

for row in grid:
	print (" ".join(char for char in row))