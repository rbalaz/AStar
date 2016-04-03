# AStar
Simple pathfinding app using AStar Algorithm.
Manual
To use the app, jump import the src file into an IDE like NetBeans or Eclipse and you can run it there as a project. When the file starts a small window will appear with buttons and an edit field. Enter the name of the map you want to load and press the 'Load from File' button. The app will load the map and display it in the window. Now, you can observer either the StepSolution by 'Solve' button or just display the optimal route from start to finish with 'QuickSolve' button.

MapEditor
Application expects a map with following format:
Width
Length
Map itself, where . or - is a blank field, # is an obstacle, S is start location and C is finish location. 
Example:
7
5
.....
#.#.C
.....
####.
S....
