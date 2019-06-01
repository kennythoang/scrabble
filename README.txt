This is an implementation of the popular board game Scrabble I made for my final project in CIS120, Fall 2018. 
It utilizes the following four core concepts from the course:

  1. 2-D arrays: There is a 15 x 15 2d array of board spaces, that stores the current state of that 
  space. The 2-d array is thus able to be looped through for the game logic, as the created board
  space class that makes up the 2d array can hold tiles, bonus point multipliers, and location,
  allowing it to be able to be used in scoring of plays and drawing the board.

  2. Collections: I'm using numerous collections. One is a set for the dictionary of valid words.
  Another is numerous array lists that hold a tile object that control and manage the state of each
  player's tile rack. Next is a map for high scores. Last is a set for the tile bag. Set for
  dictionary works bc no repeats, arraylist for tile rack is good because max size is 7 and it is
  frequently resized and necessary to get items quickly and by index. Map for high scores is good
  as key value is the score itself and value is an arraylist of strings of the names who made those
  high scores, thus allowing quick sorting of the high scores and quick access to the list of people
  who made those high scores (and allows for repeats of the same high score by numerous people). 
  Set for tile bag is good because no repeats of tile instances. 

  3. File I/O: Using file i/o to save high scores and who made them by outputting data out into a 
  text file and reading it in each time a new game is instantiated. Opted to do high scores instead
  of actually saved games due to time restrictions. 

  4. Testable component: testing selection of tiles, scoring, and validness of plays, as well
  as other Scrabble tasks (drawing tiles). 


  Here's an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Space: enum for different multipliers a board space could be (double letter, etc).
  BoardSpace: Object that makes up the actually board. Can draw itself, handle location, what the 
  space (multiplier) is, if any, and the tile that is on it, if any.
  Game: Implements Runnable and the main that is run when the game is launched. 
  Dictionary: manages a collection of valid Scrabble words
  TokenScanner: token iterator for a given reader
  Player: manages each player, including their name, score, and tile rack
  Tile: object that can draw itself, handle its location, what its point value is, and if it is
  currently selected by the player or not. Can be placed/drawn on a player's tile rack, or the 
  board, or be held in the tile bag.
  Board: handles all game logic and functionality, and much of the drawing (although it really is
  just looping through the 2d array of boardspaces and tile racks, which then draw themselves). 
  Logic and functionality wise it responds to cues from the player including their responses to
  dialog boxes and drop down menus, and a mouse clicker which handles where the player clicks and
  how to respond to it. Scores plays and verifies whether plays are valid, updating whose turn it
  is and the appropriate score, and when the game is over determines and produces the winner
  and high scores. 

To execute the game, run the Game.java file. 
