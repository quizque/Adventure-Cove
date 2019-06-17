---
--- TOWN_1 DECLARE
---

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
#                         ▄                        #
#                         │                        #
#                                                  #
#                                                  #
####################################################

-- These are the map colliders, this will prevent the player from touching these chars
#MAP_COLLISIONS / | - \ _ ▄ # │

-- Define a new TEXT event from 11,16 to 11,16 that will display to the user "The sign reads: Lil' Town"
#MAP_EVENT TEXT 26 11 26 12 The sign reads:\n Welcome to Lil' Town!

-- Define a new TELEPORT event from 10, 14 to 10, 14 that will teleport the user to 1 1 @ ROUTE_1
#MAP_EVENT TELEPORT 14 10 14 10 18 6 TOWN_HOUSE_LEFT

#MAP_EVENT TELEPORT 40 10 40 10 18 7 TOWN_HOUSE_RIGHT

#MAP_EVENT TELEPORT 20 0 29 0 20 16 ROUTE_1

-- Tell the parser that we are done declaring this map
#END_DECLARE




---
--- TOWN_INSIDE_LEFT
---

#DECLARE_MAP
#NAME TOWN_HOUSE_LEFT

#MAP 25 8
#########################
#     |-----|           #
#-                 \__/ #
#|    ______       |  | #
#|     |  |        |__| #
#-                 /  \ #
#                       #
#################   #####

#MAP_EVENT TELEPORT 17 7 19 7 14 11 TOWN_1

#MAP_COLLISIONS # | - \ / _
#END_DECLARE




---
--- TOWN_INSIDE_RIGHT
---

#DECLARE_MAP
#NAME TOWN_HOUSE_RIGHT

#MAP 25 9
#########################
#     |-----|           #
#-                      #
#|     \__/     ______  #
#|     |  |      |  |   #
#|     |__|             #
#-     /  \             #
#                       #
#################   #####

#MAP_EVENT TELEPORT 17 8 19 8 40 11 TOWN_1

#MAP_COLLISIONS # | - \ / _
#END_DECLARE


---
--- ROUTE_1
---

#DECLARE_MAP
#NAME ROUTE_1

#MAP 41 18
######     ##############################
#              wwwwwwwwwwwwwwwwwwwwwwwww#
#                                       #
#wwwwwwwwwwwwwwwww                      #
############################        #####
#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#
#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#
#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#
#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#
###      ################################
#                                       #
#           www           wwwww         #
#                        wwwwwwww       #
#   wwwww                   wwww        #
#   wwwwww                              #
# wwwwwwwww                             #
#                                       #
##################     ##################

#MAP_EVENT TELEPORT 18 17 22 17 25 1 TOWN_1

#MAP_COLLISIONS #
#END_DECLARE



---
--- ENEMY_1
---

#DECLARE_ENEMY
#NAME Dragon
#HITPOINTS 15
#DAMAGE_MAX 5
#RANDOM_SPAWN

-- WHITESPACE NEEDS FIXING!!!!
#DISPLAY 54 16
                 ___====-_  _-====___                  
           _--^^^#####//      \\#####^^^--_            
        _-^##########// (    ) \\##########^-_         
       -############//  |\^^/|  \\############-        
     _/############//   (@::@)   \\############\_     
    /#############((     \\//     ))#############\    
   -###############\\    (oo)    //###############-   
  -#################\\  / VV \  //#################-  
 -###################\\/      \//###################- 
_#/|##########/\######(   /\   )######/\##########|\#_
|/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
`  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
   `   `  `      `   / | |  | | \   '      '  '   '   
                    (  | |  | |  )                    
                   __\ | |  | | /__                   
                  (vvv(VVV)(VVV)vvv)                  


#END_DECLARE



---
--- ENEMY_2
---

#DECLARE_ENEMY
#NAME Minotaur
#HITPOINTS 20
#DAMAGE_MAX 6
#RANDOM_SPAWN

-- WHITESPACE NEEDS FIXING!!!!
#DISPLAY 36 23
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


#END_DECLARE