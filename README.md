# A Basic Sci-Fi themed RTS
A basic RTS created using the Slick2D library, with a strong focus on OOP.

## Playing the Game
Collect Unobtanium; feel great.

That's the aim of the game. (There's no defined win condition)

## Controls
You can move the map view with `w`, `a`, `s` and `d`.

Select a sprite or building by left clicking on it.

Right clicking will cause the sprite to move.

Upon selection, some sprites will show abilites that can be activated with `1`, `2`, `3` and `4`.

## Units
### Sprites
#### Scout
Scouts move fast. They don't have any other abilites. You begin the game with one scout. 

#### Builder
These guys can build things. They'll be invaluable in your journey.

#### Engineer
Engineers can mine resources.

#### Truck
Trucks can be deployed into a command centre, destructing in the process. Deploy wisely, and you can't pack a command centre back up.

### Buildings
#### Command Centre
This is your hub, your home, your only safe space on an inhospitable alien wasteland.

Engineers deposit resources here, and you can train most units here.

#### The Factory
Factories can train the only unit command centres can't: the truck.

#### Pylon 
Why is this here? Who created it? A shroud of mystery surrounds these inactive monoliths. 

Activating a pylon lets your engineers collect resources faster, don't ask me why.

## Troubleshooting
If you're having trouble starting the game, make sure that the proper libraries have been added to your classpath. The `libjinput` libraries included in `lib/` are for a linux 64bit machine. If using a different machine, you may need to download the relevant `libjinput` libraries at https://github.com/jinput/jinput/wiki/Getting-started-with-JInput.

If something is broken, please let me know! I haven't touched this in a while. 

The project was built using Java 1.8, and I've found there's less issues when using this version. Using Java 13, the application won't start. 

