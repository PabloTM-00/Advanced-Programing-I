# Unit 5. Generic Classes. Exercise 5.2
Description of the exercise
In this exercise we will practice on the use of collections and maps. We will use several types of maps together with lists and sets to create an application that allows us to make several lists with the words that appear in a text so that we know for each word of the text.

How many times it appears.

The lines in which it appears.

The lines and the position (or positions) within each line in which it appears.

To do this, we are going to build three different indexes:

CounterIndex will display each word in the text with its number of occurrences.

LineIndex will display each word with all the lines in which it appears.

PositionInLineIndex will display each word with all the lines it appears in, and within each line, the positions in which it appears.

For example, for text:

    (line 1) How much wood would a woodchuck chuck if a woodchuck could chuck wood?
    (line 2) He would chuck, he would, as much as he could, and chuck as much wood as a woodchuck would if a woodchuck could chuck wood.
Using CounterIndex we would get the following output, which displays each word followed by the number of times it appears in the text:

a 4
and 1
as 4
chuck 5
could 3
he 3
how 1
if 2
much 3
wood 4
woodchuck 4
would 4
With LineIndex we would obtain the following output, which displays the words of the text along with the lines where they appear:

a <1,2>
and <2>
as <2>
chuck <1,2>
could <1,2>
he <2>
how <1>
if <1,2>
much <1,2>
wood <1,2>
woodchuck <1,2>
would <1,2>
Finally, with PositionInLineIndex we would obtain an index in which, for each word, the lines in which it appears are shown, and within each line, all the positions in which it appears:

a
1 <5,9>
2 <17,21>
and
2 <11>
as
2 <6,8,13,16>
chuck
1 <7,12>
2 <3,12,24>
could
1 <11>
2 <10,23>
he
2 <1,4,9>
how
1 <1>
if
1 <8>
2 <20>
much
1 <2>
2 <7,14>
wood
1 <3,13>
2 <15,25>
woodchuck
1 <6,10>
2 <18,22>
would
1 <4>
2 <2,5,19>
To separate the words within each line we will use the following delimiters. In the examples above, the delimiters string is "[ .,:;\\-\\!\\?]+" (The following delimiters could also be used "[ˆa-zA-Z0-9áéíóúüÁÉÍÓÚÜ]+").