""" 
Implemented as a doubly-linked list. This is more efficient than
the Day19.py, because that uses list's remove(), which as a very
bad worst case runtime. Thank you Gary from CSE 12 and CSE 15L.
"""

# Generic Doubly Linked List Node 
class Node:

	# Add Node
	def __init__(self, data):
		self.nxt = None
		self.prv = None
		self.data  = data

	# Delete Node - well, more like disconnect
	def delete_Node(self):
		self.prv.nxt = self.nxt
		self.nxt.prv = self.prv

if __name__ == "__main__":
	n = raw_input("How many elves are playing? ")
	print "The winner (Part 2) is Elf #",

	# xrange, b/c n can be very large - virtual list
	elves = map(Node, xrange(n))
	for i in xrange(n):
		elves[i].nxt = elves[(i + 1) % n]
		elves[i].prv = elves[(i - 1) % n]

	# Elf 1 starts, kills the middle guy
	startP = elves[0]
	midP = elves[n / 2]

	# Go round and round n - 1 times
	for i in xrange(n - 1):
	
		# Disconnect the middle node, and reconnect the mid pointer
		# to the next middle node (to kill, essentially)
		midP.delete_Node()
		midP = midP.nxt
		if (n - i) % 2 == 1: midP = midP.nxt
		
		# The next elf goes
		startP = startP.nxt

	# Return 1 more than the data, because we zero indexed everything,
	# including the data
	print startP.data + 1