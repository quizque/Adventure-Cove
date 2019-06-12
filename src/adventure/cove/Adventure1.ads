-- Tell the parser we are declaring a new map
#DECLARE_MAP

-- Give the map the name TOWN_1
#NAME TOWN_1

-- Tell the parser this is the games starting area, and this is where the player should start
#GAME_START 1 1

-- Instructions to give the user when they start the game
#GAME_INSTRUCTIONS Make it to the end and defeat the boss!

-- This will state that we want a map with a width of 52 and a height of 16
#MAP 52 16
####################          ######################
#                                                  #
#                                                  #
#      /|--------|\              /|¯¯¯¯¯¯¯¯|\      #
#     | |        | |            | |        | |     #
#     | |        | |            | |        | |     #
#     | |--------| |            | |________| |     #
#     |/----------\|            |/__________\|     #
#     |  _         |            |  _         |     #
#     | | |  |¯|   |            | | |  |¯|   |     #
#     |------|-|---|            |------|-|---|     #
#                         ▄                        #
#                         │                        #
#                                                  #
#                                                  #
####################################################

-- These are the map colliders, this will prevent the player from touching these chars
#MAP_COLLISIONS / | - \ _ ▄ # │

-- Define a new TEXT event from 11,16 to 11,16 that will display to the user "The sign reads: Lil' Town"
#MAP_EVENT TEXT 25 11 25 11 The sign reads: Lil' Town.\n Help this is a new line.

-- Define a new TELEPORT event from 10, 14 to 10, 14 that will teleport the user to 1 1 @ ROUTE_1
#MAP_EVENT TELEPORT 10 14 10 14 1 1 ROUTE_1

-- Tell the parser that we are done declaring this map
#END_DECLARE


#DECLARE_ENEMY

-- Max hit points
#HIT_POINTS 25

-- Max attack damage
#ATTACK_DMG 5

-- Change for the attack to hit
#HIT_RATIO 0.9

-- Chance for enemy to get a crit bonus
#CRIT_RATIO 0.05

-- How much extra damage will it do if they get the crit bonus
#CRIT_BONUS 2

-- Set the enemy display
#ENEMY
                   (    )
                  ((((()))
                  |o\ /o)|
                  ( (  _')
                   (._.  /\__
                  ,\___,/ '  ')
    '.,_,,       (  .- .   .    )
     \   \\     ( '        )(    )
      \   \\    \.  _.__ ____( .  |
       \  /\\   .(   .'  /\  '.  )
        \(  \\.-' ( /    \/    \)
         '  ()) _'.-|/\/\/\/\/\|
             '\\ .( |\/\/\/\/\/|
               '((  \    /\    /
               ((((  '.__\/__.')
                ((,) /   ((()   )
                 "..-,  (()("   /
                  _//.   ((() ."
          _____ //,/" ___ ((( ', ___
                           ((  )
                            / /
                          _/,/'
                        /,/,"
#END_ENEMY

-- Tell the parser that we are done declaring this enemy
#END_DECLARE