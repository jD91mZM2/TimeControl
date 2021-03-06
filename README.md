# TimeControl

TimeControl is a mod that allows you to do a few things with time.  
It's currently early on in development and it doesn't have a lot of features.

Note: This mod is inspired by [Braid](http://braid-game.com/).

**Latest tested Minecraft version: `1.12.2`**

## Features

 - **Speed control**: Simply press the Up/Down arrows to change the game tick speed.
 - **Undo position**: Holding right shift causes your recent movement to be played backwards.
   It records up to 10 000 ticks of movement.
   Might not be extremely precise because it sets the velocity instead of position (much smoother).
   Shouldn't be an issue though.

## How useful is this?

It's not useful yet, but it's a fun tool. Although, I'm considering implementing the same features I had in my good old [Macro Mod](http://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/2664445-macro-mod-repeat-stuff-automatically) (Warning: I was very young when I made that. Prepare to cringe.)

## Download?

### Get the binary

Most people will want to get the `.jar` file. They can do so [here](https://github.com/jD91mZM2/TimeControl/releases).

### Compile from source

If you want to compile this mod for yourself, here's how:

 - Download and extract the forge MDK.
 - Execute `./gradlew setupDecompWorkspace`.
 - Merge this directory with it, overwriting any files.
 - `./gradlew build` or `./gradlew runClient`

## How it looks

![GIF demonstrating the movement undo ability](screenshots/undo.gif)
![GIF demonstrating the TPS modification ability](screenshots/speed.gif)
