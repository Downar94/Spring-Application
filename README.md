# Spring-Application
A Tic-Tac-Toe Game Rest Api containing several Endpoints, 2 players and 10x10 game's board. 
## Table of contents
* [Endpoints](#endpoints):
  * GET

<a name="endpoints"></a>
# Endpoints
## 1. GET
### GET /game/all
View informations on all started games.

#### Initial response:

#### Response in the middle of the game:
![310958997_422241606763847_2528255453570116688_n](https://user-images.githubusercontent.com/44844566/194732176-67d40dd7-3800-4e22-bcd6-a2661d1036fc.png)


#### Response at the end of the game:
![gameallend](https://user-images.githubusercontent.com/44844566/194732215-33fcf275-fbe1-4f64-8bdf-8b91b2b8f76b.png)


### GET /game/players
View all registered players

#### Initial response:

#### Response after adding some players:

![playersmiddle](https://user-images.githubusercontent.com/44844566/194732253-92bda04c-df26-45b9-acef-22175f90d407.png)

#### Response at the end of the game:

![playersend](https://user-images.githubusercontent.com/44844566/194732296-b40c0099-9d95-4e3b-8c13-1f33b3227958.png)

### GET /game/gameStates
View all game states

#### Initial response:

#### Response after beginning the game:

![gamestatesmiddle](https://user-images.githubusercontent.com/44844566/194732382-1449f526-d8bf-42cf-aa2f-69d4dd0ba295.png)

#### Response at the end of the game:

![gamestatesend](https://user-images.githubusercontent.com/44844566/194732386-20efacf1-9a1b-467a-b527-c637881d1021.png)

### GET /game/gameState/{index}
View game state with a given index

#### Initial response:

#### Response after beginning the game:

![gamestatemiddle](https://user-images.githubusercontent.com/44844566/194732478-40e9aeae-25ee-4bdc-96bd-ec4d33a1be39.png)

#### Response at the end of the game:

![gameStateend](https://user-images.githubusercontent.com/44844566/194732482-b1e5fefd-5d82-4f07-9fbd-1eddb2991ea6.png)

### GET /game/{index}
View nformation of the game with a given index

#### Initial response:

#### Response after beginning the game:


#### Response at the end of the game:

