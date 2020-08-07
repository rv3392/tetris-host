# Tetris with AI - Host
This project aims to create a simple framework to train and test AI agents. 

Agents take turns playing games on the GUI. They are able to connect to the GUI application through a socket hosted on port 35354 which is used both send commands and receive the current game state (covered in the Protocol section).

## How to Use

At this stage there aren't any release builds as this is still a WIP. Eventually there will be a release for at least Windows and Ubuntu that should be shipped with the JRE so that you won't need a Java install.

Note: OpenJDK 8 is required due to issues with LibGDX. AdoptOpenJDK 11 has also been tested and works.
1. Clone this repository
2. Run using "./gradlew run" or import into IntelliJ
3. Connect to the host on port 35354 using an agent

## Protocol

Movement commands can be sent to the host right after a game is started. 

Furthermore, after each update of the board by the host the connected client also receives a string representation of the board. This representation is sent to all connected agents/clients and is essentially a 24x10 grid of the colours of the grids and the current score and level.

### Commands
#### State
- queue \<agent name\>
- start
- end

#### Movement
- left
- right
- down
- rotate

#### Examples
To start a game and move the first piece one grid left, rotate it then move all the way down. Then end the game.
1. queue Example1
2. start
3. left
4. rotate
5. down
6. end

**This readme is a work in progress and is very much not done yet.**

