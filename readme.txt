Names and Logins:

Smita Jain: cs61bl-dh
Aparna Narayan: cs61bl-dg

Contribution:
Smita Jain: For my contribution to this project, I wrote some of the methods in Board.java such as the findBestSquares method and canSelect, along with other methods. I contributed to the JUnit testing for these methods and cleaning and adjusting the code as necessary.

Aparna Narayan: For my contribution to this project, I figured out which data structures to use for various aspects of the project, wrote the constructors, and several methods, like canMakeMove, selectDot, canDeselect, isClosedShape, removeSelectedDots, and others. Smita and I worked together to debug the program and write the tests. I wrote the tests for the methods/constructors in Dot.java., and we split up the tests for the methods/constructors in Board.java.

TEST DESCRIPTIONS:

BoardTest.java
testConstructor:
The constructor test makes sure that a board with the correct sizes is built and also that the exceptions are thrown if an invalid size 
out of the given range is inputted.
testMovesAllowed:
Checks that when a valid number of moves is added, that the number of MovesAllowed is changed. And if an invalid input is entered, 
then the MovesAllowed stays at its default or previous input.
testCanSelect():
Validates that only dots which are next to a selected dot AND are of the same color can be selected. Results return false if the dots
 are of a different color, or are not adjacent to the last selected dot.
testSelectDot()
Assures that valid selected dots are added to the SelectedDots array.
testCanDeselect():
Assures that only the current dot can be deselected in any given sequence of selected dots.
testRemoveSelectedDots()
Checks that the dots in the selected dots list are properly removed. 
If dots are unable to be removed it throws the given exception.
testIsClosedShape():
Checks that the arrangement is a proper closed shape as specified by the instruction guidelines and if it is not, that the program does not recognize it as a closed shape.
testFillDotsRemoved():
Checks to make sure that the removed dot spaces are no longer a null value.
testDropDots():
Tests to make sure that the dots are dropped into the given space and that null values exist in the upper levels of hte board.
testBestSquare:
Checks that if multiple squares are found on the board, it returns the square which removes the most dots overall. 
Also it validates that if no square is present it will return a null BestSquares list.
testIsGameOver:
Checks two cases: if there are no more moves in numMovesLeft, the game should be over, or if there are no more possible moves (canMakeMove returns false), the game should be over.
testCanMakeMove:
Checks if there are any possible moves that can be made on the preset board. This can only be done if there are dots of the same color adjacent to one another.

DotTest.java
testRandomConstructor: Checks when a Dot is constructed by the no-args constructor, its color is between the range of 1 and 5.
testDotConstructor: Checks if an error is thrown when an illegal color argument is given. If not, the Dot’s myColor argument should be properly set to the color argument.
testGetColor: Checks if we can properly access myColor through the public getColor() method.
testIsSameColor: Checks if 2 dots have the same color.
testIsColor: Checks if the dot calling the method has the same color as the color argument provided.

