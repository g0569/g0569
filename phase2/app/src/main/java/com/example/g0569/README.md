# GROUP 0569 GAME PROJECT
A mini rpg style game where a player has to collect team members to join their team in order to beat the
final boss. Has three stages, the player must first navigate through a maze to reach NPCs. After, they must
battle them in a game of autochess in order for them to join their team. Then must fight the boss in order
to successfully win the game.

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
The user simply needs to tap on the NPC in the inventory then tap where they want to place it in order to
move NPCs around. Once everything is placed, the user clicks on "play", then the results will automatically come out.

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

Every other classes that more than one level has to use is stored in the utils package. Such as the class NPC,
since each level uses the same NPC.

