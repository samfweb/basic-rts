# A Basic Sci-Fi themed RTS
A basic RTS created using the Slick2D library, with a strong focus on OOP. 

This was made early 2019.

# Source Code Dependencies
Add `slick` and the `libj*` libraries in `lib/` to your path.

The `libjinput` libraries included in `lib/` are for a linux 64bit machine. If using a different machine, you may need to download the relevant `libjinput` libraries at https://github.com/jinput/jinput/wiki/Getting-started-with-JInput.

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

Move an Engineer near a resource and it will begin collecting.

Engineers will automatically move to deposit at the nearest command centre once their backpacks are full. After depositing, they'll return to their original spot to continue collecting.

#### Truck
Trucks can be deployed into a command centre, destructing in the process. Deploy wisely, you can't pack a command centre back up.

### Buildings
#### Command Centre
This is your hub, your home, your only safe space on an inhospitable alien wasteland.

Engineers deposit resources here, and new units can be trained.

#### The Factory
Factories can train the only unit command centres can't: the truck.

#### Pylon 
Why is this here? Who created it? A shroud of mystery surrounds these inactive monoliths. 

Activating a pylon lets your engineers collect resources faster (somehow).

### Resouces
Engineers can mine resources.
#### Metal
You can mine metal from metal mines. You can use it for pretty much anything. It's tremendous metal. 

#### Unobtanium
Slightly easier to obtain than the name suggests. Doesn't serve a direct purpose, other than something to strive for.

## Troubleshooting
If something is broken, please let me know! I haven't touched this in a while. 

The project was built using Java 1.8, and I've found there's less issues when using this version. Using Java 13, the application won't start. 

