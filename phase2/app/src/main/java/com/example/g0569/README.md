# GROUP 0569 GAME PROJECT
A mini rpg style game where a player has to collect team members to join their team in order to beat the
final boss. Has three stages, the player must first navigate through a maze to reach NPCs. After, they must
battle them in a game of autochess in order for them to join their team. Then must fight the boss in order
to successfully win the game.

## Installation
Nothing special required. Should be able to run directly.

## Level One-- Maze Game
Upon loading the game, a maze is generated and NPCs placed around the maze. The player can either use the
direction buttons, or can turn on screen rotation toggle and tilt the screen in order to move. A timer starts
as well and the user must make contact with as many NPCs as possible under the time limit. Once the timer
stops, the inventory of the user will pop up and from there the user can enter the second stage of the game.

## Level Two -- Autochess Game
Once the user has made contact with NPCs, in order fo the NPC to join the user's team, the user first must beat
the NPC in a game of autochess. Upon loading the game, the user's inventory already has two preset NPCs for
the user to use in level two. Each NPC has a different chess placement and therefore each have a different win strategy.
Each NPC also has a different way of attacking on the board and a certain amount of damage.
The user simply needs to tap on the NPC in the inventory then tap on the left hand side board(6 position to choose from)
to place it in order to move NPCs around. Also, above the inventory we have a reset button, for reset all the
chess piece you have placed back to the inventory. Once everything is placed, the user clicks on "play",
then the results will automatically come out. Then you can choose to go back to maze or retry the game.

## Level Three -- Boss Game
After the user has beat all the NPCs they collected, they enter the final stage of the game. The user must
use the NPC's and the NPC's power to shoot at the boss that moves on the screen. The boss will randomly be able
resist certain NPC's power. If the power that was shot out matches what the boss is resistant against, then
the damage taken is halved. The goal of the game is to kill the boss in the least possible shots possible.
The user can also constantly switch NPCs during the game using the red button.

## Overall Structure
Each level uses MPV architecture which consists of a Model, View, and Presenter, and their relationships are
described in the Contract of each game.

The model of the game is the backend logic of the game and contains all smaller elements/components of the game.

The presenter of the game connects the View with the Model of the game and allows the game to update simultaneoulsy in
the view and the model. Everything data that model needs to transfer to view or view needs to transfer to model
is done through the presenter.

The view of the game is everything that the user will be able to see. Everything is drawn in the view in order
to successfully segregate backend and front end. The coordinates, bitmaps should be in the view. However,
the model of Maze Game and Chess Game needed their own personal type of coordinate system and therefore their
model also contains coordinates, this was done for more readable code and later the ability to easily rescale
the sizes.

Additionally each Model, Presenter, and View of each level and even of savegame, main, and auth all use
the same interface that is stored in base package in order to have consistency in the entire program.

This also follows the Dependency Inversion of the SOLID principles, since we inject interface of View into Presenter,
and interface of Presenter in Model, so that dependency still only flows in one direction, so even though
they all communicate through Presenter, the dependency direction is still correct. This way, we enforce
that dependency flows in the correct direction by having each level and segment implement for the base interfaces.
There are also factory design pattern in ChessGame when creating the Chess, also in Coordinate, we implement a static
create method for dependency injection purpose.

Every other classes that more than one level has to use is stored in the utils package. Such as the class NPC,
since each level uses the same NPC.

## Extensions
After phase 1, we implemented a new Object called Inventory. This stores all the NPCs the user has encountered and
so acts like a collection. In this game, the limit of NPC's the inventory holds is 6 since we only created
6 NPCs, but this is easily extendable even further if we create more NPCs. This allows users to go back and
look at their collection.

We also created a giant scoreboard for users to compare themselves to other users, based on their results. Each
time a user plays a game, they get a new score and additionally the ability to see how well they did
compared to other people or how ahead they are of other people.

## Customization
In the boss game, the user is now able to choose which NPC it wants to use to attack the boss and even
who to bring in. In the inventory, if the user does not choose to battle one of the NPCs, then they
don't have the ability to use them in the Boss game.
We also added the ability to use gravity to control the player through the maze as well. 

## Addition rules for level two Chess Game.
ChessGame:

Player attack first. Every Player chess piece that wins a chess-vs-chess fight scores 1 point.
Then NPC attack. Every NPC chess piece that wins a chess-vs-chess fight deduct 1 point.
at last if there is more than or equal to 0 points, player wins. Otherwise (negative points) Player loses.

Column 1 is Player's back line
Column 2 is Player's front line
Column 3 is NPC's front line
Column 4 is NPC's back line

There are 6 types of chess piece

Circle(type1): Basic unit. Can only attack enemy front line chess piece in the same row

Diamond(type2): Basic unit (but with higher dmg)

Heart(type3): Can attack enemy front line chess piece in any row.
(first seek to fight first row, then second row, at last third row)

Square(type4): Can attack enemy back line chess piece in any row.
(first seek to fight first row, then second row, at last third row)

Star(type5): Can attack enemy front/back line chess piece in the same row
(if no enemy piece on front line then seek to fight the back line chess piece)

Triangle(type6): Act as basic unit. deals double dmg when attacking




