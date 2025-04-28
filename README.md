How to setup project: 

1) Ensure java is installed on your machine. Check this by visiting https://www.java.com/en/download/help/version_manual.html
2) Either manually download the code from this git or use the command git clone in your terminal

______________________________________________________________________________________________________________________________

How to run terminal project:
1) Enter the part1 directory on terminal
2) Type "java Race" to begin the program or "java Race [integer]" to begin the program, specifying the track length

How to run GUI project:
1) Enter the part2 directory on terminal
2) Type "java HorseRacingGUI"

_______________________________________________________________________________________________________________________________

DIRECTIONS: 

For the terminal-based project, when you start the project, you will be prompted to enter a number (1 or 2) to select whether
you wish to add a horse or begin the race. Adding a horse prompts the user to enter the values for a horse until the values
are valid. The horse is then added to the horses.csv file within the data folder. Starting the race begins the race. This
then prompts the user to enter the number of horses they wish to participate and then allows the user to pick from the horses
stored within the file for the race. The user is allowed to enter a horse multiple times for a race. If no horses are available
a default horse is chosen. Once the horses are submitted, the race begins. After the race, the winner is displayed. The user is 
then given the option for whether they would like to begin a new race or exit.


For the GUI project, the user will be presented with a GUI with multiple options. The user can either add a horse to the race, 
cancel the race, clearing the horses, change the race's track condition, begin race, or create a new horse. Initially, the
user will not be able to select a horse from the drop down menu to add. To add a horse, the user must create a horse by entering
in the values for the horse and pressing the create horse button. When entering the values for the horse, the user must enter the
horse in the following format: [horse character],[horse name],[confidence]. If the user does not enter valid values, the horse will
not be created. When adding horses, duplicate horses are allowed, but only a maximum of 8 horses are allowed to be added for a race.
The horses pending for a race can be cleared with the cancel race button. To begin the race, press the begin race button. There must
be at least two horses pending a race to begin. Before starting the race, the track condition can be specified using a drop down menu,
which affects the ability of the horses. 
On the lower half of the screen is a demonstration of custom tracks which were not yet implemented.
To exit the simulator, press the X button on the top right corner of the screen.
